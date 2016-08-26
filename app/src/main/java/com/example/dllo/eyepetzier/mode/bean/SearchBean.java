package com.example.dllo.eyepetzier.mode.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dllo on 16/8/25.
 */
public class SearchBean implements Parcelable {

    /**
     * itemList : [{"type":"video","data":{"dataType":"VideoBeanForClient","id":3956,"title":"Axis 最新视效作品混剪","description":"Axis 是一家位于英国，并获得大奖无数的著名视效创意公司，每年都参与大量电影、广告等影视作品等制作。这是 Aixs 公司最近作品的视效混剪。From axisanimation","provider":{"name":"Vimeo","alias":"vimeo","icon":"http://img.wdjimg.com/image/video/c3ad630be461cbb081649c9e21d6cbe3_256_256.png"},"category":"动画","author":null,"cover":{"feed":"http://img.kaiyanapp.com/9761d0f1ba2f59a0614f3c061ce18c49.jpeg","detail":"http://img.kaiyanapp.com/9761d0f1ba2f59a0614f3c061ce18c49.jpeg","blurred":"http://img.kaiyanapp.com/a1ecc1a6ade8b31bab75d10f08d135cb.jpeg","sharing":null},"playUrl":"http://baobab.wandoujia.com/api/v1/playUrl?vid=3956&editionType=default","duration":138,"webUrl":{"raw":"http://www.wandoujia.com/eyepetizer/detail.html?vid=3956","forWeibo":"http://wandou.im/10qjlo"},"releaseTime":1451232000000,"playInfo":[{"height":480,"width":848,"name":"标清","type":"normal","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=3956&editionType=normal"},{"height":720,"width":1280,"name":"高清","type":"high","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=3956&editionType=high"}],"consumption":{"collectionCount":3922,"shareCount":2447,"replyCount":40},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[{"id":14,"name":"动画","actionUrl":"eyepetizer://tag/14/?title=%E5%8A%A8%E7%94%BB","adTrack":null},{"id":56,"name":"3D","actionUrl":"eyepetizer://tag/56/?title=3D","adTrack":null},{"id":2,"name":"创意","actionUrl":"eyepetizer://tag/2/?title=%E5%88%9B%E6%84%8F","adTrack":null},{"id":36,"name":"集锦","actionUrl":"eyepetizer://tag/36/?title=%E9%9B%86%E9%94%A6","adTrack":null},{"id":30,"name":"游戏","actionUrl":"eyepetizer://tag/30/?title=%E6%B8%B8%E6%88%8F","adTrack":null}],"type":"NORMAL","idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1451232000000,"promotion":null,"label":null}},{"type":"video","data":{"dataType":"VideoBeanForClient","id":3514,"title":"别人家的大学宣传片","description":"「京都学園大学 2015」动画宣传片用影视的数字绘景方式完成，其 3D 与 2D 的完美结合给我们带来很多启发，例如 3D 头发运动背后也有 2D 原画的参考并进一步的修整令画面充满活力。From 京都学園大学","provider":{"name":"YouTube","alias":"youtube","icon":"http://img.wdjimg.com/image/video/fa20228bc5b921e837156923a58713f6_256_256.png"},"category":"广告","author":null,"cover":{"feed":"http://img.kaiyanapp.com/e7ad689ecb73c63a85d23b53fa828c1c.jpeg","detail":"http://img.kaiyanapp.com/e7ad689ecb73c63a85d23b53fa828c1c.jpeg","blurred":"http://img.kaiyanapp.com/a0565fbe43040dd36d33ca363131d226.jpeg","sharing":null},"playUrl":"http://baobab.wandoujia.com/api/v1/playUrl?vid=3514&editionType=default","duration":257,"webUrl":{"raw":"http://www.wandoujia.com/eyepetizer/detail.html?vid=3514","forWeibo":"http://wandou.im/xo1wq"},"releaseTime":1471665604000,"playInfo":[{"height":360,"width":640,"name":"流畅","type":"low","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=3514&editionType=low"},{"height":480,"width":854,"name":"标清","type":"normal","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=3514&editionType=normal"},{"height":720,"width":1280,"name":"高清","type":"high","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=3514&editionType=high"}],"consumption":{"collectionCount":1485,"shareCount":2082,"replyCount":63},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[{"id":16,"name":"广告","actionUrl":"eyepetizer://tag/16/?title=%E5%B9%BF%E5%91%8A","adTrack":null},{"id":14,"name":"动画","actionUrl":"eyepetizer://tag/14/?title=%E5%8A%A8%E7%94%BB","adTrack":null},{"id":58,"name":"2D","actionUrl":"eyepetizer://tag/58/?title=2D","adTrack":null},{"id":56,"name":"3D","actionUrl":"eyepetizer://tag/56/?title=3D","adTrack":null}],"type":"NORMAL","idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1471665604000,"promotion":null,"label":null}}]
     * count : 2
     * total : 2
     * nextPageUrl : null
     */

    private int count;
    private int total;
    private String nextPageUrl;
    /**
     * type : video
     * data : {"dataType":"VideoBeanForClient","id":3956,"title":"Axis 最新视效作品混剪","description":"Axis 是一家位于英国，并获得大奖无数的著名视效创意公司，每年都参与大量电影、广告等影视作品等制作。这是 Aixs 公司最近作品的视效混剪。From axisanimation","provider":{"name":"Vimeo","alias":"vimeo","icon":"http://img.wdjimg.com/image/video/c3ad630be461cbb081649c9e21d6cbe3_256_256.png"},"category":"动画","author":null,"cover":{"feed":"http://img.kaiyanapp.com/9761d0f1ba2f59a0614f3c061ce18c49.jpeg","detail":"http://img.kaiyanapp.com/9761d0f1ba2f59a0614f3c061ce18c49.jpeg","blurred":"http://img.kaiyanapp.com/a1ecc1a6ade8b31bab75d10f08d135cb.jpeg","sharing":null},"playUrl":"http://baobab.wandoujia.com/api/v1/playUrl?vid=3956&editionType=default","duration":138,"webUrl":{"raw":"http://www.wandoujia.com/eyepetizer/detail.html?vid=3956","forWeibo":"http://wandou.im/10qjlo"},"releaseTime":1451232000000,"playInfo":[{"height":480,"width":848,"name":"标清","type":"normal","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=3956&editionType=normal"},{"height":720,"width":1280,"name":"高清","type":"high","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=3956&editionType=high"}],"consumption":{"collectionCount":3922,"shareCount":2447,"replyCount":40},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[{"id":14,"name":"动画","actionUrl":"eyepetizer://tag/14/?title=%E5%8A%A8%E7%94%BB","adTrack":null},{"id":56,"name":"3D","actionUrl":"eyepetizer://tag/56/?title=3D","adTrack":null},{"id":2,"name":"创意","actionUrl":"eyepetizer://tag/2/?title=%E5%88%9B%E6%84%8F","adTrack":null},{"id":36,"name":"集锦","actionUrl":"eyepetizer://tag/36/?title=%E9%9B%86%E9%94%A6","adTrack":null},{"id":30,"name":"游戏","actionUrl":"eyepetizer://tag/30/?title=%E6%B8%B8%E6%88%8F","adTrack":null}],"type":"NORMAL","idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1451232000000,"promotion":null,"label":null}
     */

    private List<ItemListBean> itemList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public List<ItemListBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBean> itemList) {
        this.itemList = itemList;
    }

    public static class ItemListBean implements Parcelable {
        private String type;
        /**
         * dataType : VideoBeanForClient
         * id : 3956
         * title : Axis 最新视效作品混剪
         * description : Axis 是一家位于英国，并获得大奖无数的著名视效创意公司，每年都参与大量电影、广告等影视作品等制作。这是 Aixs 公司最近作品的视效混剪。From axisanimation
         * provider : {"name":"Vimeo","alias":"vimeo","icon":"http://img.wdjimg.com/image/video/c3ad630be461cbb081649c9e21d6cbe3_256_256.png"}
         * category : 动画
         * author : null
         * cover : {"feed":"http://img.kaiyanapp.com/9761d0f1ba2f59a0614f3c061ce18c49.jpeg","detail":"http://img.kaiyanapp.com/9761d0f1ba2f59a0614f3c061ce18c49.jpeg","blurred":"http://img.kaiyanapp.com/a1ecc1a6ade8b31bab75d10f08d135cb.jpeg","sharing":null}
         * playUrl : http://baobab.wandoujia.com/api/v1/playUrl?vid=3956&editionType=default
         * duration : 138
         * webUrl : {"raw":"http://www.wandoujia.com/eyepetizer/detail.html?vid=3956","forWeibo":"http://wandou.im/10qjlo"}
         * releaseTime : 1451232000000
         * playInfo : [{"height":480,"width":848,"name":"标清","type":"normal","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=3956&editionType=normal"},{"height":720,"width":1280,"name":"高清","type":"high","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=3956&editionType=high"}]
         * consumption : {"collectionCount":3922,"shareCount":2447,"replyCount":40}
         * campaign : null
         * waterMarks : null
         * adTrack : null
         * tags : [{"id":14,"name":"动画","actionUrl":"eyepetizer://tag/14/?title=%E5%8A%A8%E7%94%BB","adTrack":null},{"id":56,"name":"3D","actionUrl":"eyepetizer://tag/56/?title=3D","adTrack":null},{"id":2,"name":"创意","actionUrl":"eyepetizer://tag/2/?title=%E5%88%9B%E6%84%8F","adTrack":null},{"id":36,"name":"集锦","actionUrl":"eyepetizer://tag/36/?title=%E9%9B%86%E9%94%A6","adTrack":null},{"id":30,"name":"游戏","actionUrl":"eyepetizer://tag/30/?title=%E6%B8%B8%E6%88%8F","adTrack":null}]
         * type : NORMAL
         * idx : 0
         * shareAdTrack : null
         * favoriteAdTrack : null
         * webAdTrack : null
         * date : 1451232000000
         * promotion : null
         * label : null
         */

        private DataBean data;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean implements Parcelable {
            private String dataType;
            private int id;
            private String title;
            private String description;
            /**
             * name : Vimeo
             * alias : vimeo
             * icon : http://img.wdjimg.com/image/video/c3ad630be461cbb081649c9e21d6cbe3_256_256.png
             */

            private ProviderBean provider;
            private String category;
            private String author;
            /**
             * feed : http://img.kaiyanapp.com/9761d0f1ba2f59a0614f3c061ce18c49.jpeg
             * detail : http://img.kaiyanapp.com/9761d0f1ba2f59a0614f3c061ce18c49.jpeg
             * blurred : http://img.kaiyanapp.com/a1ecc1a6ade8b31bab75d10f08d135cb.jpeg
             * sharing : null
             */

            private CoverBean cover;
            private String playUrl;
            private int duration;
            /**
             * raw : http://www.wandoujia.com/eyepetizer/detail.html?vid=3956
             * forWeibo : http://wandou.im/10qjlo
             */

            private WebUrlBean webUrl;
            private long releaseTime;
            /**
             * collectionCount : 3922
             * shareCount : 2447
             * replyCount : 40
             */

            private ConsumptionBean consumption;
            private String campaign;
            private String waterMarks;
            private String adTrack;
            private String type;
            private int idx;
            private String shareAdTrack;
            private String favoriteAdTrack;
            private String webAdTrack;
            private long date;
            private String promotion;
            private String label;
            /**
             * height : 480
             * width : 848
             * name : 标清
             * type : normal
             * url : http://baobab.wandoujia.com/api/v1/playUrl?vid=3956&editionType=normal
             */

            private List<PlayInfoBean> playInfo;
            /**
             * id : 14
             * name : 动画
             * actionUrl : eyepetizer://tag/14/?title=%E5%8A%A8%E7%94%BB
             * adTrack : null
             */

            private List<TagsBean> tags;

            public String getDataType() {
                return dataType;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public ProviderBean getProvider() {
                return provider;
            }

            public void setProvider(ProviderBean provider) {
                this.provider = provider;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public Object getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public CoverBean getCover() {
                return cover;
            }

            public void setCover(CoverBean cover) {
                this.cover = cover;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public WebUrlBean getWebUrl() {
                return webUrl;
            }

            public void setWebUrl(WebUrlBean webUrl) {
                this.webUrl = webUrl;
            }

            public long getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(long releaseTime) {
                this.releaseTime = releaseTime;
            }

            public ConsumptionBean getConsumption() {
                return consumption;
            }

            public void setConsumption(ConsumptionBean consumption) {
                this.consumption = consumption;
            }

            public Object getCampaign() {
                return campaign;
            }

            public void setCampaign(String campaign) {
                this.campaign = campaign;
            }

            public Object getWaterMarks() {
                return waterMarks;
            }

            public void setWaterMarks(String waterMarks) {
                this.waterMarks = waterMarks;
            }

            public Object getAdTrack() {
                return adTrack;
            }

            public void setAdTrack(String adTrack) {
                this.adTrack = adTrack;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getIdx() {
                return idx;
            }

            public void setIdx(int idx) {
                this.idx = idx;
            }

            public Object getShareAdTrack() {
                return shareAdTrack;
            }

            public void setShareAdTrack(String shareAdTrack) {
                this.shareAdTrack = shareAdTrack;
            }

            public Object getFavoriteAdTrack() {
                return favoriteAdTrack;
            }

            public void setFavoriteAdTrack(String favoriteAdTrack) {
                this.favoriteAdTrack = favoriteAdTrack;
            }

            public Object getWebAdTrack() {
                return webAdTrack;
            }

            public void setWebAdTrack(String webAdTrack) {
                this.webAdTrack = webAdTrack;
            }

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public Object getPromotion() {
                return promotion;
            }

            public void setPromotion(String promotion) {
                this.promotion = promotion;
            }

            public Object getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public List<PlayInfoBean> getPlayInfo() {
                return playInfo;
            }

            public void setPlayInfo(List<PlayInfoBean> playInfo) {
                this.playInfo = playInfo;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public static class ProviderBean implements Parcelable {
                private String name;
                private String alias;
                private String icon;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.name);
                    dest.writeString(this.alias);
                    dest.writeString(this.icon);
                }

                public ProviderBean() {
                }

                protected ProviderBean(Parcel in) {
                    this.name = in.readString();
                    this.alias = in.readString();
                    this.icon = in.readString();
                }

                public static final Parcelable.Creator<ProviderBean> CREATOR = new Parcelable.Creator<ProviderBean>() {
                    @Override
                    public ProviderBean createFromParcel(Parcel source) {
                        return new ProviderBean(source);
                    }

                    @Override
                    public ProviderBean[] newArray(int size) {
                        return new ProviderBean[size];
                    }
                };
            }

            public static class CoverBean implements Parcelable {
                private String feed;
                private String detail;
                private String blurred;
                private String sharing;

                public String getFeed() {
                    return feed;
                }

                public void setFeed(String feed) {
                    this.feed = feed;
                }

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }

                public String getBlurred() {
                    return blurred;
                }

                public void setBlurred(String blurred) {
                    this.blurred = blurred;
                }

                public Object getSharing() {
                    return sharing;
                }

                public void setSharing(String sharing) {
                    this.sharing = sharing;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.feed);
                    dest.writeString(this.detail);
                    dest.writeString(this.blurred);
                    dest.writeString(this.sharing);
                }

                public CoverBean() {
                }

                protected CoverBean(Parcel in) {
                    this.feed = in.readString();
                    this.detail = in.readString();
                    this.blurred = in.readString();
                    this.sharing = in.readString();
                }

                public static final Parcelable.Creator<CoverBean> CREATOR = new Parcelable.Creator<CoverBean>() {
                    @Override
                    public CoverBean createFromParcel(Parcel source) {
                        return new CoverBean(source);
                    }

                    @Override
                    public CoverBean[] newArray(int size) {
                        return new CoverBean[size];
                    }
                };
            }

            public static class WebUrlBean implements Parcelable {
                private String raw;
                private String forWeibo;

                public String getRaw() {
                    return raw;
                }

                public void setRaw(String raw) {
                    this.raw = raw;
                }

                public String getForWeibo() {
                    return forWeibo;
                }

                public void setForWeibo(String forWeibo) {
                    this.forWeibo = forWeibo;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.raw);
                    dest.writeString(this.forWeibo);
                }

                public WebUrlBean() {
                }

                protected WebUrlBean(Parcel in) {
                    this.raw = in.readString();
                    this.forWeibo = in.readString();
                }

                public static final Parcelable.Creator<WebUrlBean> CREATOR = new Parcelable.Creator<WebUrlBean>() {
                    @Override
                    public WebUrlBean createFromParcel(Parcel source) {
                        return new WebUrlBean(source);
                    }

                    @Override
                    public WebUrlBean[] newArray(int size) {
                        return new WebUrlBean[size];
                    }
                };
            }

            public static class ConsumptionBean implements Parcelable {
                private int collectionCount;
                private int shareCount;
                private int replyCount;

                public int getCollectionCount() {
                    return collectionCount;
                }

                public void setCollectionCount(int collectionCount) {
                    this.collectionCount = collectionCount;
                }

                public int getShareCount() {
                    return shareCount;
                }

                public void setShareCount(int shareCount) {
                    this.shareCount = shareCount;
                }

                public int getReplyCount() {
                    return replyCount;
                }

                public void setReplyCount(int replyCount) {
                    this.replyCount = replyCount;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.collectionCount);
                    dest.writeInt(this.shareCount);
                    dest.writeInt(this.replyCount);
                }

                public ConsumptionBean() {
                }

                protected ConsumptionBean(Parcel in) {
                    this.collectionCount = in.readInt();
                    this.shareCount = in.readInt();
                    this.replyCount = in.readInt();
                }

                public static final Parcelable.Creator<ConsumptionBean> CREATOR = new Parcelable.Creator<ConsumptionBean>() {
                    @Override
                    public ConsumptionBean createFromParcel(Parcel source) {
                        return new ConsumptionBean(source);
                    }

                    @Override
                    public ConsumptionBean[] newArray(int size) {
                        return new ConsumptionBean[size];
                    }
                };
            }

            public static class PlayInfoBean implements Parcelable {
                private int height;
                private int width;
                private String name;
                private String type;
                private String url;

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.height);
                    dest.writeInt(this.width);
                    dest.writeString(this.name);
                    dest.writeString(this.type);
                    dest.writeString(this.url);
                }

                public PlayInfoBean() {
                }

                protected PlayInfoBean(Parcel in) {
                    this.height = in.readInt();
                    this.width = in.readInt();
                    this.name = in.readString();
                    this.type = in.readString();
                    this.url = in.readString();
                }

                public static final Parcelable.Creator<PlayInfoBean> CREATOR = new Parcelable.Creator<PlayInfoBean>() {
                    @Override
                    public PlayInfoBean createFromParcel(Parcel source) {
                        return new PlayInfoBean(source);
                    }

                    @Override
                    public PlayInfoBean[] newArray(int size) {
                        return new PlayInfoBean[size];
                    }
                };
            }

            public static class TagsBean implements Parcelable {
                private int id;
                private String name;
                private String actionUrl;
                private String  adTrack;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getActionUrl() {
                    return actionUrl;
                }

                public void setActionUrl(String actionUrl) {
                    this.actionUrl = actionUrl;
                }

                public Object getAdTrack() {
                    return adTrack;
                }

                public void setAdTrack(String adTrack) {
                    this.adTrack = adTrack;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.id);
                    dest.writeString(this.name);
                    dest.writeString(this.actionUrl);
                    dest.writeString(this.adTrack);
                }

                public TagsBean() {
                }

                protected TagsBean(Parcel in) {
                    this.id = in.readInt();
                    this.name = in.readString();
                    this.actionUrl = in.readString();
                    this.adTrack = in.readString();
                }

                public static final Parcelable.Creator<TagsBean> CREATOR = new Parcelable.Creator<TagsBean>() {
                    @Override
                    public TagsBean createFromParcel(Parcel source) {
                        return new TagsBean(source);
                    }

                    @Override
                    public TagsBean[] newArray(int size) {
                        return new TagsBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.dataType);
                dest.writeInt(this.id);
                dest.writeString(this.title);
                dest.writeString(this.description);
                dest.writeParcelable(this.provider, flags);
                dest.writeString(this.category);
                dest.writeString(this.author);
                dest.writeParcelable(this.cover, flags);
                dest.writeString(this.playUrl);
                dest.writeInt(this.duration);
                dest.writeParcelable(this.webUrl, flags);
                dest.writeLong(this.releaseTime);
                dest.writeParcelable(this.consumption, flags);
                dest.writeString(this.campaign);
                dest.writeString(this.waterMarks);
                dest.writeString(this.adTrack);
                dest.writeString(this.type);
                dest.writeInt(this.idx);
                dest.writeString(this.shareAdTrack);
                dest.writeString(this.favoriteAdTrack);
                dest.writeString(this.webAdTrack);
                dest.writeLong(this.date);
                dest.writeString(this.promotion);
                dest.writeString(this.label);
                dest.writeTypedList(this.playInfo);
                dest.writeTypedList(this.tags);
            }

            public DataBean() {
            }

            protected DataBean(Parcel in) {
                this.dataType = in.readString();
                this.id = in.readInt();
                this.title = in.readString();
                this.description = in.readString();
                this.provider = in.readParcelable(ProviderBean.class.getClassLoader());
                this.category = in.readString();
                this.author = in.readString();
                this.cover = in.readParcelable(CoverBean.class.getClassLoader());
                this.playUrl = in.readString();
                this.duration = in.readInt();
                this.webUrl = in.readParcelable(WebUrlBean.class.getClassLoader());
                this.releaseTime = in.readLong();
                this.consumption = in.readParcelable(ConsumptionBean.class.getClassLoader());
                this.campaign = in.readString();
                this.waterMarks = in.readString();
                this.adTrack = in.readString();
                this.type = in.readString();
                this.idx = in.readInt();
                this.shareAdTrack = in.readString();
                this.favoriteAdTrack = in.readString();
                this.webAdTrack = in.readString();
                this.date = in.readLong();
                this.promotion = in.readString();
                this.label = in.readString();
                this.playInfo = in.createTypedArrayList(PlayInfoBean.CREATOR);
                this.tags = in.createTypedArrayList(TagsBean.CREATOR);
            }

            public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
                @Override
                public DataBean createFromParcel(Parcel source) {
                    return new DataBean(source);
                }

                @Override
                public DataBean[] newArray(int size) {
                    return new DataBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.type);
            dest.writeParcelable(this.data, flags);
        }

        public ItemListBean() {
        }

        protected ItemListBean(Parcel in) {
            this.type = in.readString();
            this.data = in.readParcelable(DataBean.class.getClassLoader());
        }

        public static final Parcelable.Creator<ItemListBean> CREATOR = new Parcelable.Creator<ItemListBean>() {
            @Override
            public ItemListBean createFromParcel(Parcel source) {
                return new ItemListBean(source);
            }

            @Override
            public ItemListBean[] newArray(int size) {
                return new ItemListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeInt(this.total);
        dest.writeString(this.nextPageUrl);
        dest.writeTypedList(this.itemList);
    }

    public SearchBean() {
    }

    protected SearchBean(Parcel in) {
        this.count = in.readInt();
        this.total = in.readInt();
        this.nextPageUrl = in.readString();
        this.itemList = in.createTypedArrayList(ItemListBean.CREATOR);
    }

    public static final Parcelable.Creator<SearchBean> CREATOR = new Parcelable.Creator<SearchBean>() {
        @Override
        public SearchBean createFromParcel(Parcel source) {
            return new SearchBean(source);
        }

        @Override
        public SearchBean[] newArray(int size) {
            return new SearchBean[size];
        }
    };
}
