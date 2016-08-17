package com.example.dllo.eyepetzier.ui.adapter.rv;

import android.util.Log;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.Feed2ndReviewBean;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.IItemViewDelegate;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.RvViewHolder;
import com.example.dllo.eyepetzier.utils.TimeFormator;

/**
 * Created by dllo on 16/8/17.
 */
public class FeedReviewItemTypeOne implements IItemViewDelegate<Feed2ndReviewBean.IssueListBean.ItemListBean>{
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_lv_fgmt_feed_type_1st;
    }

    @Override
    public boolean isForViewType(Feed2ndReviewBean.IssueListBean.ItemListBean item, int pos) {
        return true;
    }

    @Override
    public void convert(RvViewHolder holder, Feed2ndReviewBean.IssueListBean.ItemListBean itemListBean, int pos) {
        holder.setText(R.id.item_lv_fgmt_feed_type_1st_tv, itemListBean.getData().getText());
    }


}
