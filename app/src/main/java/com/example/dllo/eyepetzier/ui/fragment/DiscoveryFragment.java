package com.example.dllo.eyepetzier.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.eyepetzier.R;

import com.example.dllo.eyepetzier.mode.bean.DiscoveryFragmentBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.mode.net.NetUrl;
import com.example.dllo.eyepetzier.ui.adapter.rv.CommonRvAdapter;
import com.example.dllo.eyepetzier.ui.adapter.rv.RecyclerItemDecoration;
import com.example.dllo.eyepetzier.ui.adapter.rv.RvViewHolder;
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
    }

    @Override
    protected void initData() {
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        banner.setImages(imageUrls);
        // banner的点击事件
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int position) {

            }
        });
        // 解析下方16个图片
        NetRequestSingleton.getInstance().startRequest(NetUrl.DISCOVERY_FRAG_ICON, DiscoveryFragmentBean.class, new IOnHttpCallback<DiscoveryFragmentBean>() {
            @Override
            public void onSuccess(DiscoveryFragmentBean response) {
                List<DiscoveryFragmentBean.ItemListBean> listBeen = response.getItemList();
                Log.e("msg", listBeen.size()+"");
                listBeen.remove(0);
                Picasso.with(context).load(listBeen.get(0).getData().getImage()).resize(150, 150).into(top10_iv);
                Picasso.with(context).load(listBeen.get(1).getData().getImage()).resize(150, 150).into(topic_iv);
                listBeen.remove(0);
                listBeen.remove(0);
                Picasso.with(context).load(listBeen.get(0).getData().getImage()).resize(300, 150).into(view360_iv);
                listBeen.remove(0);
                CommonRvAdapter<DiscoveryFragmentBean.ItemListBean> adapter = new CommonRvAdapter<DiscoveryFragmentBean.ItemListBean>(context, response.getItemList(), R.layout.item_discovery) {
                    @Override
                    protected void convert(RvViewHolder holder, DiscoveryFragmentBean.ItemListBean itemListBean, int pos) {
                        holder.setImgUrl(R.id.item_discovery_iv, itemListBean.getData().getImage());
                        holder.setText(R.id.item_discovery_tv, itemListBean.getData().getTitle());
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
