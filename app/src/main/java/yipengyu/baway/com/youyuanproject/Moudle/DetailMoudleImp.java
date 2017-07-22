package yipengyu.baway.com.youyuanproject.Moudle;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yipengyu.baway.com.youyuanproject.bean.UserInfoBean;
import yipengyu.baway.com.youyuanproject.network.BaseObserver;
import yipengyu.baway.com.youyuanproject.network.RetrofitManager;
import yipengyu.baway.com.youyuanproject.utils.Constants;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class DetailMoudleImp implements IDetailsMoudle {
    @Override
    public void getphoto(String userid, final DetailListener listener) {
         Map<String, String> map = new HashMap<String, String>();
          map.put("user.userId",userid+"");
        RetrofitManager.post(Constants.Photo_Suggestion, map, new BaseObserver() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                UserInfoBean userInfoBean = gson.fromJson(result, UserInfoBean.class);
                int relation = userInfoBean.getData().getRelation();
                List<UserInfoBean.DataBean.PhotolistBean> photolist =userInfoBean.getData().getPhotolist();
                listener.onSuccess(photolist,relation);
            }

            @Override
            public void onFailed(int code) {
                listener.onFailed(code);

            }
        });
    }

}
