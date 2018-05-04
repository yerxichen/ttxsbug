package com.yundian.wudou.data;

/**
 * Created by Itachi on 2016/9/5.
 */
public class AdapterConvenienceServicesData {

    private String newsid,imgUrl,goodsName, location, pubdate;

    public AdapterConvenienceServicesData(String newsid, String imgUrl, String goodsName, String location, String pubdate) {
        this.newsid = newsid;
        this.imgUrl = imgUrl;
        this.goodsName = goodsName;
        this.location = location;
        this.pubdate = pubdate;
    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }
}
