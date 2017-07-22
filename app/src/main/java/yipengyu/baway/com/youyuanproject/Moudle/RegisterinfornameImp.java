package yipengyu.baway.com.youyuanproject.Moudle;

import com.getkeepsafe.relinker.ReLinker;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import yipengyu.baway.com.youyuanproject.base.IApplication;
import yipengyu.baway.com.youyuanproject.bean.RegisterBean;
import yipengyu.baway.com.youyuanproject.core.JNICore;
import yipengyu.baway.com.youyuanproject.core.SortUtils;
import yipengyu.baway.com.youyuanproject.network.BaseObserver;
import yipengyu.baway.com.youyuanproject.network.RetrofitManager;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class RegisterinfornameImp implements IRegisterinforname {

    @Override
    public void getData(String phone, String nickname, String sex, String age, String adres, String introduce, String password, String lat, String lng, final RegisterInforFragmentDataListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        if(lat.equals("")&&lng.equals("")){
            map.put("user.phone", phone);
            map.put("user.nickname", nickname);
            map.put("user.password", password);
            map.put("user.gender", sex);
            map.put("user.area", adres);
            map.put("user.age", age);
            map.put("user.introduce", introduce);
        }
        else {
            map.put("user.phone", phone);
            map.put("user.nickname", nickname);
            map.put("user.password", password);
            map.put("user.gender", sex);
            map.put("user.area", adres);
            map.put("user.age", age);
            map.put("user.introduce", introduce);
            map.put("user.lat",lat);
            map.put("user.lng",lng);
        }
        System.out.print("map----"+map.toString());
        System.out.println("aaaa" + SortUtils.getMapResult(SortUtils.sortString(map)));
        System.out.println("maps = " + map.toString());
        RetrofitManager.post("http://qhb.2dyt.com/MyInterface/userAction_add.action", map, new BaseObserver() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                RegisterBean registerBean = gson.fromJson(result, RegisterBean.class);
                if (registerBean.getResult_code() == 200) {
                    PreferencesUtils.addConfigInfo(IApplication.getApplication(), "phone", registerBean.getData().getPhone());
                    PreferencesUtils.addConfigInfo(IApplication.getApplication(), "password", registerBean.getData().getPassword());
                    PreferencesUtils.addConfigInfo(IApplication.getApplication(), "yxpassword", registerBean.getData().getYxpassword());
                    PreferencesUtils.addConfigInfo(IApplication.getApplication(), "uid", registerBean.getData().getUserId());
                    PreferencesUtils.addConfigInfo(IApplication.getApplication(), "nickname", registerBean.getData().getNickname());
                }
                listener.onSuccess(registerBean);


            }

            @Override
            public void onFailed(int code) {
                listener.onFailed(code);
            }
        });
    }
}
