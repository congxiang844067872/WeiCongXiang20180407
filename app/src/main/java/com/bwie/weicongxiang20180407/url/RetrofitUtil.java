package com.bwie.weicongxiang20180407.url;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2018/4/7.
 */

public class RetrofitUtil {
    private static RetrofitUtil retrofitUtil=null;
    private Retrofit retrofit;

    private RetrofitUtil(){

    }
    private RetrofitUtil(String baseurl){
        OkHttpClient build = new OkHttpClient.Builder()
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(build)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtil getintFice(String baseurl){
        if (retrofitUtil==null){
            synchronized (RetrofitUtil.class){
                if (retrofitUtil==null){
                    retrofitUtil=new RetrofitUtil(baseurl);
                }
            }
        }
        return retrofitUtil;
    }

    public <T> T getCreatService(Class<T> service){

        return retrofit.create(service);
    }
    public static ApiService setService(){

        return RetrofitUtil.getintFice(ApiUrl.gongtong).getCreatService(ApiService.class);
    }
}
