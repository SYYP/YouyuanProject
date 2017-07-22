package yipengyu.baway.com.youyuanproject.Moudle;

import java.util.List;

import yipengyu.baway.com.youyuanproject.bean.DataBean;
import yipengyu.baway.com.youyuanproject.bean.UserInfoBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public interface IDetailsMoudle {
    void getphoto(String userid,DetailListener listener);
    public interface DetailListener{
        public void onSuccess( List<UserInfoBean.DataBean.PhotolistBean> dataBean,int relation);
        public void onFailed(int code);
    }
}
