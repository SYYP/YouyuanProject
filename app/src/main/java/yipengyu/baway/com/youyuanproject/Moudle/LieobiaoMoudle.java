package yipengyu.baway.com.youyuanproject.Moudle;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.single.SingleEquals;
import io.reactivex.schedulers.Schedulers;
import yipengyu.baway.com.youyuanproject.base.IApplication;
import yipengyu.baway.com.youyuanproject.bean.DataBean;
import yipengyu.baway.com.youyuanproject.bean.FriendListBean;
import yipengyu.baway.com.youyuanproject.bean.FriendListDataBean;
import yipengyu.baway.com.youyuanproject.dao.DataBeanDao;
import yipengyu.baway.com.youyuanproject.dao.FriendListDataBeanDao;
import yipengyu.baway.com.youyuanproject.network.BaseObserver;
import yipengyu.baway.com.youyuanproject.network.RetrofitManager;
import yipengyu.baway.com.youyuanproject.utils.Constants;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class LieobiaoMoudle implements ILiebiaoMoudle {

    @Override
    public void getfriend(Long currentemer, boolean data, final ILieListener lieListener) {
        Map map = new HashMap<>();
        map.put("user.currenttimer", currentemer+"");
        //判断走数据库还是走网络
        if(data){
            Observable.create(new ObservableOnSubscribe< List<FriendListDataBean>>() {
                @Override
                public void subscribe(@NonNull ObservableEmitter< List<FriendListDataBean>> e) throws Exception {
                    List<FriendListDataBean> list1 = IApplication.daoSession.getFriendListDataBeanDao().queryBuilder()
                            .orderDesc(FriendListDataBeanDao.Properties.Id)
                            .limit(10)
                            .distinct()
                            .build().list();

                    e.onNext(list1);
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<FriendListDataBean>>() {
                        @Override
                        public void accept(@NonNull  List<FriendListDataBean> list) throws Exception {
                           lieListener.onSuccess(list,true);

                        }
                    });
            

        }

        RetrofitManager.post(Constants.HAOYOU_LIEBIAO, map, new BaseObserver() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                FriendListBean friendListBean = gson.fromJson(result, FriendListBean.class);
                if(friendListBean.getResult_code()==200){

                    final List<FriendListDataBean> date = friendListBean.getData();
                    lieListener.onSuccess(date,true);
                    //添加到数据库
                    Observable.create(new ObservableOnSubscribe<Long>() {
                        @Override
                        public void subscribe(@NonNull ObservableEmitter<Long> e) throws Exception {

                            IApplication.getApplication().daoSession.getFriendListDataBeanDao().insertOrReplaceInTx(date);


                        }

                    }).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(@NonNull Long aLong) throws Exception {

                                }
                            });
                }

            }

            @Override
            public void onFailed(int code) {
                 lieListener.onFailed(code);
            }
        });
    }
}
