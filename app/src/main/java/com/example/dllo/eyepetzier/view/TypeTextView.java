package com.example.dllo.eyepetzier.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dllo on 16/8/20.
 */
public class TypeTextView extends TextView {
    private Context context;
    private String showText;
    private MediaPlayer player;
    private Timer timer;
    private static final int TYPE_TIME_DELAY = 40;
    private int typeTimeDelay = TYPE_TIME_DELAY;
    private OnTypeViewListener listener;

    public TypeTextView(Context context) {
        super(context);
        initTypeTextView(context);
    }



    public TypeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeTextView(context);
    }

    public TypeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeTextView(context);
    }

    private void initTypeTextView(Context context) {
        this.context = context;
    }

    public void setListener(OnTypeViewListener listener) {
        this.listener = listener;
    }

    public void start(String textString){
        start(textString, TYPE_TIME_DELAY);
    }

    public void start(final String textString, final int timeDelay){
        if (TextUtils.isEmpty(textString) || typeTimeDelay < 0){
            return;
        }
        post(new Runnable() {
            @Override
            public void run() {
                showText = textString;
                typeTimeDelay = timeDelay;
                setText("");
                startTypeTimer();
                if (listener != null){
                    listener.onTypeStart();
                }
            }
        });
    }

    public void stop(){
        stopTypeTimer();

    }

    private void startTypeTimer() {
        stopTypeTimer();
        timer = new Timer();
        timer.schedule(new TypeTimerTask(),typeTimeDelay);
    }

    private void stopTypeTimer() {
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }

    public interface OnTypeViewListener{
        void onTypeStart();
        void onTypeOver();
    }

    class TypeTimerTask extends TimerTask{

        @Override
        public void run() {
            post(new Runnable() {
                @Override
                public void run() {
                    if (getText().toString().length() < showText.length()){

                        setText(showText.substring(0,getText().toString().length() + 1));
                        startTypeTimer();
                    }else {
                        stopTypeTimer();
                        if (listener != null){
                            listener.onTypeOver();
                        }
                    }
                }
            });
        }
    }


}
