package yipengyu.baway.com.youyuanproject.Moudle;

import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import yipengyu.baway.com.youyuanproject.base.IApplication;
import yipengyu.baway.com.youyuanproject.bean.UploadPhotoBean;
import yipengyu.baway.com.youyuanproject.core.JNICore;
import yipengyu.baway.com.youyuanproject.core.SortUtils;
import yipengyu.baway.com.youyuanproject.network.BaseObserver;
import yipengyu.baway.com.youyuanproject.network.RetrofitManager;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;
import yipengyu.baway.com.youyuanproject.widget.MyToast;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class PhotoMoudleImp implements  IphotoMoudle{
    @Override
    public void getfile(File file, final PhotoListener photolister) {

        if(!file.exists()){
            MyToast.makeText(IApplication.getApplication()," 照片不存在", Toast.LENGTH_SHORT);
            return;
        }
        String [] arr = file.getAbsolutePath().split("/");

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String picwidth = PreferencesUtils.getValueByKey(IApplication.getApplication(),"picwidth","100");
        String picheight = PreferencesUtils.getValueByKey(IApplication.getApplication(),"picheight","100");
        long ctimer = System.currentTimeMillis() ;
        Map<String,String> map = new HashMap<String,String>();
        map.put("user.currenttimer",ctimer+"");
        map.put("user.picWidth", picwidth);
        map.put("user.picHeight",picheight);
        System.out.println(picwidth);
        System.out.println(picheight);
        String mapResult = SortUtils.getMapResult(SortUtils.sortString(map));
        String sign =  JNICore.getSign(mapResult) ;
        map.put("user.sign",sign);


        MultipartBody body = new MultipartBody.Builder()
                .addFormDataPart("image",arr[arr.length-1],requestFile)
                .build();

        RetrofitManager.uploadPhoto(body, map, new BaseObserver() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                UploadPhotoBean bean =  gson.fromJson(result, UploadPhotoBean.class);
                photolister.onSuccess(bean);
            }

            @Override
            public void onFailed(int code) {
        photolister.onFailed(code);
            }
        });
    }
}
