package com.example.dllo.eyepetzier.ui.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dllo.eyepetzier.R;
import com.example.dllo.eyepetzier.mode.bean.AuthorFragmentBean;
import com.example.dllo.eyepetzier.utils.Contant;
import com.example.dllo.eyepetzier.utils.L;
import com.example.dllo.eyepetzier.utils.T;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dllo on 16/8/22.
 * 视频播放界面
 */
public class PlayActivity extends AbsBaseActivity {
    private ImageView backIv;
    private TextView titleTv;
    private ImageView infoHighIv;
    private ImageView likeIv;
    private ImageView shareIv;
    private ImageView playIv;
    private ImageView nextIv;
    private SeekBar playSeekBar;
    private TextView startTimeTv;
    private TextView endTimeTv;
    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;
    private ImageView infoNormalIv;
    private ImageView infoLowIv;
    private Handler handler;
    private RelativeLayout controlRl;
    private RelativeLayout loadingRl;
    private ImageView loadingIv;
    private LinearLayout infoLl;
    private AuthorFragmentBean.ItemListBean.DataBean dataBean;
    private List<AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean> videoItemListBeen;
    private AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean videoDataBean;
    private int currentPos = 0;// 当前播放的位置
    private boolean isPlaying = false;// 记录是否播放的状态
    private int pos;// 当前视频所在的位置
    private SurfaceHolder surfaceHolder;
    private Timer timer = new Timer();
    private boolean isShowControlRl = false;// 记录控制界面的显示状态
    private boolean isInfoShow = false; // 记录切换清晰度的按钮显示的状态
    private Animation loadingAnimation;
    private List<AuthorFragmentBean.ItemListBean.DataBean.VideoItemListBean.VideoDataBean.PlayInfoBean> playInfoBeen;
    private String defaultUrl;
    private ImageView loadingOuterIv;

    @Override
    protected int setLayout() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_play;
    }

    @Override
    protected void initView() {
        backIv = bindView(R.id.play_aty_back_iv);
        titleTv = bindView(R.id.play_aty_title_tv);
        infoHighIv = bindView(R.id.play_aty_info_high_iv);
        infoNormalIv = bindView(R.id.play_aty_info_normal_iv);
        infoLowIv = bindView(R.id.play_aty_info_low_iv);
        likeIv = bindView(R.id.play_aty_like_iv);
        shareIv = bindView(R.id.play_aty_share_iv);
        playIv = bindView(R.id.play_aty_play_iv);
        nextIv = bindView(R.id.play_aty_next_iv);
        startTimeTv = bindView(R.id.play_aty_star_time_tv);
        endTimeTv = bindView(R.id.play_aty_end_time_tv);
        playSeekBar = bindView(R.id.play_aty_seekbar);
        surfaceView = bindView(R.id.play_aty_surfaceview);
        controlRl = bindView(R.id.play_aty_control_rl);
        loadingIv = bindView(R.id.play_aty_loading_iv);
        loadingRl = bindView(R.id.play_aty_loading_rl);
        infoLl = bindView(R.id.play_aty_info_ll);
        loadingOuterIv = bindView(R.id.play_aty_loading_outer_iv);
        infoLl.setOnClickListener(listener);
        controlRl.setOnClickListener(listener);
        backIv.setOnClickListener(listener);
        infoHighIv.setOnClickListener(listener);
        infoNormalIv.setOnClickListener(listener);
        infoLowIv.setOnClickListener(listener);
        likeIv.setOnClickListener(listener);
        playIv.setOnClickListener(listener);
        nextIv.setOnClickListener(listener);
        shareIv.setOnClickListener(listener);
        surfaceView.setOnClickListener(listener);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(callback);
        mediaPlayer = new MediaPlayer();
        playSeekBar.setOnSeekBarChangeListener(changeListener);
    }

    @Override
    protected void initData() {
        // 等待动画
        loadingAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_loading);
        loadingAnimation.setInterpolator(new LinearInterpolator());
        loading();
        Intent intent = getIntent();
        dataBean = intent.getParcelableExtra(Contant.VIDEO_TO_PLAY);
        pos = intent.getIntExtra(Contant.VIDEO_POS, 0);
        videoItemListBeen = dataBean.getItemList();
        videoDataBean = videoItemListBeen.get(pos).getData();
        defaultUrl = videoItemListBeen.get(pos).getData().getPlayUrl();
        playInfoBeen = videoDataBean.getPlayInfo();
        titleTv.setText(videoDataBean.getTitle());

        playIv.setImageResource(R.mipmap.ic_player_pause);
        controlRl.setVisibility(View.INVISIBLE);
        // 设置视频播放的进度时间
        long time = videoDataBean.getDuration();
        endTimeTv.setText(String.valueOf(time / 60) + ":" + String.valueOf(time % 60));
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case Contant.VIDEO_DURATION_MESSAGE:
                        int current = (int) msg.obj;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                        String start = simpleDateFormat.format(new Date(current));
                        startTimeTv.setText(start);
                        break;
                    case Contant.AUTO_INVISIABLE:
                        controlRl.setVisibility(View.INVISIBLE);
                        isShowControlRl = true;
                }
                return false;
            }
        });
        autoInvisible();
        mediaPlayer.setOnBufferingUpdateListener(bufferingUpdateListener);
    }

    /**
     * 等待动画
     */
    private void loading() {
        loadingRl.setVisibility(View.VISIBLE);
        if (loadingAnimation != null) {
            loadingIv.startAnimation(loadingAnimation);
        }
    }

    /**
     * view的点击事件
     */
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                // 返回到视频详情页
                case R.id.play_aty_back_iv:
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Contant.TO_VIDEO, dataBean);
                    goTo(PlayActivity.this, VideoIntroduceActivity.class, bundle);
//                    mediaPlayer.stop();
                    if (mediaPlayer != null) {
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    finish();
                    break;
                // 选择视频的清晰度
                // 高清
                case R.id.play_aty_info_high_iv:
                    if (isInfoShow) {
                        infoLowIv.setVisibility(View.GONE);
                        infoNormalIv.setVisibility(View.GONE);
                        isInfoShow = !isInfoShow;
                    } else {
                        infoNormalIv.setVisibility(View.VISIBLE);
                        infoLowIv.setVisibility(View.VISIBLE);
                        isInfoShow = !isInfoShow;
                    }
//                    loading();
//                    currentPos = mediaPlayer.getCurrentPosition();
//                    String highUrl = playInfoBeen.get(2).getUrl();
//                    play(highUrl,currentPos);
                    T.shortMsg("清晰度");
                    break;
                // 流畅
                case R.id.play_aty_info_low_iv:
                    if (isInfoShow) {
                        infoHighIv.setVisibility(View.GONE);
                        infoNormalIv.setVisibility(View.GONE);
                        isInfoShow = !isInfoShow;
                    } else {
                        infoNormalIv.setVisibility(View.VISIBLE);
                        infoHighIv.setVisibility(View.VISIBLE);
                        isInfoShow = !isInfoShow;
                    }
                    loading();
//                    currentPos = mediaPlayer.getCurrentPosition();
                    String lowUrl = playInfoBeen.get(0).getUrl();
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        mediaPlayer.reset();
                        play(lowUrl, 0);
                    }
                    L.d("lowurl", lowUrl);
                    T.shortMsg("流畅");
                    break;
                // 标清
                case R.id.play_aty_info_normal_iv:
                    if (isInfoShow) {
                        infoLowIv.setVisibility(View.GONE);
                        infoHighIv.setVisibility(View.GONE);
                        isInfoShow = !isInfoShow;
                    } else {
                        infoLowIv.setVisibility(View.VISIBLE);
                        infoHighIv.setVisibility(View.VISIBLE);
                        isInfoShow = !isInfoShow;
                    }
                    T.shortMsg("标清");
                    loading();
                    currentPos = mediaPlayer.getCurrentPosition();
                    mediaPlayer.setOnVideoSizeChangedListener(sizeChangedListener);
                    String normalUrl = playInfoBeen.get(1).getUrl();
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        mediaPlayer.reset();
                        play(normalUrl, 0);
                    }
                    L.d("current", currentPos + "=-=-=-=-=-=-==");
                    break;
                // 收藏
                case R.id.play_aty_like_iv:
                    T.shortMsg("收藏");
                    break;
                // 分享
                case R.id.play_aty_share_iv:
                    T.shortMsg("分享");
                    break;
                // 播放暂停
                case R.id.play_aty_play_iv:
                    if (isPlaying) {
                        playIv.setImageResource(R.mipmap.ic_player_play);
                        isPlaying = !isPlaying;
                    } else {
                        playIv.setImageResource(R.mipmap.ic_player_pause);
                        isPlaying = !isPlaying;
                    }
                    pause();
                    break;
                // 下一个
                case R.id.play_aty_next_iv:
                    T.shortMsg("下一个");
                    next();
                    break;
                // 控制界面是否显示
                case R.id.play_aty_surfaceview:
                    if (isShowControlRl) {
                        controlRl.setVisibility(View.VISIBLE);
                        isShowControlRl = false;
                        autoInvisible();
                    } else {
                        controlRl.setVisibility(View.INVISIBLE);
                        isShowControlRl = true;
                    }
                    break;
            }
        }
    };

    /**
     * surfaceHolder的回调事件
     */
    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mediaPlayer.setDisplay(surfaceHolder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // 当surfaceHolder销毁的时候停止播放
            stop();
        }
    };

    /**
     * 开始播放
     *
     * @param url
     * @param msec
     */
    private void play(String url, final int msec) {
        if (mediaPlayer != null) {
            try {

                mediaPlayer.setDisplay(surfaceHolder);
                // 设置音频流的类型 The audio stream for music playback
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                Log.d("PlayActivity", "surfaceholder");
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepareAsync();
                Log.d("PlayActivity", "prepareasync");
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        Log.d("PlayActivity", "prepared");
                        mediaPlayer.start();

                        loadingRl.setVisibility(View.GONE);
                        playSeekBar.setMax(videoDataBean.getDuration() * 1000);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                isPlaying = true;
                                while (isPlaying) {
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if (mediaPlayer != null) {
                                        currentPos = mediaPlayer.getCurrentPosition();
                                    }
                                    playSeekBar.setProgress(currentPos);
                                    Message message = new Message();
                                    message.what = Contant.VIDEO_DURATION_MESSAGE;
                                    message.obj = currentPos;
                                    handler.sendMessage(message);

                                }
                            }
                        }).start();
                    }
                });

                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        // 发生错误重新播放
                        isPlaying = false;
                        loadingOuterIv.setImageResource(R.mipmap.ic_eye_white_error);
                        return false;
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        playIv.setImageResource(R.mipmap.ic_player_play);
                        mediaPlayer.seekTo(0);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 暂停播放
     */
    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }

    /**
     * 停止播放
     */
    private void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            isPlaying = false;
        }
    }

    /**
     * 下一个视频
     */
    private void next() {

        loading();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            int size = videoItemListBeen.size();
            if (pos < size) {
                pos += 1;
                videoDataBean = videoItemListBeen.get(pos).getData();
                String url = videoDataBean.getPlayUrl();
                mediaPlayer.reset();
                play(url, 0);
                titleTv.setText(videoDataBean.getTitle());
            } else {
                nextIv.setVisibility(View.GONE);
            }
        }
    }

    /**
     * seekbar改变时的监听事件
     */
    private SeekBar.OnSeekBarChangeListener changeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int progress = seekBar.getProgress();
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.seekTo(progress);
            }
        }
    };

    /**
     * 4秒后,操作界面消失
     */
    private void autoInvisible() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(Contant.AUTO_INVISIABLE);
            }
        }, 4000);
    }

    /**
     * 按返回键时停止播放
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        mediaPlayer.stop();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        finish();
    }

    /**
     * 进入界面后自动播放视频
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        play(defaultUrl, 0);
    }

    private MediaPlayer.OnVideoSizeChangedListener sizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
            mp.reset();
            String normalUrl = playInfoBeen.get(1).getUrl();
            play(normalUrl, 0);
        }
    };

    /**
     * 缓冲的进度
     */
    private MediaPlayer.OnBufferingUpdateListener bufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {

            percent += (mediaPlayer.getDuration() * percent / 100);
            playSeekBar.setSecondaryProgress(percent);
            L.d("present", percent + "");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
