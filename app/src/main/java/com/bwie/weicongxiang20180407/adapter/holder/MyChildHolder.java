package com.bwie.weicongxiang20180407.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.weicongxiang20180407.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by admin on 2018/4/7.
 */

public class MyChildHolder extends RecyclerView.ViewHolder {

    public CheckBox che_child;
    public SimpleDraweeView sdv_1;
    public TextView text_title;
    public TextView text_time;
    public TextView text_jian;
    public TextView text_num;
    public TextView text_jia;

    public MyChildHolder(View itemView) {
        super(itemView);
        che_child = itemView.findViewById(R.id.che_child);
        sdv_1 = itemView.findViewById(R.id.sdv_1);
        text_title = itemView.findViewById(R.id.text_title);
        text_time = itemView.findViewById(R.id.text_time);
        text_jian = itemView.findViewById(R.id.text_jian);
        text_num = itemView.findViewById(R.id.text_num);
        text_jia = itemView.findViewById(R.id.text_jia);
    }
}
