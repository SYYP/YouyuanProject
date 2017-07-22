package yipengyu.baway.com.youyuanproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.activtiy.DongtaiActivity;
import yipengyu.baway.com.youyuanproject.base.IFragment;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class DongtaiFragment extends IFragment implements View.OnClickListener {

    private TextView frag_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab_frag3,container,false);
         ininview(view);
        return view;
    }

    private void ininview(View view) {
        frag_text = (TextView) view.findViewById(R.id.frag_text);
        frag_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.frag_text:
                 Intent it=new Intent(getActivity(), DongtaiActivity.class);
                 startActivity(it);
                 break;
         }

    }
}
