package com.example.dllo.eyepetzier.ui.adapter.rv.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/8/12.
 */
public class RvViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    private View convertView;
    private Context context;

    public RvViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        convertView = itemView;
        views = new SparseArray<>();
    }

    /**
     * 当行布局已经初始化的时候绑定行布局的方法
     * @param context
     * @param itemView
     * @return
     */
    public static RvViewHolder createViewHolder(Context context, View itemView) {
        RvViewHolder holder = new RvViewHolder(context, itemView);
        return holder;
    }

    /**
     * 当行布局还没有初始化的时候,绑定行布局的方法
     *
     * @param context
     * @param parent
     * @param layoutId
     * @return
     */
    public static RvViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        RvViewHolder holder = new RvViewHolder(context, itemView);

        return holder;
    }


    /**
     * 通过viewId获取组件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
//        Log.d("RvViewHolder", "viewId:" + viewId);
        return (T) view;
    }


    /**
     * 提供获取convertView的方法
     *
     * @return
     */
    public View getConvertView() {
        return convertView;
    }


    /************以下为一些其他的设置数据的方法****************/


    /**
     * 设置文字
     *
     * @param viewId
     * @param text
     * @return
     */
    public RvViewHolder setText(int viewId, String text) {
        TextView t = getView(viewId);
//        TextView t = (TextView) convertView.findViewById(R.id.item_author_fragment_rv_text_header_tv);
        t.setText(text);
        return this;
    }

    public RvViewHolder setAdapter(int viewId,RecyclerView.Adapter adapter){
        RecyclerView recyclerView = getView(viewId);
        recyclerView.setLayoutManager(new GridLayoutManager(context,1,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);
        return this;
    }
    /**
     * 通过图片id设置图片
     *
     * @param viewId
     * @param resId
     * @return
     */
    public RvViewHolder setImageResourec(int viewId, int resId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }



    public RvViewHolder setImgUrl(int viewId, String url){
        ImageView imageView = getView(viewId);
        Picasso.with(context).load(url).skipMemoryCache().error(R.mipmap.default_icon).into(imageView);
        return this;
    }

    public RvViewHolder setImgUrl(int viewId, String url,int width,int height){
        ImageView imageView = getView(viewId);
        Picasso.with(context).load(url).skipMemoryCache().error(R.mipmap.default_icon).resize(width,height).into(imageView);
        return this;
    }

    /**
     * 设置bitmap
     *
     * @param viewId
     * @param bitmap
     * @return
     */
    public RvViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置drawable
     *
     * @param viewId
     * @param drawable
     * @return
     */
    public RvViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView imageView = getView(viewId);
        imageView.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param viewId
     * @param color
     * @return
     */
    public RvViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }


    public RvViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public RvViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public RvViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(context.getResources().getColor(textColorRes));
        return this;
    }

    @SuppressLint("NewApi")
    public RvViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public RvViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     *将文字转换为超链接
     * @param viewId
     * @return
     */
    public RvViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /**
     * 设置字体 Typeface typeFace =Typeface.createFromAsset(getAssets(),"fonts/HandmadeTypewriter.ttf");
     * @param typeface
     * @param viewIds
     * @return
     */
    public RvViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public RvViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public RvViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public RvViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public RvViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public RvViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public RvViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public RvViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public RvViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }


    /********关于事件的一些方法*************/

    public RvViewHolder setOnClickListener(int viewId, View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public RvViewHolder setOnLongClickLintener(int viewId, View.OnLongClickListener listener){
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public RvViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener){
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

}
