package com.yundian.wudou.data;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class AdapterDailySaleData {

    private String storeId;
    private String storeName;
    private String productId;
    private String imageid;
    private String productName;
    private String productPrice;

    public AdapterDailySaleData(String storeId, String storeName, String productId, String imageid, String productName, String productPrice) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.productId = productId;
        this.imageid = imageid;
        this.productName = productName;
        this.productPrice = productPrice;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
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

}
