package com.example.dllo.eyepetzier.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.FeedFragmentBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.mode.net.NetUrl;
import com.example.dllo.eyepetzier.ui.adapter.lv.FeedFragmentLvAdapter;
import com.example.dllo.eyepetzier.ui.adapter.rv.CommonRvAdapter;
import com.example.dllo.eyepetzier.ui.adapter.rv.RvViewHolder;
import com.example.dllo.eyepetzier.view.TitleTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/8/12.
 * 精选页面的fragment
 */
public class FeedFragment extends AbaBaseFragment {

    /**
     * 第一块 listview
     */
    private ListView listView;
    private TextView textViewLvFooter;
    private View footView;
    /**
     * 第二块 recyclerView
     */
    private ImageView imageView2ndPart;
    private RecyclerView rv2ndPart;
    /**
     * 第三块
     */
    private TitleTextView tvHeader3rdPart;
    private RelativeLayout relatLay3rdPart;
    private TextView tvTitle3rdPart;
    private RecyclerView rv3rdPart;
    // listview的后半部分
    private View footViewFeed;
    private CircleImageView circleImgv3rdPart;
    private TextView tvSubTitle3rdPart;
    private TextView tvDescrip3rdPart;
    /**
     * 第四块
     */
    private RelativeLayout relatLay4thPart;
    private TextView tvTitle4thPart;
    private RecyclerView rv4thPart;
    private CircleImageView circleImgv4thPart;
    private TextView tvSubTitle4thPart;
    private TextView tvDescrip4thPart;
    private RelativeLayout rlFooter4thPart;
    private TextView tvFooter4thPart;

    @Override
    protected int setLayout() {
        return R.layout.fragment_feed;
    }

    @Override
    protected void initView() {
        // 1st part
        listView = bindView(R.id.fgmt_feed_listview);
        footView = getActivity().getLayoutInflater().inflate(R.layout.item_lv_fgmt_feed_type_foot, null);
        textViewLvFooter = (TextView) footView.findViewById(R.id.item_lv_fgmt_feed_type_foot_tv);
        // 2nd part
        footViewFeed = LayoutInflater.from(context).inflate(R.layout.fragment_feed_foot_view, null);
        imageView2ndPart = (ImageView) footViewFeed.findViewById(R.id.fgmt_feed_include_1st).findViewById(R.id.view_reuse_fgmt_feed_imgv_header_1st);
        rv2ndPart = (RecyclerView)  footViewFeed.findViewById(R.id.fgmt_feed_include_1st).findViewById(R.id.view_reuse_fgmt_feed_rv_1st);
        // 3rd part
        tvHeader3rdPart = (TitleTextView) footViewFeed.findViewById(R.id.fgmt_feed_include_2nd).findViewById(R.id.view_reuse_fgmt_feed_tv_header_2nd);
        relatLay3rdPart = (RelativeLayout) footViewFeed.findViewById(R.id.fgmt_feed_include_2nd).findViewById(R.id.view_reuse_fgmt_feed_relatlay_2nd);
        circleImgv3rdPart = (CircleImageView) footViewFeed.findViewById(R.id.fgmt_feed_include_2nd).findViewById(R.id.view_reuse_fgmt_feed_cirimg_icon_2nd);
        tvTitle3rdPart = (TextView) footViewFeed.findViewById(R.id.fgmt_feed_include_2nd).findViewById(R.id.view_reuse_fgmt_feed_tv_title_2nd);
        tvSubTitle3rdPart = (TextView) footViewFeed.findViewById(R.id.fgmt_feed_include_2nd).findViewById(R.id.view_reuse_fgmt_feed_tv_subtitle_2nd);
        tvDescrip3rdPart = (TextView) footViewFeed.findViewById(R.id.fgmt_feed_include_2nd).findViewById(R.id.view_reuse_fgmt_feed_tv_description_2nd);
        rv3rdPart = (RecyclerView) footViewFeed.findViewById(R.id.fgmt_feed_include_2nd).findViewById(R.id.view_reuse_fgmt_feed_rv_2nd);
        // 4th part
        relatLay4thPart = (RelativeLayout) footViewFeed.findViewById(R.id.fgmt_feed_include_3rd).findViewById(R.id.view_reuse_fgmt_feed_relatlay_3rd);
        circleImgv4thPart = (CircleImageView) footViewFeed.findViewById(R.id.fgmt_feed_include_3rd).findViewById(R.id.view_reuse_fgmt_feed_cirimg_icon_3rd);
        tvTitle4thPart = (TextView) footViewFeed.findViewById(R.id.fgmt_feed_include_3rd).findViewById(R.id.view_reuse_fgmt_feed_tv_title_3rd);
        tvSubTitle4thPart = (TextView) footViewFeed.findViewById(R.id.fgmt_feed_include_3rd).findViewById(R.id.view_reuse_fgmt_feed_tv_subtitle_3rd);
        tvDescrip4thPart = (TextView) footViewFeed.findViewById(R.id.fgmt_feed_include_3rd).findViewById(R.id.view_reuse_fgmt_feed_tv_description_3rd);
        rv4thPart = (RecyclerView) footViewFeed.findViewById(R.id.fgmt_feed_include_3rd).findViewById(R.id.view_reuse_fgmt_feed_rv_3rd);
        rlFooter4thPart = (RelativeLayout) footViewFeed.findViewById(R.id.fgmt_feed_include_3rd).findViewById(R.id.view_reuse_fgmt_feed_include_foot_3rd);
        tvFooter4thPart = (TextView) rlFooter4thPart.findViewById(R.id.item_lv_fgmt_feed_type_foot_tv);
    }

    @Override
    protected void initData() {

        initListview();

    }

    private void initListview() {

        NetRequestSingleton.getInstance().startRequest(NetUrl.FEED_FRAGMENT_URL, FeedFragmentBean.class, new IOnHttpCallback<FeedFragmentBean>() {
            @Override
            public void onSuccess(FeedFragmentBean response) {

                // set 1st part
                set1stPart(response);
                // set 2nd part
                set2ndPart(response);
                // set 3rd part
                set3rdPart(response);
                // set 4th part
                set4thPart(response);
            }

            private void set4thPart(FeedFragmentBean response) {
                relatLay4thPart.setVisibility(View.VISIBLE);
                FeedFragmentBean.SectionListBean.ItemListBean.DataBean.HeaderBean headerBean = response.getSectionList().get(2).getItemList().get(1).getData().getHeader();
                Picasso.with(context).load(headerBean.getIcon()).error(R.mipmap.ic_launcher).into(circleImgv4thPart);
                tvTitle4thPart.setText(headerBean.getTitle());
                tvSubTitle4thPart.setText(headerBean.getSubTitle());
                tvDescrip4thPart.setText(headerBean.getDescription());
                initRV(response.getSectionList().get(2).getItemList().get(1).getData().getItemList(), rv4thPart);
                rlFooter4thPart.setVisibility(View.VISIBLE);
                tvFooter4thPart.setText(response.getSectionList().get(2).getFooter().getData().getText());

            }

            private void set3rdPart(FeedFragmentBean response) {
                tvHeader3rdPart.setVisibility(View.VISIBLE);
                tvHeader3rdPart.setText(response.getSectionList().get(2).getHeader().getData().getText());

                relatLay3rdPart.setVisibility(View.VISIBLE);
                FeedFragmentBean.SectionListBean.ItemListBean.DataBean.HeaderBean headerBean = response.getSectionList().get(2).getItemList().get(0).getData().getHeader();
                Picasso.with(context).load(headerBean.getIcon()).error(R.mipmap.ic_launcher).into(circleImgv3rdPart);
                tvTitle3rdPart.setText(headerBean.getTitle());
                tvSubTitle3rdPart.setText(headerBean.getSubTitle());
                tvDescrip3rdPart.setText(headerBean.getDescription());

                initRV(response.getSectionList().get(2).getItemList().get(0).getData().getItemList(), rv3rdPart);
            }

            private void set1stPart(FeedFragmentBean response) {

                FeedFragmentLvAdapter adapter = new FeedFragmentLvAdapter(getContext());
                adapter.setDatas(response.getSectionList().get(0).getItemList());
                listView.setAdapter(adapter);
                textViewLvFooter.setText(response.getSectionList().get(0).getFooter().getData().getText());
                listView.addFooterView(footView);
                listView.addFooterView(footViewFeed);
            }

            private void set2ndPart(FeedFragmentBean response) {
                imageView2ndPart.setVisibility(View.VISIBLE);
                Picasso.with(getContext()).load(response.getSectionList().get(1).getItemList().get(0).getData().getHeader().getCover()).error(R.mipmap.ic_launcher).into(imageView2ndPart);

                List<FeedFragmentBean.SectionListBean.ItemListBean.DataBean.ItemListBeanInner> datas = response.getSectionList().get(1).getItemList().get(0).getData().getItemList();

                initRV(datas, rv2ndPart);
            }

            private void initRV(final List<FeedFragmentBean.SectionListBean.ItemListBean.DataBean.ItemListBeanInner> datas, RecyclerView rv) {
                CommonRvAdapter<FeedFragmentBean.SectionListBean.ItemListBean.DataBean.ItemListBeanInner> adapterRv =
                        new CommonRvAdapter<FeedFragmentBean.SectionListBean.ItemListBean.DataBean.ItemListBeanInner>(
                                context,
                                datas,
                                R.layout.item_lv_fgmt_feed_type_2nd) {

                            @Override
                            protected void convert(RvViewHolder holder, FeedFragmentBean.SectionListBean.ItemListBean.DataBean.ItemListBeanInner itemListBeanInner, int pos) {
                                holder.setText(R.id.item_lv_fgmt_feed_type_2nd_tv_title, itemListBeanInner.getData().getTitle());
                                holder.setText(R.id.item_lv_fgmt_feed_type_2nd_tv_category, "#" + itemListBeanInner.getData().getCategory() + " / " + formatTime(itemListBeanInner, pos));
                                holder.setImgUrl(R.id.item_lv_fgmt_feed_type_2nd_imgv_feed, itemListBeanInner.getData().getCover().getFeed());
                            }

                            /**
                             * 用于将duration时间转换成分秒time
                             * @param position
                             * @return time字符串
                             */
                            @NonNull
                            private String formatTime(FeedFragmentBean.SectionListBean.ItemListBean.DataBean.ItemListBeanInner itemListBeanInner, int position) {
                                int times = itemListBeanInner.getData().getDuration();
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
                                return minute + "' " + second + "\"";
                            }
                        };
                rv.setAdapter(adapterRv);
                rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            }

            @Override
            public void onError(Throwable ex) {

            }
        });


    }
}
