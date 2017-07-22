package yipengyu.baway.com.youyuanproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.bean.FriendListDataBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Liedapter extends BaseAdapter {
      private Context context;
    private List<FriendListDataBean>  listDataBeen;
       private  OnItemClick onItemClick;
    public Liedapter(Context context, List<FriendListDataBean> listDataBeen) {
        this.context = context;
        this.listDataBeen = listDataBeen;
    }

    @Override
    public int getCount() {
        return listDataBeen!=null?listDataBeen.size():0;
    }

    @Override
    public Object getItem(int position) {
        return listDataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
             holder=new ViewHolder();

            convertView= LayoutInflater.from(context).inflate(R.layout.list_adapter,null);
            //获得资源ID
            holder.lie_jieshao= (TextView) convertView.findViewById(R.id.list_item_jieao);
            holder.lie_age= (TextView) convertView.findViewById(R.id.list_item_age);
            holder.lie_name= (TextView) convertView.findViewById(R.id.list_item_name);
            holder.lie_sex= (TextView) convertView.findViewById(R.id.list_item_sex);
             holder.lie_img= (ImageView) convertView.findViewById(R.id.list_item_img);
              holder.lie_name.setText(listDataBeen.get(position).getIntroduce());
             holder.lie_sex.setText(listDataBeen.get(position).getGender());
             holder.lie_jieshao.setText(listDataBeen.get(position).getNickname());
            Glide.with(context).load(listDataBeen.get(position).getImagePath()).into(holder.lie_img);
        }
        return convertView;
    }

    class ViewHolder{
        ImageView lie_img;
        TextView lie_name,lie_jieshao,lie_age,lie_sex;
    }
    //设置监听 创建接口
    public interface OnItemClick{
        void getItemOnclick(View view,int position);
    }
    public void setOnItemClick(OnItemClick onItem){
        this.onItemClick=onItem;
    }

}
