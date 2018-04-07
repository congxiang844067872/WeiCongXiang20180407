package com.bwie.weicongxiang20180407.model;

import com.bwie.weicongxiang20180407.presenter.Presenter;
import com.bwie.weicongxiang20180407.presenter.PresenterPort;
import com.bwie.weicongxiang20180407.url.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by admin on 2018/4/7.
 */

public class Model {
    private PresenterPort presenterPort;
    private Disposable d;
    public Model(PresenterPort presenterPort) {

        this.presenterPort = presenterPort;
    }

    public void getUrl(Observable<ResponseBody> retrofitUtilObservable) {
        retrofitUtilObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                        Model.this.d = d;
                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        presenterPort.getResponseBodyBean(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void setJieYue() {
        d.dispose();
    }
}
