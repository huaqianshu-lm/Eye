package com.example.dllo.eyepetzier.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.eyepetzier.mode.bean.FeedFragmentBean;

import java.util.List;

/**
 * Created by dllo on 16/8/13.
 */
public class FeedFragmentAdapter extends BaseAdapter {

    private Context context;
    private List<FeedFragmentBean> datas;

    public FeedFragmentAdapter(Context context) {
        this.context = context;
    }

    public FeedFragmentAdapter setDatas(List<FeedFragmentBean> datas) {
        this.datas = datas;
        return this;
    }

    private boolean ifNull(){
        return datas != null && datas.size() > 0;
    }

    @Override
    public int getCount() {
        return ifNull() ? datas.size() : 0;
    }

    @Override
    public FeedFragmentBean getItem(int position) {
        return ifNull() ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate()
        }
        return convertView;
    }

    class MyHolder{



        public MyHolder(View itemView) {


        }
    }
}
