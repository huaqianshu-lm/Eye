package com.example.dllo.eyepetzier.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.net.NetUrl;
import com.example.dllo.eyepetzier.ui.adapter.vp.DiscoveryDetailViewPagerAdapter;
import com.example.dllo.eyepetzier.ui.fragment.All2ndDetailFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/8/18.
 * 发现二级详情界面
 */
public class DiscoveryDetailedActivity extends AbsBaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragments;
    private DiscoveryDetailViewPagerAdapter adapter;
    private ImageView backeImageView;
    private ImageView shareIamgeView;
    @Override
    protected int setLayout() {
        return R.layout.activity_discoverydetail;
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.discoverydetail_viewpager);
        tabLayout = bindView(R.id.discoverydetail_tablayout);
        backeImageView = bindView(R.id.discoverydetail_back_iv);
        shareIamgeView = bindView(R.id.discoverydetail_share_iv);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(All2ndDetailFragment.getDiscoveryDetailAllFragment(NetUrl.DISCOVERY_DETAIL_TIME));
        fragments.add(All2ndDetailFragment.getDiscoveryDetailAllFragment(NetUrl.DISCOVERY_DETAIL_SHARE));
        adapter = new DiscoveryDetailViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("时间");
        tabLayout.getTabAt(1).setText("分享");
    }
}
