package yipengyu.baway.com.youyuanproject.activtiy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yipengyu.baway.com.youyuanproject.Presenter.DetailPresenter;
import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.View.DetailView;
import yipengyu.baway.com.youyuanproject.adapter.DetailAdapter;
import yipengyu.baway.com.youyuanproject.adapter.FoundAdapter;
import yipengyu.baway.com.youyuanproject.base.BaseMvpActivity;
import yipengyu.baway.com.youyuanproject.base.IActivity;
import yipengyu.baway.com.youyuanproject.base.IApplication;
import yipengyu.baway.com.youyuanproject.bean.ImageInfo;
import yipengyu.baway.com.youyuanproject.bean.UserInfoBean;
import yipengyu.baway.com.youyuanproject.utils.PicShowDialog;
import yipengyu.baway.com.youyuanproject.widget.MyToast;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class   DetailsActivity extends BaseMvpActivity<DetailView,DetailPresenter> implements DetailView {

    private ImageView layout_img;
    private RecyclerView layout_recycler;
    private TextView layout_introduce;
    private TextView layout_name;
    private TextView layout_sex;
    private TextView layout_age;
    private TextView layout_address;
    private TextView layout_qianming;
    private String name;
    private String age;
    private String sex;
    private String address;
    private String introduce;
    private String path;
    private String useid;
    private TextView layout_time;
    private String str;
    private String time;
    private Long aLong;
    private String time1;
    private DetailAdapter detailAdapter;
        private List<ImageInfo> list=new ArrayList<>();
    private ImageInfo imageInfo;
    private Button btn_friend;

    @Override
    public DetailPresenter ininpresenter() {
        return new DetailPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_frag_item_layout);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        age = intent.getStringExtra("age");
        sex = intent.getStringExtra("sex");
        address = intent.getStringExtra("address");
        introduce = intent.getStringExtra("introduce");
        path = intent.getStringExtra("path");
        useid = intent.getStringExtra("Useid");
        time= intent.getStringExtra("time");

       aLong = Long.valueOf(time);
      // SimpleDateFormat formatter =new SimpleDateFormat   ("yyyy年MM月dd日HH:mm:ss");
//       // Date curDate =  new Date(System.currentTimeMillis());
      //str = formatter.format(formatter.parse()time);
        initView();
        btn_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IApplication.getApplication().login){


                    AlertDialog.Builder builder=new AlertDialog.Builder(DetailsActivity.this);
                    builder.setTitle("你未登录请先登录");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            IApplication.getApplication().setLogin(false);
                            Intent intent=new Intent(DetailsActivity.this,LoginAtivitys.class);
                            startActivity(intent);

                        }

                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder .show();

                }else  if (IApplication.getApplication().login==false){
                    presenter.addfriend(Integer.valueOf(useid));
                }
                 if(btn_friend.getText().equals("发消息")){
                     Intent intent1=new Intent(DetailsActivity.this,ChatActivity.class);

                     startActivity(intent1);

                 }

            }


        });

    }
    public static String formatData(String dataFormat, Long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }
    private void initView() {
        layout_img = (ImageView) findViewById(R.id.layout_img);
        layout_recycler = (RecyclerView) findViewById(R.id.layout_recycler);
        layout_introduce = (TextView) findViewById(R.id.layout_introduce);
        layout_name = (TextView) findViewById(R.id.layout_name);
        layout_sex = (TextView) findViewById(R.id.layout_sex);
        layout_age = (TextView) findViewById(R.id.layout_age);
        layout_address = (TextView) findViewById(R.id.layout_address);
        layout_qianming = (TextView) findViewById(R.id.layout_qianming);
        layout_time = (TextView) findViewById(R.id.layout_time);
        Glide.with(this).load(path).into(layout_img);
        btn_friend = (Button) findViewById(R.id.friend);
        layout_name.setText(name);
        layout_age.setText(age);
        layout_sex.setText(sex);
        layout_address.setText(address);
        layout_qianming.setText(introduce);
        presenter.getphoto(useid+"");
        String s = formatData("yyyy年MM月dd日HH:mm:ss", aLong);
        layout_time.setText(s);
    }

    @Override
    public void onSu(int relation) {

             if(relation==0){
                  btn_friend.setText("发消息");

             }
        else {
                 btn_friend.setText("添加好友");
             }
    }

    @Override
    public void onSuccess(List<UserInfoBean.DataBean.PhotolistBean> dataBean,int relation) {
         if(relation==1){
             btn_friend.setText("发消息");


         }
        else {
             btn_friend.setText("添加好友");
         }

        for(int i=0; i<dataBean.size();i++){
            imageInfo = new ImageInfo();
            imageInfo.setHeight(dataBean.get(i).getPicHeight());
            imageInfo.setWidth(dataBean.get(i).getPicWidth());
            imageInfo.setUrl(dataBean.get(i).getImagePath());
            list.add(imageInfo);
        }
        detailAdapter = new DetailAdapter(dataBean,this);
         layout_recycler.setAdapter(detailAdapter);
        detailAdapter.setOnItemClickListener(new FoundAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PicShowDialog dialog=new PicShowDialog(DetailsActivity.this,list,position);
                dialog.show();
            }
        });
        layout_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));



    }

    @Override
    public void onFailed(int code) {
        MyToast.makeText(DetailsActivity.this,"错误", Toast.LENGTH_SHORT);

    }
}
