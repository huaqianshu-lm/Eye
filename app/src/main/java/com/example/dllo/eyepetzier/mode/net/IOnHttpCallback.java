package com.example.dllo.eyepetzier.mode.net;

/**
 * 外部接口 ( 一次封装用 )
 * 定义的网络请求接口
 * @param <T>
 */
public interface IOnHttpCallback<T> {
    void onSuccess(T response); //请求成功,返回请求成功的结果
    void onError(Throwable ex);//请求失败,传一个异常的父类
}