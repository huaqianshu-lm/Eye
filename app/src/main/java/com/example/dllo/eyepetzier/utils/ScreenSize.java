package com.example.dllo.eyepetzier.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by dllo on 16/7/25.
 * 屏幕适配类
 * 获取屏幕尺寸(宽 * 高)的工具类
 */
public class ScreenSize {
    public static int getScreenSize(Context context, EScreenSizeDensity state) {

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);

        if (state == EScreenSizeDensity.HEIGHT)
            return  metrics.heightPixels;
        else if(state == EScreenSizeDensity.WIDTH)
            return metrics.widthPixels;

        return 0;
    }
}
