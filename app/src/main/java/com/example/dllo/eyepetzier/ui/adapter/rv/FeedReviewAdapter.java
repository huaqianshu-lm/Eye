package com.example.dllo.eyepetzier.ui.adapter.rv;

import android.content.Context;

import com.example.dllo.eyepetzier.mode.bean.Feed2ndReviewBean;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.AbsRvAdapter;

import java.util.List;

/**
 * Created by dllo on 16/8/17.
 * 精选二级页面 : 查看所有的rv adpater
 */
public class FeedReviewAdapter extends AbsRvAdapter<Feed2ndReviewBean.IssueListBean.ItemListBean>{

    public FeedReviewAdapter(Context context, List<Feed2ndReviewBean.IssueListBean.ItemListBean> datas) {
        super(context, datas);
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getType().equals("video")) {
                addItemViewDelegate(new FeedReviewItemTypeTwo());
            } else if (datas.get(i).getType().equals("textHeader")) {
                addItemViewDelegate(new FeedReviewItemTypeOne());
            }
        }
    }
}
