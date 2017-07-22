package yipengyu.baway.com.youyuanproject.Moudle;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.subscribers.SafeSubscriber;
import yipengyu.baway.com.youyuanproject.bean.FriendBean;
import yipengyu.baway.com.youyuanproject.bean.UserInfoBean;
import yipengyu.baway.com.youyuanproject.network.BaseObserver;
import yipengyu.baway.com.youyuanproject.network.RetrofitManager;
import yipengyu.baway.com.youyuanproject.utils.Constants;

import static android.R.attr.data;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AddfriendsMoudle implements IAddFriendMoudle {

    @Override
    public void addFriend(int userid, final AddFriendListener listener) {
        Map<String,String> map=new HashMap<>();
        map.put("relationship.friendId",userid+"");
        RetrofitManager.post(Constants.ADDFRIEND, map, new BaseObserver() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                    FriendBean friendBean = gson.fromJson(result, FriendBean.class);
             //  UserInfoBean userInfoBean = gson.fromJson(result, UserInfoBean.class);
              //  int relation = userInfoBean.getData().getRelation();
                int relation = friendBean.getAddUser().getRelation();
                listener.onSuccess(relation);


            }

            @Override
            public void onFailed(int code) {
                    listener.onFailed(code);
            }
        });
    }
}
