package com.example.dllo.eyepetzier.ui.adapter.rv.tools;

import android.support.v4.util.SparseArrayCompat;

/**
 * Created by dllo on 16/8/13.
 * 多种行布局的管理类
 */
public class ItemViewDelegateManager<T> {

    SparseArrayCompat<IItemViewDelegate<T>> delegates = new SparseArrayCompat<>();

    public int getItemViewDelegateCount(){
        return delegates.size();
    }

    /**
     * 添加到最后一条
     * @param delegate
     * @return
     */
    public ItemViewDelegateManager<T> addDelegate(IItemViewDelegate<T> delegate){
        int viewType = delegates.size();
        if (delegate != null){
            delegates.put(viewType,delegate);
            viewType ++;
        }
        return this;
    }

    /**
     * 添加到指定的位置
     * @param viewType
     * @param delegate
     * @return
     */
    public ItemViewDelegateManager<T> addDelegate(int viewType, IItemViewDelegate<T> delegate){
        if (delegates.get(viewType) != null){
            throw new IllegalArgumentException("An ItemViewDelegate is already registered for the viewType ="
            + viewType +". Already registered ItemViewDelegate is" + delegates.get(viewType));

        }
        delegates.put(viewType,delegate);
        return this;
    }

    /**
     * 按value删除
     * @param delegate
     * @return
     */
    public ItemViewDelegateManager<T> removeDelegate(IItemViewDelegate<T> delegate){
        if (delegate == null){
            throw new NullPointerException("ItemViewDelegate is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);
        if (indexToRemove >= 0){
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    /**
     * 按key值删除
     * @param itemType
     * @return
     */
    public ItemViewDelegateManager<T> removeDelegate(int itemType){
        int indexToRemove = delegates.indexOfKey(itemType);
        if (indexToRemove >= 0){
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    /**
     * 获取行布局的类型
     * @param item
     * @param pos
     * @return
     */
    public int  getItemViewType(T item,int pos){
        int delegatesCount = delegates.size();
        for (int i = delegatesCount -1 ;i >= 0;i--){
            IItemViewDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item,pos)){
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException("No ItemViewDelegate added that matches position =" +
        pos + "in data source");

    }

    /**
     *把行布局的信息传出去
     * @param holder
     * @param item
     * @param pos
     */
    public void convert(RvViewHolder holder, T item, int pos){
        for (int i = 0; i < delegates.size(); i++) {
            IItemViewDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item,pos)){
                delegate.convert(holder,item,pos);
                return;
            }
        }
        throw  new IllegalArgumentException(" No ItemViewDelegateManager added that matches position=" + pos + "in data source");
    }


    public IItemViewDelegate getItemViewDelegate(int viewType){
        return delegates.get(viewType);
    }

    public int getItemViewLayoutId(int viewType){
        return getItemViewDelegate(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(IItemViewDelegate IItemViewDelegate){
        return delegates.indexOfValue(IItemViewDelegate);
    }
}
