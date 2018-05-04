package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/8/29.
 */
public class AdapterCouponsData {
    private String code, condition, dataStart, dataEnd, status, couponid;
    private float price;

    public AdapterCouponsData(String code, String condition, String dataStart, String dataEnd, float price, String status, String couponid) {
        this.code = code;
        this.condition = condition;
        this.dataStart = dataStart;
        this.dataEnd = dataEnd;
        this.price = price;
        this.status = status;
        this.couponid = couponid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDataStart() {
        return dataStart;
    }

    public void setDataStart(String dataStart) {
        this.dataStart = dataStart;
    }

    public String getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(String dataEnd) {
        this.dataEnd = dataEnd;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCouponid() {
        return couponid;
    }

    public void setCouponid(String couponid) {
        this.couponid = couponid;
    }
}
