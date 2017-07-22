package yipengyu.baway.com.youyuanproject.Presenter;

import android.text.TextUtils;

import yipengyu.baway.com.youyuanproject.Moudle.RegisterMoudle;
import yipengyu.baway.com.youyuanproject.View.Registerview;
import yipengyu.baway.com.youyuanproject.base.BasePresnter;
import yipengyu.baway.com.youyuanproject.widget.PhoneCheckUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Registerpresenter extends BasePresnter<Registerview> {

      public RegisterMoudle registerMoudle;
      public Registerpresenter(){

       this.registerMoudle= new RegisterMoudle();
      }

       public void getTelephone(String phone){

            if(TextUtils.isEmpty(phone)){

                view.phontEmpy(1);
                return;

            }
           if(!PhoneCheckUtils.isChinaPhoneLegal(phone)){
               view.phontEmpy(2);
               return;
           }
           registerMoudle.getVerificationCode(phone);
           view.showTimer();

       }
}
