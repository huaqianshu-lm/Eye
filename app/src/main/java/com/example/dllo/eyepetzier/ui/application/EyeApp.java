package com.example.dllo.eyepetzier.ui.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/8/12.
 * 整个应用的application 在这里可以获取全局的context
 */
public class EyeApp extends Application{
    protected static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    /**
     * 获得context的方法
     * @return
     */
    public static Context getContext(){
        return context;
    }
}
