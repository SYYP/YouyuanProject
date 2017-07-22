package yipengyu.baway.com.youyuanproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yipengyu.baway.com.youyuanproject.R;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public abstract class BaseMvpFragment<V,T extends BasePresnter<V>>extends Fragment {

       public T presenter;
    public  abstract T initPresenter();
    public BaseMvpFragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_mvp, container, false);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach((V) this);

    }
}
