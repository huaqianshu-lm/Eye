package com.example.dllo.eyepetzier.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.ui.activity.DownloadActivity;

/**
 * Created by dllo on 16/8/12.
 * 我的页面的fragment
 */
public class MineFragment extends AbaBaseFragment implements View.OnClickListener {
    private TextView downloadTv;
    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        downloadTv = bindView(R.id.mine_fragment_cache_tv);
        downloadTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_fragment_cache_tv:
                goTo(context, DownloadActivity.class);
                break;
        }
    }
}
