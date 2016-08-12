package com.example.dllo.eyepetzier.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.eyepetzier.ui.activity.AbsBaseActivity;

/**
 * Created by dllo on 16/8/12.
 * fragment的基类,有context对象,绑定布局,初始化组件,初始化数据,简化findViewById,intent跳转的方法
 */
public abstract class AbsBaseFragment extends Fragment {
    protected Context context;
    private View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(setLayout(), container, false);
        }
        return view;
    }

    /**
     * 绑定布局的方法
     * @return
     */
    protected abstract int setLayout();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    /**
     * 初始化组件的方法
     */
    protected abstract void initView();


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化数据的方法
     */
    protected abstract void initData();

    /**
     * 简化findViewById方法
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View> T bindView(int resId){
        return (T) getView().findViewById(resId);
    }

    /**
     * intent跳转
     * @param from
     * @param to
     */
    protected void goTo (Context from, Class<? extends AbsBaseActivity> to){
        Intent intent = new Intent(from,to);
        context.startActivity(intent);
    }
}
