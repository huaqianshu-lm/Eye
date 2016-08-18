package com.example.dllo.eyepetzier.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by dllo on 16/8/17.
 * 这个自定义imageview旨在处理oom的情况
 */
public class OomImageView extends ImageView{

    public OomImageView(Context context) {
        super(context);
    }

    public OomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {

        super.onDetachedFromWindow();

        setImageDrawable(null);
    }
}
