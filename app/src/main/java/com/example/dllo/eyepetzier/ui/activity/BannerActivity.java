package com.example.dllo.eyepetzier.ui.activity;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.BannerBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.ui.adapter.vp.InnerAdapter;
import com.example.dllo.eyepetzier.view.swipeadapterview.SwipeAdapterView;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by dllo on 16/8/30.
 * banner的详情界面
 */
public class BannerActivity extends AbsBaseActivity implements SwipeAdapterView.onFlingListener {
    private String url = "http://baobab.wandoujia.com/api/v3/recommend?udid=0cabbeb79d7941f88fd57610de6665101860dbcf&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_360_market&last_channel=eyepetizer_360_market&system_version_code=22";
    private int cardWidth;
    private int cardHeight;

    private SwipeAdapterView swipeView;
    private InnerAdapter adapter;

    private ImageView imageView;
    private List<BannerBean.ItemListBean> listBeen;
    private int i = 0;
    private TextView textView;

    public BannerActivity() {
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_banner;
    }

    @Override
    protected void initView() {
        imageView = (ImageView) findViewById(R.id.iv);
        textView = (TextView) findViewById(R.id.num);
        swipeView = (SwipeAdapterView) findViewById(R.id.swipe_view);
    }

    @Override
    protected void initData() {
        textView.setText("1");
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        cardWidth = (int) (dm.widthPixels - (2 * 18 * density));
        cardHeight = (int) (dm.heightPixels - (338 * density));
//        swipeView.setIsNeedSwipe(false);
        swipeView.setFlingListener(this);
        adapter = new InnerAdapter();
        swipeView.setAdapter(adapter);
        loadData();
    }



    @Override
    public void removeFirstObjectInAdapter() {
        adapter.remove(0);
        /**
         * 背景图片变换
         */
        if (i < listBeen.size() - 1) {
            i++;
            String url = listBeen.get(i).getData().getCover().getBlurred();
            Picasso.with(BannerActivity.this).load(url).into(imageView);
            int p = i + 1;
            textView.setText(p + "");
        } else {
            i = 0;
            Picasso.with(BannerActivity.this).load(listBeen.get(0).getData().getCover().getBlurred()).into(imageView);
            textView.setText("1");
        }
    }

    @Override
    public void onLeftCardExit(Object dataObject) {

    }

    @Override
    public void onRightCardExit(Object dataObject) {


    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        if (itemsInAdapter == 3) {
            loadData();

        }
    }

    @Override
    public void onScroll(float progress, float scrollXProgress) {
    }

    private void loadData() {

        NetRequestSingleton.getInstance().startRequest(url, BannerBean.class, new IOnHttpCallback<BannerBean>() {
            @Override
            public void onSuccess(BannerBean response) {
                listBeen = response.getItemList();
                adapter.addAll(listBeen, cardWidth, cardHeight);
                Picasso.with(BannerActivity.this).load(listBeen.get(0).getData().getCover().getBlurred()).into(imageView);
            }

            @Override
            public void onError(Throwable ex) {

            }
        });
    }
}
