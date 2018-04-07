package com.bwie.weicongxiang20180407.presenter;

import com.bwie.weicongxiang20180407.activity.GouWuCheActivity;
import com.bwie.weicongxiang20180407.model.Model;
import com.bwie.weicongxiang20180407.url.RetrofitUtil;
import com.bwie.weicongxiang20180407.view.MyView;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by admin on 2018/4/7.
 */

public class Presenter extends BasePresenter implements PresenterPort{
    private MyView myView;
    private final Model model;

    public Presenter(MyView myView) {
        model = new Model(this);
        this.myView = myView;
    }

    @Override
    public void getResponseBodyBean(ResponseBody responseBody) {
        myView.getResponseBodyBean(responseBody);
    }

    @Override
    public void getError(Throwable throwable) {
        myView.getError(throwable);
    }

    public void getUrl(Observable<ResponseBody> retrofitUtilObservable) {
        model.getUrl(retrofitUtilObservable);
    }

    public void setJieYue() {
        model.setJieYue();
    }
}
