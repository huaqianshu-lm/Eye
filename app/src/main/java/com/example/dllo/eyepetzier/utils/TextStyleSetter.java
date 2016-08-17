package com.example.dllo.eyepetzier.utils;

import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by dllo on 16/8/16.
 * 文字样式设置
 */
public class TextStyleSetter {

    public TextStyleSetter() {
    }

    /**
     * 设置每个文字之间的空格
     */
    public String makeWordSpace(String str){
        ArrayList<String> strs = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            strs.add(String.valueOf(str.charAt(i)));
        }
        str = "";
        for (int i = 0; i < strs.size(); i++) {
            str += strs.get(i) + " ";
        }
        return str;
    }

    /**
     * 给文字加粗 (中文)
     */
    public void setBoldText(Paint paint) {
        paint.setFakeBoldText(true);
    }
}
