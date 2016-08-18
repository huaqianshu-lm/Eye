package com.example.dllo.eyepetzier.ui.adapter.lv;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.Feed2ndReviewBean;
import com.example.dllo.eyepetzier.mode.bean.FeedFragmentBean;
import com.example.dllo.eyepetzier.utils.EScreenSizeDensity;
import com.example.dllo.eyepetzier.utils.ScreenSize;
import com.example.dllo.eyepetzier.utils.TextStyleSetter;
import com.example.dllo.eyepetzier.view.TitleTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/8/13.
 *
 */
public class Feed2ndReviewLvAdapter extends BaseAdapter {

    private Context context;
    private List<Feed2ndReviewBean.IssueListBean.ItemListBean> datas;
    /**
     * 不同行type
     */
    private static final int TYPE_1 = 0;
    private static final int TYPE_2 = 1;

    public Feed2ndReviewLvAdapter(Context context) {
        this.context = context;
    }

    public Feed2ndReviewLvAdapter setDatas(List<Feed2ndReviewBean.IssueListBean.ItemListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
        return this;
    }

    /**
     *  行尾添加单条数据
     */
    public Feed2ndReviewLvAdapter addLastItem(Feed2ndReviewBean.IssueListBean.ItemListBean data) {
        datas.add(data);
        notifyDataSetChanged();
        return this;
    }

    /**
     * 行尾添加数据组
     */
    public Feed2ndReviewLvAdapter addLastItem(List<Feed2ndReviewBean.IssueListBean.ItemListBean> datas) {

        this.datas.addAll(datas);
        notifyDataSetChanged();
        return this;
    }

    private boolean ifNull() {
        return datas != null && datas.size() > 0;
    }

    @Override
    public int getCount() {
        return ifNull() ? datas.size() : 0;
    }

    @Override
    public Feed2ndReviewBean.IssueListBean.ItemListBean getItem(int position) {
        return ifNull() ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int viewType = getItemViewType(position);
        HolderType1st holder1st = null;
        HolderType2nd holder2nd = null;
        if (convertView == null) {
            switch (viewType){
                case TYPE_1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_fgmt_feed_type_1st, parent, false);
                    holder1st = new HolderType1st(convertView);
                    convertView.setTag(holder1st);
                    break;
                case TYPE_2:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_fgmt_feed_type_2nd, parent, false);
                    holder2nd = new HolderType2nd(convertView);
                    convertView.setTag(holder2nd);

                    // 指定item的高度
                    ViewGroup.LayoutParams params = convertView.getLayoutParams();
                    params.height = ScreenSize.getScreenSize(context, EScreenSizeDensity.HEIGHT) * 2 / 5;
                    params.width = ScreenSize.getScreenSize(context, EScreenSizeDensity.WIDTH);
                    convertView.setLayoutParams(params);
                    break;
            }

        } else {
            switch (viewType){
                case TYPE_1:
                    holder1st = (HolderType1st) convertView.getTag();
                    break;
                case TYPE_2:
                    holder2nd = (HolderType2nd) convertView.getTag();
                    break;
            }
        }

        switch (viewType){

            case TYPE_1:
                holder1st.titleTextView.setText(datas.get(position).getData().getText());
                break;
            case TYPE_2:
                holder2nd.tvTitle.setText(datas.get(position).getData().getTitle());
                // tvTitle 字体加粗
                new TextStyleSetter().setBoldText(holder2nd.tvTitle.getPaint());
                String time = formatTime(position);
                holder2nd.tvCategory.setText("#" + datas.get(position).getData().getCategory() + " / " + time);
                if (datas.get(position).getData().getAuthor() != null) {
                    holder2nd.tvAuthorName.setVisibility(View.VISIBLE);
                    holder2nd.tvAuthorName.setText(datas.get(position).getData().getAuthor().getName());
                }
                Picasso.with(context).load(datas.get(position).getData().getCover().getFeed()).config(Bitmap.Config.RGB_565).skipMemoryCache().error(R.mipmap.ic_launcher).into(holder2nd.imgvFeed);
                break;
        }
        return convertView;
    }

    /**
     * 用于将duration时间转换成分秒time
     * @param position
     * @return time字符串
     */
    @NonNull
    private String formatTime(int position) {
        int times = datas.get(position).getData().getDuration();
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

    /**
     * type1 holder
     */
    class HolderType1st {

        private TitleTextView titleTextView;

        public HolderType1st(View itemView) {
            titleTextView = (TitleTextView) itemView.findViewById(R.id.item_lv_fgmt_feed_type_1st_tv);
        }
    }

    /**
     * type2 holder
     */
    class HolderType2nd {

        private final TextView tvTitle;
        private final TextView tvCategory;
        private final TextView tvAuthorName;
        private final ImageView imgvFeed;

        public HolderType2nd(View itemView) {

            tvTitle = (TextView) itemView.findViewById(R.id.item_lv_fgmt_feed_type_2nd_tv_title);
            tvCategory = (TextView) itemView.findViewById(R.id.item_lv_fgmt_feed_type_2nd_tv_category);
            tvAuthorName = (TextView)itemView.findViewById(R.id.item_lv_fgmt_feed_type_2nd_tv_author_name);
            imgvFeed = (ImageView) itemView.findViewById(R.id.item_lv_fgmt_feed_type_2nd_imgv_feed);
        }
    }

    /**
     * 少复写了2个系统方法
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        String type = datas.get(position).getType();
        switch (type) {
            case "textHeader":
                return TYPE_1;
            case "video":
                return TYPE_2;
        }

        return super.getItemViewType(position);
    }

    /**
     * 获取行布局类型
     *
     * @return
     */
//    private ItemType getItemType(int position) {
//        String type = datas.get(position).getType();
//        switch (type) {
//            case "textHeader":
//                return ItemType.TEXT_HEADER;
//            case "video":
//                return ItemType.VIDEO;
//        }
//        return null;
//    }

    /**
     * 行布局类型的枚举
     */
    private enum ItemType {
        TEXT_HEADER, VIDEO, FORWARD_FOOTER;
    }
}
