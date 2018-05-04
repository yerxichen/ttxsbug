package com.yundian.wudou.data;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class AdapterEvaluateData {

    private String pid, imgUrl, productName,isreviews,time,percentage,message;
    private List<String> imgUrls;

    public AdapterEvaluateData(String pid, String imgUrl, String productName, String isreviews, String time, String percentage, String message, List<String> imgUrls) {
        this.pid = pid;
        this.imgUrl = imgUrl;
        this.productName = productName;
        this.isreviews = isreviews;
        this.time = time;
        this.percentage = percentage;
        this.message = message;
        this.imgUrls = imgUrls;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getIsreviews() {
        return isreviews;
    }

    public void setIsreviews(String isreviews) {
        this.isreviews = isreviews;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }
}
