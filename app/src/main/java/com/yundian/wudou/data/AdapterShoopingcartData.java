package com.yundian.wudou.data;

import java.io.Serializable;

/**
 * Created by cookie on 2016/8/9.
 */
public class AdapterShoopingcartData implements Serializable {

    private boolean checked, parent;
    private String storeId, storeName, storeUrl, productId, productName, productUrl, productWeight, startValue, sendPrice;
    private float productPrice;
    private int productCount;
    private int type = 0;

    public AdapterShoopingcartData(boolean checked, boolean parent, String storeId, String storeName, String storeUrl, String productId, String productName,
                                   String productUrl, float productPrice, String productWeight, int productCount, String startValue, String sendPrice) {
        this.checked = checked;
        this.parent = parent;
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

        if (parent) {
            this.type = 0;
        } else {
            this.type = 1;
        }
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
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

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
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

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
