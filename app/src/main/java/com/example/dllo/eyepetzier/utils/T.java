package com.example.dllo.eyepetzier.utils;

import android.widget.Toast;

import com.example.dllo.eyepetzier.ui.application.EyeApp;

/**
 * Created by dllo on 16/8/12.
 * Toast的工具类,用final修饰不能被继承
 */
public final class T {
    private static boolean isDebug = false;// 是否为调式模式

    /**
     *私有的构造方法
     */
    private T (){

    }

    /**
     * 短时间的toast
     * @param msg
     */
    public static void shortMsg(String msg){
        if (isDebug){
            Toast.makeText(EyeApp.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 长时间的toast
     * @param msg
     */
    public static void longMsg (String msg){
        if (isDebug){
            Toast.makeText(EyeApp.getContext(), msg, Toast.LENGTH_LONG).show();
        }
    }

}
