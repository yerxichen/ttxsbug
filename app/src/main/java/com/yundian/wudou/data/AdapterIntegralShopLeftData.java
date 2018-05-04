package com.yundian.wudou.data;

/**
 * Created by Itachi on 2016/8/29.
 */
public class AdapterIntegralShopLeftData {

    private String imageAddress, point, counts, already, goodsName;

    public AdapterIntegralShopLeftData(String imageAddress, String point, String counts, String already, String goodsName) {

        this.imageAddress = imageAddress;
        this.point = point;
        this.counts = counts;
        this.already = already;
        this.goodsName = goodsName;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public String getAlready() {
        return already;
    }

    public void setAlready(String already) {
        this.already = already;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }


}
