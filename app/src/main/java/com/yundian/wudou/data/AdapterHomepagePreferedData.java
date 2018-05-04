package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/8/8.
 */
public class AdapterHomepagePreferedData {
    private String imageAddress, pid;
    private String name, price;

    public AdapterHomepagePreferedData(String imageAddress, String name, String price, String pid) {
        this.imageAddress = imageAddress;
        this.name = name;
        this.price = price;
        this.pid = pid;
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
