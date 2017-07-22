package yipengyu.baway.com.youyuanproject.activtiy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.horizontalselectedviewlibrary.HorizontalselectedView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.jakewharton.rxbinding2.view.RxView;
import com.lljjcoder.citypickerview.widget.CityPickerView;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import yipengyu.baway.com.youyuanproject.Presenter.RegisterinfornamePresenter;
import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.View.RegisterfornameView;
import yipengyu.baway.com.youyuanproject.base.BaseMvpActivity;
import yipengyu.baway.com.youyuanproject.base.IActivity;
import yipengyu.baway.com.youyuanproject.base.IApplication;
import yipengyu.baway.com.youyuanproject.bean.RegisterBean;
import yipengyu.baway.com.youyuanproject.cipher.Md5Utils;
import yipengyu.baway.com.youyuanproject.utils.AppManager;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;
import yipengyu.baway.com.youyuanproject.widget.MyToast;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class RegisterActivity2 extends BaseMvpActivity<RegisterfornameView,RegisterinfornamePresenter> implements RegisterfornameView  {
    private HorizontalselectedView hsMain;
    List<String> strings = new ArrayList<String>();
    private TextView sex,select_sex;
    private String[] sexArry = new String[] { "女", "男" };
    private TextView select_address;
    private Button register_next;
    private EditText ed_name;
    private String pwd;
    private String phone;
    private String selectedString;
    private EditText jieshao;
    private String  lat;
    private String  lng;

    @Override
    public RegisterinfornamePresenter ininpresenter() {
        return new RegisterinfornamePresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2);
        Intent intent = getIntent();
        pwd = intent.getStringExtra("pwd");
        phone = intent.getStringExtra("phone");
       lat = PreferencesUtils.getValueByKey(this, "lat",11.00+"" );
       lng = PreferencesUtils.getValueByKey(this, "lng",13.00+"" );
        KLog.a(pwd);

        String userids = PreferencesUtils.getValueByKey(this, "userids", "");
        try {
            EMClient.getInstance().createAccount(userids, pwd);//同步方法
        } catch (HyphenateException e) {
            e.printStackTrace();
        }

        ininview();
        inindata();
        shangchuan();
    }

    private void inindata() {
        for (int i = 0; i < 40; i++) {
            strings.add(i + "岁");
        }
        hsMain.setData(strings);
        select_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断输入法的隐藏状态
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    // selectAddress();//调用CityPicker选取区域


                    CityPickerView cityPickerView = new CityPickerView(RegisterActivity2.this);
                    cityPickerView.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
                        @Override
                        public void onSelected(String... citySelected) {
                            //省份
                            String province = citySelected[0];
                            //城市
                            String city = citySelected[1];
                            //区县
                            String district = citySelected[2];
                            //邮编
                            String code = citySelected[3];
                            //  Toast.makeText(RegisterActivity2.this,province+"-"+city+"-"+district,Toast.LENGTH_LONG).show();
                            select_address.setText(province + " " + city + " " + district);
                        }
                    });
                    cityPickerView.show();
                }
            }
        });


    }

    private void ininview() {
        hsMain = (HorizontalselectedView) findViewById(R.id.hd_main);
        sex= (TextView) findViewById(R.id.sex);
        select_sex= (TextView) findViewById(R.id.select_sex);
        select_address = (TextView) findViewById(R.id.select_address);
        ed_name = (EditText) findViewById(R.id.register_name);
        register_next = (Button) findViewById(R.id.Register_next);
        jieshao = (EditText) findViewById(R.id.register_abstracts);

        select_sex.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 showSexChooseDialog();
             }
         });
        register_ok();
    }

    /* 性别选择框 */
    private void showSexChooseDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);// 自定义对话框
        builder.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                select_sex.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();// 让弹出框显示
    }
    private void register_ok() {

        if(TextUtils.isEmpty(ed_name.getText().toString())||TextUtils.isEmpty(select_sex.getText().toString())||TextUtils.isEmpty(select_address.getText().toString().trim())){
            MyToast.makeText(IApplication.getApplication(),getString(R.string.edname),Toast.LENGTH_SHORT);

        }
        else{
          //  Intent it=new Intent(RegisterActivity2.this,PhotoActivity.class);
        }

    }

    @Override
    public void registerSuccess(RegisterBean registerBean) {
        //跳到上传形象照页面

        if(registerBean.getResult_code() == 200){

           // activity.toIActivity(UploadPhotoActivity.class,null,0);
//            AppManager.getAppManager().finishActivity(this);
//            AppManager.getAppManager().finishActivity(LoginAtivitys.class);
//            AppManager.getAppManager().finishActivity(RegisterActivitys.class);
            Intent it=new Intent(RegisterActivity2.this,PhotoActivity.class);
            startActivity(it);

        }else {
            MyToast.makeText(IApplication.getApplication(),registerBean.getResult_message(), Toast.LENGTH_SHORT);
        }

    }

    @Override
    public void registerFailed(int code) {
        // 给一个用户友好的提示
        MyToast.makeText(IApplication.getApplication(),code+"", Toast.LENGTH_SHORT);
    }
    private void shangchuan() {

        RxView.clicks(register_next).throttleFirst(5, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        selectedString = hsMain.getSelectedString();
                        String substring = selectedString.substring(0, selectedString.toString().length() - 1);
                        presenter.vaildInfor(phone, ed_name.getText().toString().trim(), select_sex.getText().toString().trim()
                                , substring, select_address.getText().toString().trim()
                                , jieshao.getText().toString().trim(), Md5Utils.getMD5(pwd),lat,lng);

                    }
                });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
