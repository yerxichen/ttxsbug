package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/8/16.
 */
public class AdapterNearbyAddressListData {

    private String locate, details, addressinfo;
    private boolean recommend;

    public AdapterNearbyAddressListData(String locate, String details, String addressinfo, boolean recommend) {
        this.locate = locate;
        this.details = details;
        this.addressinfo = addressinfo;
        this.recommend = recommend;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAddressinfo() {
        return addressinfo;
    }

    public void setAddressinfo(String addressinfo) {
        this.addressinfo = addressinfo;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
