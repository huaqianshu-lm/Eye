package com.example.dllo.eyepetzier.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.text.BidiFormatter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dllo.eyepetzier.R;

import com.example.dllo.eyepetzier.mode.bean.DiscoveryFragmentBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.mode.net.NetUrl;

import com.example.dllo.eyepetzier.ui.activity.BannerActivity;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.CommonRvAdapter;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.RecyclerItemDecoration;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.RvViewHolder;

import com.example.dllo.eyepetzier.ui.activity.DiscoveryDetailedActivity;


import com.example.dllo.eyepetzier.utils.T;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by dllo on 16/8/12.
 * 发现页面的fragment
 */
public class DiscoveryFragment extends AbaBaseFragment {
    private Banner banner;
    private String[] imageUrls = {
            "http://img.wdjimg.com/image/video/475e1691e682d303807aa3829efc57cc_0_0.jpeg",
            "http://img.wdjimg.com/image/video/475e1691e682d303807aa3829efc57cc_0_0.jpeg",
            "http://img.wdjimg.com/image/video/475e1691e682d303807aa3829efc57cc_0_0.jpeg"
    };
    private RecyclerView recyclerView;
    private ImageView top10_iv;
    private ImageView topic_iv;
    private ImageView view360_iv;
    private ImageView loadingIv;
    private RelativeLayout loadingRl;
    @Override
    protected int setLayout() {
        return R.layout.fragment_discovery;
    }

    @Override
    protected void initView() {
        banner = bindView(R.id.discovery_banner);
        recyclerView = bindView(R.id.FragmentDiscovery_rv);
        top10_iv = bindView(R.id.iv_top10);
        topic_iv = bindView(R.id.iv_topic);
        view360_iv = bindView(R.id.iv_360);
        loadingIv = bindView(R.id.discovery_fragment_loading_iv);
        loadingRl = bindView(R.id.discovery_fragment_loading_rl);
    }

    @Override
    protected void initData() {
        // 加载动画
        Animation loadingAnimaition = AnimationUtils.loadAnimation(context,R.anim.rotate_loading);
        loadingAnimaition.setInterpolator(new LinearInterpolator());
        loadingIv.startAnimation(loadingAnimaition);
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        banner.setImages(imageUrls);
        // banner的点击事件
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int position) {
                switch (position) {
                    case 1:

                    case 2:

                    case 3:
                        Intent intent = new Intent(context, BannerActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        // 解析下方16个图片
        NetRequestSingleton.getInstance().startRequest(NetUrl.DISCOVERY_FRAG_ICON, DiscoveryFragmentBean.class, new IOnHttpCallback<DiscoveryFragmentBean>() {
            @Override
            public void onSuccess(DiscoveryFragmentBean response) {
                loadingRl.setVisibility(View.GONE);
                List<DiscoveryFragmentBean.ItemListBean> listBeen = response.getItemList();
                listBeen.remove(0);
                Picasso.with(context).load(listBeen.get(0).getData().getImage()).resize(300, 300).into(top10_iv);
                Picasso.with(context).load(listBeen.get(1).getData().getImage()).resize(300, 300).into(topic_iv);
                listBeen.remove(0);
                listBeen.remove(0);
                Picasso.with(context).load(listBeen.get(0).getData().getImage()).resize(600, 300).into(view360_iv);
                listBeen.remove(0);
                CommonRvAdapter<DiscoveryFragmentBean.ItemListBean> adapter = new CommonRvAdapter<DiscoveryFragmentBean.ItemListBean>(context, response.getItemList(), R.layout.item_discovery) {
                    @Override
                    protected void convert(RvViewHolder holder, DiscoveryFragmentBean.ItemListBean itemListBean, int pos) {
                        holder.setImgUrl(R.id.item_discovery_iv, itemListBean.getData().getImage(), 300, 300);
                        holder.setText(R.id.item_discovery_tv, itemListBean.getData().getTitle());
                        holder.setOnClickListener(R.id.item_discovery_iv, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString("time", NetUrl.DISCOVERY_DETAIL_TIME);
                                bundle.putString("share", NetUrl.DISCOVERY_DETAIL_SHARE);
                                goTo(context, DiscoveryDetailedActivity.class, bundle);
                            }
                        });
                    }
                };
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(gridLayoutManager);
                // 给recycleView划线
                recyclerView.addItemDecoration(new RecyclerItemDecoration());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex) {

            }
        });

    }
}
