package yipengyu.baway.com.youyuanproject.Presenter;

import java.io.File;

import yipengyu.baway.com.youyuanproject.Moudle.IphotoMoudle;
import yipengyu.baway.com.youyuanproject.Moudle.PhotoMoudleImp;
import yipengyu.baway.com.youyuanproject.View.PhotoView;
import yipengyu.baway.com.youyuanproject.base.BasePresnter;
import yipengyu.baway.com.youyuanproject.bean.UploadPhotoBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class PhotoPresenter extends BasePresnter<PhotoView> {

     private PhotoMoudleImp photoMoudleImp;
    public PhotoPresenter(){
        photoMoudleImp=new PhotoMoudleImp();
    }

    public  void getdatafile(File file){

        photoMoudleImp.getfile(file, new IphotoMoudle.PhotoListener() {
            @Override
            public void onSuccess(UploadPhotoBean uploadPhotoBean) {
                view.onSuccess(uploadPhotoBean);
            }

            @Override
            public void onFailed(int code) {
              view.onFailed(code);
            }
        });

    }
}
