package yipengyu.baway.com.youyuanproject.activtiy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import yipengyu.baway.com.youyuanproject.Presenter.Registerpresenter;
import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.View.Registerview;
import yipengyu.baway.com.youyuanproject.base.BaseMvpActivity;
import yipengyu.baway.com.youyuanproject.base.IApplication;
import yipengyu.baway.com.youyuanproject.widget.MyToast;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class RegisterActivitys extends BaseMvpActivity<Registerview, Registerpresenter>implements Registerview,View.OnClickListener {

    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.check_code_input)
    DivEtidActivity checkCodeInput;
    @BindView(R.id.get_phone_check_code_again_register)
    Button getPhoneCheckCodeAgainRegister;
    @BindView(R.id.password)
    DivEtidActivity password;
    private Button button;
    EventHandler eventHandler ;
    private Unbinder unbinder;

    @Override
    public Registerpresenter ininpresenter() {
        return new Registerpresenter();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        unbinder = ButterKnife.bind(this);
        button = (Button) findViewById(R.id.btn_ok);
        getPhoneCheckCodeAgainRegister.setOnClickListener(this);
        SMSSDK.initSDK(this,"1f2f8992c8990","ed0e9811b2630996b107c723ca9330b7");
        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message m = Message.obtain();
                m.what = 1;
                m.arg1 = event;//event
                m.arg2 =  result;//result
                handler.sendMessage(m);
                System.out.println("result = " + result);
                System.out.println("data = " + data);

            }
        };

        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
        inindata();

    }


    private void inindata() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(registerPhone.getText().toString())||TextUtils.isEmpty(password.getText().toString())) {
                    //toast（自定义）
                    MyToast.makeText(IApplication.getApplication(), getString(R.string.error), Toast.LENGTH_SHORT);
                }
                smsCommitCommitVM(registerPhone.getText().toString(),checkCodeInput.getText().toString());
            }


        });
    }
    /**
     * 添加一个提交按钮，进行验证码验证
     */
    public void smsCommitCommitVM(String str, String str1){
        SMSSDK.submitVerificationCode("86", str, str1);//提交短信验证码，在监听中返回，str :手机号 str1:收到的验证码
    }

    /**
     * 接收afterEvent 方法里面的Message
     */
    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //跳转
                    Intent it=new Intent(RegisterActivitys.this, RegisterActivity2.class);
                    it.putExtra("pwd",password.getText().toString());
                    it.putExtra("phone",registerPhone.getText().toString());
                    startActivity(it);
//
//                    if(msg.arg2 == SMSSDK.RESULT_COMPLETE){//发送成功的情况
//                        if(msg.arg1 == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){//验证成功通过
//                            //跳转
//                            Intent it=new Intent(RegisterActivitys.this, RegisterActivity2.class);
//                             it.putExtra("pwd",password.getText().toString());
//                            it.putExtra("phone",registerPhone.getText().toString());
//                             startActivity(it);
//
//
//                        }
//                    else if(msg.arg1 == SMSSDK.EVENT_GET_VERIFICATION_CODE){//验证码已经从服务器发出
//                            MyToast.makeText(IApplication.getApplication(),getString(R.string.send), Toast.LENGTH_SHORT);
//                        }
//                    }else{
//                        MyToast.makeText(IApplication.getApplication(),getString(R.string.yazheng), Toast.LENGTH_SHORT);
//                    }
                    break;
            }
        };
    };

    public void unRegisterEventHandler(){  //最后注销监听，否则可能会造成内存泄露
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        finish();
    }


    @Override
    public void phontEmpy(int type) {

         switch (type){
             case 1:
                   //toast（自定义）
                 MyToast.makeText(IApplication.getApplication(),getString(R.string.error), Toast.LENGTH_SHORT);
                 break;
             case 2:
                 MyToast.makeText(IApplication.getApplication(),getString(R.string.geshi),Toast.LENGTH_SHORT);
                 break;
         }
    }

    @Override
    public void showTimer() {
        //registerPhone
        getPhoneCheckCodeAgainRegister.setClickable(false);

        Observable.interval(0,1, TimeUnit.SECONDS)
                .take(30)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return 29 - aLong;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {


//                        d.dispose();

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {

                        System.out.println("aLong = " + aLong);
                        getPhoneCheckCodeAgainRegister.setText(aLong+" 秒 ");

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getPhoneCheckCodeAgainRegister.setClickable(true);
                        getPhoneCheckCodeAgainRegister.setText(getText(R.string.getCheckCode));

                    }
                });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_phone_check_code_again_register:
                 presenter.getTelephone(registerPhone.getText().toString());
                checkCodeInput.requestFocus();//获取EditText焦点
                break;
        }
    }
}
