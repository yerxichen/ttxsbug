package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/8/8.
 */
public class AdapterHomepageSaleData {

    private String imageAddress;
    private String name;
    private String price;
    private String pid;

    public String getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(String marketprice) {
        this.marketprice = marketprice;
    }

    private String marketprice;

    public AdapterHomepageSaleData(String imageAddress, String name, String price, String pid,String marketprice) {
        this.imageAddress = imageAddress;
        this.name = name;
        this.price = price;
        this.pid = pid;
        this.marketprice=marketprice;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
