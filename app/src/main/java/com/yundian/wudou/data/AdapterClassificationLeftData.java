package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/8/4.
 */
public class AdapterClassificationLeftData {

    private boolean select;
    private String cateid;
    private String cateName;

    public AdapterClassificationLeftData(boolean select, String cateid, String cateName) {
        this.select = select;
        this.cateid = cateid;
        this.cateName = cateName;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
