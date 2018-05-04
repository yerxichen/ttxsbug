package com.yundian.wudou.data;

import java.util.List;

/**
 * Created by cookie on 2016/8/12.
 */
public class AdapterMyAllOrderData {

    private String storeid, storename, storeimg, oid, osn, orderamount,surplusmoney,
            addtime, orderstatus, isreviews, reviewstext,ordertype,creditsvalue;
    private boolean paysate;
    private List<ProductData> mProductDataList;

    public AdapterMyAllOrderData(String storeid, String storename, String storeimg, String oid, String osn, String orderamount, String surplusmoney, String addtime, String orderstatus, String isreviews, String reviewstext, String ordertype, String creditsvalue, boolean paysate, List<ProductData> mProductDataList) {
        this.storeid = storeid;
        this.storename = storename;
        this.storeimg = storeimg;
        this.oid = oid;
        this.osn = osn;
        this.orderamount = orderamount;
        this.surplusmoney = surplusmoney;
        this.addtime = addtime;
        this.orderstatus = orderstatus;
        this.isreviews = isreviews;
        this.reviewstext = reviewstext;
        this.ordertype = ordertype;
        this.creditsvalue = creditsvalue;
        this.paysate = paysate;
        this.mProductDataList = mProductDataList;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getStoreimg() {
        return storeimg;
    }

    public void setStoreimg(String storeimg) {
        this.storeimg = storeimg;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOsn() {
        return osn;
    }

    public void setOsn(String osn) {
        this.osn = osn;
    }

    public String getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(String orderamount) {
        this.orderamount = orderamount;
    }

    public String getSurplusmoney() {
        return surplusmoney;
    }

    public void setSurplusmoney(String surplusmoney) {
        this.surplusmoney = surplusmoney;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getIsreviews() {
        return isreviews;
    }

    public void setIsreviews(String isreviews) {
        this.isreviews = isreviews;
    }

    public String getReviewstext() {
        return reviewstext;
    }

    public void setReviewstext(String reviewstext) {
        this.reviewstext = reviewstext;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getCreditsvalue() {
        return creditsvalue;
    }

    public void setCreditsvalue(String creditsvalue) {
        this.creditsvalue = creditsvalue;
    }

    public boolean isPaysate() {
        return paysate;
    }

    public void setPaysate(boolean paysate) {
        this.paysate = paysate;
    }

    public List<ProductData> getmProductDataList() {
        return mProductDataList;
    }

    public void setmProductDataList(List<ProductData> mProductDataList) {
        this.mProductDataList = mProductDataList;
    }

    public static class ProductData {

        private String pid, imageUrl;

        public ProductData(String pid, String imageUrl) {
            this.pid = pid;
            this.imageUrl = imageUrl;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
