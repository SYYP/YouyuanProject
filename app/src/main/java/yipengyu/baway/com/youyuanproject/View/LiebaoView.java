package yipengyu.baway.com.youyuanproject.View;

import java.util.List;

import yipengyu.baway.com.youyuanproject.bean.FriendListDataBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public interface LiebaoView {

    void onSuccess(final List<FriendListDataBean> list, boolean temp);

    void onFailed(int code);
}
