package com.example.dllo.eyepetzier.ui.adapter;

import android.content.Context;
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
 */
public class FeedFragmentAdapter extends BaseAdapter {

    private Context context;
    private List<FeedFragmentBean.SectionListBean> datas;

    public FeedFragmentAdapter(Context context) {
        this.context = context;
    }

    public FeedFragmentAdapter setDatas(List<FeedFragmentBean.SectionListBean> datas) {
        this.datas = datas;
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
    public FeedFragmentBean.SectionListBean getItem(int position) {
        return ifNull() ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HolderType1st type1st = null;
        HolderType2nd type2nd = null;
        switch (getItemType(position)) {
            case TEXT_HEADER:

                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_fgmt_feed_type_1st, parent, false);
                    type1st = new HolderType1st(convertView);
                    convertView.setTag(type1st);
                } else
                    type1st = (HolderType1st) convertView.getTag();

                type1st.titleTextView.setText(datas.get(0).getItemList().get(position).getData().getText());
                return convertView;

            case VIDEO:
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_fgmt_feed_type_2nd, parent, false);
                    type2nd = new HolderType2nd(convertView);
                    convertView.setTag(type2nd);
                } else
                    type2nd = (HolderType2nd) convertView.getTag();

                type2nd.tvTitle.setText(datas.get(0).getItemList().get(position).getData().getTitle());
                type2nd.tvCategory.setText(datas.get(0).getItemList().get(position).getData().getCategory());
                Picasso.with(context).load(datas.get(0).getItemList().get(position).getData().getCover().getFeed()).error(R.mipmap.ic_launcher).into(type2nd.imgvFeed);
                return convertView;
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
        private final ImageView imgvFeed;

        public HolderType2nd(View itemView) {

            tvTitle = (TextView) itemView.findViewById(R.id.item_lv_fgmt_feed_type_2nd_tv_title);
            tvCategory = (TextView) itemView.findViewById(R.id.item_lv_fgmt_feed_type_2nd_tv_category);
            imgvFeed = (ImageView) itemView.findViewById(R.id.item_lv_fgmt_feed_type_2nd_imgv_feed);

        }
    }


    /**
     * 获取行布局类型
     *
     * @return
     */
    private ItemType getItemType(int position) {
        String type = datas.get(0).getItemList().get(position).getType();
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
