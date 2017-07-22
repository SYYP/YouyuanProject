package yipengyu.baway.com.youyuanproject.Presenter;

import java.util.List;

import yipengyu.baway.com.youyuanproject.Moudle.ILiebiaoMoudle;
import yipengyu.baway.com.youyuanproject.Moudle.LieobiaoMoudle;
import yipengyu.baway.com.youyuanproject.View.LiebaoView;
import yipengyu.baway.com.youyuanproject.base.BasePresnter;
import yipengyu.baway.com.youyuanproject.bean.FriendListDataBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class LiebaoPresenter extends BasePresnter<LiebaoView>{
private LieobiaoMoudle lieobiaoMoudle;
    public LiebaoPresenter(){
        lieobiaoMoudle=new LieobiaoMoudle();
    }

    public void getFiiend(long currenttmter,boolean data){

        lieobiaoMoudle.getfriend(currenttmter, data, new ILiebiaoMoudle.ILieListener() {
            @Override
            public void onSuccess(List<FriendListDataBean> list, boolean data) {
                    view.onSuccess(list,data);
            }

            @Override
            public void onFailed(int code) {
                    view.onFailed(code);
            }
        });
    }
}
