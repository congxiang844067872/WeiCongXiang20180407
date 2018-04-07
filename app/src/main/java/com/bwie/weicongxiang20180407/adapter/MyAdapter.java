package com.bwie.weicongxiang20180407.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.weicongxiang20180407.R;
import com.bwie.weicongxiang20180407.activity.GouWuCheActivity;
import com.bwie.weicongxiang20180407.adapter.holder.MyHolder;
import com.bwie.weicongxiang20180407.bean.GouWuCheBean;
import com.bwie.weicongxiang20180407.presenter.Presenter;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Created by admin on 2018/4/7.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    private final Context context;
    private final GouWuCheBean gouWuCheBean;
    private final Map<String, String> map;
    private final Handler handler;
    private final Presenter presenter;
    private int conindex;
    private MyChildAdapter adapter;
    private List<GouWuCheBean.DataBean> data;

    public MyAdapter(Context context, GouWuCheBean gouWuCheBean, Map<String, String> map, Handler handler, Presenter presenter) {

        this.context = context;
        this.gouWuCheBean = gouWuCheBean;
        this.map = map;
        this.handler = handler;
        this.presenter = presenter;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        data = gouWuCheBean.getData();
        holder.che_grid.setChecked(data.get(position).isGridchebox());
        holder.text_shangjia.setText(data.get(position).getSellerName());

        holder.recycler_child.setLayoutManager(new LinearLayoutManager(context
                ,LinearLayoutManager.VERTICAL,false));
        adapter = new MyChildAdapter(context
                , gouWuCheBean, handler, map, presenter,position);
        holder.recycler_child.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return gouWuCheBean.getData().size();
    }

    public void allChed(boolean checked) {
        adapter.allChed(checked);
    }
}
