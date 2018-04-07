package com.bwie.weicongxiang20180407.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.weicongxiang20180407.MainActivity;
import com.bwie.weicongxiang20180407.R;
import com.bwie.weicongxiang20180407.adapter.MyAdapter;
import com.bwie.weicongxiang20180407.bean.GouWuCheBean;
import com.bwie.weicongxiang20180407.bean.MyHeJiBean;
import com.bwie.weicongxiang20180407.presenter.Presenter;
import com.bwie.weicongxiang20180407.url.ApiUrl;
import com.bwie.weicongxiang20180407.url.RetrofitUtil;
import com.bwie.weicongxiang20180407.view.MyView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

public class GouWuCheActivity extends AppCompatActivity implements MyView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.che_all)
    CheckBox cheAll;
    @BindView(R.id.text_all)
    TextView textAll;
    @BindView(R.id.text_jiesuan)
    TextView textJiesuan;
    private Map<String, String> map;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                MyHeJiBean myHeJiBean= (MyHeJiBean) msg.obj;
                textAll.setText("合计:¥"+myHeJiBean.getPrice());
                textJiesuan.setText("去结算("+myHeJiBean.getNum()+")");
            }

        }
    };
    private Presenter presenter;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_wu_che);
        ButterKnife.bind(this);

        presenter = new Presenter(this);

        map = new HashMap<>();
        map.put("uid", "4427");

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.getUrl(RetrofitUtil.setService().doGet(ApiUrl.select, map));
    }


    @Override
    public void getResponseBodyBean(ResponseBody responseBody) {
        try {
            //Log.d("+++++++++", responseBody.string());
            GouWuCheBean gouWuCheBean = new Gson().fromJson(responseBody
                    .string(), GouWuCheBean.class);

            List<GouWuCheBean.DataBean> list = gouWuCheBean.getData();
            //二级列表全部选中一级列表选中
            for (int i=0;i<list.size();i++){
                if (ischecked(i,list)){
                    list.get(i).setGridchebox(true);
                }
            }
            cheAll.setChecked(isChild(list));

            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager
                    .VERTICAL, false));
            adapter = new MyAdapter(this, gouWuCheBean, map, handler, presenter);
            recyclerView.setAdapter(adapter);
            //adapter.setNumSumAll();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getError(Throwable throwable) {

    }
    private boolean isChild(List<GouWuCheBean.DataBean> list) {
        for (int i=0;i<list.size();i++){
            if (!list.get(i).isGridchebox()){
                return false;
            }
        }
        return true;
    }

    private boolean ischecked(int i, List<GouWuCheBean.DataBean> list) {
        for (int j=0;j<list.get(i).getList().size();j++){
            if (list.get(i).getList().get(j).getSelected()==0){
                return false;
            }
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.setJieYue();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @OnClick({R.id.che_all, R.id.text_jiesuan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.che_all:
                if (adapter!=null){
                    adapter.allChed(cheAll.isChecked());
                }
                break;
            case R.id.text_jiesuan:
                Intent intent = new Intent(GouWuCheActivity
                        .this, DiTuActivity.class);
                startActivity(intent);
                break;
        }
    }
}
