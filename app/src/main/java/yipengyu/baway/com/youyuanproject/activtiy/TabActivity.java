package yipengyu.baway.com.youyuanproject.activtiy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;


import yipengyu.baway.com.youyuanproject.Fragment.DongtaiFragment;
import yipengyu.baway.com.youyuanproject.Fragment.FoundFragment;
import yipengyu.baway.com.youyuanproject.Fragment.LiebiaoFragment;
import yipengyu.baway.com.youyuanproject.Fragment.MyFragment;
import yipengyu.baway.com.youyuanproject.MainActivity;
import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.base.IActivity;
import yipengyu.baway.com.youyuanproject.base.IApplication;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class TabActivity extends IActivity implements View.OnClickListener {
    private Fragment currentf;
    private RadioButton rb_found;
    private RadioButton rb_xiaoxi;
    private RadioButton rb_liebiao;
    private RadioButton rb_my;
     private FoundFragment foundFragment;
    private DongtaiFragment dongtaiFragment;
    private LiebiaoFragment liebiaoFragment;
    private MyFragment myFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabactivity);
        //获得资源id
        ininview();
        addFragments(new FoundFragment());
    }

    private void ininview() {
        rb_found = (RadioButton) findViewById(R.id.rb1);
        rb_xiaoxi = (RadioButton) findViewById(R.id.rb2);
        rb_liebiao = (RadioButton) findViewById(R.id.rb3);
        rb_my = (RadioButton) findViewById(R.id.rb4);
        rb_found.setOnClickListener(this);
        rb_xiaoxi.setOnClickListener(this);
        rb_liebiao.setOnClickListener(this);
        rb_my .setOnClickListener(this);
    }

    private void addFragments(Fragment f) {
        // 第一步：得到fragment管理类
        FragmentManager manager = getSupportFragmentManager();
        // 第二步：开启一个事务
        FragmentTransaction transaction = manager.beginTransaction();

        if (currentf != null) {
            //每次把前一个fragment给隐藏了
            transaction.hide(currentf);
        }

        //isAdded:判断当前的fragment对象是否被加载过
        if (!f.isAdded()) {
            // 第三步：调用添加fragment的方法 第一个参数：容器的id 第二个参数：要放置的fragment的一个实例对象
            transaction.add(R.id.fl, f);
        }
        //显示当前的fragment
        transaction.show(f);

        // 第四步：提交
        transaction.commit();

        currentf = f;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb1:
                if(foundFragment==null){
                    foundFragment=new FoundFragment();

                }
                addFragments(foundFragment);
                break;
            case R.id.rb2:

                if(liebiaoFragment==null){

                    liebiaoFragment=new LiebiaoFragment();
                }
                addFragments(liebiaoFragment);
                break;
            case R.id.rb3:
                if(dongtaiFragment==null){
                    dongtaiFragment=new DongtaiFragment();

                }
                addFragments(dongtaiFragment);

                break;
            case R.id.rb4:
                if(myFragment==null){
                    myFragment=new MyFragment();
                }
                addFragments(myFragment);
                break;
        }
    }
}
