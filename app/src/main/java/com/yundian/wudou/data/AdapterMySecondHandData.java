package com.yundian.wudou.data;

/**
 * Created by Itachi on 2016/8/18.
 */
public class AdapterMySecondHandData {

    private String imgUrl,pid,productName, regional, pubdate,price,status;

    public AdapterMySecondHandData(String imgUrl, String pid, String productName, String regional, String pubdate, String price, String status) {
        this.imgUrl = imgUrl;
        this.pid = pid;
        this.productName = productName;
        this.regional = regional;
        this.pubdate = pubdate;
        this.price = price;
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
