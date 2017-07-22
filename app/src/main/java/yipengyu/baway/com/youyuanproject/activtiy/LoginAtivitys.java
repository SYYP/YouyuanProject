package yipengyu.baway.com.youyuanproject.activtiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import yipengyu.baway.com.youyuanproject.Presenter.LoginPresenter;
import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.View.LoginView;
import yipengyu.baway.com.youyuanproject.base.BaseMvpActivity;
import yipengyu.baway.com.youyuanproject.base.IActivity;
import yipengyu.baway.com.youyuanproject.base.IApplication;
import yipengyu.baway.com.youyuanproject.bean.LoginBean;
import yipengyu.baway.com.youyuanproject.utils.GsonUtil;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;
import yipengyu.baway.com.youyuanproject.widget.MyToast;
import yipengyu.baway.com.youyuanproject.widget.keyboard.KeyBoardHelper;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class LoginAtivitys extends BaseMvpActivity<LoginView,LoginPresenter> implements LoginView,KeyBoardHelper.OnKeyBoardStatusChangeListener{

    private Button btn_register;
    private EditText login_name;
    private EditText login_pwd;
     String lat;
    String lng;
    private int userId;
    private String yxpassword;

    @Override
    public LoginPresenter ininpresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
          ininview();
        KeyBoardHelper keyBoardHelper = new KeyBoardHelper(this) ;
        keyBoardHelper.onCreate();
        keyBoardHelper.setOnKeyBoardStatusChangeListener(this);
    }

    private void ininview() {
        btn_register = (Button) findViewById(R.id.Register);
        login_name = (EditText) findViewById(R.id.login_name);
        login_pwd = (EditText) findViewById(R.id.login_pwd);

        lat = PreferencesUtils.getValueByKey(this, "lat",+11.34+"" );
        lng = PreferencesUtils.getValueByKey(this, "lng",10.22+"" );
        Button btn_login= (Button) findViewById(R.id.btn_logins);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getuser(login_name.getText().toString().trim(),login_pwd.getText().toString().trim(),lat,lng);
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(LoginAtivitys.this,RegisterActivitys.class);
                startActivity(it);
                  finish();
            }
        });
    }

    @Override
    public void onSuccess(String reuslt) {

        LoginBean loginBean = GsonUtil.getInstance().fromJson(reuslt, LoginBean.class);

        if( loginBean.getResult_code()==200){
            userId = loginBean.getData().getUserId();
            PreferencesUtils.addConfigInfo(this,"userids",userId);
            yxpassword = loginBean.getData().getYxpassword();
            System.out.print("aaaaaaaaaaaaaaaaaaa"+loginBean.getResult_code());
            //跳转
            Intent intent=new Intent(LoginAtivitys.this,TabActivity.class);
            startActivity(intent);
            MyToast.makeText(IApplication.getApplication(),"登录成功", Toast.LENGTH_SHORT);
            EMClient.getInstance().login(String.valueOf(userId),yxpassword,new EMCallBack() {//回调
                @Override
                public void onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    Log.d("main", "登录聊天服务器成功！");
                }

                @Override
                public void onProgress(int progress, String status) {

                }

                @Override
                public void onError(int code, String message) {
                    Log.d("main", "登录聊天服务器失败！");
                }
            });
        }
        else {
            MyToast.makeText(IApplication.getApplication(),"登录失败", Toast.LENGTH_SHORT);
        }

    }

    @Override
    public void onFailed(int code) {
        MyToast.makeText(IApplication.getApplication(),"失败", Toast.LENGTH_SHORT);
    }

    @Override
    public void OnKeyBoardPop(int keyBoardheight) {

        PreferencesUtils.addConfigInfo(this,"gaodu",keyBoardheight);
        System.out.println("keyBoardheight OnKeyBoardPop = " + keyBoardheight);
    }

    @Override
    public void OnKeyBoardClose(int oldKeyBoardheight) {

    }
}
