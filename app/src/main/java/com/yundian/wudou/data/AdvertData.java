package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/9/12.
 */
public class AdvertData {
    private String imageAddress, urlType, urlAddress;

    public  AdvertData()
    {

    }

    public AdvertData(String imageAddress, String urlType, String urlAddress) {
        this.imageAddress = imageAddress;
        this.urlType = urlType;
        this.urlAddress = urlAddress;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }
}
