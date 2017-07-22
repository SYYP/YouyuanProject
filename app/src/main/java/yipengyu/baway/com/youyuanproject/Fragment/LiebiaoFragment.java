package yipengyu.baway.com.youyuanproject.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import yipengyu.baway.com.youyuanproject.Presenter.LiebaoPresenter;
import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.View.LiebaoView;
import yipengyu.baway.com.youyuanproject.activtiy.ChatActivity;
import yipengyu.baway.com.youyuanproject.activtiy.LoginAtivitys;
import yipengyu.baway.com.youyuanproject.activtiy.TabActivity;
import yipengyu.baway.com.youyuanproject.adapter.Liedapter;
import yipengyu.baway.com.youyuanproject.base.BaseMvpFragment;
import yipengyu.baway.com.youyuanproject.base.IApplication;
import yipengyu.baway.com.youyuanproject.base.IFragment;
import yipengyu.baway.com.youyuanproject.bean.FriendListDataBean;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;
import yipengyu.baway.com.youyuanproject.widget.MyToast;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class LiebiaoFragment extends BaseMvpFragment<LiebaoView,LiebaoPresenter> implements LiebaoView {

    private ListView listview;
    private SpringView lie_spring;
   boolean temps =false;
     private List<FriendListDataBean> lists=new ArrayList<>();
    private Liedapter liedapter;

    @Override
    public LiebaoPresenter initPresenter() {
        return new LiebaoPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       final View view=inflater.inflate(R.layout.tab_frag2,container,false);
        final ImageView lie_img= (ImageView) view.findViewById(R.id.login_image);
        lie_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(IApplication.getApplication().login){


                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setTitle("你未登录请先登录");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            IApplication.getApplication().setLogin(false);
                            Intent intent=new Intent(getActivity(),LoginAtivitys.class);
                            lie_img.setVisibility(View.GONE);
                            startActivity(intent);

                        }

                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder .show();

                }

            }
        });


        if(IApplication.getApplication().login==false){
            lie_img.setVisibility(View.GONE);

        }
        initview(view);
        presenter.getFiiend(System.currentTimeMillis(),true);
        lie_spring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                temps = true;
                presenter.getFiiend(System.currentTimeMillis(),temps);
            }

            @Override
            public void onLoadmore() {
                if (lists != null) {
                    temps = false;
                    long lastTime = lists.get(lists.size() - 1).getRelationtime();

                    presenter.getFiiend(lastTime,temps);
                }
            }
        });
        listview.setAdapter(liedapter);

        return view;
    }

    private void initview(View view) {

        listview = (ListView) view.findViewById(R.id.liebiao_list);
        lie_spring = (SpringView) view.findViewById(R.id.liebiao_spring);
        lie_spring .setHeader(new MeituanHeader(getActivity()));
        lie_spring .setFooter(new MeituanFooter(getActivity()));
        lie_spring.setType(SpringView.Type.FOLLOW);
        liedapter = new Liedapter(getActivity(),lists);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int userId = lists.get(position).getUserId();
             PreferencesUtils.addConfigInfo(getActivity(),"who",userId+"");
                PreferencesUtils.addConfigInfo(getActivity(),"paizhao",lists.get(position).getImagePath()+"");
                 Intent it=new Intent(getActivity(), ChatActivity.class);
                startActivity(it);

            }
        });




    }

    @Override
    public void onSuccess(List<FriendListDataBean> list, boolean temp) {
        lie_spring.onFinishFreshAndLoad();
        if (list == null || list.size() == 0) {
            return;
        }
        if (temp) {
            lists.clear();
        }
        if (temp &&! temps) {
        }else{
            if (list!=null&&list.size()!=0) {
                lists.addAll(list);
            }
        }

        liedapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(int code) {
        MyToast.makeText(getActivity(),"错误",Toast.LENGTH_SHORT);
    }
}
