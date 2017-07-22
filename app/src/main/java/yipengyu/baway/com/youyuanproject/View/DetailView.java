package yipengyu.baway.com.youyuanproject.View;

import java.util.List;

import yipengyu.baway.com.youyuanproject.bean.UserInfoBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public interface DetailView {

    public void onSu(int relation );
    public void onSuccess(List<UserInfoBean.DataBean.PhotolistBean> dataBean,int relation);
    public void onFailed(int code);
}
