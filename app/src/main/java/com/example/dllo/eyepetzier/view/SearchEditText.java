package com.example.dllo.eyepetzier.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by dllo on 16/8/19.
 */
public class SearchEditText extends EditText{
    public SearchEditText(Context context) {
        super(context);
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable [] drawables = getCompoundDrawables();
        if (drawables != null){
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null){
                float textWidth = getPaint().measureText(getText().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth;
                drawableWidth = drawableLeft.getIntrinsicWidth();
                float bodyWidth = textWidth + drawablePadding + drawableWidth;
                canvas.translate((getWidth() - bodyWidth)/4,0);
            }
        }
        super.onDraw(canvas);
    }

}
