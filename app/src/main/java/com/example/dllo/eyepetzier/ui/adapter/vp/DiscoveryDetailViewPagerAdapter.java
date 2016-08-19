package com.example.dllo.eyepetzier.ui.adapter.vp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/8/18.
 * 发现二级界面的ViewPager的适配器
 */
public class DiscoveryDetailViewPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> fragments;

    public DiscoveryDetailViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
