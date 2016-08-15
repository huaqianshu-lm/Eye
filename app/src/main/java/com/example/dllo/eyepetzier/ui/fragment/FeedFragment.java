package com.example.dllo.eyepetzier.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.FeedFragmentBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.mode.net.NetUrl;
import com.example.dllo.eyepetzier.ui.adapter.lv.FeedFragmentLvAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/8/12.
 * 精选页面的fragment
 */
public class FeedFragment extends AbaBaseFragment {

    /**
     * 第一块 listview
     */
    private ListView listView;
    private TextView textViewLvFooter;
    private View footView;
    /**
     * 第二块 recyclerView
     */
    private ImageView imageView2ndPart;
    private RecyclerView rv2ndPart;

    @Override
    protected int setLayout() {
        return R.layout.fragment_feed;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.fgmt_feed_listview);
        footView = getActivity().getLayoutInflater().inflate(R.layout.item_lv_fgmt_feed_type_foot, null);
        textViewLvFooter = (TextView) footView.findViewById(R.id.item_lv_fgmt_feed_type_foot_tv);

        imageView2ndPart = (ImageView) bindView(R.id.fgmt_feed_include_1st).findViewById(R.id.view_reuse_fgmt_feed_imgv_header);
//        rv2ndPart = (RecyclerView) bindView(R.id.fgmt_feed_include_1st).findViewById(R.id.view_reuse_fgmt_feed_rv);
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
                // set 1st part
                adapter.setDatas(response.getSectionList().get(0).getItemList());
                listView.setAdapter(adapter);
                textViewLvFooter.setText(response.getSectionList().get(0).getFooter().getData().getText());
                listView.addFooterView(footView);
                // set 2nd part
                imageView2ndPart.setVisibility(View.VISIBLE);
                Picasso.with(getContext()).load(response.getSectionList().get(1).getItemList().get(0).getData().getHeader().getCover()).error(R.mipmap.ic_launcher).into(imageView2ndPart);
            }

            @Override
            public void onError(Throwable ex) {

            }
        });
    }
}
