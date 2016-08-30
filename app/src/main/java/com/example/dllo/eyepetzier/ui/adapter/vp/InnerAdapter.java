package com.example.dllo.eyepetzier.ui.adapter.vp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.BannerBean;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by dllo on 16/8/24.
 * banner详情界面的适配器
 */
public class InnerAdapter extends BaseAdapter {

    ArrayList<BannerBean.ItemListBean> objs;
    ArrayList<BannerBean.ItemListBean> initobjs;

    private int cardWidth;
    private int cardHeight;


    public InnerAdapter() {

        objs = new ArrayList<>();
        initobjs = new ArrayList<>();
    }

    public void addAll(Collection<BannerBean.ItemListBean> collection, int cardWidth, int cardHeight) {
        if (isEmpty()) {
            objs.addAll(collection);
            initobjs.addAll(collection);
            notifyDataSetChanged();
        } else {
            objs.addAll(collection);
        }
        this.cardHeight = cardHeight;
        this.cardWidth = cardWidth;
    }

    public void clear() {
        objs.clear();
        notifyDataSetChanged();
    }

    public boolean isEmpty() {

        return objs.isEmpty();
    }

    public void remove(int index) {
        if (index > -1 && index < objs.size()) {
            objs.remove(index);
            notifyDataSetChanged();
        }
    }
    public void add(int index) {
        objs.add(1, initobjs.get(1));

    }


    @Override
    public int getCount() {
        return objs.size();
    }

    @Override
    public BannerBean.ItemListBean getItem(int position) {
        if(objs == null ||objs.size() == 0)
        {
            return null;
        }
        return objs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        BannerBean.ItemListBean talent = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_new, parent, false);
            holder = new ViewHolder();
            convertView.setTag(holder);
            convertView.getLayoutParams().width = cardWidth;
            holder.portraitView = (ImageView) convertView.findViewById(R.id.portrait);
            holder.tv_content = (TextView) convertView.findViewById(R.id.swipe_tv);
            holder.portraitView.getLayoutParams().width = cardWidth;
            holder.portraitView.getLayoutParams().height = cardHeight;

        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(parent.getContext()).load(talent.getData().getCover().getFeed())
                .centerCrop().placeholder(R.drawable.default_card)
                .into(holder.portraitView);
//        Picasso.with(parent.getContext()).load(talent.getData().getCover().getFeed()).centerCrop().placeholder(R.drawable.default_card).into(holder.portraitView);
        holder.tv_content.setText(objs.get(position).getData().getDescription());

        final CharSequence no = "暂无";

        return convertView;
    }


    private static class ViewHolder {
        ImageView portraitView;
        TextView tv_content;
    }


}
