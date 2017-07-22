package yipengyu.baway.com.youyuanproject.View;

import yipengyu.baway.com.youyuanproject.bean.UploadPhotoBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public interface PhotoView {

    void onSuccess(UploadPhotoBean uploadPhotoBean);

    void onFailed(int code);
}
