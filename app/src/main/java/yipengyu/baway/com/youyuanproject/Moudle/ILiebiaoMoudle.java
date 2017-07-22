package yipengyu.baway.com.youyuanproject.Moudle;

import java.util.List;

import yipengyu.baway.com.youyuanproject.bean.FriendListDataBean;
import yipengyu.baway.com.youyuanproject.bean.UploadPhotoBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public interface ILiebiaoMoudle {

 void getfriend(Long currtemer,boolean data, ILieListener lieListener);



    public  interface ILieListener{
        void onSuccess( final List<FriendListDataBean> list,boolean data);

        void onFailed(int code);
    }

}
