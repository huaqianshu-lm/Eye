package com.example.dllo.eyepetzier.ui.fragment;

import android.widget.ListView;

import com.example.dllo.eyepetzier.R;

/**
 * Created by dllo on 16/8/12.
 * 精选页面的fragment
 */
public class FeedFragment extends AbaBaseFragment {

    private ListView listView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_feed;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.fgmt_feed_listview);
    }

    @Override
    protected void initData() {

    }
}
