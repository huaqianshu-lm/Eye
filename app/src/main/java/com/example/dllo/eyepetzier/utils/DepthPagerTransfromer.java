package com.example.dllo.eyepetzier.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by dllo on 16/8/20.
 */
public class DepthPagerTransfromer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();
        if (position < -1){
            page.setAlpha(0);
        }else if (position <= 0){
            page.setAlpha(1);
            page.setTranslationX(1);
        }else if (position <= 1){
            page.setTranslationX(pageWidth * -position);
        }else {
            page.setAlpha(0);
        }
    }
}
