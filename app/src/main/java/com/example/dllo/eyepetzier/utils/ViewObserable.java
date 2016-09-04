package com.example.dllo.eyepetzier.utils;

import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import rx.Observable;


/**
 * Created by dllo on 16/9/4.
 */
public class ViewObserable  {
   public static Observable<CharSequence> create(TextView textView){
       return Observable.create(new TextViewOnSubscribe(textView)).debounce(500, TimeUnit.MILLISECONDS);
   }
}
