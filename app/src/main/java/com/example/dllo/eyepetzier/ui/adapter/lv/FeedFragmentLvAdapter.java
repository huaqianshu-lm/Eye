package com.example.dllo.eyepetzier.ui.adapter.lv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.FeedFragmentBean;
import com.example.dllo.eyepetzier.view.TitleTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/8/13.
 *
 */
public class FeedFragmentLvAdapter extends BaseAdapter {

    private Context context;
    private List<FeedFragmentBean.SectionListBean.ItemListBean> datas;

    public FeedFragmentLvAdapter(Context context) {
        this.context = context;
    }

    public FeedFragmentLvAdapter setDatas(List<FeedFragmentBean.SectionListBean.ItemListBean> datas) {
        this.datas = datas;
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
    public FeedFragmentBean.SectionListBean.ItemListBean getItem(int position) {
        return ifNull() ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HolderType1st holder1st = null;
        HolderType2nd holder2nd = null;
        if (convertView == null) {
            switch (getItemType(position)){
                case TEXT_HEADER:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_fgmt_feed_type_1st, parent, false);
                    holder1st = new HolderType1st(convertView);
                    convertView.setTag(holder1st);
                    break;
                case VIDEO:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_fgmt_feed_type_2nd, parent, false);
                    holder2nd = new HolderType2nd(convertView);
                    convertView.setTag(holder2nd);
                    break;
            }
        } else {
            switch (getItemType(position)){
                case TEXT_HEADER:
                    holder1st = (HolderType1st) convertView.getTag();
                    break;
                case VIDEO:
                    holder2nd = (HolderType2nd) convertView.getTag();
                    break;
            }
        }

        switch (getItemType(position)){
            case TEXT_HEADER:
                holder1st.titleTextView.setText(datas.get(position).getData().getText());
                break;
            case VIDEO:
                holder2nd.tvTitle.setText(datas.get(position).getData().getTitle());
                String time = formatTime(position);
                holder2nd.tvCategory.setText("#" + datas.get(position).getData().getCategory() + " / " + time);
                if (datas.get(position).getData().getAuthor() != null) {
                    holder2nd.tvAuthorName.setVisibility(View.VISIBLE);
                    holder2nd.tvAuthorName.setText(datas.get(position).getData().getAuthor().getName());
                }
                Picasso.with(context).load(datas.get(position).getData().getCover().getFeed()).error(R.mipmap.ic_launcher).into(holder2nd.imgvFeed);
                break;
        }
//        convertView = getWrongView(position, convertView, parent);
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

    private View getWrongView(int position, View convertView, ViewGroup parent) {
        HolderType1st holder1st;
        HolderType2nd holder2nd;
        switch (getItemType(position)) {
            case TEXT_HEADER:
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_fgmt_feed_type_1st, parent, false);
                    holder1st = new HolderType1st(convertView);
                    convertView.setTag(holder1st);
                } else
                    holder1st = (HolderType1st) convertView.getTag();
//                Log.e("zzz",datas.get(0).getItemList().get(position).getData().getText() );
                holder1st.titleTextView.setText(datas.get(position).getData().getText());
                break;

            case VIDEO:
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_fgmt_feed_type_2nd, parent, false);
                    holder2nd = new HolderType2nd(convertView);
                    convertView.setTag(holder2nd);
                } else
                    holder2nd = (HolderType2nd) convertView.getTag();

                holder2nd.tvTitle.setText(datas.get(position).getData().getTitle());
                holder2nd.tvCategory.setText(datas.get(position).getData().getCategory());

                Picasso.with(context).load(datas.get(position).getData().getCover().getFeed()).error(R.mipmap.ic_launcher).into(holder2nd.imgvFeed);
                break;
        }
        return convertView;
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
     * 获取行布局类型
     *
     * @return
     */
    private ItemType getItemType(int position) {
        String type = datas.get(position).getType();
        switch (type) {
            case "textHeader":
                return ItemType.TEXT_HEADER;
            case "video":
                return ItemType.VIDEO;
        }
        return null;
    }

    /**
     * 行布局类型的枚举
     */
    private enum ItemType {
        TEXT_HEADER, VIDEO, FORWARD_FOOTER;
    }
}
