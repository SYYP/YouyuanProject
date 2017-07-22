package yipengyu.baway.com.youyuanproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMMessage;

import java.util.List;

import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.base.BaseMvpActivity;
import yipengyu.baway.com.youyuanproject.biaoqing.util.EaseSmileUtils;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class ChatAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<EMMessage> list;
    Context context;

    public ChatAdapter(List<EMMessage> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerView.ViewHolder holder = null;
         switch (viewType){
             case 0:
                 View view=inflater.inflate(R.layout.message_one,parent,false);
                 holder=new MyLetfViewholder(view);
                 break;
             case 1:
                 //自己发送的文本
                 View view1=inflater.inflate(R.layout.message_two,parent,false);
                 holder=new MyRightViewHolder(view1);
                 break;
         }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case 0:
               MyLetfViewholder holder1= (MyLetfViewholder) holder;
                Glide.with(context).load(PreferencesUtils.getValueByKey(context,"zhaopian","")).into( holder1.mesage_pho) ;
                String ss= list.get(position).getBody().toString();
                String substring1 = ss.substring(5, ss.length() - 1);
             //   holder1.mesagge_count.setText(substring1);
                holder1.mesagge_count.setText(EaseSmileUtils.getSmiledText(context,substring1));
                System.out.print("asdsss------------"+list.get(position).getBody());
                break;
            case 1:
                //自己发送的文本
                MyRightViewHolder holder2= (MyRightViewHolder) holder;
                String s = list.get(position).getBody().toString();
                String substring = s.substring(5, s.length() - 1);
                holder2.mesagge_count2.setText(EaseSmileUtils.getSmiledText(context,substring));
                //  holder2.mesagge_count2.setText(substring);
                System.out.print("asd------------"+list.get(position).getBody());
                Glide.with(context).load(PreferencesUtils.getValueByKey(context,"photo","")).error(R.drawable.a).into(holder2.mesage_pho2) ;
                break;
        }



    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType() == EMMessage.Type.TXT) {
            if (list.get(position).direct() == EMMessage.Direct.RECEIVE) {
                return 0;
            } else {
                return 1;
            }
        }


        return  -1 ;
    }

      class MyLetfViewholder extends RecyclerView.ViewHolder {
          TextView mesagge_count;
          ImageView mesage_pho;
          public MyLetfViewholder(View itemView) {
              super(itemView);
              mesagge_count= (TextView) itemView.findViewById(R.id.mesage_count);
              mesage_pho= (ImageView) itemView.findViewById(R.id.mesage_pho);
          }
      }
      class MyRightViewHolder extends RecyclerView.ViewHolder {
          TextView mesagge_count2;
          ImageView mesage_pho2;
          public MyRightViewHolder(View itemView) {
              super(itemView);
              mesagge_count2= (TextView) itemView.findViewById(R.id.message2_count);
             mesage_pho2= (ImageView) itemView.findViewById(R.id.mesage2_pho);
          }
      }
}
