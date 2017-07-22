package yipengyu.baway.com.youyuanproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.getkeepsafe.relinker.ReLinker;

import yipengyu.baway.com.youyuanproject.R;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class IActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acti);

    }
}
