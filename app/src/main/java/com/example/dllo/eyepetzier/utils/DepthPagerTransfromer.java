package com.example.dllo.eyepetzier.utils;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.eyepetzier.R;

/**
 * Created by dllo on 16/8/20.
 */
public class DepthPagerTransfromer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();
        ImageView iv = (ImageView) page.findViewById(R.id.item_video_introduce_vp_bg_iv);
        if (position < -1) {
            page.setAlpha(1);
        } else if (position <= 0) {
//            page.setAlpha(1);
//            page.setTranslationX(1);
            iv.setTranslationX(-position * (pageWidth / 4 * 3));
        } else if (position <= 1) {
            page.setTranslationX(pageWidth * -position / 4 * 3);
        } else {
            page.setAlpha(1);
        }
    }
}
