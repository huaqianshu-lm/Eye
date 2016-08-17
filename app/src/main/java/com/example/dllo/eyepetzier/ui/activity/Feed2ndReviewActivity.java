package com.example.dllo.eyepetzier.ui.activity;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.Feed2ndReviewBean;
import com.example.dllo.eyepetzier.mode.bean.FeedFragmentBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.mode.net.NetUrl;
import com.example.dllo.eyepetzier.ui.adapter.lv.Feed2ndReviewLvAdapter;
import com.example.dllo.eyepetzier.utils.TextStyleSetter;
import com.example.dllo.eyepetzier.view.TitleTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/17.
 * 精选二级页面 : 每日精选
 * 由一级页面 查看往期编辑精选 跳转
 */
public class Feed2ndReviewActivity extends AbsBaseActivity{

    private TitleTextView tvTitle;
    private TitleTextView tvTime;
    private ImageView ivBack;
    private ImageView ivSearch;
    private ListView lv;
    private Feed2ndReviewLvAdapter adapter;
    //    private RecyclerView rv;
    /**
     * 接口的集合
     */
    private List<String> netUrls;
    /**
     * 当前加载了几天的页,用于netUrls
     */
    private int index = 0;
    /**
     * 判断lv是否滑动到了底部
     */
    boolean isScrollToBottom;

    @Override
    protected int setLayout() {
        return R.layout.activity_feed2nd_review;
    }

    @Override
    protected void initView() {
        tvTitle = bindView(R.id.title_tv);
        tvTime = bindView(R.id.title_time);
        ivBack = bindView(R.id.title_iv_back);
        ivSearch = bindView(R.id.title_iv);
//        rv = bindView(R.id.acty_feed2nd_review_rv);
        lv = bindView(R.id.acty_feed2nd_review_lv);
    }

    @Override
    protected void initData() {
        // setTitle
        setTitle();
        // init lv
        initLv();
        // lv scroll load data
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // 如果滑动到最后的位置
//                Log.e("zzzz", "view.getLastVisiblePosition():" + view.getLastVisiblePosition());
//                Log.e("zzzz", "firstVisiblePosition():" + view.getFirstVisiblePosition());
//                FeedFragmentBean.SectionListBean.ItemListBean bean = (FeedFragmentBean.SectionListBean.ItemListBean) view.getAdapter().getItem(view.getFirstVisiblePosition());
//                Log.e("zzzzz", );

                if (view.getLastVisiblePosition() == totalItemCount - 1) {
                    // 解析下一天的数据
                    NetRequestSingleton.getInstance().startRequest(netUrls.get(index++), Feed2ndReviewBean.class, new IOnHttpCallback<Feed2ndReviewBean>() {
                        @Override
                        public void onSuccess(Feed2ndReviewBean response) {
                            adapter.addLastItem(getDatas(response));
                        }

                        @Override
                        public void onError(Throwable ex) {

                        }
                    });
                }
//                Log.e("zzzz", "view.getFirstVisiblePosition():" + view.getFirstVisiblePosition());
//                Log.e("xxxx", "(firstVisibleItem):" + view.getAdapter().getItemViewType(firstVisibleItem));
//                view.getAdapter().getItem(firstVisibleItem)
//                Log.e("zzz", "view.getFirstVisiblePosition():" + view.getFirstVisiblePosition());
            }
        });

    }

    /**
     * 初始化listview
     */
    private void initLv() {
        netUrls = new ArrayList<>();
        netUrls.add(NetUrl.FEED_2ND_REVIEW_URL_TODAY);
        netUrls.add(NetUrl.FEED_2ND_REVIEW_URL_AUG_16);
        netUrls.add(NetUrl.FEED_2ND_REVIEW_URL_AUG_15);
        netUrls.add(NetUrl.FEED_2ND_REVIEW_URL_AUG_14);
        netUrls.add(NetUrl.FEED_2ND_REVIEW_URL_AUG_13);

        NetRequestSingleton.getInstance().startRequest(netUrls.get(index++), Feed2ndReviewBean.class, new IOnHttpCallback<Feed2ndReviewBean>() {
            @Override
            public void onSuccess(Feed2ndReviewBean response) {
                adapter = new Feed2ndReviewLvAdapter(Feed2ndReviewActivity.this);
                adapter.setDatas(getDatas(response));
                lv.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex) {
            }
        });
    }

    /**
     * 获取listview数据
     * @param response
     * @return
     */
    @NonNull
    private List<Feed2ndReviewBean.IssueListBean.ItemListBean> getDatas(Feed2ndReviewBean response) {
        List<Feed2ndReviewBean.IssueListBean.ItemListBean> datas = new ArrayList<>();
        for (int i = 0; i < response.getIssueList().size(); i++) {
            for (int j = 0; j < response.getIssueList().get(i).getItemList().size(); j++) {
                datas.add(response.getIssueList().get(i).getItemList().get(j));
            }
        }
        return datas;
    }

    private void setTitle() {
        tvTitle.setText(R.string.acty_feed2nd_review_tv_title);
        new TextStyleSetter().setBoldText(tvTitle.getPaint());
        tvTitle.setTextSize(20);

        tvTime.setVisibility(View.VISIBLE);
        tvTime.setText(R.string.acty_feed2nd_review_tv_time);

        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivSearch.setVisibility(View.GONE);
    }
}

