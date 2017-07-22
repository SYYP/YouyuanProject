package yipengyu.baway.com.youyuanproject.View;

import java.util.List;

import yipengyu.baway.com.youyuanproject.bean.DataBean;
import yipengyu.baway.com.youyuanproject.bean.IndexBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public interface Foundview {
    public void success(List<DataBean> indexBean,boolean isData);
    public void failed(int code);

}
