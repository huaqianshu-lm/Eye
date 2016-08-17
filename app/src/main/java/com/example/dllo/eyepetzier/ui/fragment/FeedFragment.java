package com.example.dllo.eyepetzier.ui.fragment;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
import com.example.dllo.eyepetzier.utils.EScreenSizeDensity;
import com.example.dllo.eyepetzier.utils.ScreenSize;
import com.example.dllo.eyepetzier.utils.TextStyleSetter;
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

            private TextStyleSetter setter;

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

            /**
             * 第一部分 Listview
             * @param response
             */
            private void set1stPart(FeedFragmentBean response) {

                FeedFragmentLvAdapter adapter = new FeedFragmentLvAdapter(getContext());
                adapter.setDatas(response.getSectionList().get(0).getItemList());
                listView.setAdapter(adapter);
                String str = response.getSectionList().get(0).getFooter().getData().getText();
                // 给文字添加间距
                textViewLvFooter.setText(new TextStyleSetter().makeWordSpace(str));
                textViewLvFooter.setTextColor(Color.GRAY);
                // 没有footview才添加
                if (listView.getFooterViewsCount() == 0) {
                    listView.addFooterView(footView);
                    listView.addFooterView(footViewFeed);
                }
                // 去除分割线
                listView.setDivider(null);
                listView.setDividerHeight(0);
            }

            /**
             * 第二部分 rv
             * @param response
             */
            private void set2ndPart(FeedFragmentBean response) {
                imageView2ndPart.setVisibility(View.VISIBLE);
                Picasso.with(getContext()).load(response.getSectionList().get(1).getItemList().get(0).getData().getHeader().getCover()).error(R.mipmap.ic_launcher).into(imageView2ndPart);

                List<FeedFragmentBean.SectionListBean.ItemListBean.DataBean.ItemListBeanInner> datas = response.getSectionList().get(1).getItemList().get(0).getData().getItemList();

                initRV(datas, rv2ndPart);
            }

            /**
             * 第三部分 最新作者头
             * @param response
             */
            private void set3rdPart(FeedFragmentBean response) {
                tvHeader3rdPart.setVisibility(View.VISIBLE);
                setter = new TextStyleSetter();
                // 设置字间距,并且加粗
                tvHeader3rdPart.setText(setter.makeWordSpace(response.getSectionList().get(2).getHeader().getData().getText()));
                setter.setBoldText(tvHeader3rdPart.getPaint());

                relatLay3rdPart.setVisibility(View.VISIBLE);
                FeedFragmentBean.SectionListBean.ItemListBean.DataBean.HeaderBean headerBean = response.getSectionList().get(2).getItemList().get(0).getData().getHeader();
                Picasso.with(context).load(headerBean.getIcon()).error(R.mipmap.ic_launcher).into(circleImgv3rdPart);
                // 设置头像宽高
                setLogoSize(circleImgv3rdPart);

                tvTitle3rdPart.setText(headerBean.getTitle());
                setter.setBoldText(tvTitle3rdPart.getPaint());
                tvSubTitle3rdPart.setText(headerBean.getSubTitle());
                setColor(tvSubTitle3rdPart, R.color.grey);
                tvDescrip3rdPart.setText(headerBean.getDescription());
                // 设置单行省略
                setLines(tvDescrip3rdPart);

                initRV(response.getSectionList().get(2).getItemList().get(0).getData().getItemList(), rv3rdPart);
            }

            /**
             * 第四部分 最新作者尾
             * @param response
             */
            private void set4thPart(FeedFragmentBean response) {
                relatLay4thPart.setVisibility(View.VISIBLE);
                FeedFragmentBean.SectionListBean.ItemListBean.DataBean.HeaderBean headerBean = response.getSectionList().get(2).getItemList().get(1).getData().getHeader();
                Picasso.with(context).load(headerBean.getIcon()).error(R.mipmap.ic_launcher).into(circleImgv4thPart);
                setLogoSize(circleImgv4thPart);
                tvTitle4thPart.setText(headerBean.getTitle());
                setter.setBoldText(tvTitle4thPart.getPaint());
                tvSubTitle4thPart.setText(headerBean.getSubTitle());
                setColor(tvSubTitle4thPart, R.color.grey);
                tvDescrip4thPart.setText(headerBean.getDescription());
                // 设置单行省略
                setLines(tvDescrip4thPart);
                initRV(response.getSectionList().get(2).getItemList().get(1).getData().getItemList(), rv4thPart);
                rlFooter4thPart.setVisibility(View.VISIBLE);
                tvFooter4thPart.setText(setter.makeWordSpace(response.getSectionList().get(2).getFooter().getData().getText()));
                setter.setBoldText(tvFooter4thPart.getPaint());
                tvFooter4thPart.setTextColor(Color.GRAY);

            }
            /**
             * 设置文字颜色
             */
            private void setColor(TextView tv, int color){
                tv.setTextColor(ContextCompat.getColor(context, color));
            }

            /**
             * 设置行省略
             */
            private void setLines(TextView tv) {
                tv.setSingleLine();
                tv.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
            }

            /**
             * 设置头像宽高
             * @param img
             */
            private void setLogoSize(ImageView img) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) img.getLayoutParams();
                layoutParams.width = ScreenSize.getScreenSize(context, EScreenSizeDensity.WIDTH) / 8;
                layoutParams.height = ScreenSize.getScreenSize(context, EScreenSizeDensity.HEIGHT) / 14;
                img.setLayoutParams(layoutParams);
            }

            /**
             * 初始化RV
             * @param datas
             * @param rv
             */
            private void initRV(final List<FeedFragmentBean.SectionListBean.ItemListBean.DataBean.ItemListBeanInner> datas, RecyclerView rv) {
                final CommonRvAdapter<FeedFragmentBean.SectionListBean.ItemListBean.DataBean.ItemListBeanInner> adapterRv =
                        new CommonRvAdapter<FeedFragmentBean.SectionListBean.ItemListBean.DataBean.ItemListBeanInner>(
                                context,
                                datas,
                                R.layout.item_lv_fgmt_feed_type_2nd) {

                            @Override
                            protected void convert(RvViewHolder holder, FeedFragmentBean.SectionListBean.ItemListBean.DataBean.ItemListBeanInner itemListBeanInner, int pos) {
                                holder.setText(R.id.item_lv_fgmt_feed_type_2nd_tv_title, itemListBeanInner.getData().getTitle());
                                holder.setText(R.id.item_lv_fgmt_feed_type_2nd_tv_category, "#" + itemListBeanInner.getData().getCategory() + " / " + formatTime(itemListBeanInner, pos));
                                holder.setImgUrl(R.id.item_lv_fgmt_feed_type_2nd_imgv_feed, itemListBeanInner.getData().getCover().getFeed());

                                /**
                                 * 设置图片宽高
                                 */
                                ImageView imageView = (ImageView) holder.getConvertView().findViewById(R.id.item_lv_fgmt_feed_type_2nd_imgv_feed);
                                FrameLayout.LayoutParams layoutParamsImg = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                                layoutParamsImg.width = ScreenSize.getScreenSize(context, EScreenSizeDensity.WIDTH) * 2 / 3;
                                layoutParamsImg.height = ScreenSize.getScreenSize(context, EScreenSizeDensity.HEIGHT) * 1 / 4;
                                imageView.setLayoutParams(layoutParamsImg);

                                /**
                                 * 设置rv行布局宽高
                                 */
                                FrameLayout frameLayout = (FrameLayout) holder.getConvertView().findViewById(R.id.item_lv_fgmt_feed_type_2nd_framelayout);
                                ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
                                layoutParams.width = ScreenSize.getScreenSize(context, EScreenSizeDensity.WIDTH) * 2 / 3;
                                layoutParams.height = ScreenSize.getScreenSize(context, EScreenSizeDensity.HEIGHT) * 1 / 4;
                                frameLayout.setLayoutParams(layoutParams);
                                frameLayout.setPadding(8, 16, 8, 16);

                                /**
                                 * 设置文字大小
                                 */
                                TextView tvTitle = (TextView) holder.getConvertView().findViewById(R.id.item_lv_fgmt_feed_type_2nd_tv_title);
                                tvTitle.setTextSize(15);
                                setter.setBoldText(tvTitle.getPaint());
                                TextView tvCategory = (TextView) holder.getConvertView().findViewById(R.id.item_lv_fgmt_feed_type_2nd_tv_category);
                                tvCategory.setTextSize(10);
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
