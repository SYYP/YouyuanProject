package yipengyu.baway.com.youyuanproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import yipengyu.baway.com.youyuanproject.R;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public abstract class BaseMvpActivity<V,T extends BasePresnter<V>>extends IActivity {


      public T presenter;
     public abstract  T ininpresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_mvp);
        presenter =  ininpresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
