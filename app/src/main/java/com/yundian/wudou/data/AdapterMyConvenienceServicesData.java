package com.yundian.wudou.data;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class AdapterMyConvenienceServicesData {

    private String newsId, imgUrl, goodsName, location, pubdate, status;

    public AdapterMyConvenienceServicesData(String newsId, String imgUrl, String goodsName, String location, String pubdate, String status) {
        this.newsId = newsId;
        this.imgUrl = imgUrl;
        this.goodsName = goodsName;
        this.location = location;
        this.pubdate = pubdate;
        this.status = status;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
