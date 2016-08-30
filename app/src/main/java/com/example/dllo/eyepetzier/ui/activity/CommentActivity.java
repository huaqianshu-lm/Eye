package com.example.dllo.eyepetzier.ui.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.CommentActyBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.CommonRvAdapter;
import com.example.dllo.eyepetzier.ui.adapter.rv.tools.RvViewHolder;
import com.example.dllo.eyepetzier.utils.Contant;
import com.example.dllo.eyepetzier.utils.EScreenSizeDensity;
import com.example.dllo.eyepetzier.utils.ScreenSize;
import com.example.dllo.eyepetzier.utils.T;
import com.example.dllo.eyepetzier.utils.TextStyleSetter;
import com.example.dllo.eyepetzier.utils.TimeFormator;
import com.example.dllo.eyepetzier.view.TitleTextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 视频二级页面的子页:评论页
 */
public class CommentActivity extends AbsBaseActivity {

    private TextView tvVideoTitle;
    private TextView tvTotalCommentNum;
    private EditText etWriteComment;
    private RecyclerView rv;
    private String backgroundUrl;
    private ImageView backBtn;

    private String parseUrl;
    private List<CommentActyBean.ReplyListBean> replyListBeans;
    private String nextUrl;
    private CommonRvAdapter<CommentActyBean.ReplyListBean> adapter;

    private TextStyleSetter textStyleSetter;

    @Override
    protected int setLayout() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initView() {
        tvVideoTitle = bindView(R.id.acty_comment_tv_video_title);
        tvTotalCommentNum = bindView(R.id.acty_comment_tv_total_comment_num);
        etWriteComment = bindView(R.id.acty_comment_et_write_comment);
        rv = bindView(R.id.acty_comment_rv);
        backBtn = bindView(R.id.acty_comment_imgv_arrow_down);
    }

    @Override
    protected void initData() {

        Bundle bundle = getIntent().getExtras();
        parseUrl = bundle.getString(Contant.KEY_ACTY_COMMENT_URL);
        backgroundUrl = bundle.getString(Contant.KEY_3RD_BACKGROUND_URL);
        Log.e("CommentActivity", parseUrl);

        NetRequestSingleton.getInstance().startRequest(parseUrl, CommentActyBean.class, new IOnHttpCallback<CommentActyBean>() {
            @Override
            public void onSuccess(CommentActyBean response) {

                // init transparent part
                initTransparentPart(response);

                // init rv
                replyListBeans = response.getReplyList();
                initRv(replyListBeans);

                // parse nexturl
                nextUrl = response.getNextPageUrl();
                parseNextUrl();
            }

            @Override
            public void onError(Throwable ex) {
                T.shortMsg("网络连接失败");
            }
        });
    }


    /**
     * 初始化上半部分
     */
    private void initTransparentPart(CommentActyBean commentActyBean) {

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resultCode = 666;
                setResult(resultCode);
                finish();
            }
        });
        tvVideoTitle.setText(commentActyBean.getReplyList().get(0).getVideoTitle());
        textStyleSetter = new TextStyleSetter();
        textStyleSetter.setBoldText(tvVideoTitle.getPaint());
        tvTotalCommentNum.setText("- " + commentActyBean.getCount() + " 条评论 -");
        etWriteComment.setCursorVisible(false);
        etWriteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etWriteComment.setCursorVisible(true);
            }
        });
    }

    /**
     * 初始化rv
     */
    private void initRv(List<CommentActyBean.ReplyListBean> replyListBeans) {

        /**
         * picasso设置背景图片
         */
        Picasso.with(CommentActivity.this).load(backgroundUrl).error(R.mipmap.ic_launcher).into(new Target() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    rv.setBackgroundDrawable(new BitmapDrawable(bitmap));
                } else {
                    rv.setBackground(new BitmapDrawable(getResources(),bitmap));
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                rv.setTag(CommentActivity.this); //这里需要给view setTag, 要不设置不上,至少是第一次
            }
        });

        adapter = new CommonRvAdapter<CommentActyBean.ReplyListBean>(CommentActivity.this, replyListBeans, R.layout.item_rv_coment_acty) {
            @Override
            protected void convert(RvViewHolder holder, CommentActyBean.ReplyListBean replyListBean, int pos) {

                holder.setImgUrl(R.id.item_rv_coment_acty_cirimg_avatar, replyListBean.getUser().getAvatar());
                CircleImageView cirImg = (CircleImageView) holder.getConvertView().findViewById(R.id.item_rv_coment_acty_cirimg_avatar);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cirImg.getLayoutParams();
                params.width = ScreenSize.getScreenSize(CommentActivity.this, EScreenSizeDensity.WIDTH) / 6;
                params.height = ScreenSize.getScreenSize(CommentActivity.this, EScreenSizeDensity.WIDTH) / 6;
                cirImg.setLayoutParams(params);

                holder.setText(R.id.item_rv_coment_acty_tv_nickname, replyListBean.getUser().getNickname());
                TextView tvNickname = (TextView) holder.getConvertView().findViewById(R.id.item_rv_coment_acty_tv_nickname);
                textStyleSetter.setBoldText(tvNickname.getPaint());
                holder.setText(R.id.item_rv_coment_acty_tv_message, replyListBean.getMessage());
                holder.setText(R.id.item_rv_coment_acty_tv_likecount, String.valueOf(replyListBean.getLikeCount()));
                holder.setVisible(R.id.item_rv_coment_acty_tv_hot, replyListBean.isHot());

                // set create time text
                String txtCreateTime = setCreateTimeText(replyListBean);
                holder.setText(R.id.item_rv_coment_acty_tv_createtime, txtCreateTime);
            }

            private String setCreateTimeText(CommentActyBean.ReplyListBean replyListBean) {
                // 获取当前时间
                Date currentDate = new Date(System.currentTimeMillis());
                // 获取创建时间
                Date createDate = new Date(replyListBean.getCreateTime());
                String txtCreateTime = "";
                if (currentDate.getYear() - createDate.getYear() == 0) {
                    if (currentDate.getMonth() - createDate.getMonth() == 0) {
                        if (currentDate.getDate() - createDate.getDate() == 0 ) {

//                            txtCreateTime = new TimeFormator("HH : mm").formatSimpleTime(createDate); // 不好用? why??
                            SimpleDateFormat formator = new SimpleDateFormat("HH : mm");
                            txtCreateTime = formator.format(createDate);

                        } else {
                            txtCreateTime = ( currentDate.getDate() - createDate.getDate() ) + "天前";
                        }
                    } else {
                        txtCreateTime = ( currentDate.getMonth() - createDate.getMonth() ) + "月前";
                    }

                } else {
                    txtCreateTime = ( currentDate.getYear() - createDate.getYear() ) + "年前";
                }
                return txtCreateTime;
            }
        };
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(CommentActivity.this, LinearLayoutManager.VERTICAL, false));

    }
    /**
     * 解析 nextUrl
     */
    private void parseNextUrl() {

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private RecyclerView.LayoutManager layoutManager;
            private int lastVisibleItemPosition;
            int totalItemCount;
            boolean isFirstLoaded = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);

                totalItemCount = layoutManager.getItemCount();
                if (newState == 0 && lastVisibleItemPosition == (totalItemCount - 1) && !isFirstLoaded && nextUrl != null) {
                    // 防止二次解析
                    isFirstLoaded = true;
                    //
                    NetRequestSingleton.getInstance().startRequest(nextUrl, CommentActyBean.class, new IOnHttpCallback<CommentActyBean>() {
                        @Override
                        public void onSuccess(CommentActyBean response) {
                            replyListBeans = response.getReplyList();
                            adapter.addItemAtEnd(replyListBeans);
                            nextUrl = response.getNextPageUrl();
                            isFirstLoaded = false;
                        }

                        @Override
                        public void onError(Throwable ex) {

                        }
                    });

                } else if (nextUrl == null) {
                    /**
                     * 添加尾布局
                     */

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);

                layoutManager = rv.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                }
            }
        });
    }

}
