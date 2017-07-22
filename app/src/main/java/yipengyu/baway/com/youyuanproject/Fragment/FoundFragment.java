package yipengyu.baway.com.youyuanproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import yipengyu.baway.com.youyuanproject.Moudle.FoundMoudle;
import yipengyu.baway.com.youyuanproject.Presenter.FoundPresenter;
import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.View.Foundview;
import yipengyu.baway.com.youyuanproject.activtiy.DetailsActivity;
import yipengyu.baway.com.youyuanproject.adapter.FoundAdapter;
import yipengyu.baway.com.youyuanproject.base.BaseMvpFragment;
import yipengyu.baway.com.youyuanproject.base.IFragment;
import yipengyu.baway.com.youyuanproject.bean.DataBean;
import yipengyu.baway.com.youyuanproject.bean.IndexBean;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class FoundFragment extends BaseMvpFragment<Foundview,FoundPresenter> implements Foundview {
    private FloatingActionButton fab;
    private RecyclerView recyclerview;
    private SpringView springView;
     private List<DataBean> list=new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    int page=1;
    private FoundAdapter adapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private long currttmer;

    // private HorizontalDividerItemDecoration horizontalDividerItemDecoration;
    @Override
    public FoundPresenter initPresenter() {
        return new FoundPresenter();
    }
    public FoundFragment(){
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_frag1, container, false);
       ininview(view);
         adapter.setOnItemClickListener(new FoundAdapter.OnItemClickListener() {
             @Override
             public void onItemClick(View view, int position) {
                 Intent intent=new Intent(getActivity(), DetailsActivity.class);
                   intent.putExtra("name",list.get(position).getNickname());
                   intent.putExtra("sex",list.get(position).getGender());
                   intent.putExtra("age",list.get(position).getAge()+"");
                   intent.putExtra("address",list.get(position).getArea());
                   intent.putExtra("introduce",list.get(position).getIntroduce());
                   intent.putExtra("path",list.get(position).getImagePath());
                   intent.putExtra("Useid",list.get(position).getUserId()+"");
                   intent.putExtra("time",list.get(position).getLasttime()+"");
                  PreferencesUtils.addConfigInfo(getActivity(),"zhaopian",list.get(position).getImagePath());
                 startActivity(intent);

             }
         });
        return view;

    }

    private void ininview(View view) {
        currttmer = System.currentTimeMillis();
        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        springView = (SpringView) view.findViewById(R.id.springview);
        fab.setTag(1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag() ;
                if(tag == 1){
                    fab.setTag(2);
                    toStaggeredGridLayoutManager();


                } else {
                    fab.setTag(1);
                    toLinearLayoutManager();
                }
            }
        });
        adapter = new FoundAdapter(getActivity(),list);
        toLinearLayoutManager();
        presenter.getData(currttmer,page);
        springView.setHeader(new MeituanHeader(getActivity()));
        springView.setFooter(new MeituanFooter(getActivity()));
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                System.out.println("onRefresh = " );
                page = 1 ;
           //    PreferencesUtils.addConfigInfo(getActivity(),"tag",1);
               presenter.getData(currttmer,page);
            }

            @Override
            public void onLoadmore() {
             //  PreferencesUtils.addConfigInfo(getActivity(),"tag",2);
                page=2;
                if (list!=null) {
                    long lasttime = list.get(list.size() - 1).getLasttime();
                    presenter.getData(lasttime,page);
                }
                System.out.println("onLoadmore = " );
            //    presenter.getData(++page);

            }
        });
    }

    public void toStaggeredGridLayoutManager(){
        if(staggeredGridLayoutManager == null){
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        }
       adapter.dataChange(2);
       recyclerview.setLayoutManager(staggeredGridLayoutManager);
       recyclerview.setAdapter(adapter);
      //  recyclerview.removeItemDecoration(horizontalDividerItemDecoration);

    }
    public void toLinearLayoutManager(){
        if(linearLayoutManager == null){
            linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        }
        adapter.dataChange(1);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(adapter);
      //  recyclerview.addItemDecoration(horizontalDividerItemDecoration);

    }


    @Override
    public void success(List<DataBean> indexBean,boolean isData) {
        springView.onFinishFreshAndLoad();
        if(indexBean!=null&&indexBean.size()!=0){

            if(page==1){
                list.clear();

            }
            if (isData && page == 2) {
            } else {
                Log.d("RecommendFragment", "data:" + indexBean);
                // adapter.setData(data, page);

                if (indexBean!= null || indexBean.size() != 0) {
                    list.addAll(indexBean);

                }
                //notifyDataSetChanged();
                //list.addAll(data);
            }
          //  adapter.setData(list,page);
            adapter.notifyDataSetChanged();
        }


    }

    @Override
    public void failed(int code) {
        Log.d("RecommendFragment", "e:" + code);
    }
}
