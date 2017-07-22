package yipengyu.baway.com.youyuanproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.bean.DataBean;
import yipengyu.baway.com.youyuanproject.bean.IndexBean;
import yipengyu.baway.com.youyuanproject.utils.DeviceUtils;
import yipengyu.baway.com.youyuanproject.utils.DisanceUtils;
import yipengyu.baway.com.youyuanproject.utils.Distance;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class FoundAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<DataBean> list;
    private Context context;
    private int tag = 1; // 1 先行布局 2 瀑布流
    private int itemWidth ;
    private OnItemClickListener mOnItemClickListener = null;
     public FoundAdapter(Context context,List<DataBean> list){
         this.context = context;
         this.list=list;
         //当前屏幕 的宽度 除以2
         itemWidth = DeviceUtils.getDisplayInfomation(context).x / 2 ;

     }
//    public void setData(List<DataBean> bean, int page) {
//        if (list == null) {
//            list = new ArrayList<DataBean>();
//        }
//        if(page == 1){
//            list.clear();
//        }
//        list.addAll(bean);
//        notifyDataSetChanged();
//    }
    public void dataChange(int type) {
        this.tag = type;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.tab_frag1_layout, parent, false);
            MyshuipingHolder verticalViewHolder = new MyshuipingHolder(view);
            view.setOnClickListener(this);
            return verticalViewHolder;

        } else {
            View views = LayoutInflater.from(context).inflate(R.layout.tab_frag1_grade, parent, false);
            MyGradeHolder pinterestViewHolder = new MyGradeHolder(views);
            views.setOnClickListener(this);
            return pinterestViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyshuipingHolder) {

            //列表的形式展示
            MyshuipingHolder verticalViewHolder = (MyshuipingHolder) holder;
           verticalViewHolder.itemView.setTag(position);
            verticalViewHolder.f1_layout_name.setText(list.get(position).getNickname());


            verticalViewHolder.f1_layout_jieshao.setText(list.get(position).getIntroduce());
            verticalViewHolder.f1_layout_age.setText(list.get(position).getAge() + "岁 , ");
            verticalViewHolder.f1_layout_sex.setText(list.get(position).getGender());
            Glide.with(context).load(list.get(position).getImagePath()).error(R.mipmap.ic_launcher).into(verticalViewHolder.f1_layout_img);

           String lat = PreferencesUtils.getValueByKey(context, "lat", "0.0");
            String lng = PreferencesUtils.getValueByKey(context,"lng","0.0");
            double dlat = Double.valueOf(lat);
            double dlng = Double.valueOf(lng);
            double olat = list.get(position).getLat();
            double olng = list.get(position).getLng() ;


            if(!TextUtils.isEmpty(lat) && !TextUtils.isEmpty(lng) && olat != 0.0 && olng != 0.0){

                float v = (float) Distance.LantitudeLongitudeDist(dlng, dlat, olng, olat);


                  verticalViewHolder.plocation.setVisibility(View.VISIBLE);

                verticalViewHolder.f1_layout_location.setText(DisanceUtils.standedDistance(v));

            }
        } else {
            MyGradeHolder staggeredViewHolder = ( MyGradeHolder) holder;
                staggeredViewHolder.itemView.setTag(position);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) staggeredViewHolder.grade_img.getLayoutParams() ;

            float scale =  (float) itemWidth / (float) list.get(position).getPicWidth()  ;
            params.width = itemWidth;
            params.height = (int)( (float)scale * (float)list.get(position).getPicHeight()) ;

            System.out.println("params.scale = " + scale);
            System.out.println("params.width = " + params.width + " " + list.get(position).getPicWidth());
            System.out.println("params.height = " + params.height + "  " + list.get(position).getPicHeight());

            staggeredViewHolder.grade_img.setLayoutParams(params);
            String lat =  PreferencesUtils.getValueByKey(context, "lat","");
            String lng = PreferencesUtils.getValueByKey(context,"lng","");
            double dlat = Double.valueOf(lat);
            double dlng = Double.valueOf(lng);
            double olat = list.get(position).getLat();
            double olng = list.get(position).getLng() ;


            if(!TextUtils.isEmpty(lat) && !TextUtils.isEmpty(lng) && olat != 0.0 && olng != 0.0){

                float v = (float) Distance.LantitudeLongitudeDist(dlng, dlat, olng, olat);

                staggeredViewHolder.grade_text.setText(DisanceUtils.standedDistance(v));

            }

            Glide.with(context).load(list.get(position).getImagePath()).error(R.mipmap.ic_launcher).into(staggeredViewHolder.grade_img);

        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (tag == 1) {
            return 0;
        } else {
            return 1;
        }

    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    static  class MyshuipingHolder extends RecyclerView.ViewHolder {
         private TextView f1_layout_age,f1_layout_name,f1_layout_sex,f1_layout_jieshao,f1_layout_location;
         private ImageView f1_layout_img,plocation;
         //private
         public MyshuipingHolder(View itemView) {
             super(itemView);
             f1_layout_age= (TextView) itemView.findViewById(R.id.f1_layout_age);
             f1_layout_name= (TextView) itemView.findViewById(R.id.f1_layout_name);
             f1_layout_sex= (TextView) itemView.findViewById(R.id.f1_layout_sex);
             f1_layout_jieshao= (TextView) itemView.findViewById(R.id.f1_layout_jieshao);
             f1_layout_img= (ImageView) itemView.findViewById(R.id.f1_layout_img);
             f1_layout_location=(TextView) itemView.findViewById(R.id.f1_layout_location);
             plocation= (ImageView) itemView.findViewById(R.id.plocation);
         }
     }
    static class MyGradeHolder extends RecyclerView.ViewHolder {
        ImageView grade_img;
        TextView grade_text;
        public MyGradeHolder(View itemView) {

            super(itemView);
            grade_img= (ImageView) itemView.findViewById(R.id.grade_img);
            grade_text= (TextView) itemView.findViewById(R.id.grade_text);
        }
    }
     //创建监听接口
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
