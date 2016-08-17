package com.example.dllo.eyepetzier.ui.adapter.rv.tools;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by dllo on 16/8/13.
 * 普通的recyclerView的adapter
 */
public abstract class CommonRvAdapter<T> extends AbsRvAdapter<T> {
    protected Context context;
    protected int layoutId;
    protected List<T> datas;
    protected LayoutInflater inflater;

    public CommonRvAdapter(Context context, List<T> datas, final int layoutId) {
        super(context,datas);
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.datas = datas;
        this.layoutId = layoutId;

        addItemViewDelegate(new IItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int pos) {
                return true;
            }

            @Override
            public void convert(RvViewHolder holder, T t, int pos) {
                CommonRvAdapter.this.convert(holder,t,pos);
            }
        });
    }

    /**
     * 设置行布局的抽象方法,只要实现了该类就会复写这个方法,在这个方法里设置行布局的各种数据
     * @param holder
     * @param t 数据
     * @param pos
     */
    protected abstract void convert(RvViewHolder holder, T t , int pos);
}
