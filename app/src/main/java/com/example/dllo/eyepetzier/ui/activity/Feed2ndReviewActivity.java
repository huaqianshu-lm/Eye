package com.example.dllo.eyepetzier.ui.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.Feed2ndReviewBean;
import com.example.dllo.eyepetzier.utils.TextStyleSetter;
import com.example.dllo.eyepetzier.utils.TimeFormator;
import com.example.dllo.eyepetzier.view.TitleTextView;

/**
 * Created by dllo on 16/8/17.
 * 精选二级页面 : 每日精选
 * 由一级页面 查看往期编辑精选 跳转
 */
public class Feed2ndReviewActivity extends AbsBaseActivity{

    private TitleTextView tvTitle;
    private TitleTextView tvTime;
    private ImageView ivBack;
    private ImageView ivSearch;
    private RecyclerView rv;

    @Override
    protected int setLayout() {
        return R.layout.activity_feed2nd_review;
    }

    @Override
    protected void initView() {
        tvTitle = bindView(R.id.title_tv);
        tvTime = bindView(R.id.title_time);
        ivBack = bindView(R.id.title_iv_back);
        ivSearch = bindView(R.id.title_iv);
        rv = bindView(R.id.acty_feed2nd_review_rv);
    }

    @Override
    protected void initData() {
        // setTitle
        setTitle();
        // init rv

    }

    private void setTitle() {
        tvTitle.setText("每日精选");
        new TextStyleSetter().setBoldText(tvTitle.getPaint());
        tvTitle.setTextSize(20);

        tvTime.setVisibility(View.VISIBLE);
        tvTime.setText("Today");

        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivSearch.setVisibility(View.GONE);
    }
}

