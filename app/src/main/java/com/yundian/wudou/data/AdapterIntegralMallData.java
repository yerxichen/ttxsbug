package com.yundian.wudou.data;

/**
 * Created by Itachi on 2016/8/29.
 */
public class AdapterIntegralMallData {

    private String pid,productUrl,productName,productPrice,productCount,productExchange;

    public AdapterIntegralMallData(String pid, String productUrl, String productName, String productPrice, String productCount, String productExchange) {
        this.pid = pid;
        this.productUrl = productUrl;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
        this.productExchange = productExchange;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getProductExchange() {
        return productExchange;
    }

    public void setProductExchange(String productExchange) {
        this.productExchange = productExchange;
    }
}
