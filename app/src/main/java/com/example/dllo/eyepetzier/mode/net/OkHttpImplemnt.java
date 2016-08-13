package com.example.dllo.eyepetzier.mode.net;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dllo on 16/8/12.
 * OkHttp网络请求实现类
 */
public class OkHttpImplemnt implements INetwork {

    // variables
    private OkHttpClient mClient;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson mGson;

    // constructor
    OkHttpImplemnt() {
//        mGson = new Gson();
        File cacheDir = Environment.getExternalStorageDirectory();
        mClient = new OkHttpClient.Builder()
                .cache(new Cache(cacheDir, 10 * 1024 * 1024))
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    // 只请求,不解析
    @Override
    public void startRequest(String url, final IOnHttpCallback<String> onHttpCallback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onHttpCallback.onError(e); // 失败了回调一个异常
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String resultStr = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onHttpCallback.onSuccess(resultStr);
                    }
                });
            }
        });
    }

    // 既请求,也解析
    @Override
    public <T> void startRequest(String url, final Class<T> clazz, final IOnHttpCallback<T> onHttpCallback) {
            Request request = new Request.Builder()
                    .url(url).build();
            mClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onHttpCallback.onError(e); // 失败了回调一个异常
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String resultStr = response.body().string();
                    // 解析
//                    final T resultEntity = mGson.fromJson(resultStr, clazz);
                    final T resultEntity = JSON.parseObject(resultStr, clazz);;
                    // 给到主线程
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onHttpCallback.onSuccess(resultEntity);
                        }
                    });
                }
            });
    }
}
