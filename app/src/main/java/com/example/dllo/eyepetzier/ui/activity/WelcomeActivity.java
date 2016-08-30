package com.example.dllo.eyepetzier.ui.activity;

import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.WelcomeBean;
import com.example.dllo.eyepetzier.mode.net.IOnHttpCallback;
import com.example.dllo.eyepetzier.mode.net.NetRequestSingleton;
import com.example.dllo.eyepetzier.mode.net.NetUrl;
import com.example.dllo.eyepetzier.utils.L;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/8/30.
 * 欢迎页
 */
public class WelcomeActivity extends AbsBaseActivity implements View.OnClickListener {
    private ImageView bgIv;
    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        bgIv = bindView(R.id.welcome_activity_bg_iv);
        bgIv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        NetRequestSingleton.getInstance().startRequest(NetUrl.WELCOME_URL, WelcomeBean.class, new IOnHttpCallback<WelcomeBean>() {
            @Override
            public void onSuccess(WelcomeBean response) {
                WelcomeBean.StartPageBean startPageBean = response.getStartPage();
                String startUrl = startPageBean.getImageUrl();
                Picasso.with(WelcomeActivity.this).load(startUrl).into(bgIv);
                Animation animation = new ScaleAnimation(1f,1.1f,1f,1.1f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                animation.setRepeatMode(Animation.REVERSE);
                animation.setDuration(3000);
                bgIv.setAnimation(animation);
            }

            @Override
            public void onError(Throwable ex) {

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                goTo(WelcomeActivity.this,MainActivity.class);
                finish();
                L.d("welcome");
            }
        }).start();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.welcome_activity_bg_iv:
                // 点击图片时直接跳转到MainActivity
                goTo(WelcomeActivity.this,MainActivity.class);
                finish();
                break;
        }
    }
}
