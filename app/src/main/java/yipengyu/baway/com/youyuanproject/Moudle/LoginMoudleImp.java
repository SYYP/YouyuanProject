package yipengyu.baway.com.youyuanproject.Moudle;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import yipengyu.baway.com.youyuanproject.base.IApplication;
import yipengyu.baway.com.youyuanproject.bean.LoginBean;
import yipengyu.baway.com.youyuanproject.cipher.Md5Utils;
import yipengyu.baway.com.youyuanproject.cipher.aes.JNCryptorUtils;
import yipengyu.baway.com.youyuanproject.cipher.rsa.RsaUtils;
import yipengyu.baway.com.youyuanproject.network.BaseObserver;
import yipengyu.baway.com.youyuanproject.network.RetrofitManager;
import yipengyu.baway.com.youyuanproject.utils.Constants;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class LoginMoudleImp implements ILoginMoudle {
    @Override
    public void getuserinfor(String name, String pwd,String lat, String lng, final LoginUserListener listener) {

        String randomKey =   RsaUtils.getStringRandom(16);
        String rsaRandomKey = RsaUtils.getInstance().createRsaSecret(IApplication.getApplication(),randomKey);
        String cipherPhone = JNCryptorUtils.getInstance().encryptData(name,IApplication.getApplication(),randomKey);
        Map map = new HashMap<String,String>();
         if(lat.equals(0)||lng.equals(0)){
             map.put("user.phone",cipherPhone);
             map.put("user.password", Md5Utils.getMD5(pwd));
             map.put("user.secretkey",rsaRandomKey);
         }else{
             map.put("user.phone",cipherPhone);
             map.put("user.password", Md5Utils.getMD5(pwd));
             map.put("user.secretkey",rsaRandomKey);
             map.put("user.lat",lat);
             map.put("user.lng",lng);

         }

        RetrofitManager.post(Constants.LOGIN_ACTION, map, new BaseObserver() {
            @Override
            public void onSuccess(String result) {
                listener.onSuccess(result);
                System.out.println("result = " + result);
                Gson gson=new Gson();
                LoginBean loginBean = gson.fromJson(result, LoginBean.class);
         PreferencesUtils.addConfigInfo(IApplication.getApplication(), "luserId", loginBean.getData().getUserId());
            }

            @Override
            public void onFailed(int code) {
                listener.onFailed(code);
            }
        });

    }
}
