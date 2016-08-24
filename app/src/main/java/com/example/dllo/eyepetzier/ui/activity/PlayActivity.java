package com.example.dllo.eyepetzier.ui.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
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
        Intent intent = getIntent();
        dataBean = intent.getParcelableExtra(Contant.VIDEO_TO_PLAY);
        pos = intent.getIntExtra(Contant.VIDEO_POS, 0);
        videoItemListBeen = dataBean.getItemList();
        videoDataBean = videoItemListBeen.get(pos).getData();
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
                    bundle.putParcelable(Contant.AUTHOR_TO_VIDEO, dataBean);
                    goTo(PlayActivity.this, VideoIntroduceActivity.class, bundle);
                    mediaPlayer.release();
                    mediaPlayer = null;
                    finish();
//                    mediaPlayer.stop();
                    break;
                // 选择视频的清晰度
                case R.id.play_aty_info_high_iv:
                    if (isInfoShow) {
                        infoLowIv.setVisibility(View.GONE);
                        infoNormalIv.setVisibility(View.GONE);
                        isInfoShow = !isInfoShow;
                    }else {
                        infoNormalIv.setVisibility(View.VISIBLE);
                        infoLowIv.setVisibility(View.VISIBLE);
                        isInfoShow = ! isInfoShow;
                    }
                    T.shortMsg("清晰度");
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
                    if (isShowControlRl){
                        controlRl.setVisibility(View.VISIBLE);
                        isShowControlRl = false;
                        autoInvisible();
                    }else {
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
     * @param pos
     */
    private void play(final int pos) {
//        mediaPlayer.reset();
        // 设置音频流的类型 The audio stream for music playback
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDisplay(surfaceHolder);
        try {
            String url = videoItemListBeen.get(pos).getData().getPlayUrl();
            mediaPlayer.setDataSource(url);
            // 设置显示视频的surfaceHolder
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    playSeekBar.setMax(videoDataBean.getDuration() * 1000);
//                    mediaPlayer.seekTo(pos);
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
                                currentPos = mediaPlayer.getCurrentPosition();
                                L.d(currentPos + "----------");
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

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    playIv.setImageResource(R.mipmap.ic_player_play);
//                    mediaPlayer.stop()
                    mediaPlayer.seekTo(0);
                }
            });
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    // 发生错误重新播放
                    mediaPlayer.start();
                    isPlaying = false;
                    return false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
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
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//            mediaPlayer.release();
            int size = videoItemListBeen.size();
            if (pos < size) {
                pos += 1;
                mediaPlayer.reset();
                play(pos);
                titleTv.setText(videoItemListBeen.get(pos).getData().getTitle());
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
    private void autoInvisible(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(Contant.AUTO_INVISIABLE);
            }
        },4000);
    }

    /**
     * 按返回键时停止播放
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.release();
        mediaPlayer = null;
        finish();
    }

    /**
     * 进入界面后自动播放视频
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        play(pos);
    }
}
