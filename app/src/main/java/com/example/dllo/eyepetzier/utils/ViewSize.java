package com.example.dllo.eyepetzier.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

/**
 * Created by dllo on 16/8/20.
 * 获取组件宽高
 */
public class ViewSize {

    private int width;
    private int height;

    public <T extends ViewGroup, K extends View> int getViewSize(Class<T> clazz, final K k, EScreenSizeDensity state) {

        T.LayoutParams layoutParams = (T.LayoutParams) k.getLayoutParams();
        final ViewTreeObserver vto = k.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                k.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                height = k.getHeight();
                width = k.getWidth();
            }
        });

        if (state == EScreenSizeDensity.HEIGHT)
            return  height;
        else if(state == EScreenSizeDensity.WIDTH)
            return width;

        return 0;
    }
}
