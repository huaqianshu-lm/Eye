package com.example.dllo.eyepetzier.ui.adapter.rv.tools;

/**
 * Created by dllo on 16/8/13.
 * 设置多种行布局的接口
 */
public interface IItemViewDelegate<T> {
    /**
     * 获取行布局的id
     * @return
     */
    int getItemViewLayoutId();

    /**
     * 是否为行布局
     * @param item
     * @param pos
     * @return
     */
    boolean isForViewType(T item, int pos);

    /**
     * 在该方法中为行布局的组件设置数据
     * @param holder
     * @param t
     * @param pos
     */
    void convert(RvViewHolder holder, T t, int pos);



}
