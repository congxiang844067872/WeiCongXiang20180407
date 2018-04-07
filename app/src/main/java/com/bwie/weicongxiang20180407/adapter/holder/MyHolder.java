package com.bwie.weicongxiang20180407.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.weicongxiang20180407.R;

/**
 * Created by admin on 2018/4/7.
 */

public class MyHolder extends RecyclerView.ViewHolder {

    public CheckBox che_grid;
    public TextView text_shangjia;
    public RecyclerView recycler_child;

    public MyHolder(View itemView) {
        super(itemView);
        che_grid = itemView.findViewById(R.id.che_grid);
        text_shangjia = itemView.findViewById(R.id.text_shangjia);
        recycler_child = itemView.findViewById(R.id.recycler_child);
    }
}
