package com.example.dllo.eyepetzier.mode.net;

/**
 * Created by johnU on 2016/8/14.
 * 接口网址常量类
 */

public final class NetUrl {

    /**
     * 精选页接口
     */
    // 精选页一级接口
    public static final String FEED_FRAGMENT_URL = "http://baobab.wandoujia.com/api/v3/tabs/selected?udid=86f35dc937824e09bf8d0c7dc0cfea543ed2a2a3&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_360_market&last_channel=eyepetizer_360_market&system_version_code=22";
    // 精选页二级接口 : 查看往期精选
    // 8.17
    public static final String FEED_2ND_REVIEW_URL_TODAY = "http://baobab.wandoujia.com/api/v2/feed?num=2&udid=86f35dc937824e09bf8d0c7dc0cfea543ed2a2a3&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_360_market&last_channel=eyepetizer_360_market&system_version_code=22";
    // 8.16
    public static final String FEED_2ND_REVIEW_URL_AUG_16 = "http://baobab.wandoujia.com/api/v2/feed?date=1471276800000&num=2";
    // 8.15
    public static final String FEED_2ND_REVIEW_URL_AUG_15 = "http://baobab.wandoujia.com/api/v2/feed?date=1471147200000&num=2";
    // 8.14
    public static final String FEED_2ND_REVIEW_URL_AUG_14 = "http://baobab.wandoujia.com/api/v2/feed?date=1471060800000&num=2";
    // 8.13
    public static final String FEED_2ND_REVIEW_URL_AUG_13 = "http://baobab.wandoujia.com/api/v2/feed?date=1470931200000&num=2";

    // 发现页接口
    /**
     * 发现页轮播图接口
     */
    // 发现界面轮播详情界面
    public static final String DISCOVERY_FRAG_BANNER = "http://www.wandoujia.com/needle/source/getJSON/935";
    // 发现界面下边12个图片
    public static final String DISCOVERY_FRAG_ICON = "http://baobab.wandoujia.com/api/v3/discovery?udid=fc33d7ade6d5482bba5eeae629440fe0c6ac1a9e&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=22";

    /**
     * authorFragment界面的网址
     */
    public static final String AUTHOR_FRAGMENT_URL = "http://baobab.wandoujia.com/api/v3/tabs/pgcs?udid=fc33d7ade6d5482bba5eeae629440fe0c6ac1a9e&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=22";

 }
