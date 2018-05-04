package com.yundian.wudou.data;


/**
 * Created by Itachi on 2016/8/19.
 */
public class AdapterSecondHandDivisionData {

    private String pid,imgUrl,name, region, time;

    public AdapterSecondHandDivisionData(String pid, String imgUrl, String name, String region, String time) {
        this.pid = pid;
        this.imgUrl = imgUrl;
        this.name = name;
        this.region = region;
        this.time = time;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
