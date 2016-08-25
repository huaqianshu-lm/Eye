package com.example.dllo.eyepetzier.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.AuthorFragmentBean;
import com.example.dllo.eyepetzier.ui.adapter.vp.VideoVpAdapter;
import com.example.dllo.eyepetzier.utils.Contant;
import com.example.dllo.eyepetzier.utils.DepthPagerTransfromer;
import com.example.dllo.eyepetzier.utils.L;
import com.example.dllo.eyepetzier.utils.T;
import com.example.dllo.eyepetzier.view.TypeTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/8/19.
 */
public class VideoIntroduceActivity extends AbsBaseActivity implements TypeTextView.OnTypeViewListener, View.OnClickListener {
    private ViewPager viewPager;
    private VideoVpAdapter videoVpAdapter;
    private List<View> views;//viewpager里的view的集合
    private TabLayout tabLayout;
    private AuthorFragmentBean.ItemListBean.DataBean dataBean;
    private TypeTextView titleTv;
    private TextView secondTitleTv;
    private TextView secondSubTitleTv;
    private TextView secondDescriptionTv;
    private TypeTextView contentTv;
    private ImageView backIv;
    private ImageView playIv;
    private ImageView toDetailIv;
    private CircleImageView iconIv;
    private ImageView bgIv;
    private ImageView blurIv;
    private LinearLayout likeLl;
    private LinearLayout shareLl;
    private LinearLayout commentLl;
    private LinearLayout downloadLl;
    private TypeTextView categoryTv;
    private TypeTextView durationTv;
    private View view;// viewpager里放在view
    private RelativeLayout textInfoRl;
    private List<AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean> videoItemListBeen;
    private AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean videoItemListBean;
    private AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean.CoverBean coverBean;
    private AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean videoDataBean;
    private int width; // 屏幕的宽度
    private int height; // 屏幕的高度
    private Animation animation; // 图片的缩放动画
    private TextView likeTv, shareTv, commmentTv;
    private Bundle toPlayBundle;
    private int pos;// 当前视频的位置,由上一个界面传来
    private int nextPos; // 在该界面滑动的位置,传到下一个界面
    private int bgHeight;
    private RelativeLayout loadingRl;
    private ImageView loadingIv;


    @Override
    protected int setLayout() {
        return R.layout.activity_video_introduce;
    }

    @Override
    protected void initView() {
        initWidget();
        backIv.setAlpha(0.6f);
        videoVpAdapter = new VideoVpAdapter();
        views = new ArrayList<>();
    }

    @Override
    protected void initData() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_loading);
        loadAnimation.setInterpolator(new LinearInterpolator());
        loadingIv.startAnimation(loadAnimation);
        Intent intent = getIntent();
        pos = intent.getIntExtra(Contant.VIDEO_POS, 0);
        L.d("pos", pos + " =======");
        dataBean = intent.getParcelableExtra(Contant.TO_VIDEO);
        // 获取屏幕的宽和高
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        bgHeight = height / 17 * 9;
//        Log.e("VideoIntroduceActivity", dataBean.getText());
        if (dataBean != null) {
            // 刚进入时的界面
            videoItemListBeen = dataBean.getItemList();
            L.d(videoItemListBeen.size() + " ----------");
            videoItemListBean = videoItemListBeen.get(pos);
            loadingRl.setVisibility(View.GONE);
            setVp();
            int count = videoItemListBeen.size();
            for (int i = 0; i < count; i++) {
                view = LayoutInflater.from(this).inflate(R.layout.item_video_introduce_vp, null);
                bgIv = (ImageView) view.findViewById(R.id.item_video_introduce_vp_bg_iv);
                bgIv.setOnClickListener(this);
                // 设置图片缩放动画
                animation = new ScaleAnimation(1, 1.1f, 1, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setRepeatCount(Animation.INFINITE);
                animation.setRepeatMode(Animation.REVERSE);
                animation.setDuration(4000);
                bgIv.setAnimation(animation);
                loadAnimation.startNow();
                AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean videoItemListBean = videoItemListBeen.get(i);
                AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean.CoverBean coverBean = videoItemListBean.getData().getCover();
                Picasso.with(this).load(coverBean.getDetail()).resize(width, bgHeight).into(bgIv);
                Log.d("VideoIntroduceActivity", coverBean.getDetail());
                views.add(view);
            }
            // 设打字效果
            contentTv.setListener(this);
            videoVpAdapter.setViews(views);
            viewPager.setAdapter(videoVpAdapter);
            viewPager.setPageTransformer(true, new DepthPagerTransfromer());
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setSelectedTabIndicatorHeight(3);
            viewPager.setCurrentItem(pos);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    // 当滑动时动画停止
                    bgIv.clearAnimation();
                    // 当滑动的时候组件的透明度的变化
                    if (positionOffset > 0 && positionOffset < 0.5) {
                        float alpha = 1.0f - positionOffset * 2;
                        playIv.setAlpha(alpha);
                        backIv.setAlpha(alpha);
                    } else if (positionOffset > 0.5 && positionOffset < 1) {
                        float alpha = positionOffset * 2 - 1.0f;
                        playIv.setAlpha(alpha);
                        backIv.setAlpha(alpha);
                    }
                    textInfoRl.setAlpha(Math.abs(positionOffset - 1.0f));
                }

                @Override
                public void onPageSelected(int position) {
                    // 停止滑动时动画开始
                    animation.startNow();
                    videoItemListBean = videoItemListBeen.get(position);
                    setVp();
                    nextPos = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

    }

    /**
     * 设置页面的内容
     */
    private void setVp() {
        videoDataBean = videoItemListBean.getData();
        coverBean = videoDataBean.getCover();
        contentTv.start(videoDataBean.getDescription());
        titleTv.start(videoDataBean.getTitle());
        categoryTv.start(videoDataBean.getCategory(),10);
        // 设置title,分类时间
        videoDataBean = videoItemListBean.getData();
        int min = videoDataBean.getDuration() / 60;
        int sec = videoDataBean.getDuration() % 60;
        String duration = String.valueOf(min) + "'" + String.valueOf(sec) + "\"";
        durationTv.start(duration);
        int blurIvHeight = height / 17 * 8;
        float rotateWidth = width / 2;
        float rotateHeight = blurIvHeight / 2;
        Picasso.with(VideoIntroduceActivity.this).load(coverBean.getBlurred()).resize(width, blurIvHeight).rotate(180f, rotateWidth, rotateHeight).into(blurIv);
        // 设置带图标的内容
        AuthorFragmentBean.ItemListBean.DataBean.HeaderBean headerBean = dataBean.getHeader();
        if (headerBean != null) {
            Picasso.with(VideoIntroduceActivity.this).load(headerBean.getIcon()).resize(150, 150).into(iconIv);
            secondTitleTv.setText(headerBean.getTitle());
            secondSubTitleTv.setText(headerBean.getSubTitle());
            secondDescriptionTv.setText(headerBean.getDescription());
        }
        AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean.ConsumptionBean consumptionBean = videoDataBean.getConsumption();
        likeTv.setText(String.valueOf(consumptionBean.getCollectionCount()));
        shareTv.setText(String.valueOf(consumptionBean.getShareCount()));
        commmentTv.setText(String.valueOf(consumptionBean.getReplyCount()));

    }

    /**
     * 初始化组件
     */
    private void initWidget() {
        viewPager = bindView(R.id.video_introduce_activity_viewpager);
        tabLayout = bindView(R.id.video_introduce_activity_tablayout);
        toDetailIv = bindView(R.id.item_video_introduce_vp_to_detail_iv);
        iconIv = bindView(R.id.item_video_introduce_vp_civ);
        blurIv = bindView(R.id.item_video_introduce_vp_blur_iv);
        likeLl = bindView(R.id.item_video_introduce_like_ll);
        shareLl = bindView(R.id.item_video_introduce_share_ll);
        commentLl = bindView(R.id.item_video_introduce_comment_ll);
        downloadLl = bindView(R.id.item_video_introduce_download_ll);
        categoryTv = bindView(R.id.item_video_introduce_vp_subtitle_tv);
        durationTv = bindView(R.id.item_video_introduce_vp_duration_tv);
        titleTv = bindView(R.id.item_video_introduce_vp_title_tv);
        secondTitleTv = bindView(R.id.item_video_introduce_vp_second_title_tv);
        secondSubTitleTv = bindView(R.id.item_video_introduce_vp_second_subtitle_tv);
        secondDescriptionTv = bindView(R.id.item_video_introduce_vp_description_tv);
        contentTv = bindView(R.id.item_video_introduce_vp_content_tv);
        backIv = bindView(R.id.item_video_introduce_vp_back_iv);
        playIv = bindView(R.id.item_video_introduce_vp_play_iv);
        textInfoRl = bindView(R.id.video_introduce_activity_info_rl);
        likeTv = bindView(R.id.item_video_introduce_vp_like_tv);
        shareTv = bindView(R.id.item_video_introduce_vp_share_tv);
        commmentTv = bindView(R.id.item_video_introduce_vp_comment_tv);
        loadingIv = bindView(R.id.video_introduce_activity_loading_iv);
        loadingRl = bindView(R.id.video_introduce_activity_loading_rl);
        backIv.setOnClickListener(this);
        toDetailIv.setOnClickListener(this);
        playIv.setOnClickListener(this);

    }

    @Override
    public void onTypeStart() {

    }

    @Override
    public void onTypeOver() {

    }


    @Override
    public void onClick(View v) {
        toPlayBundle = new Bundle();
        toPlayBundle.putParcelable(Contant.VIDEO_TO_PLAY, dataBean);
        toPlayBundle.putInt(Contant.VIDEO_POS, nextPos);
        switch (v.getId()) {
            // 返回
            case R.id.item_video_introduce_vp_back_iv:
                finish();
                break;
            // 跳到下一个详情界面
            case R.id.item_video_introduce_vp_to_detail_iv:
                T.shortMsg("跳转到下一个界面");
                break;
            // 跳转到播放视频页
            case R.id.item_video_introduce_vp_bg_iv:

                goTo(VideoIntroduceActivity.this, PlayActivity.class, toPlayBundle);
                T.shortMsg("播放视频");
                break;
            case R.id.item_video_introduce_vp_play_iv:
                goTo(VideoIntroduceActivity.this, PlayActivity.class, toPlayBundle);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goTo(this, MainActivity.class);
    }
}
