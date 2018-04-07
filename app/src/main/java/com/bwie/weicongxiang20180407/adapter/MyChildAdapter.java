package com.bwie.weicongxiang20180407.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.weicongxiang20180407.R;
import com.bwie.weicongxiang20180407.adapter.holder.MyChildHolder;
import com.bwie.weicongxiang20180407.bean.GouWuCheBean;
import com.bwie.weicongxiang20180407.bean.MyHeJiBean;
import com.bwie.weicongxiang20180407.presenter.Presenter;
import com.bwie.weicongxiang20180407.url.ApiUrl;
import com.bwie.weicongxiang20180407.url.RetrofitUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by admin on 2018/4/7.
 */

public class MyChildAdapter extends RecyclerView.Adapter<MyChildHolder> {
    private final Context context;
    private final GouWuCheBean gouWuCheBean;
    private final Handler handler;
    private final Map<String, String> map;
    private final Presenter presenter;
    private int i;
    private int conAllCheck;

    public MyChildAdapter(Context context, GouWuCheBean gouWuCheBean, Handler handler, Map<String, String> map, Presenter presenter, int i) {

        this.context = context;
        this.gouWuCheBean = gouWuCheBean;
        this.handler = handler;
        this.map = map;
        this.presenter = presenter;
        this.i = i;
    }

    @Override
    public MyChildHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_child, parent, false);
        MyChildHolder holder = new MyChildHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyChildHolder holder, int position) {
        GouWuCheBean.DataBean.ListBean listBean = gouWuCheBean.getData()
                .get(i).getList().get(position);
        //holder.che_child.setChecked(listBean.getSelected()==0?true:false);
        String[] split = listBean.getImages().split("\\|");
        holder.sdv_1.setImageURI(split[0]);
        holder.text_title.setText(listBean.getTitle());
        holder.text_time.setText("¥:"+listBean.getBargainPrice());
        holder.text_num.setText(listBean.getNum()+"");
        holder.text_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        setPriceNum();

    }
    public void setPriceNum() {
        double price=0;
        int num=0;
        for (int i=0;i<gouWuCheBean.getData().size();i++){
            List<GouWuCheBean.DataBean.ListBean> listBeans = gouWuCheBean
                    .getData().get(i).getList();
            for (int j=0;j<listBeans.size();j++){
                if (listBeans.get(j).getSelected()==1){
                    price+=listBeans.get(j).getBargainPrice()*listBeans
                            .get(j).getNum();
                    num+=listBeans.get(j).getNum();
                }
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String format = decimalFormat.format(price);

        MyHeJiBean myHeJiBean = new MyHeJiBean(format, num);
        Message msg= Message.obtain();
        msg.obj=myHeJiBean;
        msg.what=0;
        handler.sendMessage(msg);
    }
    @Override
    public int getItemCount() {
        return gouWuCheBean.getData().get(i).getList().size();
    }

    public void allChed(boolean checked) {
        ArrayList<GouWuCheBean.DataBean.ListBean> listBeans = new ArrayList<>();
        List<GouWuCheBean.DataBean> data = gouWuCheBean.getData();
        for (int i=0;i<data.size();i++){
            List<GouWuCheBean.DataBean.ListBean> list = data.get(i).getList();
            for (int j=0;j<list.size();j++){
                listBeans.add(list.get(j));
            }
        }
        conAllCheck = 0;
        conAllChecked(listBeans,checked);

    }

    private void conAllChecked(final ArrayList<GouWuCheBean.DataBean.ListBean> listBeans
            , final boolean checked) {
        final GouWuCheBean.DataBean.ListBean listBean = listBeans.get(conAllCheck);

        Map<String, String> params = new HashMap<>();
        params.put("uid", "4427");
        params.put("sellerid", String.valueOf(listBean.getSellerid()));
        params.put("pid", String.valueOf(listBean.getPid()));
        params.put("num", String.valueOf(listBean.getNum()));
        params.put("selected", String.valueOf(checked ? 1 : 0));
        RetrofitUtil.setService().doGet(ApiUrl.gengxin,params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody value) {

                        conAllCheck++;
                        if (conAllCheck<listBeans.size()){
                            conAllChecked(listBeans,checked);
                        }else {
                            presenter.getUrl(RetrofitUtil.setService()
                                    .doGet(ApiUrl.select,map));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
