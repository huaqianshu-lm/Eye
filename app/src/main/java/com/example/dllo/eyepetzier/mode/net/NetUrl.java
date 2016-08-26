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
    public static final String FEED_2ND_REVIEW_URL_TODAY = "http://baobab.wandoujia.com/api/v2/feed?num=2&udid=86f35dc937824e09bf8d0c7dc0cfea543ed2a2a3&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_360_market&last_channel=eyepetizer_360_market&system_version_code=22";
    // 精选页二级接口 : 最近更新作者
    public static final String AUTHOR_2ND_DETAIL_URL_START = "http://baobab.wandoujia.com/api/v3/pgc/videos?pgcId=";
    public static final String AUTHOR_2ND_DETAIL_URL_CENTER = "&strategy=";
    public static final String AUTHOR_2ND_DETAIL_URL_END = "&udid=86f35dc937824e09bf8d0c7dc0cfea543ed2a2a3&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_360_market&last_channel=eyepetizer_360_market&system_version_code=22";
    // 按时间排序
    public static final String AUTHOR_2ND_DETAIL_URL_DATE = "date";
    // 按分享排序
    public static final String AUTHOR_2ND_DETAIL_URL_SHARE = "shareCount";
    // url的key, bundle用
    public static final String KEY_URL_AUTHOR_2ND_DETAIL_DATE = "key_author_2nd_url_date";
    public static final String KEY_URL_AUTHOR_2ND_DETAIL_SHARE = "key_author_2nd_url_share";
    public static final String KEY_AUTHOR = "key_author";
    public static final String KEY_DESCRIPTION = "key_description";
    public static final String KEY_LOGO = "key_logo";
    // 发现页接口

    /**
     * 发现页接口
      */

    // 发现页轮播图接口
    // 发现界面轮播详情界面
    public static final String DISCOVERY_FRAG_BANNER = "http://www.wandoujia.com/needle/source/getJSON/935";
    // 发现界面下边12个图片
    public static final String DISCOVERY_FRAG_ICON = "http://baobab.wandoujia.com/api/v3/discovery?udid=fc33d7ade6d5482bba5eeae629440fe0c6ac1a9e&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=22";
    public static final String DISCOVERY_DETAIL_TIME = "http://baobab.wandoujia.com/api/v3/tag/videos?tagId=658&strategy=date&udid=86f35dc937824e09bf8d0c7dc0cfea543ed2a2a3&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_360_market&last_channel=eyepetizer_360_market&system_version_code=22";
    public static final String DISCOVERY_DETAIL_SHARE = "http://baobab.wandoujia.com/api/v3/tag/videos?tagId=658&strategy=shareCount&udid=86f35dc937824e09bf8d0c7dc0cfea543ed2a2a3&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_360_market&last_channel=eyepetizer_360_market&system_version_code=22";
    /**
     * authorFragment界面的网址
     */
    public static final String AUTHOR_FRAGMENT_URL = "http://baobab.wandoujia.com/api/v3/tabs/pgcs?udid=fc33d7ade6d5482bba5eeae629440fe0c6ac1a9e&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=22";
    /**
     * 搜索界面的url
     */
    public static final String SEARCH_URL = "http://baobab.wandoujia.com/api/v3/queries/hot?udid=86f35dc937824e09bf8d0c7dc0cfea543ed2a2a3&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_360_market&last_channel=eyepetizer_360_market&system_version_code=22";
    public static final String SEARCH__ITEM = "http://baobab.wandoujia.com/api/v1/search?query=参数&udid=0cabbeb79d7941f88fd57610de6665101860dbcf&vc=89&vn=1.13.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_360_market&last_channel=eyepetizer_360_market";

    /**
     * 三级页面接口 拼接
     */
    public static final String ALL_3RD_MORE_URL_START = "http://baobab.wandoujia.com/api/v3/video/";
    public static final String ALL_3RD_MORE_URL_END = "/detail/related?udid=86f35dc937824e09bf8d0c7dc0cfea543ed2a2a3&vc=126&vn=2.4.1&deviceModel=Google%20Nexus%205%20-%205.1.0%20-%20API%2022%20-%201080x1920&first_channel=eyepetizer_360_market&last_channel=eyepetizer_360_market&system_version_code=22";

}
