package com.example.dllo.eyepetzier.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by dllo on 16/9/4.
 */
public class TextViewOnSubscribe implements Observable.OnSubscribe<CharSequence> {
    private TextView mTextView;

    public TextViewOnSubscribe(TextView textView) {
        mTextView = textView;
    }

    @Override
    public void call(final Subscriber<? super CharSequence> subscriber) {
        mTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                subscriber.onNext(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
