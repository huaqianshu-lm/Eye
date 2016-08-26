package com.example.dllo.eyepetzier.ui.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.All3rdMoreActyBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.CommonRvAdapter;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.RvViewHolder;
import com.example.dllo.eyepetzier.utils.Contant;
import com.example.dllo.eyepetzier.utils.EScreenSizeDensity;
import com.example.dllo.eyepetzier.utils.ScreenSize;
import com.example.dllo.eyepetzier.utils.T;
import com.example.dllo.eyepetzier.utils.TextStyleSetter;
import com.example.dllo.eyepetzier.view.TitleTextView;
import com.example.dllo.eyepetzier.view.TypeTextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 三级页面: 更多
 */
public class All3rdMoreActivity extends AbsBaseActivity implements View.OnClickListener {

    private TypeTextView tvTitle;
    private ScrollView sv;
    private LinearLayout ll;
    private LinearLayout llouter;
    private Bundle bundle;
    private ImageView imgv;

    private List<All3rdMoreActyBean.ItemListBeanOuter> outerItemBeans;
    private All3rdMoreActyBean.ItemListBeanOuter outerItemBean;
    private All3rdMoreActyBean.ItemListBeanOuter.DataBeanOuter.HeaderBean headerBean;
    private List<All3rdMoreActyBean.ItemListBeanOuter.DataBeanOuter.ItemListBeanInner> innerItemBeans;
    private All3rdMoreActyBean.ItemListBeanOuter.DataBeanOuter.ItemListBeanInner.DataBeanInner innerDataBean;

    /**
     * 复用view的控件
     */
    private RelativeLayout rlType1st;
    private CircleImageView cirImgvLogoType1st;
    private TextView tvTitleType1st;
    private TextView tvSubtitleType1st;
    private TextView tvDecriptionType1st;
    private RelativeLayout rlType2nd;
    private TextView tvTitleType2nd;
    private RecyclerView rv;

    @Override
    protected int setLayout() {
        return R.layout.activity_all3rd_more;
    }

    @Override
    protected void initView() {

        tvTitle = bindView(R.id.acty_all3rd_more_tv_title);
        sv = bindView(R.id.acty_all3rd_more_sv_content);
        ll = bindView(R.id.acty_all3rd_more_ll_content);
        imgv = bindView(R.id.acty_all3rd_more_iv_detail);
        llouter = bindView(R.id.acty_all3rd_more_ll);

    }

    @Override
    protected void initData() {

        bundle = getIntent().getExtras();

        // set bg
        setBackground();
        // 初始化标题栏
        initTitlebar();
        // 初始化Scrollview
        initScrollView();

    }

    /**
     * 设置背景图片
     */
    private void setBackground() {

        /**
         * picasso设置背景图片
         */
        Picasso.with(All3rdMoreActivity.this).load(bundle.getString(Contant.KEY_3RD_BACKGROUND_URL)).error(R.mipmap.ic_launcher).into(new Target() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    llouter.setBackgroundDrawable(new BitmapDrawable(bitmap));
                } else {
                    llouter.setBackground(new BitmapDrawable(getResources(),bitmap));
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                llouter.setTag(All3rdMoreActivity.this); //这里需要给view setTag, 要不设置不上,至少是第一次
            }
        });
    }

    /**
     * 初始化标题栏
     */
    private void initTitlebar() {
        tvTitle.setText(bundle.get(Contant.KEY_3RD_TOOLBAR_TITLE).toString());
        // 旋转图标
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_action_open_detail_more);
        Matrix matrix = new Matrix();
        matrix.postRotate(180, 26, 26);
        Bitmap rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        imgv.setImageBitmap(rotateBitmap);

        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * 初始化Scrollview
     */
    private void initScrollView() {

        NetRequestSingleton.getInstance().startRequest(bundle.get(Contant.KEY_3RD_DATA_URL).toString(), All3rdMoreActyBean.class, new IOnHttpCallback<All3rdMoreActyBean>() {
            @Override
            public void onSuccess(All3rdMoreActyBean response) {
                outerItemBeans = response.getItemList();
                if (outerItemBeans != null && outerItemBeans.size() > 0) {
                    for (int i = 0; i < outerItemBeans.size(); i++) {
                        View itemView = getLayoutInflater().inflate(R.layout.view_reuse_sv_acty_all3rd_more, null);
                        initItemView(itemView);
                        innerItemBeans = outerItemBeans.get(i).getData().getItemList();
                        headerBean = outerItemBeans.get(i).getData().getHeader();
                        switch (getBeanType(i)) {
                            case VideoCollectionWithBrief:
                                initTypeOne();
                                initRV(innerItemBeans);
                                break;

                            case BriefCard:
                                initTypeOne();
                                rv.setVisibility(View.GONE);
                                break;

                            case VideoCollectionWithTitle:
                                rlType1st.setVisibility(View.GONE);
                                tvTitleType2nd.setText(headerBean.getTitle());
                                initRV(innerItemBeans);
                                break;
                        }
                        ll.addView(itemView);
                    }
                }

                /**
                 * 添加尾布局
                 */
                View footview = getLayoutInflater().inflate(R.layout.title, null);
                TitleTextView tvTitle = (TitleTextView) footview.findViewById(R.id.title_tv);
                tvTitle.setText(R.string.all_3rd_more_acty_footview_tv_end);
                tvTitle.setTextColor(ContextCompat.getColor(All3rdMoreActivity.this, R.color.white));
                RelativeLayout rl = (RelativeLayout) footview.findViewById(R.id.title_rl);
                rl.setBackgroundColor(ContextCompat.getColor(All3rdMoreActivity.this, R.color.transparent));
                // 0表示添加在顶部,-1表示添加在底部
                ll.addView(footview);

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) footview.getLayoutParams();
                params.height = ScreenSize.getScreenSize(All3rdMoreActivity.this, EScreenSizeDensity.HEIGHT) / 3;
                footview.setLayoutParams(params);
            }

            private void initTypeOne() {
                rlType2nd.setVisibility(View.GONE);
                Picasso.with(All3rdMoreActivity.this).load(bundle.getString(Contant.KEY_3RD_ICON_URL)).config(Bitmap.Config.RGB_565).skipMemoryCache().error(R.mipmap.ic_launcher).resize(150, 150).into(cirImgvLogoType1st);
                tvTitleType1st.setText(bundle.getString(Contant.KEY_3RD_TITLE));
                tvSubtitleType1st.setText(bundle.getString(Contant.KEY_3RD_SUBTITLE));
                tvDecriptionType1st.setText(bundle.getString(Contant.KEY_3RD_DESCRIPTION));
            }

            /**
             * 初始化itemview的组件
             */
            private void initItemView(View itemView) {
                rlType1st = (RelativeLayout) itemView.findViewById(R.id.item_sv_acty_all3rd_more_1st_rl);
                cirImgvLogoType1st = (CircleImageView) itemView.findViewById(R.id.item_video_introduce_vp_civ);
                tvTitleType1st = (TextView) itemView.findViewById(R.id.item_video_introduce_vp_second_title_tv);
                tvSubtitleType1st = (TextView) itemView.findViewById(R.id.item_video_introduce_vp_second_subtitle_tv);
                tvDecriptionType1st = (TextView) itemView.findViewById(R.id.item_video_introduce_vp_description_tv);
                rlType2nd = (RelativeLayout) itemView.findViewById(R.id.item_sv_acty_all3rd_more_2nd_rl);
                tvTitleType2nd = (TextView) itemView.findViewById(R.id.item_sv_acty_all3rd_more_2nd_tv_title);
                rv = (RecyclerView) itemView.findViewById(R.id.item_sv_acty_all3rd_more_rv);

                rlType1st.setOnClickListener(All3rdMoreActivity.this);
                rlType2nd.setOnClickListener(All3rdMoreActivity.this);
            }

            @Override
            public void onError(Throwable ex) {

            }
        });


    }

    private void initRV(List<All3rdMoreActyBean.ItemListBeanOuter.DataBeanOuter.ItemListBeanInner> innerItemBeans) {


        CommonRvAdapter<All3rdMoreActyBean.ItemListBeanOuter.DataBeanOuter.ItemListBeanInner> adapter = new CommonRvAdapter<All3rdMoreActyBean.ItemListBeanOuter.DataBeanOuter.ItemListBeanInner>(All3rdMoreActivity.this, innerItemBeans, R.layout.item_lv_fgmt_feed_type_2nd) {

            @Override
            protected void convert(RvViewHolder holder, All3rdMoreActyBean.ItemListBeanOuter.DataBeanOuter.ItemListBeanInner itemListBeanInner, int pos) {
                innerDataBean = itemListBeanInner.getData();

                holder.setText(R.id.item_lv_fgmt_feed_type_2nd_tv_title, innerDataBean.getTitle());
                holder.setText(R.id.item_lv_fgmt_feed_type_2nd_tv_category, "#" + innerDataBean.getCategory() + " / " + formatTime(innerDataBean));
                holder.setImgUrl(R.id.item_lv_fgmt_feed_type_2nd_imgv_feed, innerDataBean.getCover().getFeed());

                /**
                 * 设置图片宽高
                 */
                ImageView imageView = (ImageView) holder.getConvertView().findViewById(R.id.item_lv_fgmt_feed_type_2nd_imgv_feed);
                FrameLayout.LayoutParams layoutParamsImg = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                layoutParamsImg.width = ScreenSize.getScreenSize(context, EScreenSizeDensity.WIDTH) * 2 / 3;
                layoutParamsImg.height = ScreenSize.getScreenSize(context, EScreenSizeDensity.HEIGHT) / 4;
                imageView.setLayoutParams(layoutParamsImg);

                /**
                 * 设置rv行布局宽高
                 */
                FrameLayout frameLayout = (FrameLayout) holder.getConvertView().findViewById(R.id.item_lv_fgmt_feed_type_2nd_framelayout);
                ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
                layoutParams.width = ScreenSize.getScreenSize(context, EScreenSizeDensity.WIDTH) * 2 / 3;
                layoutParams.height = ScreenSize.getScreenSize(context, EScreenSizeDensity.HEIGHT) / 4;
                frameLayout.setLayoutParams(layoutParams);
                frameLayout.setPadding(8, 16, 8, 16);

                /**
                 * 设置文字大小
                 */
                TextView tvTitle = (TextView) holder.getConvertView().findViewById(R.id.item_lv_fgmt_feed_type_2nd_tv_title);
                tvTitle.setTextSize(15);
                new TextStyleSetter().setBoldText(tvTitle.getPaint());
                TextView tvCategory = (TextView) holder.getConvertView().findViewById(R.id.item_lv_fgmt_feed_type_2nd_tv_category);
                tvCategory.setTextSize(10);

                /**
                 * 设置点击事件
                 */
                holder.setOnClickListener(R.id.item_lv_fgmt_feed_type_2nd_framelayout, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T.shortMsg("跳转到视频详情界面");
                    }
                });
            }

            /**
             * 用于将duration时间转换成分秒time
             * @return time字符串
             */
            @NonNull
            private String formatTime(All3rdMoreActyBean.ItemListBeanOuter.DataBeanOuter.ItemListBeanInner.DataBeanInner innerDataBean) {
                int times = innerDataBean.getDuration();
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

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(All3rdMoreActivity.this, LinearLayoutManager.HORIZONTAL, false));

    }

    private EBeanType getBeanType(int position) {
        String str = outerItemBeans.get(position).getType();
        switch (str) {
            case "videoCollectionWithBrief":
                return EBeanType.VideoCollectionWithBrief;

            case "videoCollectionWithTitle":
                return EBeanType.VideoCollectionWithTitle;

            case "briefCard":
                return EBeanType.BriefCard;

            default:
                return null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_sv_acty_all3rd_more_1st_rl:
                T.shortMsg("跳转到排序详情界面");
                break;

            case R.id.item_sv_acty_all3rd_more_2nd_rl:
                T.shortMsg("跳转到排序详情界面");
                break;

        }
    }

    private enum EBeanType {
        VideoCollectionWithBrief, VideoCollectionWithTitle, BriefCard;
    }
}
