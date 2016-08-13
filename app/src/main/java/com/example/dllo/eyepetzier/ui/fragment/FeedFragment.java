package com.example.dllo.eyepetzier.ui.fragment;

import android.widget.ListView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.FeedFragmentBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.ui.adapter.FeedFragmentAdapter;

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

        String url = "http://baobab.wandoujia.com/api/v3/tabs/selected?udid=86f35dc937824e09bf8d0c7dc0cfea543ed2a2a3&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_360_market&last_channel=eyepetizer_360_market&system_version_code=22";
        NetRequestSingleton.getInstance().startRequest(url, FeedFragmentBean.class, new IOnHttpCallback<FeedFragmentBean>() {
            @Override
            public void onSuccess(FeedFragmentBean response) {
                FeedFragmentAdapter adapter = new FeedFragmentAdapter(getContext());
                adapter.setDatas(response.getSectionList());
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex) {

            }
        });
    }
}
