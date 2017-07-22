package yipengyu.baway.com.youyuanproject.Presenter;

import java.util.List;

import yipengyu.baway.com.youyuanproject.Moudle.FoundMoudle;
import yipengyu.baway.com.youyuanproject.Moudle.IFoundmoudle;
import yipengyu.baway.com.youyuanproject.View.Foundview;
import yipengyu.baway.com.youyuanproject.base.BasePresnter;
import yipengyu.baway.com.youyuanproject.bean.DataBean;
import yipengyu.baway.com.youyuanproject.bean.IndexBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class FoundPresenter extends BasePresnter<Foundview> {
     private FoundMoudle foundMoudle;
     public FoundPresenter(){
         foundMoudle=new FoundMoudle();

     }
    public void getData(Long currenttimer,int page){

        foundMoudle.getData(currenttimer,page, new IFoundmoudle.DataListener() {
            @Override
            public void onSuccess(List<DataBean> indexBean, boolean isData) {
                try {
                    view.success(indexBean,isData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int code) {
                try {
                    view.failed(code);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
