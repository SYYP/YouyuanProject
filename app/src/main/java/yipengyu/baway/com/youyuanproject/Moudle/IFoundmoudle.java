package yipengyu.baway.com.youyuanproject.Moudle;

import java.util.List;

import yipengyu.baway.com.youyuanproject.bean.DataBean;
import yipengyu.baway.com.youyuanproject.bean.IndexBean;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public interface IFoundmoudle {

    public void getData(Long currenttimer,int page,DataListener dataListener);

    public interface DataListener{
        public void onSuccess(List<DataBean> dataBean, boolean isData);
        public void onFailed(int code);
    }
}
