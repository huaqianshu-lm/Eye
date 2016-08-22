package com.example.dllo.eyepetzier.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by admin on 2016/8/16.
 */
public class FlowLayout extends FrameLayout {

    private FlowLayout.onItemClickListener onItemClickListener;
    private int x;// 点击时的x值
    private int y;// 点击时的y值
    private int i;// 子布局的位置

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.measure(MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST),
                    MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
        }
        setMeasuredDimension(width, getMeasureHeight());// 设置了自己的大小

    }

    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int result = 0;
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 0;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.max(result, size);
            }
        }
        return result;

    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int result = 0;
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 0;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.max(result, size);
            }
        }
        return result;

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        layoutFlow();
    }

    private int getMeasureHeight() {
        layoutFlow();
        return getChildAt(getChildCount() - 1).getBottom();

    }

    private void layoutFlow() {
        int childCount = getChildCount();
        int left = getPaddingLeft() + 20;
        int top = getPaddingTop();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int measuredWidth = child.getMeasuredWidth();
            if (i == 0) {
                child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());
                left = child.getMeasuredWidth();
            } else {
                View childAt = getChildAt(i - 1);
                int totalWidth = childAt.getRight() + child.getMeasuredWidth() + getPaddingRight() + 20;
                if (totalWidth > getMeasuredWidth()) {
                    top = childAt.getBottom();
                    left = getPaddingLeft();
                } else {
                    left = childAt.getRight();
                }
                left += 20;
                child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());
            }
        }
    }

    /**
     * 每一个item的点击事件的接口
     */
    public interface onItemClickListener {
        void onItemClick(int position, View childAt);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        onItemClickListener = listener;
    }

    /**
     * 实现点击
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();

                i = pointToPosition(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                int position = pointToPosition(event.getX(), event.getY());
                if (i == position)
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position, getChildAt(position));
                    }
                break;

        }
        return true;
    }

    /**
     * 确定item的位置
     * @param x
     * @param y
     * @return
     */
    private int pointToPosition(float x, float y) {
        int top = 0;
        int left = 0;
        int bottom = 0;
        int right = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            top = childAt.getTop();
            left = childAt.getLeft();
            right = childAt.getRight();
            bottom = childAt.getBottom();
            if (y >= top && y <= bottom && x >= left && x <= right) {
                return i;
            }
        }
        return -1;
    }
}
