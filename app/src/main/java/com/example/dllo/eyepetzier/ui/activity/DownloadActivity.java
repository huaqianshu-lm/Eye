package com.example.dllo.eyepetzier.ui.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.AuthorFragmentBean;
import com.example.dllo.eyepetzier.utils.L;
import com.example.dllo.eyepetzier.utils.T;

import java.util.List;

/**
 * Created by dllo on 16/8/28.
 */
public class DownloadActivity extends AbsBaseActivity implements View.OnClickListener {
    private TextView editTv;
    private TextView byselfDownloadTv;
    private TextView autoDownloadTv;
    private RecyclerView mRecyclerView;
    private ImageView backIv;
    private AuthorFragmentBean.ItemListBean.DataBean mDataBean;
    private List<AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean> mVideoItemListBeen;
    private AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean mVideoItemListBean;

    @Override
    protected int setLayout() {
        return R.layout.activity_download;
    }

    @Override
    protected void initView() {
        editTv = bindView(R.id.download_aty_edit_tv);
        byselfDownloadTv = bindView(R.id.download_aty_byself_download_tv);
        autoDownloadTv = bindView(R.id.download_aty_auto_download_tv);
        mRecyclerView = bindView(R.id.download_aty_rv);
        backIv = bindView(R.id.download_aty_back_iv);
        backIv.setOnClickListener(this);
        editTv.setOnClickListener(this);
        byselfDownloadTv.setOnClickListener(this);
        autoDownloadTv.setOnClickListener(this);
        Log.d("DownloadActivity", "dwon");


    }

    @Override
    protected void initData() {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
            String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
            long  duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
            long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
            String displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
            L.d(id + title + path + duration + size + displayName);
        }
        cursor.close();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download_aty_edit_tv:
                T.shortMsg("edit");
                break;
            case R.id.download_aty_byself_download_tv:
                T.shortMsg("byself");
                break;
            case R.id.download_aty_auto_download_tv:
                T.shortMsg("auto");
                break;
            case R.id.download_aty_back_iv:
                finish();
                break;
        }
    }
}
