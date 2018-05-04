package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/8/4.
 */
public class AdapterClassificationRightRecycleData {

    private String imgUrl,name,cateId,cateNumber;

    public AdapterClassificationRightRecycleData(String imgUrl, String name, String cateId, String cateNumber) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.cateId = cateId;
        this.cateNumber = cateNumber;
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

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getCateNumber() {
        return cateNumber;
    }

    public void setCateNumber(String cateNumber) {
        this.cateNumber = cateNumber;
    }
}
