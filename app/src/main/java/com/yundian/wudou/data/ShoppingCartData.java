package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/9/28.
 */
public class ShoppingCartData {
    private String storeId, storeName,storeUrl, productId, productName, productUrl, productPrice,productWeight, productCount,startValue,sendPrice;

    public ShoppingCartData(String storeId, String storeName, String storeUrl, String productId, String productName, String productUrl,
                            String productPrice, String productWeight, String productCount, String startValue, String sendPrice) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeUrl = storeUrl;
        this.productId = productId;
        this.productName = productName;
        this.productUrl = productUrl;
        this.productPrice = productPrice;
        this.productWeight = productWeight;
        this.productCount = productCount;
        this.startValue = startValue;
        this.sendPrice = sendPrice;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getStartValue() {
        return startValue;
    }

    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    public String getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(String sendPrice) {
        this.sendPrice = sendPrice;
    }
}
