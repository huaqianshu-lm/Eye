package com.example.dllo.eyepetzier.ui.adapter.rv;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.Feed2ndReviewBean;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.IItemViewDelegate;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.RvViewHolder;
import com.example.dllo.eyepetzier.utils.TextStyleSetter;

/**
 * Created by dllo on 16/8/17.
 */
public class FeedReviewItemTypeTwo implements IItemViewDelegate<Feed2ndReviewBean.IssueListBean.ItemListBean>{
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_lv_fgmt_feed_type_2nd;
    }

    @Override
    public boolean isForViewType(Feed2ndReviewBean.IssueListBean.ItemListBean item, int pos) {
        return true;
    }

    @Override
    public void convert(RvViewHolder holder, Feed2ndReviewBean.IssueListBean.ItemListBean itemListBean, int pos) {

        holder.setText(R.id.item_lv_fgmt_feed_type_2nd_tv_title, );
        // tvTitle 字体加粗
         new TextStyleSetter().setBoldText(holder2nd.tvTitle.getPaint()); 

        holder.setText(R.id.item_lv_fgmt_feed_type_2nd_tv_category, "#" + datas.get(position).getData().getCategory() + " / " + time);

        if (datas.get(position).getData().getAuthor() != null) {
            holder.setText(R.id.item_lv_fgmt_feed_type_2nd_tv_author_name, );
        }

        holder.setImgUrl(R.id.item_lv_fgmt_feed_type_2nd_imgv_feed, );
        
    }

    /**
     * 用于将duration时间转换成分秒time
     * @param times
     * @return time字符串
     */
    private String formatTime(int times) {
        String minute = "";
        String second = "";
        int minutes = times / 60;
        int seconds = times % 60;
        if (minutes >= 10) {
            minute = "" + minutes;
        } else
            minute = "0" + minutes;
        if (seconds >= 10) {
            second = "" + seconds;
        } else
            second = "0" + seconds;
        return minute + "' " + second +"\"";
    }


}
