package com.example.shopping4.app;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.TimeUnit;
import com.zhy.http.okhttp.OkHttpUtils;



import okhttp3.OkHttpClient;

/**
 * created by: xy
 * on: 2023/6/4
 * description:代表整个软件
 */
public class MyApplication extends Application {
    public static Context getContext() {
        return mContext;
    }
    //上下文的源头
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        /**
         * 初始化OkhttpUtils
         */
        initOkhttpClient();
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
