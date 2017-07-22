package yipengyu.baway.com.youyuanproject.Moudle;

import android.util.Log;

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
import io.reactivex.schedulers.Schedulers;
import yipengyu.baway.com.youyuanproject.base.IApplication;
import yipengyu.baway.com.youyuanproject.bean.DataBean;
import yipengyu.baway.com.youyuanproject.bean.IndexBean;
import yipengyu.baway.com.youyuanproject.dao.DataBeanDao;
import yipengyu.baway.com.youyuanproject.network.BaseObserver;
import yipengyu.baway.com.youyuanproject.network.RetrofitManager;
import yipengyu.baway.com.youyuanproject.utils.Constants;
import yipengyu.baway.com.youyuanproject.utils.FoundFragmentDaoUtil;
import yipengyu.baway.com.youyuanproject.utils.MessageDaoUtils;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class FoundMoudle implements IFoundmoudle {
    boolean temp = false;
    @Override
    public void getData(final Long currenttimer,int page, final DataListener dataListener) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("user.currenttimer",currenttimer+"");
         /*
                         查询数据
                             */
        if(page==1){
            /*
              下拉刷新
             */
            Observable.create(new ObservableOnSubscribe<List<DataBean>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<DataBean>> e) throws Exception {


                List<DataBean> nearbyDataBeen = IApplication.getApplication().daoSession.getDataBeanDao().queryBuilder()
                        .orderDesc(DataBeanDao.Properties.Id)
                        .distinct()
                        .limit(20)
                        .build().list();

                e.onNext(nearbyDataBeen);

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DataBean>>() {
                    @Override
                    public void accept(@NonNull List<DataBean> nearbyDataBeen) throws Exception {
                        dataListener.onSuccess(nearbyDataBeen,true);
                        Log.d("RecyclerModerImlp", "nearbyDataBeen:" + nearbyDataBeen);
                    }
                });}
        RetrofitManager.post(Constants.Found_USER, map, new BaseObserver() {
            @Override
            public void onSuccess(String result) {
                try {
                    Gson gson = new Gson();
                    final IndexBean indexBean = gson.fromJson(result, IndexBean.class);
                    final List<DataBean> data = indexBean.getData();
                    int userId = PreferencesUtils.getValueByKey(IApplication.getApplication(), "luserId", 0);
                    if (indexBean.getResult_code() == 200 && indexBean.getData().size() != 0) {
                        if (userId != 0) {
                            for (int i = 0; i < data.size(); i++) {
                                if (data.get(i).getUserId() == userId) {
                                    data.remove(i);
                                }
                            }
                        }
                        MessageDaoUtils.insert(data);
                        temp = true;
                        dataListener.onSuccess(data,false);
                      //添加到数据库
                        Observable.create(new ObservableOnSubscribe< List<DataBean>>() {
                            @Override
                            public void subscribe(@NonNull ObservableEmitter<List<DataBean>> e) throws Exception {
                                       IApplication.daoSession.getDataBeanDao().insertOrReplaceInTx(data);
                            }
                        }).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<List<DataBean>>() {
                                    @Override
                                    public void accept(@NonNull  List<DataBean> dataBeen) throws Exception {

                                    }
                                });

                        String tag = PreferencesUtils.getValueByKey(IApplication.getApplication(), "tag", "");
                         if(tag.equals(2)){
                                 /*
                              条件 查询数据（上拉加载更多）
                             */
                             Observable.create(new ObservableOnSubscribe< List<DataBean>>() {
                                 @Override
                                 public void subscribe(@NonNull ObservableEmitter< List<DataBean>> e) throws Exception {
                                     List<DataBean> dataBeen = IApplication.daoSession.getDataBeanDao().queryBuilder()
                                             .orderDesc(DataBeanDao.Properties.Id)
                                             .limit(20)
                                             .build().list();

                                     e.onNext(dataBeen);
                                 }
                             }).subscribeOn(Schedulers.io())
                                     .observeOn(AndroidSchedulers.mainThread())
                                     .subscribe(new Consumer<List<DataBean>>() {
                                         @Override
                                         public void accept(@NonNull  List<DataBean> dataBeen) throws Exception {
                                   //          dataListener.onSuccess(dataBeen,currenttimer);

                                         }
                                     });

                         }





                    }

                } catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int code) {
                dataListener.onFailed(code);

            }
        });
    }

}
