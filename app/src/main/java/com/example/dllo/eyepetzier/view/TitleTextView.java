package com.example.dllo.eyepetzier.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by dllo on 16/8/12.
 */
public class TitleTextView extends TextView {
    public TitleTextView(Context context) {
        super(context);
        init(context);
    }

    public TitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lobster-1.4.otf");
        this.setTypeface(tf);
    }
}
