package com.example.dllo.eyepetzier.ui.activity;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.ui.adapter.vp.MainViewAdapter;
import com.example.dllo.eyepetzier.ui.fragment.AuthorFragment;
import com.example.dllo.eyepetzier.ui.fragment.DiscoveryFragment;
import com.example.dllo.eyepetzier.ui.fragment.FeedFragment;
import com.example.dllo.eyepetzier.ui.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AbsBaseActivity implements View.OnClickListener, FeedFragment.ITurn {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainViewAdapter mainViewAdapter;
    private List<Fragment> fragments;
    private ImageView titleIv;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.main_viewpager);
        tabLayout = bindView(R.id.main_tablayout);
        titleIv = bindView(R.id.search_iv);
        titleIv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
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
    private void setTabLayout(){
        int [] ids = {R.layout.item_tab_feed,R.layout.item_tab_discovery,R.layout.item_tab_author,R.layout.item_tab_mine};
        for (int i = 0; i < ids.length; i++) {
            View view = LayoutInflater.from(MainActivity.this).inflate(ids[i],null);
            tabLayout.getTabAt(i).setCustomView(view);
        }
        tabLayout.setSelectedTabIndicatorHeight(0);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Color.GRAY,Color.BLACK);
    }


    private void setTitle(){
        titleIv.setImageResource(R.mipmap.search);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3){
                    titleIv.setImageResource(R.mipmap.menu);
                }else {
                    titleIv.setImageResource(R.mipmap.search);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void turn() {
        /**
         * 切换到作者页
         */
        viewPager.setCurrentItem(2);
    }
}
