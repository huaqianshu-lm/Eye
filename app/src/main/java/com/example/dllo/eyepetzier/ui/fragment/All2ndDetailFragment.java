package com.example.dllo.eyepetzier.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
//import com.example.dllo.eyepetzier.mode.bean.DisconveryDetailBean;
import com.example.dllo.eyepetzier.mode.bean.AuthorFragmentBean;
import com.example.dllo.eyepetzier.mode.bean.SearchBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.ui.activity.VideoIntroduceActivity;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.CommonRvAdapter;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.RvViewHolder;
import com.example.dllo.eyepetzier.utils.Contant;
import com.example.dllo.eyepetzier.utils.EScreenSizeDensity;
import com.example.dllo.eyepetzier.utils.ScreenSize;
import com.example.dllo.eyepetzier.utils.TextStyleSetter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/18.
 * 发现二级界面的时间排序界面
 */
public class All2ndDetailFragment extends AbaBaseFragment {
    private String str;
    private RecyclerView recyclerView;
    /**
     * 判断lv是否滑动到了底部
     */
    private boolean isFirstLoaded = false;
    private CommonRvAdapter<SearchBean.ItemListBean> adapter;
    private List<SearchBean.ItemListBean> itemListBeen;
    private SearchBean.ItemListBean.DataBean dataBean;
    private AuthorFragmentBean.ItemListBean.DataBean authorDataBean = new AuthorFragmentBean.ItemListBean.DataBean();
    private List<AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean> mVideoItemListBeen = new ArrayList<>();
    private AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean mVideoDataBean;
    private AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean mVideoItemListBean;
    private AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean.ConsumptionBean authorConsBean = new AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean.ConsumptionBean();

    public static All2ndDetailFragment getDiscoveryDetailAllFragment(String str) {
        All2ndDetailFragment all2ndDetailFragment = new All2ndDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        all2ndDetailFragment.setArguments(bundle);
        return all2ndDetailFragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_all2nd_detail;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.fragment_discoverydetail_rv);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        this.str = bundle.getString("url");
        NetRequestSingleton.getInstance().startRequest(this.str, SearchBean.class, new IOnHttpCallback<SearchBean>() {
            @Override
            public void onSuccess(SearchBean response) {

                itemListBeen = response.getItemList();
                int count = itemListBeen.size();
                for (int i = 0; i < count; i++) {
                    dataBean = itemListBeen.get(i).getData();
                    mVideoItemListBean = new AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean();
                    mVideoDataBean = new AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean();
                    mVideoDataBean.setDescription(dataBean.getDescription());
                    mVideoDataBean.setTitle(dataBean.getTitle());
                    mVideoDataBean.setCategory(dataBean.getCategory());
                    SearchBean.ItemListBean.DataBean.CoverBean coverBean = dataBean.getCover();
                    AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean.CoverBean authorCoverBean = new AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean.CoverBean();
                    authorCoverBean.setBlurred(coverBean.getBlurred());
                    authorCoverBean.setDetail(coverBean.getDetail());
                    mVideoDataBean.setCover(authorCoverBean);
                    List<AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean.PlayInfoBean> authorPlayInfoBeen = new ArrayList<>();
                    int size = dataBean.getPlayInfo().size();
                    for (int i1 = 0; i1 < size; i1++) {
                        SearchBean.ItemListBean.DataBean.PlayInfoBean playInfoBean = dataBean.getPlayInfo().get(i1);
                        AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean.PlayInfoBean authorPlayInfoBean = new AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean.PlayInfoBean();
                        authorPlayInfoBean.setUrl(playInfoBean.getUrl());
                        authorPlayInfoBeen.add(authorPlayInfoBean);
                    }
                    SearchBean.ItemListBean.DataBean.ConsumptionBean consumptionBean = dataBean.getConsumption();
                    authorConsBean.setCollectionCount(consumptionBean.getCollectionCount());
                    authorConsBean.setReplyCount(consumptionBean.getReplyCount());
                    authorConsBean.setShareCount(consumptionBean.getShareCount());
                    mVideoDataBean.setConsumption(authorConsBean);
                    mVideoDataBean.setPlayUrl(dataBean.getPlayUrl());
                    mVideoItemListBean.setData(mVideoDataBean);
                    mVideoItemListBeen.add(mVideoItemListBean);
                }
                authorDataBean.setItemList(mVideoItemListBeen);
                final Bundle bundle = new Bundle();
                bundle.putParcelable(Contant.TO_VIDEO, authorDataBean);


                adapter = new CommonRvAdapter<SearchBean.ItemListBean>(context, response.getItemList(), R.layout.item_lv_fgmt_feed_type_2nd) {
                    @Override
                    protected void convert(RvViewHolder holder, SearchBean.ItemListBean itemListBean, final int pos) {

                        holder.setIsRecyclable(false);
                        String textTitle = itemListBean.getData().getTitle();
                        holder.setText(R.id.item_lv_fgmt_feed_type_2nd_tv_title, textTitle);
                        // tvTitle 字体加粗
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_lv_fgmt_feed_type_2nd, null);
                        TextView tvTitle = (TextView) view.findViewById(R.id.item_lv_fgmt_feed_type_2nd_tv_title);
                        new TextStyleSetter().setBoldText(tvTitle.getPaint());
                        String time = formatTime(itemListBean);
                        holder.setText(R.id.item_lv_fgmt_feed_type_2nd_tv_category, "#" + itemListBean.getData().getCategory() + " / " + time);
                        if (itemListBean.getData().getAuthor() != null) {
                            holder.setVisible(R.id.item_lv_fgmt_feed_type_2nd_tv_author_name, true);
//                            holder.setText(R.id.item_lv_fgmt_feed_type_2nd_tv_author_name, itemListBean.getData().getAuthor().getName());
                        }
                        holder.setImgUrl(R.id.item_lv_fgmt_feed_type_2nd_imgv_feed, itemListBean.getData().getCover().getFeed());
//                        holder.setImgUrl(R.id.item_discovery_detail_iv, itemListBean.getData().getCover().getFeed(), 200, 100);

                        // set item width & height
                        View itemView = holder.getConvertView();
                        ViewGroup.LayoutParams params = itemView.getLayoutParams();
                        params.height = ScreenSize.getScreenSize(context, EScreenSizeDensity.HEIGHT) * 2 / 5;
                        params.width = ScreenSize.getScreenSize(context, EScreenSizeDensity.WIDTH);
                        itemView.setLayoutParams(params);


                        holder.setOnClickListener(R.id.item_lv_fgmt_feed_type_2nd_imgv_feed, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bundle.putInt(Contant.VIDEO_POS, pos);
                                goTo(context, VideoIntroduceActivity.class, bundle);
                            }
                        });


                    }
                };
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapter);

                str = (String) response.getNextPageUrl();

            }

            @Override
            public void onError(Throwable ex) {

            }

            /**
             * 用于将duration时间转换成分秒time
             * @param itemListBean
             * @return time字符串
             */
            @NonNull
            private String formatTime(SearchBean.ItemListBean itemListBean) {
                int times = itemListBean.getData().getDuration();
                String minute = "";
                String second = "";
                int minutes = times / 60;
                int seconds = times % 60;
                if (minutes >= 10) {
                    minute = "" + minutes;
                } else
                    minute = "0" + minutes;
                if (seconds >= 10) {
                    second = "" + seconds;
                } else
                    second = "0" + seconds;
                return minute + "' " + second + "\"";
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private RecyclerView.LayoutManager layoutManager;
            private int lastVisibleItemPosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int totalItemCount = layoutManager.getItemCount();
                // 当rv停下来,并且滑动到最后的位置时
                if (newState == 0 && lastVisibleItemPosition == (totalItemCount - 1) && !isFirstLoaded && str != null) {

                    isFirstLoaded = true;
                    // 解析下一天的数据
                    NetRequestSingleton.getInstance().startRequest(str, SearchBean.class, new IOnHttpCallback<SearchBean>() {
                        @Override
                        public void onSuccess(SearchBean response) {

                            adapter.addItemAtEnd(response.getItemList());
                            str = (String) response.getNextPageUrl();
                            isFirstLoaded = false;
                        }

                        @Override
                        public void onError(Throwable ex) {
                        }
                    });
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                }
            }
        });

    }


}
