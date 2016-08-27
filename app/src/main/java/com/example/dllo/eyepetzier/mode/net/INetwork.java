package com.example.dllo.eyepetzier.mode.net;

/**
 * 外部接口 ( 二次封装用 )
 * 适合多框架类使用的网络请求接口
 */
public interface INetwork {
    void startRequest(String url, IOnHttpCallback<String> onHttpCallback); // 只请求,不解析
    <T> void startRequest(String url, Class<T> clazz, IOnHttpCallback<T> onHttpCallback); // 既请求,也解析

}