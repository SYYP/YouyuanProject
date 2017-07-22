package yipengyu.baway.com.youyuanproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.bean.UserInfoBean;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
      private List<UserInfoBean.DataBean.PhotolistBean> list;
    private Context context;
    private FoundAdapter.OnItemClickListener mOnItemClickListener = null;
    public DetailAdapter(List<UserInfoBean.DataBean.PhotolistBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View view= LayoutInflater.from(context).inflate(R.layout.drtail_item,parent,false);
          MyViewHolder myViewHolder=new MyViewHolder(view);
          view.setOnClickListener(this);
         return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1= (MyViewHolder) holder;
      //  GlideUtils.getInstance().havaRound(, holder1.detail_img,context);
        holder1.itemView.setTag(position);
        Glide.with(context).load(list.get(position).getImagePath()).into(holder1.detail_img);
        PreferencesUtils.addConfigInfo(context,"who",list.get(position).getUserId()+"");
        PreferencesUtils.addConfigInfo(context,"tarentou",list.get(position).getImagePath()+"");
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView detail_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            detail_img = (ImageView) itemView.findViewById(R.id.detail_img);
        }
    }
    //创建监听接口
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    public void setOnItemClickListener(FoundAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
