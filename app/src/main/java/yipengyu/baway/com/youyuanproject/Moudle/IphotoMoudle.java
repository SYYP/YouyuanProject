package yipengyu.baway.com.youyuanproject.Moudle;

import java.io.File;

import yipengyu.baway.com.youyuanproject.bean.RegisterBean;
import yipengyu.baway.com.youyuanproject.bean.UploadPhotoBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public interface IphotoMoudle {

     void getfile(File file, PhotoListener photolister);

    public  interface PhotoListener{
        void onSuccess(UploadPhotoBean uploadPhotoBean);

         void onFailed(int code);
    }
}
