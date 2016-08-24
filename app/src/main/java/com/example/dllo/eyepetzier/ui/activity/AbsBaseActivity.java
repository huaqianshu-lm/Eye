package com.example.dllo.eyepetzier.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by dllo on 16/8/12.
 * Activity的基类 ,有绑定布局,初始化组件,简化findViewById,intent跳转(带值和不带值)的方法
 *
 */
public abstract class AbsBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //设置全屏显示

//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        initData();
    }

    /**
     * 绑定布局的方法
     *
     * @return
     */
    protected abstract int setLayout();

    /**
     * 初始化组件的方法
     */
    protected abstract void initView();

    /**
     * 逻辑代码,处理数据的方法
     */
    protected abstract void initData();

    /**
     * 简化findViewById的方法
     * @param resId
     * @param <T>
     * @return
     */
    protected  <T extends View> T bindView(int resId){
        return (T) findViewById(resId);
    }

    /**
     * intent跳转
     * @param from 当前界面
     * @param to 目标界面
     */
    protected void goTo (Context from,Class<? extends AbsBaseActivity> to){
        Intent intent = new Intent(from,to);
        startActivity(intent);
    }

    /**
     * intent带值跳转
     * @param from
     * @param to
     * @param values
     */
    protected void goTo(Context from, Class<? extends AbsBaseActivity> to, Bundle values){
        Intent intent = new Intent(from,to);
        intent.putExtras(values);
        startActivity(intent);
    }
}
