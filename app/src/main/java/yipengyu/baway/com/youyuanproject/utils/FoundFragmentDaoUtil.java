package yipengyu.baway.com.youyuanproject.utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import yipengyu.baway.com.youyuanproject.base.IApplication;
import yipengyu.baway.com.youyuanproject.bean.DataBean;
import yipengyu.baway.com.youyuanproject.dao.DaoSession;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class FoundFragmentDaoUtil {
 /*
 添加数据
  */
     public static void insert(final List<DataBean >dataBean){

         Observable.create(new ObservableOnSubscribe<Long>() {
             @Override
             public void subscribe(@NonNull ObservableEmitter<Long> e) throws Exception {
                  IApplication.daoSession.getDataBeanDao().insertInTx(dataBean);
             }
         }).subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<Long>() {
                     @Override
                     public void accept(@NonNull Long aLong) throws Exception {

                     }
                 });

     }
    /*
      查询数据
     */
     public  static  void select(String tag){

          Observable.create(new ObservableOnSubscribe< List<DataBean>>() {
              @Override
              public void subscribe(@NonNull ObservableEmitter< List<DataBean>> e) throws Exception {
                  List<DataBean> dataBeen = IApplication.daoSession.getDataBeanDao().loadAll();
                  e.onNext(dataBeen);
              }
          }).subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<List<DataBean>>() {
                      @Override
                      public void accept(@NonNull  List<DataBean> dataBeen) throws Exception {

                      }
                  });

     }
}
