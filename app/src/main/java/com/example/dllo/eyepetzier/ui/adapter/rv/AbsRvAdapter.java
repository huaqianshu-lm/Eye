package com.example.dllo.eyepetzier.ui.adapter.rv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dllo on 16/8/12.
 * 多种行布局的adapter
 */
public class AbsRvAdapter<T> extends RecyclerView.Adapter<RvViewHolder> {
    protected Context context;
    protected List<T> datas;

    protected ItemViewDelegateManager itemViewDelegateManager;
    protected OnItemClickListener onItemClickListener;

    public AbsRvAdapter(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
        itemViewDelegateManager = new ItemViewDelegateManager();
    }

    public AbsRvAdapter(Context context) {
        this.context = context;
        itemViewDelegateManager = new ItemViewDelegateManager();
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        // 如果没有多种类型的行布局的话就返回默认的值
        if (!useItemViewDelegateManager()) {
            return super.getItemViewType(position);
        }
        // 否则就返回行布局的类型的值,值为int类型
        return itemViewDelegateManager.getItemViewType(datas.get(position),position);
    }


    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IItemViewDelegate IItemViewDelegate = itemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = IItemViewDelegate.getItemViewLayoutId();
        RvViewHolder holder = RvViewHolder.createViewHolder(context,parent,layoutId);
        onViewHolderCreated(holder,holder.getConvertView());
        setListener(parent,holder,viewType);
        return holder;
    }

    public void onViewHolderCreated(RvViewHolder holder, View itemView){

    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
        convert(holder,datas.get(position));
    }

    protected boolean isEnable(int viewType){
        return true;
    }

    /**
     * 设置行布局的点击事件(包括长按点击事件)
     * @param parent
     * @param rvViewHolder
     * @param viewType
     */
    protected void setListener(ViewGroup parent, final RvViewHolder rvViewHolder, final int viewType){
        if (!isEnable(viewType)){
            return;
        }
        rvViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    int pos = rvViewHolder.getAdapterPosition();
                    onItemClickListener.onItemClick(v, rvViewHolder,pos);
                }
            }
        });
        rvViewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null){
                    int pos = rvViewHolder.getAdapterPosition();
                    onItemClickListener.onItemLongClick(v, rvViewHolder,pos);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public List<T> getDatas(){
        return datas;
    }

    /**
     * 添加设置行布局的接口
     * @param IItemViewDelegate
     * @return
     */
    public AbsRvAdapter addItemViewDelegate(IItemViewDelegate<T> IItemViewDelegate){
        itemViewDelegateManager.addDelegate(IItemViewDelegate);
        return this;
    }

    public AbsRvAdapter addItemViewDelegate(int viewType, IItemViewDelegate<T> IItemViewDelegate){
        itemViewDelegateManager.addDelegate(viewType, IItemViewDelegate);
        return this;
    }


    /**
     * 点击事件的接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int pos);

        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    protected boolean useItemViewDelegateManager() {
        return itemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    /**
     * 将行布局的数据传出去
     * @param holder
     * @param t
     */
    public void convert(RvViewHolder holder , T t){
        itemViewDelegateManager.convert(holder,t,holder.getAdapterPosition());
    }
}
