package com.example.dllo.eyepetzier.ui.adapter.vp;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

import com.example.dllo.eyepetzier.R;

import java.util.List;

/**
 * Created by dllo on 16/8/12.
 * mainActivity viewpager 的Adapter
 */
public class MainViewAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
//    private String[] titles;
    private List<String> titles;
    public MainViewAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainViewAdapter setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
        return this;
    }

    public MainViewAdapter setTitles(List<String> titles) {
        this.titles = titles;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titles.get(position);
//    }
}
