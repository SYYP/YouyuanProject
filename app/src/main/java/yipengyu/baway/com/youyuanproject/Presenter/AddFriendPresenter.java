package yipengyu.baway.com.youyuanproject.Presenter;

import java.util.List;

import yipengyu.baway.com.youyuanproject.Moudle.AddfriendsMoudle;
import yipengyu.baway.com.youyuanproject.Moudle.IAddFriendMoudle;
import yipengyu.baway.com.youyuanproject.View.AddfriendView;
import yipengyu.baway.com.youyuanproject.base.BasePresnter;
import yipengyu.baway.com.youyuanproject.bean.UserInfoBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AddFriendPresenter extends BasePresnter<AddfriendView>{
         private AddfriendsMoudle addfriendsMoudle;
        public AddFriendPresenter (){
            addfriendsMoudle=new AddfriendsMoudle();
        }

      public void addfriend(int userid){

          addfriendsMoudle.addFriend(userid, new IAddFriendMoudle.AddFriendListener() {
              @Override
              public void onSuccess( int relation) {
                      view.onSuccess(relation);
              }

              @Override
              public void onFailed(int code) {
                     view.onFailed(code);
              }
          });

      }
}
