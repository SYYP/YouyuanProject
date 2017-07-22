package yipengyu.baway.com.youyuanproject.activtiy;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.adapter.ChatAdapter;
import yipengyu.baway.com.youyuanproject.base.IActivity;
import yipengyu.baway.com.youyuanproject.biaoqing.util.EaseDefaultEmojiconDatas;
import yipengyu.baway.com.youyuanproject.biaoqing.util.EaseSmileUtils;
import yipengyu.baway.com.youyuanproject.biaoqing.util.dongmain.EaseEmojicon;
import yipengyu.baway.com.youyuanproject.biaoqing.util.dongmain.EaseEmojiconGroupEntity;
import yipengyu.baway.com.youyuanproject.biaoqing.util.emojicon.EaseEmojiconMenu;
import yipengyu.baway.com.youyuanproject.biaoqing.util.emojicon.EaseEmojiconMenuBase;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;
import yipengyu.baway.com.youyuanproject.widget.EditTextPreIme;
import yipengyu.baway.com.youyuanproject.widget.keyboard.KeyBoardHelper;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class ChatActivity extends IActivity implements KeyBoardHelper.OnKeyBoardStatusChangeListener, View.OnClickListener, EditTextPreIme.EditTextListener {
    @BindView(R.id.chat_recycler)
    RecyclerView chatRecycler;
    @BindView(R.id.chat_spring)
    SpringView chatSpring;
    @BindView(R.id.btn_yuyin)
    Button btnYuyin;
    @BindView(R.id.btn_yuin2)
    Button btnYuin2;

    @BindView(R.id.btn_biaoqing)
    Button btnBiaoqing;
    @BindView(R.id.btn_more)
    Button btnMore;
    @BindView(R.id.btn_send)
    Button btnSend;

    private LinearLayout liner;
    private Handler handler;
    private int keyHeight;
    private EMMessageListener msgListener;
    private List<EMMessage> list = new ArrayList<>();
    private ChatAdapter chadapter;
    private EditTextPreIme etSendmessage;
   private EaseEmojiconMenu emojiconMenu;
    private Button btn_speak;
    boolean bool=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        liner = (LinearLayout) findViewById(R.id.liner);
        etSendmessage = (EditTextPreIme) findViewById(R.id.et_sendmessage);
        btn_speak = (Button) findViewById(R.id.btn_speak);
        ButterKnife.bind(this);
        btnBiaoqing.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        btnYuin2.setOnClickListener(this);
        btnYuyin.setOnClickListener(this);
        chadapter = new ChatAdapter(list, this);
        chatRecycler.setAdapter(chadapter);

        chatRecycler.setLayoutManager(new LinearLayoutManager(this));
        initEmoje(null);
        initLisitener();

        //取出键盘的高度
        keyHeight = PreferencesUtils.getValueByKey(this, "gaodu", 300);
        EaseEmojiconMenu.LayoutParams params = (EaseEmojiconMenu.LayoutParams) liner.getLayoutParams();
        //LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) liner.getLayoutParams();
        params.height = keyHeight;
        liner.setLayoutParams(params);
        //实现键盘高度工具类
        KeyBoardHelper keyBoardHelper = new KeyBoardHelper(this);
        keyBoardHelper.onCreate();
        keyBoardHelper.setOnKeyBoardStatusChangeListener(this);
        btnBiaoqing.setTag(1);
        receive();
        setKeyBoardModelResize();

        etSendmessage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (liner.getVisibility() == View.VISIBLE) {
                    setKeyBoardModelPan();
                } else {
                    setKeyBoardModelResize();
                }
                return false;
            }
        });

        etSendmessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                btnMore.setVisibility(View.GONE);
                btnSend.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    btnMore.setVisibility(View.VISIBLE);
                    btnSend.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void OnKeyBoardPop(int keyBoardheight) {
//        handler.sendEmptyMessageAtTime(1,200);
//        Message message=new Message();
//        message.obj=keyBoardheight;
//        handler.sendMessage(message);
    }

    @Override
    public void OnKeyBoardClose(int oldKeyBoardheight) {

    }

    //设置键盘与控件的模式
    public void setKeyBoardModelPan() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    public void setKeyBoardModelResize() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
    //隐藏与显示键盘

    public void hidenKeyBoard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void showKeyBoard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_biaoqing:
                setKeyBoardModelPan();
                int tag = (Integer) btnBiaoqing.getTag();
                if (tag == 1) {
                    // 显示表情
                    hidenKeyBoard(etSendmessage);
                    btnBiaoqing.setBackgroundResource(R.drawable.ease_chatting_setmode_keyboard_btn_pressed);
                    liner.setVisibility(View.VISIBLE);

                    btnBiaoqing.setTag(2);

                } else {
                    btnBiaoqing.setTag(1);
                    // liner.setVisibility(View.GONE);
                    btnBiaoqing.setBackgroundResource(R.drawable.ease_chatting_biaoqing_btn_enable);
                    //  显示键盘
                    showKeyBoard(etSendmessage);

                }

                break;

            case R.id.btn_send:
                setTextMessage();
                etSendmessage.setText("");

                break;
            case R.id.btn_yuyin:
                etSendmessage.setVisibility(View.GONE);
                btnYuyin.setVisibility(View.GONE);
                btnYuin2.setVisibility(View.VISIBLE);
                btn_speak.setVisibility(View.VISIBLE);
                 if(bool){
                     etSendmessage.setVisibility(View.GONE);
                     btnYuyin.setVisibility(View.GONE);
                     btnYuin2.setVisibility(View.VISIBLE);
                     btn_speak.setVisibility(View.VISIBLE);
                      hidenKeyBoard(etSendmessage);
                 }


                break;
            case  R.id.btn_yuin2:
              //  setKeyBoardModelPan();
                setKeyBoardModelResize();
                btnYuin2.setVisibility(View.GONE);
                etSendmessage.setVisibility(View.VISIBLE);
                    btn_speak.setVisibility(View.GONE);
                btnYuyin.setVisibility(View.VISIBLE);
                    showKeyBoard(etSendmessage);

                    bool=true;


                break;


        }
    }

    //发送消息
    public void setTextMessage() {
        String who = PreferencesUtils.getValueByKey(this, "who", "1");
        EMMessage emMessage = EMMessage.createTxtSendMessage(etSendmessage.getText().toString(), who);
        EMClient.getInstance().chatManager().sendMessage(emMessage);
        emMessage.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {
                System.out.println("emMessage = onSuccess ");
            }

            @Override
            public void onError(int i, String s) {
                System.out.println("emMessage = error");
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
        list.add(emMessage);
        chadapter.notifyDataSetChanged();
        chatRecycler.scrollToPosition(chadapter.getItemCount()-1);



    }

    public void receive() {
        //收到消息
//收到透传消息
//收到已读回执
//收到已送达回执
//消息状态变动
        msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                if (messages.size() > 0) {
                    list.addAll(messages);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            chadapter.notifyDataSetChanged();
                            chatRecycler.scrollToPosition(chadapter.getItemCount()-1);
                        }
                    });


                    System.out.println("messages.get(0).getBody() = " + messages.get(0).getBody());
                }

                System.out.println("onMessageReceived messages = " + messages);
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
                System.out.println("onCmdMessageReceived messages = " + messages);

            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
                System.out.println("onMessageRead messages = " + messages);

            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
                System.out.println("onMessageDelivered messages = " + message);

            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
                System.out.println("onMessageChanged messages = " + message);

            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            System.out.println("chatTitle = onBack KEYCODE_BACK");
            if (liner.getVisibility() == View.VISIBLE) {
                liner.setVisibility(View.GONE);
                btnBiaoqing.setTag(1);

                return false;
            } else {
                return super.onKeyDown(keyCode, event);
            }

        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void onBack() {
        etSendmessage.setListener(null);

        System.out.println("chatTitle = onBack");


        setKeyBoardModelResize();
        liner.setVisibility(View.GONE);
        btnBiaoqing.setTag(1);
    }
    private void initEmoje(List<EaseEmojiconGroupEntity> emojiconGroupList) {
        if (emojiconMenu == null) {


            emojiconMenu = (EaseEmojiconMenu) View.inflate(ChatActivity.this, R.layout.ease_layout_emojicon_menu, null);

            //动态修改底部view 的高度 (表情 符号 view 的高度)


            //  buttomLayoutView.setVisibility(View.GONE);

            if (emojiconGroupList == null) {
                emojiconGroupList = new ArrayList<>();
                emojiconGroupList.add(new EaseEmojiconGroupEntity(R.drawable.ee_1, Arrays.asList(EaseDefaultEmojiconDatas.getData())));
            }
            emojiconMenu.init(emojiconGroupList);
        }
        liner.addView(emojiconMenu);
    }

    private void initLisitener() {
        // emojicon menu
        emojiconMenu.setEmojiconMenuListener(new EaseEmojiconMenuBase.EaseEmojiconMenuListener() {

            @Override
            public void onExpressionClicked(EaseEmojicon emojicon) {
                if (emojicon.getType() != EaseEmojicon.Type.BIG_EXPRESSION) {
                    if (emojicon.getEmojiText() != null) {
                        etSendmessage.append(EaseSmileUtils.getSmiledText(ChatActivity.this, emojicon.getEmojiText()));
                    }
                }
            }

            @Override
            public void onDeleteImageClicked() {
                if (!TextUtils.isEmpty(etSendmessage.getText())) {
                    KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
                    etSendmessage.dispatchKeyEvent(event);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }
}
