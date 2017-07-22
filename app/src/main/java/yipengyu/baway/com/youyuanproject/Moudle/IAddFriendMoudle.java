package yipengyu.baway.com.youyuanproject.Moudle;

import java.util.List;

import yipengyu.baway.com.youyuanproject.bean.UserInfoBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public interface IAddFriendMoudle {

      void addFriend(int relation,AddFriendListener listener);

    public interface AddFriendListener {
        public void onSuccess(int relation);

        public void onFailed(int code);
    }
    }
