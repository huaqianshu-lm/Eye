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
public class Feed2ndReviewActivity extends AbsBaseActivity {

    private TitleTextView tvTitle;
    private TitleTextView tvTime;
    private ImageView ivBack;
    private ImageView ivSearch;
    private ListView lv;
    private Feed2ndReviewLvAdapter adapter;
    //    private RecyclerView rv;
    private String nextUrl;
    /**
     * 判断lv是否滑动到了底部
     */
    private boolean isFirstLoaded = false;

    @Override
    protected int setLayout() {
        return R.layout.activity_feed2nd_review;
    }

    @Override
    protected void initView() {
        tvTitle = bindView(R.id.title_tv);
        tvTime = bindView(R.id.title_time);
        ivBack = bindView(R.id.title_iv_back);
        ivSearch = bindView(R.id.search_iv);
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


    }

    /**
     * 初始化listview
     */
    private void initLv() {

        NetRequestSingleton.getInstance().startRequest(NetUrl.FEED_2ND_REVIEW_URL_TODAY, Feed2ndReviewBean.class, new IOnHttpCallback<Feed2ndReviewBean>() {
            @Override
            public void onSuccess(Feed2ndReviewBean response) {
                adapter = new Feed2ndReviewLvAdapter(Feed2ndReviewActivity.this);
                adapter.setDatas(getDatas(response));
                lv.setAdapter(adapter);
                nextUrl = response.getNextPageUrl();
            }

            @Override
            public void onError(Throwable ex) {

            }
        });

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            /**
             * @param view
             * @param scrollState 参数值0 : 代表不在划动, 参数值1 : 代表手动去划动, 参数值2 : 代表惯性在划动
             */
            @Override
            public void onScrollStateChanged(final AbsListView view, int scrollState) {
                // 当listview停下来,并且滑动到最后的位置时
                if (scrollState == 0 && view.getLastVisiblePosition() == view.getAdapter().getCount() - 1 && !isFirstLoaded && nextUrl != null) {

                    isFirstLoaded = true;
                    // 解析下一天的数据
                    NetRequestSingleton.getInstance().startRequest(nextUrl, Feed2ndReviewBean.class, new IOnHttpCallback<Feed2ndReviewBean>() {
                        @Override
                        public void onSuccess(Feed2ndReviewBean response) {
                            adapter.addLastItem(getDatas(response));
                            nextUrl = response.getNextPageUrl();

                            isFirstLoaded = false;
                        }

                        @Override
                        public void onError(Throwable ex) {
                        }
                    });


                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // 改变标题栏textview的文字
                // nextUrl在子线程中获取,不判断会崩
                if (nextUrl != null) {
                    Feed2ndReviewBean.IssueListBean.ItemListBean firstVisibleBean = (Feed2ndReviewBean.IssueListBean.ItemListBean) view.getAdapter().getItem(firstVisibleItem);
                    if (firstVisibleBean.getType().equals("textHeader")) {

                        String str = firstVisibleBean.getData().getText();
                        for (int i = 0; i < str.length(); i++) {
                            // 46是"."的char值,判断str里是否含有"."
                            if (str.charAt(i) == 46) {
                                tvTime.setText(firstVisibleBean.getData().getText());
                            }
                        }

                    }
                }

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

