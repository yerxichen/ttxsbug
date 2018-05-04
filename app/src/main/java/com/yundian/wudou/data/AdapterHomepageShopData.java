package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/8/8.
 */
public class AdapterHomepageShopData {

    private String imageAddress, storeId;
    private String name, count, startprice, sendprice, distance;
    private boolean expert;

    public AdapterHomepageShopData(String imageAddress, String storeId, String name, String count, String startprice, String sendprice,
                                   String distance, boolean expert) {
        this.imageAddress = imageAddress;
        this.storeId = storeId;
        this.name = name;
        this.count = count;
        this.startprice = startprice;
        this.sendprice = sendprice;
        this.distance = distance;
        this.expert = expert;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getStartprice() {
        return startprice;
    }

    public void setStartprice(String startprice) {
        this.startprice = startprice;
    }

    public String getSendprice() {
        return sendprice;
    }

    public void setSendprice(String sendprice) {
        this.sendprice = sendprice;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public boolean isExpert() {
        return expert;
    }

    public void setExpert(boolean expert) {
        this.expert = expert;
    }
}
