package com.example.dllo.eyepetzier.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by dllo on 16/8/20.
 * 为appBarlayout设置的自定义行为,效果为跟随滑动
 */
public class FollowScrollBehavior extends CoordinatorLayout.Behavior<View>{

    public FollowScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        int offset = dependency.getBottom() - child.getTop();
        child.offsetTopAndBottom(offset);

        return true;
    }
}
