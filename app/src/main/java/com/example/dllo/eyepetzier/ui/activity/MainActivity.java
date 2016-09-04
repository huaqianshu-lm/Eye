package com.example.dllo.eyepetzier.ui.activity;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.AuthorFragmentBean;
import com.example.dllo.eyepetzier.mode.bean.SearchBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.mode.net.NetUrl;
import com.example.dllo.eyepetzier.mode.net.OkHttpImplemnt;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.CommonRvAdapter;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.RvViewHolder;
import com.example.dllo.eyepetzier.ui.adapter.vp.MainViewAdapter;
import com.example.dllo.eyepetzier.ui.fragment.AuthorFragment;
import com.example.dllo.eyepetzier.ui.fragment.DiscoveryFragment;
import com.example.dllo.eyepetzier.ui.fragment.FeedFragment;
import com.example.dllo.eyepetzier.ui.fragment.MineFragment;
import com.example.dllo.eyepetzier.utils.Contant;
import com.example.dllo.eyepetzier.utils.T;
import com.example.dllo.eyepetzier.utils.ViewObserable;
import com.example.dllo.eyepetzier.view.FlowLayout;
import com.example.dllo.eyepetzier.view.SearchEditText;
import com.example.dllo.eyepetzier.view.TitleTextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

import rx.functions.Action1;


public class MainActivity extends AbsBaseActivity implements View.OnClickListener, FeedFragment.ITurn {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainViewAdapter mainViewAdapter;
    private List<Fragment> fragments;
    private ImageView searchIv;
    private PopupWindow searchPop;
    private RelativeLayout titleRl;
    private LinearLayout searchLl;
    private TextView canclRreachTv;
    private JSONArray list;
    private FlowLayout searchFl;
    private SearchEditText searchEt;
    private ImageView setIv;
    private RelativeLayout searchDefaultRl;
    private RelativeLayout searchResultRl;
    private RecyclerView searchRecyclerView;
    private TextView searchResultTv;
    private RelativeLayout loadingRl;
    private ImageView loadingIv;
    private SearchBean searchBean;
    private List<SearchBean.ItemListBean> itemListBeen;
    private SearchBean.ItemListBean.DataBean dataBean;
    private CommonRvAdapter<SearchBean.ItemListBean> searchAdapter;
    private Animation loadingAnimation;
    private TextView searchResultNumTv;
    private AuthorFragmentBean.ItemListBean.DataBean authorDataBean = new AuthorFragmentBean.ItemListBean.DataBean();
    private List<AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean> mVideoItemListBeen = new ArrayList<>();
    private AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean mVideoDataBean;
    private AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean mVideoItemListBean;
    private AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean.ConsumptionBean authorConsBean = new AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean.ConsumptionBean();
    private boolean isSuccess = true;


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        viewPager = bindView(R.id.main_viewpager);
        tabLayout = bindView(R.id.main_tablayout);
        searchIv = bindView(R.id.search_iv);
        titleRl = bindView(R.id.main_title);
        searchLl = bindView(R.id.main_search_title);
        canclRreachTv = bindView(R.id.search_title_tv);
        setIv = bindView(R.id.set_iv);
        searchEt = bindView(R.id.search_et);
        setIv.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        canclRreachTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        searchLl.setVisibility(View.GONE);
        // 设置搜索界面
        setSearchFlow();
        mainViewAdapter = new MainViewAdapter(getSupportFragmentManager());
        initFragment();
        mainViewAdapter.setFragments(fragments);
        viewPager.setAdapter(mainViewAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        setTabLayout();
        setTitle();
        // 等待动画
        loadingAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_loading);
        loadingAnimation.setInterpolator(new LinearInterpolator());
        // 搜索框

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)){
                    searchDefaultRl.setVisibility(View.VISIBLE);
                    searchResultRl.setVisibility(View.GONE);
                }
                String str = searchEt.getText().toString();
                setSearchResult(-1, str);
                searchDefaultRl.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    /**
     * 搜索界面
     */
    private void setSearchFlow() {
        searchPop = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        searchPop.setAnimationStyle(R.style.pop_style);
        View searchPopView = LayoutInflater.from(this).inflate(R.layout.pop_search, null);
        // 搜索前的默认界面
        searchPop.setContentView(searchPopView);
        searchFl = (FlowLayout) searchPopView.findViewById(R.id.search_pop_flow);
        searchDefaultRl = (RelativeLayout) searchPopView.findViewById(R.id.search_default_rl);
        // 搜索后的界面
        searchResultRl = (RelativeLayout) searchPopView.findViewById(R.id.search_result_rl);
        searchRecyclerView = (RecyclerView) searchPopView.findViewById(R.id.search_result_rv);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        searchResultTv = (TextView) searchPopView.findViewById(R.id.search_result_item_tv);
        searchResultNumTv = (TextView) searchPopView.findViewById(R.id.search_result_num_tv);
        // 动画界面
        loadingIv = (ImageView) searchPopView.findViewById(R.id.search_loading_iv);
        loadingRl = (RelativeLayout) searchPopView.findViewById(R.id.search_loading_rl);
        NetRequestSingleton.getInstance().startRequest(NetUrl.SEARCH_URL, new IOnHttpCallback<String>() {
            @Override
            public void onSuccess(String response) {
                list = JSON.parseArray(response);
                int size = list.size();
                View view = View.inflate(MainActivity.this, R.layout.item_flow_card, null);
                for (int i = 0; i < size; i++) {
                    TextView searchTv = new TextView(MainActivity.this);
//                    TextView searchTv = (TextView) view.findViewById(R.id.card_tv);
                    String str = list.get(i).toString();
                    searchTv.setText(str);
                    searchFl.addView(searchTv);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) searchTv.getLayoutParams();
                    marginLayoutParams.leftMargin = 10;
                    searchTv.setLayoutParams(marginLayoutParams);
                    searchTv.setTextColor(Color.GRAY);
                }
            }

            @Override
            public void onError(Throwable ex) {

            }
        });
        searchFl.setOnItemClickListener(new FlowLayout.onItemClickListener() {
            @Override
            public void onItemClick(int position, View childAt) {
                loadingRl.setVisibility(View.VISIBLE);
                loadingIv.startAnimation(loadingAnimation);
                searchDefaultRl.setVisibility(View.GONE);
                setSearchResult(position, null);

                T.shortMsg("item");
            }
        });
    }

    /**
     * 搜索结果的设置
     *
     * @param position
     */
    private void setSearchResult(final int position, String str) {
        String content = null;
        if (position > -1) {
            content = (String) list.get(position);
        } else {
            if (str != null) {
                content = str;
            }
        }
        String searchUrl = NetUrl.SEARCH__ITEM.replace("参数", content);
        final String finalContent = content;
        NetRequestSingleton.getInstance().startRequest(searchUrl, SearchBean.class, new IOnHttpCallback<SearchBean>() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onSuccess(final SearchBean response) {
                searchBean = response;
                itemListBeen = response.getItemList();
                Log.d("search", itemListBeen.size() + "=====");
                // 搜索结果的标题
                searchResultTv.setText(finalContent);
                searchResultNumTv.setText(itemListBeen.size() + "");

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
                    mVideoDataBean.setDuration(dataBean.getDuration());
                    mVideoDataBean.setPlayUrl(dataBean.getPlayUrl());
                    mVideoItemListBean.setData(mVideoDataBean);
                    mVideoItemListBeen.add(mVideoItemListBean);
                }
                authorDataBean.setItemList(mVideoItemListBeen);
                final Bundle bundle = new Bundle();
                bundle.putParcelable(Contant.TO_VIDEO, authorDataBean);
                searchAdapter = new CommonRvAdapter<SearchBean.ItemListBean>(MainActivity.this, itemListBeen, R.layout.item_search_rv) {
                    @Override
                    protected void convert(RvViewHolder holder, SearchBean.ItemListBean itemListBean, final int pos) {
                        holder.setIsRecyclable(false);
                        dataBean = itemListBean.getData();

                        holder.setText(R.id.item_author_fragment_child_rv_title_tv, dataBean.getTitle());
                        holder.setText(R.id.item_author_fragment_child_rv_category_tv, dataBean.getCategory());
                        // 获取屏幕的宽度
                        holder.setImgUrl(R.id.item_author_fragment_child_rv_iv, dataBean.getCover().getFeed());
                        int minute = dataBean.getDuration() / 60;
                        int sec = dataBean.getDuration() % 60;
                        String time = String.valueOf(minute) + "'" + String.valueOf(sec) + "\"";
                        holder.setText(R.id.item_author_fragment_child_rv_latestreleasttime_tv, time);

                        bundle.putInt(Contant.VIDEO_POS, pos);
                        holder.setOnClickListener(R.id.item_author_fragment_child_rv_iv, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goTo(MainActivity.this, VideoIntroduceActivity.class, bundle);
                                Log.d("search pos", pos + "----========----");
                                T.shortMsg("作者界面视频图片的点击事件,跳转到视频的详情界面");

                            }
                        });
                    }

                };
                searchRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }

                    @Override
                    public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (isSuccess) {
//                            loadingIv.startAnimation(loadingAnimation);
                            loadingRl.setVisibility(View.VISIBLE);
                            if (searchBean.getNextPageUrl() != null) {
                                isSuccess = false;
                                NetRequestSingleton.getInstance().startRequest((String) searchBean.getNextPageUrl(), SearchBean.class, new IOnHttpCallback<SearchBean>() {
                                    @Override
                                    public void onSuccess(SearchBean response) {
                                        for (int i = 0; i < response.getItemList().size(); i++) {
                                            searchBean = response;
                                            itemListBeen.add(response.getItemList().get(i));
                                        }
                                        isSuccess = true;
                                        searchAdapter.setDatas(itemListBeen);
                                    }

                                    @Override
                                    public void onError(Throwable ex) {
                                    }
                                });
                            } else {

                                loadingRl.setVisibility(View.GONE);
                                searchResultNumTv.setText(String.valueOf(itemListBeen.size()));
                            }
                        }
                    }
                });
                searchResultRl.setVisibility(View.VISIBLE);
                searchRecyclerView.setAdapter(searchAdapter);
            }


            @Override
            public void onError(Throwable ex) {

            }
        });


    }

    /**
     * 初始化fragement
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new FeedFragment());
        fragments.add(new DiscoveryFragment());
        fragments.add(new AuthorFragment());
        fragments.add(new MineFragment());
    }

    /**
     * 设置tabLayout
     */
    private void setTabLayout() {
        int[] ids = {R.layout.item_tab_feed, R.layout.item_tab_discovery, R.layout.item_tab_author, R.layout.item_tab_mine};
        for (int i = 0; i < ids.length; i++) {
            View view = LayoutInflater.from(MainActivity.this).inflate(ids[i], null);
            tabLayout.getTabAt(i).setCustomView(view);
        }

        tabLayout.setSelectedTabIndicatorHeight(0);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Color.GRAY, Color.BLACK);
    }


    private void setTitle() {
        setIv.setVisibility(View.GONE);
        searchIv.setImageResource(R.mipmap.search);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    setIv.setVisibility(View.VISIBLE);
                    setIv.setImageResource(R.mipmap.menu);
                    searchIv.setVisibility(View.GONE);
                } else {
                    setIv.setVisibility(View.GONE);
                    searchIv.setVisibility(View.VISIBLE);
                    searchIv.setImageResource(R.mipmap.search);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        // title切换的动画
        Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(400);
        Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(400);

        switch (v.getId()) {
            case R.id.search_iv:

                searchPop.showAsDropDown(titleRl);
                searchLl.setAnimation(in);
                titleRl.setAnimation(out);
                titleRl.setVisibility(View.GONE);
                searchLl.setVisibility(View.VISIBLE);
                break;
            case R.id.search_title_tv:
                searchPop.dismiss();
                setSearchFlow();
                searchLl.setAnimation(out);
                titleRl.setAnimation(in);
//                searchResultRl.setVisibility(View.GONE);
                searchLl.setVisibility(View.GONE);
                titleRl.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public void turn() {
        /**
         * 切换到作者页
         */
        viewPager.setCurrentItem(2);
    }

    @Override
    public void onBackPressed() {
        searchPop.dismiss();
        super.onBackPressed();
        searchLl.setVisibility(View.GONE);
//        searchResultRl.setVisibility(View.GONE);
        titleRl.setVisibility(View.VISIBLE);
    }


}
