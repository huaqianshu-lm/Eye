package com.example.dllo.eyepetzier.ui.activity;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.mode.net.NetUrl;
import com.example.dllo.eyepetzier.mode.net.OkHttpImplemnt;
import com.example.dllo.eyepetzier.ui.adapter.vp.MainViewAdapter;
import com.example.dllo.eyepetzier.ui.fragment.AuthorFragment;
import com.example.dllo.eyepetzier.ui.fragment.DiscoveryFragment;
import com.example.dllo.eyepetzier.ui.fragment.FeedFragment;
import com.example.dllo.eyepetzier.ui.fragment.MineFragment;
import com.example.dllo.eyepetzier.utils.T;
import com.example.dllo.eyepetzier.view.FlowLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;


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
    private ImageView setIv;


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
        tabLayout.setupWithViewPager(viewPager);
        setTabLayout();
        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_tab_strip_icon_feed_selected);
        setTitle();



    }


    /**
     * 搜索界面
     */
    private void setSearchFlow() {
        searchPop = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        searchPop.setAnimationStyle(R.style.pop_style);
        View searchPopView = LayoutInflater.from(this).inflate(R.layout.pop_search, null);
        searchPop.setContentView(searchPopView);
        searchFl = (FlowLayout) searchPopView.findViewById(R.id.search_pop_flow);
        searchFl.setOnItemClickListener(new FlowLayout.onItemClickListener() {
            @Override
            public void onItemClick(int position, View childAt) {
                T.shortMsg("item");
            }
        });
        NetRequestSingleton.getInstance().startRequest(NetUrl.SEARCH_URL, new IOnHttpCallback<String>() {
            @Override
            public void onSuccess(String response) {
                list = JSON.parseArray(response);
                int size = list.size();
                View view = View.inflate(MainActivity.this,R.layout.item_flow_card,null);
                for (int i = 0; i < size; i++) {
                    TextView searchTv = new TextView(MainActivity.this);
//                    TextView searchTv = (TextView) view.findViewById(R.id.card_tv);
                    String str = list.get(i).toString();
                    searchTv.setText(str);
                    searchFl.addView(searchTv);
                }
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
                searchLl.setAnimation(out);
                titleRl.setAnimation(in);
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
}
