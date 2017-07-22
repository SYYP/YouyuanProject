package yipengyu.baway.com.youyuanproject.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.base.IFragment;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class MyFragment extends IFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab_frag4,container,false);
        return view;
    }
}
