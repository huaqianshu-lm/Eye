package com.example.dllo.eyepetzier.ui.adapter.rv;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dllo on 16/8/16.
 * 给recycleView画线
 */
public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        if (position > 1) {
            outRect.top = 6;
        }
        if (position % 2 != 0) {
            outRect.left = 6;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        c.drawColor(0x000000);
    }
}
