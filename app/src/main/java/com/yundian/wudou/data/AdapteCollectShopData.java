package com.yundian.wudou.data;

/**
 * Created by sxt on 2016/8/18.
 */
public class AdapteCollectShopData {

    private String imgUrl,shopName,productcount,monthlysales;
    private float ratingbar;

    public AdapteCollectShopData(String imgUrl, String shopName, String productcount, String monthlysales, float ratingbar) {
        this.imgUrl = imgUrl;
        this.shopName = shopName;
        this.productcount = productcount;
        this.monthlysales = monthlysales;
        this.ratingbar = ratingbar;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getProductcount() {
        return productcount;
    }

    public void setProductcount(String productcount) {
        this.productcount = productcount;
    }

    public String getMonthlysales() {
        return monthlysales;
    }

    public void setMonthlysales(String monthlysales) {
        this.monthlysales = monthlysales;
    }

    public float getRatingbar() {
        return ratingbar;
    }

    public void setRatingbar(float ratingbar) {
        this.ratingbar = ratingbar;
    }
}
