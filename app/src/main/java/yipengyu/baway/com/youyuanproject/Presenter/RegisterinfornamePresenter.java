package yipengyu.baway.com.youyuanproject.Presenter;

import yipengyu.baway.com.youyuanproject.Moudle.IRegisterinforname;
import yipengyu.baway.com.youyuanproject.Moudle.RegisterinfornameImp;
import yipengyu.baway.com.youyuanproject.View.RegisterfornameView;
import yipengyu.baway.com.youyuanproject.base.BasePresnter;
import yipengyu.baway.com.youyuanproject.bean.RegisterBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class RegisterinfornamePresenter extends BasePresnter<RegisterfornameView> {
    private RegisterinfornameImp registerinfornameImp;
    public RegisterinfornamePresenter(){
        registerinfornameImp=new RegisterinfornameImp();
    }

    public void vaildInfor(String phone,String nickname,String sex,String age,String area,String introduce,String password,String lat,String lng){


        //非空判断

       registerinfornameImp.getData(phone, nickname, sex, age, area, introduce, password,lat,lng ,new IRegisterinforname.RegisterInforFragmentDataListener() {
           @Override
           public void onSuccess(RegisterBean registerBean) {
               view.registerSuccess(registerBean);
           }

           @Override
           public void onFailed(int code) {
               view.registerFailed(code);
           }
       });






    }
}
