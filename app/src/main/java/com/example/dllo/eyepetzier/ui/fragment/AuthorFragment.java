package com.example.dllo.eyepetzier.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.AuthorFragmentBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.mode.net.NetUrl;
import com.example.dllo.eyepetzier.ui.activity.Author2ndDetailActivity;
import com.example.dllo.eyepetzier.ui.activity.VideoIntroduceActivity;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.CommonRvAdapter;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.RvViewHolder;
import com.example.dllo.eyepetzier.utils.Contant;
import com.example.dllo.eyepetzier.utils.L;
import com.example.dllo.eyepetzier.utils.T;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/12.
 * 作者页面的fragment
 */
public class AuthorFragment extends AbaBaseFragment {
    private RecyclerView recyclerView;
    private AuthorFragmentBean.ItemListBean.DataBean dataBean;
    private ImageView loadingIv;
    private RelativeLayout loadingRl;

    @Override
    protected int setLayout() {
        return R.layout.fragment_author;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.author_fragment_rv);
        loadingIv = bindView(R.id.author_fragment_loading_iv);
        loadingRl = bindView(R.id.author_fragment_loading_rl);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    protected void initData() {
        Animation loadingAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_loading);
        loadingAnimation.setInterpolator(new LinearInterpolator());
        loadingIv.startAnimation(loadingAnimation);
        NetRequestSingleton.getInstance().startRequest(NetUrl.AUTHOR_FRAGMENT_URL, AuthorFragmentBean.class, new IOnHttpCallback<AuthorFragmentBean>() {
            @Override
            public void onSuccess(AuthorFragmentBean response) {
                final List<AuthorFragmentBean.ItemListBean> itemListBeen = response.getItemList();
                L.d("itemlistbean", itemListBeen.size() + "");
                loadingRl.setVisibility(View.GONE);
                CommonRvAdapter<AuthorFragmentBean.ItemListBean> adapter = new CommonRvAdapter<AuthorFragmentBean.ItemListBean>(context, itemListBeen, R.layout.item_author_fragment_rv) {
                    @Override
                    protected void convert(RvViewHolder holder, final AuthorFragmentBean.ItemListBean itemListBean, int pos) {
                        holder.setIsRecyclable(false);
                        dataBean = itemListBean.getData();
                        final AuthorFragmentBean.ItemListBean.DataBean.HeaderBean headerBean = dataBean.getHeader();
                        if (itemListBean.getType().equals("leftAlignTextHeader")) {
                            holder.setText(R.id.item_author_fragment_description_tv, itemListBean.getData().getText());
                            holder.setVisible(R.id.item_author_fragment_title_tv, false);
                            holder.setVisible(R.id.item_author_fragment_subtitle_tv, false);
                            holder.setVisible(R.id.item_author_fragment_civ, false);
                            holder.setVisible(R.id.item_author_fragment_more_iv, false);
                            holder.setVisible(R.id.item_author_fragment_rv_rv, false);
                            L.d("header", itemListBean.getData().getText());
                        }
                        if (itemListBean.getType().equals("briefCard")) {
                            holder.setText(R.id.item_author_fragment_title_tv, dataBean.getTitle());
                            holder.setText(R.id.item_author_fragment_description_tv, dataBean.getDescription());
                            holder.setText(R.id.item_author_fragment_subtitle_tv, dataBean.getSubTitle());
                            holder.setImgUrl(R.id.item_author_fragment_civ, dataBean.getIcon(), 150, 150);
                            holder.setVisible(R.id.item_author_fragment_rv_rv, false);
                            final int id = dataBean.getId();
                            final String title = dataBean.getTitle();
                            final String des = dataBean.getDescription();
                            final String icon = dataBean.getIcon();
                            holder.setOnClickListener(R.id.top, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundle = new Bundle();
                                    L.d("id", id + "---");
                                    String urlDate = NetUrl.AUTHOR_2ND_DETAIL_URL_START
                                            + String.valueOf(id) + NetUrl.AUTHOR_2ND_DETAIL_URL_CENTER
                                            + NetUrl.AUTHOR_2ND_DETAIL_URL_DATE + NetUrl.AUTHOR_2ND_DETAIL_URL_END;
                                    String urlShare = NetUrl.AUTHOR_2ND_DETAIL_URL_START
                                            + String.valueOf(id) + NetUrl.AUTHOR_2ND_DETAIL_URL_CENTER
                                            + NetUrl.AUTHOR_2ND_DETAIL_URL_SHARE + NetUrl.AUTHOR_2ND_DETAIL_URL_END;
                                    Log.e("zzz", urlDate);
                                    bundle.putString(NetUrl.KEY_URL_AUTHOR_2ND_DETAIL_DATE, urlDate);
                                    bundle.putString(NetUrl.KEY_URL_AUTHOR_2ND_DETAIL_SHARE, urlShare);
                                    bundle.putString(NetUrl.KEY_AUTHOR, title);
                                    bundle.putString(NetUrl.KEY_DESCRIPTION, des);
                                    bundle.putString(NetUrl.KEY_LOGO, icon);
                                    goTo(context, Author2ndDetailActivity.class, bundle);
                                    T.shortMsg("作者界面的item点击事件,跳转到排序界面");
                                }
                            });
                        }
                        if (itemListBean.getType().equals("blankCard")) {
                            holder.setVisible(R.id.top, false);
                            holder.setVisible(R.id.item_author_fragment_rv_rv, false);
                            holder.setVisible(R.id.item_author_fragment_line, false);
                        }
                        if (itemListBean.getType().equals("videoCollectionWithBrief")) {
                            final List<AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean> videoItemListBeen = itemListBean.getData().getItemList();

                            holder.setText(R.id.item_author_fragment_title_tv, headerBean.getTitle());
                            holder.setText(R.id.item_author_fragment_subtitle_tv, headerBean.getSubTitle());
                            holder.setText(R.id.item_author_fragment_description_tv, headerBean.getDescription());
                            holder.setImgUrl(R.id.item_author_fragment_civ, headerBean.getIcon(), 150, 150);
                            final CommonRvAdapter<AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean> videoAdapter = new CommonRvAdapter<AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean>(context, videoItemListBeen, R.layout.item_author_fragment_child_rv) {
                                @Override
                                protected void convert(RvViewHolder holder, AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean videoItemListBean, final int pos) {
                                    AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean videoDataBean = videoItemListBean.getData();
                                    holder.setText(R.id.item_author_fragment_child_rv_title_tv, videoDataBean.getTitle());
                                    holder.setText(R.id.item_author_fragment_child_rv_category_tv, videoDataBean.getCategory());
                                    // 获取屏幕的宽度
                                    DisplayMetrics dm = new DisplayMetrics();
                                    WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                                    wm.getDefaultDisplay().getMetrics(dm);
                                    int width = dm.widthPixels;
                                    holder.setImgUrl(R.id.item_author_fragment_child_rv_iv, videoDataBean.getCover().getFeed(), width / 4 * 3
                                            , 500);
                                    int minute = videoDataBean.getDuration() / 60;
                                    int sec = videoDataBean.getDuration() % 60;
                                    String time = String.valueOf(minute) + "'" + String.valueOf(sec) + "\"";
                                    holder.setText(R.id.item_author_fragment_child_rv_latestreleasttime_tv, time);
                                    final Bundle bundle = new Bundle();
                                    bundle.putParcelableArrayList(Contant.TO_VIDEO, (ArrayList<? extends Parcelable>) videoItemListBeen);
                                    bundle.putParcelable(Contant.TO_VIDEO, dataBean);
                                    bundle.putInt(Contant.VIDEO_POS, pos);
                                    holder.setOnClickListener(R.id.item_author_fragment_child_rv_iv, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            goTo(context, VideoIntroduceActivity.class, bundle);
                                            T.shortMsg("作者界面视频图片的点击事件,跳转到视频的详情界面");

                                        }
                                    });
                                }
                            };
                            holder.setOnClickListener(R.id.top, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.e("AuthorFragment", "getId():" + itemListBean.getData().getHeader().getId());
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelableArrayList(Contant.AUTHOR_TO_SORT, (ArrayList<? extends Parcelable>) itemListBeen);

                                    goTo(context, VideoIntroduceActivity.class, bundle);

                                    String urlDate = NetUrl.AUTHOR_2ND_DETAIL_URL_START
                                            + itemListBean.getData().getHeader().getId() + NetUrl.AUTHOR_2ND_DETAIL_URL_CENTER
                                            + NetUrl.AUTHOR_2ND_DETAIL_URL_DATE + NetUrl.AUTHOR_2ND_DETAIL_URL_END;
                                    String urlShare = NetUrl.AUTHOR_2ND_DETAIL_URL_START
                                            + itemListBean.getData().getHeader().getId() + NetUrl.AUTHOR_2ND_DETAIL_URL_CENTER
                                            + NetUrl.AUTHOR_2ND_DETAIL_URL_SHARE + NetUrl.AUTHOR_2ND_DETAIL_URL_END;
                                    bundle.putString(NetUrl.KEY_URL_AUTHOR_2ND_DETAIL_DATE, urlDate);
                                    bundle.putString(NetUrl.KEY_URL_AUTHOR_2ND_DETAIL_SHARE, urlShare);
                                    bundle.putString(NetUrl.KEY_AUTHOR, headerBean.getTitle());
                                    bundle.putString(NetUrl.KEY_DESCRIPTION, headerBean.getDescription());
                                    bundle.putString(NetUrl.KEY_LOGO, headerBean.getIcon());
                                    goTo(getActivity(), Author2ndDetailActivity.class, bundle);

                                    T.shortMsg("作者界面的item点击事件,跳转到排序界面");
                                }
                            });
                            holder.setAdapter(R.id.item_author_fragment_rv_rv, videoAdapter);
                        }

                    }
                };

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex) {

            }
        });
    }

}
