package yipengyu.baway.com.youyuanproject.Presenter;

import yipengyu.baway.com.youyuanproject.Moudle.ILoginMoudle;
import yipengyu.baway.com.youyuanproject.Moudle.LoginMoudleImp;
import yipengyu.baway.com.youyuanproject.View.LoginView;
import yipengyu.baway.com.youyuanproject.base.BasePresnter;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class LoginPresenter extends BasePresnter<LoginView> {
       private LoginMoudleImp loginMoudleImp;


     public LoginPresenter (){
        loginMoudleImp=new LoginMoudleImp();
     }
      public void getuser(String phone,String pwd,String lat, String lng){
          loginMoudleImp.getuserinfor(phone, pwd,lat,lng, new ILoginMoudle.LoginUserListener() {
              @Override
              public void onSuccess(String reuslt) {
                  view.onSuccess(reuslt);
              }

              @Override
              public void onFailed(int code) {
            view.onFailed(code);
              }
          });
      }
}
