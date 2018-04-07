package com.bwie.weicongxiang20180407.presenter;

import okhttp3.ResponseBody;

/**
 * Created by admin on 2018/4/7.
 */

public interface PresenterPort {
    void getResponseBodyBean(ResponseBody responseBody);
    void getError(Throwable throwable);
}
