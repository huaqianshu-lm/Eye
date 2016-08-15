package com.example.dllo.eyepetzier.ui.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.AuthorFragmentBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.mode.net.NetUrl;
import com.example.dllo.eyepetzier.ui.adapter.rv.CommonRvAdapter;
import com.example.dllo.eyepetzier.ui.adapter.rv.RvViewHolder;
import com.example.dllo.eyepetzier.utils.L;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 16/8/12.
 * 作者页面的fragment
 */
public class AuthorFragment extends AbaBaseFragment {
    private RecyclerView recyclerView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_author;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.author_fragment_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void initData() {
        NetRequestSingleton.getInstance().startRequest(NetUrl.AUTHOR_FRAGMENT_URL, AuthorFragmentBean.class, new IOnHttpCallback<AuthorFragmentBean>() {
            @Override
            public void onSuccess(AuthorFragmentBean response) {
                final List<AuthorFragmentBean.ItemListBean> itemListBeen = response.getItemList();
                L.d("itemlistbean", itemListBeen.size() + "");
                CommonRvAdapter<AuthorFragmentBean.ItemListBean> adapter = new CommonRvAdapter<AuthorFragmentBean.ItemListBean>(context, itemListBeen, R.layout.item_author_fragment_rv) {
                    @Override
                    protected void convert(RvViewHolder holder, AuthorFragmentBean.ItemListBean itemListBean, int pos) {

                        switch (itemListBean.getType()) {
                            case "leftAlignTextHeader":
                                holder.setText(R.id.item_author_fragment_description_tv, itemListBean.getData().getText());
                                holder.setText(R.id.item_author_fragment_title_tv, "");
                                holder.setText(R.id.item_author_fragment_subtitle_tv, "");
                                holder.setVisible(R.id.item_author_fragment_civ, false);
                                holder.setVisible(R.id.item_author_fragment_more_iv, false);
                                holder.setVisible(R.id.item_author_fragment_rv_rv,false);
                                L.d("header", itemListBean.getData().getText());
                                break;
                            case "briefCard":
                                holder.setText(R.id.item_author_fragment_title_tv, itemListBean.getData().getTitle());
                                holder.setText(R.id.item_author_fragment_description_tv, itemListBean.getData().getDescription());
                                holder.setText(R.id.item_author_fragment_subtitle_tv, itemListBean.getData().getSubTitle());
                                holder.setImgUrl(R.id.item_author_fragment_civ, itemListBean.getData().getIcon(), 150, 150);
                                holder.setVisible(R.id.item_author_fragment_rv_rv,false);
                                break;
                            case "blankCard":
                                holder.setVisible(R.id.top, false);
                                holder.setVisible(R.id.item_author_fragment_rv_rv,false);
                                break;
                            case "videoCollectionWithBrief":
                                List<AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean> videoItemListBeen = itemListBean.getData().getItemList();
                                holder.setText(R.id.item_author_fragment_title_tv, itemListBean.getData().getHeader().getTitle());
                                holder.setText(R.id.item_author_fragment_subtitle_tv, itemListBean.getData().getHeader().getSubTitle());
                                holder.setText(R.id.item_author_fragment_description_tv, itemListBean.getData().getHeader().getDescription());
                                holder.setImgUrl(R.id.item_author_fragment_civ, itemListBean.getData().getHeader().getIcon(), 150, 150);
                                final CommonRvAdapter<AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean> videoAdapter = new CommonRvAdapter<AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean>(context, videoItemListBeen, R.layout.item_author_fragment_child_rv) {
                                    @Override
                                    protected void convert(RvViewHolder holder, AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean videoItemListBean, int pos) {
                                        holder.setText(R.id.item_author_fragment_child_rv_title_tv, videoItemListBean.getData().getTitle());
                                        holder.setText(R.id.item_author_fragment_child_rv_category_tv, videoItemListBean.getData().getCategory());
                                        // 获取屏幕的宽度
                                        DisplayMetrics dm = new DisplayMetrics();
                                        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                                        wm.getDefaultDisplay().getMetrics(dm);
                                        int width = dm.widthPixels;
                                        holder.setImgUrl(R.id.item_author_fragment_child_rv_iv, videoItemListBean.getData().getCover().getFeed(), width /4 *3, 500);
                                        SimpleDateFormat s = new SimpleDateFormat("mm:ss");
                                        String time = s.format(new Date(videoItemListBean.getData().getReleaseTime()));
                                        holder.setText(R.id.item_author_fragment_child_rv_latestreleasttime_tv, time);
                                    }
                                };
                                holder.setAdapter(R.id.item_author_fragment_rv_rv, videoAdapter);
                                break;
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
