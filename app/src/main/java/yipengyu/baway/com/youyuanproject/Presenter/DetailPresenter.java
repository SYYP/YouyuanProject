package yipengyu.baway.com.youyuanproject.Presenter;

import android.view.View;

import java.util.List;

import yipengyu.baway.com.youyuanproject.Moudle.AddfriendsMoudle;
import yipengyu.baway.com.youyuanproject.Moudle.DetailMoudleImp;
import yipengyu.baway.com.youyuanproject.Moudle.IAddFriendMoudle;
import yipengyu.baway.com.youyuanproject.Moudle.IDetailsMoudle;
import yipengyu.baway.com.youyuanproject.Moudle.IFoundmoudle;
import yipengyu.baway.com.youyuanproject.View.DetailView;
import yipengyu.baway.com.youyuanproject.base.BasePresnter;
import yipengyu.baway.com.youyuanproject.bean.UserInfoBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class DetailPresenter extends BasePresnter<DetailView>{
      private DetailMoudleImp detailMoudleImp;
      private AddfriendsMoudle addfriendsMoudle;
      public DetailPresenter(){
          detailMoudleImp=new DetailMoudleImp();
          addfriendsMoudle=new AddfriendsMoudle();
      }


       public void getphoto(String userid){
          detailMoudleImp.getphoto(userid, new IDetailsMoudle.DetailListener() {
              @Override
              public void onSuccess(List<UserInfoBean.DataBean.PhotolistBean> dataBean,int relation) {
                         view.onSuccess(dataBean,relation);
              }

              @Override
              public void onFailed(int code) {
            view.onFailed(code);
              }
          });

       }

    public void addfriend(int userid){

        addfriendsMoudle.addFriend(userid, new IAddFriendMoudle.AddFriendListener() {
            @Override
            public void onSuccess( int relation) {
                view.onSu(relation);
            }

            @Override
            public void onFailed(int code) {
                view.onFailed(code);
            }
        });

    }
}
