package com.example.dllo.eyepetzier.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.net.NetUrl;
import com.example.dllo.eyepetzier.ui.adapter.vp.MainViewAdapter;
import com.example.dllo.eyepetzier.ui.fragment.All2ndDetailFragment;
import com.example.dllo.eyepetzier.utils.EScreenSizeDensity;
import com.example.dllo.eyepetzier.utils.ScreenSize;
import com.example.dllo.eyepetzier.view.TitleTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/8/19.
 * 作者二级详情页
 */
public class Author2ndDetailActivity extends AbsBaseActivity {

    private TitleTextView tvTitle;
    private ImageView ivBack;
    private ImageView ivShare;
    private View lineTitle;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private CircleImageView cirImageView;
    private TextView tvAuthor;
    private TextView tvDescription;

    private RelativeLayout relatLay;

    private AppBarLayout appBarLayout1st;
    private AppBarLayout appBarLayout2nd;

    private Intent intent;

    private View lineTop;
    private View lineBottom;

    @Override
    protected int setLayout() {
        return R.layout.activity_author2nd_detail;
    }

    @Override
    protected void initView() {
        tvTitle = bindView(R.id.title_tv);
        ivBack = bindView(R.id.title_iv_back);
        ivShare = bindView(R.id.set_iv);
        lineTitle = bindView(R.id.title_line);
        tabLayout = bindView(R.id.acty_author2nd_detail_tablay);
        viewPager = bindView(R.id.acty_author2nd_detail_vp);
        cirImageView = bindView(R.id.acty_author2nd_detail_cirimg_logo);
        tvAuthor = bindView(R.id.acty_author2nd_detail_tv_author);
        tvDescription = bindView(R.id.acty_author2nd_detail_tv_description);
        relatLay = bindView(R.id.acty_author2nd_detail_include);
        appBarLayout1st = bindView(R.id.acty_author2nd_detail_appbarlayout_1st);
        appBarLayout2nd = bindView(R.id.acty_author2nd_detail_appbarlayout_2nd);
    }

    @Override
    protected void initData() {

        // init title bar
        initTitleBar();

        // init toolbar
        initToolbar();

        // init tab with vp
        initTabWithVp();
    }

    /**
     * 初始化标题栏
     */
    private void initTitleBar() {
        tvTitle.setVisibility(View.GONE);
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lineTitle.setVisibility(View.INVISIBLE);

        ivShare.setVisibility(View.VISIBLE);
        ivShare.setImageResource(R.mipmap.ic_action_share_grey);
        ivShare.setScaleType(ImageView.ScaleType.CENTER_CROP);

//        ViewTreeObserver vto = ivBack.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onGlobalLayout() {
//                ivBack.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivShare.getLayoutParams();
////                params.height = ivBack.getHeight();
////                params.width = ivBack.getWidth();
//                params.addRule(RelativeLayout.ALIGN_TOP,R.id.title_iv_back);
//                params.addRule(RelativeLayout.ALIGN_BOTTOM,R.id.title_iv_back);
//                ivShare.setLayoutParams(params);
//                ivShare.setImageResource(R.mipmap.ic_action_share_grey);
//                ivShare.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            }
//        });
    }

    /**
     * 初始化可变换toolbar
     */
    private void initToolbar() {
        intent = getIntent();
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) cirImageView.getLayoutParams();
        params.width = ScreenSize.getScreenSize(Author2ndDetailActivity.this, EScreenSizeDensity.WIDTH)/5;
        params.height = params.width;
        cirImageView.setLayoutParams(params);
        Picasso.with(this).load(intent.getStringExtra(NetUrl.KEY_LOGO)).error(R.mipmap.ic_launcher).into(cirImageView);
        tvAuthor.setText(intent.getStringExtra(NetUrl.KEY_AUTHOR));
        tvDescription.setText(intent.getStringExtra(NetUrl.KEY_DESCRIPTION));

        final LinearLayout.LayoutParams layoutParamsTitle = (LinearLayout.LayoutParams) cirImageView.getLayoutParams();
        // 获取标题栏宽高
        // oncreate方法直接获取高度为0
        ViewTreeObserver vto = relatLay.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {

                relatLay.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                layoutParamsTitle.topMargin = relatLay.getHeight();
                cirImageView.setLayoutParams(layoutParamsTitle);

            }
        });
        relatLay.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));

        final AppBarLayout.LayoutParams layoutParamsTool = (AppBarLayout.LayoutParams) tvDescription.getLayoutParams();
        vto = appBarLayout1st.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                appBarLayout1st.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                Log.e("vvv", "appBarLayout1st.getHeight():" + appBarLayout1st.getHeight());
                layoutParamsTool.topMargin = appBarLayout1st.getHeight()+180;
                tvDescription.setLayoutParams(layoutParamsTool);
            }
        });

        vto = tabLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                tabLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                tabLayout.setMinimumHeight(layoutParamsTitle.topMargin + tabLayout.getHeight() + 80);
            }
        });
    }

    /**
     * 初始化tab & vp
     */
    private void initTabWithVp() {
        List<String> titles = new ArrayList<>();
        titles.add("按时间排序");
        titles.add("按分享排序");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(All2ndDetailFragment.getDiscoveryDetailAllFragment(intent.getStringExtra(NetUrl.KEY_URL_AUTHOR_2ND_DETAIL_DATE)));
        fragments.add(All2ndDetailFragment.getDiscoveryDetailAllFragment(intent.getStringExtra(NetUrl.KEY_URL_AUTHOR_2ND_DETAIL_SHARE)));
        MainViewAdapter adapter = new MainViewAdapter(getSupportFragmentManager());
//        adapter.setFragments(fragments).setTitles(titles);
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.transparent));

        View view = getLayoutInflater().inflate(R.layout.view_tab_indicator_left, null);
        final View lineTopLeft = view.findViewById(R.id.view_tabindicator_line_top_left);
        final View lineBottomLeft = view.findViewById(R.id.view_tabindicator_line_bottom_left);
        TextView tv = (TextView) view.findViewById(R.id.view_tabindicator_tv_left);
        lineTopLeft.setVisibility(View.VISIBLE);
        lineBottomLeft.setVisibility(View.VISIBLE);
        tv.setText(titles.get(0));
        tv.setTextColor(Color.BLACK);
        tabLayout.getTabAt(0).setCustomView(view).select();

        view = getLayoutInflater().inflate(R.layout.view_tab_indicator_right, null);
        final View lineTopRight = view.findViewById(R.id.view_tabindicator_line_top_right);
        final View lineBottomRight = view.findViewById(R.id.view_tabindicator_line_bottom_right);
        tv = (TextView) view.findViewById(R.id.view_tabindicator_tv_right);
        tv.setText(titles.get(1));
        tv.setTextColor(Color.BLACK);
        tabLayout.getTabAt(1).setCustomView(view);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    lineTopLeft.setVisibility(View.VISIBLE);
                    lineBottomLeft.setVisibility(View.VISIBLE);
                    viewPager.setCurrentItem(0);
                } else if(tab.getPosition() == 1) {
                    lineTopRight.setVisibility(View.VISIBLE);
                    lineBottomRight.setVisibility(View.VISIBLE);
                    viewPager.setCurrentItem(1);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    lineTopLeft.setVisibility(View.INVISIBLE);
                    lineBottomLeft.setVisibility(View.INVISIBLE);
                } else if(tab.getPosition() == 1) {
                    lineTopRight.setVisibility(View.INVISIBLE);
                    lineBottomRight.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                Log.e("vvvvvv", "reselected");
            }
        });

    }
}
