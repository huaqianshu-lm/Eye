package com.example.dllo.eyepetzier.ui.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.ui.adapter.MainViewAdapter;
import com.example.dllo.eyepetzier.ui.fragment.AuthorFragment;
import com.example.dllo.eyepetzier.ui.fragment.DiscoveryFragment;
import com.example.dllo.eyepetzier.ui.fragment.FeedFragment;
import com.example.dllo.eyepetzier.ui.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AbsBaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainViewAdapter mainViewAdapter;
    private List<Fragment> fragments;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.main_viewpager);
        tabLayout = bindView(R.id.main_tablayout);
    }

    @Override
    protected void initData() {
        mainViewAdapter = new MainViewAdapter(getSupportFragmentManager(),this);
        initFragment();
        mainViewAdapter.setFragments(fragments);
        viewPager.setAdapter(mainViewAdapter);
        tabLayout.setupWithViewPager(viewPager);

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


}
