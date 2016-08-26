package com.example.dllo.eyepetzier.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 16/8/17.
 */
public class TimeFormator {

    private SimpleDateFormat formator;
    /**
     * @param rule "yyyy/MM/dd HH:mm:ss" etc. 
     */
    public TimeFormator(String rule) {
        formator = new SimpleDateFormat(rule);
    }

    /**
     *  简单时间格式化 
     *  @param data time data 
     */
    public String formatSimpleTime(String data){
        return formator.format(new Date(Long.valueOf(data)));
    }
    public String formatSimpleTime(Long data){
        return formator.format(new Date(data));
    }
    public String formatSimpleTime(Date data){
        return formator.format(data);
    }

}
