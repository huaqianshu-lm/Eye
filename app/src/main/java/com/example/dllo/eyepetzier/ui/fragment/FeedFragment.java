package com.example.dllo.eyepetzier.ui.fragment;

import android.widget.ListView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.FeedFragmentBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.mode.net.NetUrl;
import com.example.dllo.eyepetzier.ui.adapter.FeedFragmentLvAdapter;

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

        initListview();

    }

    private void initListview() {

        NetRequestSingleton.getInstance().startRequest(NetUrl.FEED_FRAGMENT_URL, FeedFragmentBean.class, new IOnHttpCallback<FeedFragmentBean>() {
            @Override
            public void onSuccess(FeedFragmentBean response) {
                FeedFragmentLvAdapter adapter = new FeedFragmentLvAdapter(getContext());
                adapter.setDatas(response.getSectionList().get(0).getItemList());
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex) {

            }
        });
    }
}
