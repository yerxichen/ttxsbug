package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/8/11.
 */
public class AdapterStoreDetailsRightData {

    private String productId,productUrl,productName,productPrice,productCount;

    public AdapterStoreDetailsRightData(String productId, String productUrl, String productName, String productPrice, String productCount) {
        this.productId = productId;
        this.productUrl = productUrl;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
}
