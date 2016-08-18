package com.example.dllo.eyepetzier.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.eyepetzier.ui.activity.AbsBaseActivity;

/**
 * Created by dllo on 16/8/12.
 * fragment的基类,有绑定布局,初始化组件,初始化数据,简化findViewById,intent跳转的方法
 */
public abstract class AbaBaseFragment extends Fragment {
    protected Context context;
    protected View view;
    /**
     * 当activity与fragment发生关联时回调
     *
     * @param context 这里的context就是相关的activity
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(setLayout(),container,false);
        }
        return view;

    }

    /**
     * 绑定布局
     *
     * @return
     */
    protected abstract int setLayout();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    /**
     * 初始化组件
     */
    protected abstract void initView();

    /**
     * 简化findViewById
     *
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View> T bindView(int resId) {
        return (T) getView().findViewById(resId);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 设置数据
     */
    protected abstract void initData();

    /**
     * 简化intent跳转
     * @param from
     * @param to
     */
    protected void goTo(Context from, Class<? extends AbsBaseActivity> to) {
        Intent intent = new Intent(from, to);
        from.startActivity(intent);
    }
    /**
     * intent 跳转 with value
     */
    protected void goTo(Context from, Class<? extends AbsBaseActivity> to, Bundle bundle){
        Intent intent = new Intent(from, to);
        intent.putExtras(bundle);
        from.startActivity(intent);
    }
    /**
     * intent 跳转 with requestcode
     */
    protected void goTo(Context from, Class<? extends AbsBaseActivity> to, int requestCode) {
        Intent intent = new Intent(from, to);
        getActivity().startActivityForResult(intent, requestCode);
    }

}
