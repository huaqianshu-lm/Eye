package com.example.dllo.eyepetzier.utils;

import android.util.Log;

/**
 * Created by dllo on 16/8/12.
 * Log的工具类,用final修饰不能被继承
 */
public final class L {
    private static boolean isDebug = false; // 是否为调式模式
    private static String TAG = "EYEPETZIER"; // 默认标签
    /**
     * 私有化的构造方法
     */
    private L(){

    }

    /**
     * 使用默认标签的log
     * @param msg
     */
    public static void d(String msg){
        if (isDebug){
            Log.d(TAG, msg);
        }
    }

    /**
     *使用自定义的标签的log
     * @param tag
     * @param msg
     */
    public static void d(String tag,String msg){
        if (isDebug){
            Log.d(tag, msg);
        }
    }

}
