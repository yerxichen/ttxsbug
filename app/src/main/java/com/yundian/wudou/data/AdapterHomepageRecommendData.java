package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/8/8.
 */
public class AdapterHomepageRecommendData {

    private String imgUrl,url,urlType;

    public AdapterHomepageRecommendData(String imgUrl, String url, String urlType) {
        this.imgUrl = imgUrl;
        this.url = url;
        this.urlType = urlType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }
}
