package com.yundian.wudou.data;

import java.util.List;

/**
 * Created by cookie on 2016/8/15.
 */
public class AdapterSettlementData {

    private String shopName;
    private String commodityCount;
    private String commodityWeight;
    private String commodityPrice;
    private String sendPrice;
    private String strSendPrice;
    private List<String> imgUrls;

    public AdapterSettlementData(String shopName, String commodityCount, String commodityWeight, String commodityPrice, String strSendPrice, String sendPrice, List<String> imgUrls) {
        this.shopName = shopName;
        this.commodityCount = commodityCount;
        this.commodityWeight = commodityWeight;
        this.commodityPrice = commodityPrice;
        this.sendPrice = sendPrice;
        this.strSendPrice = strSendPrice;
        this.imgUrls = imgUrls;
    }

    public String getStrSendPrice() {
        return strSendPrice;
    }

    public void setStrSendPrice(String strSendPrice) {
        this.strSendPrice = strSendPrice;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCommodityCount() {
        return commodityCount;
    }

    public void setCommodityCount(String commodityCount) {
        this.commodityCount = commodityCount;
    }

    public String getCommodityWeight() {
        return commodityWeight;
    }

    public void setCommodityWeight(String commodityWeight) {
        this.commodityWeight = commodityWeight;
    }

    public String getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(String commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(String sendPrice) {
        this.sendPrice = sendPrice;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }
}
