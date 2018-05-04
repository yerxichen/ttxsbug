package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/8/15.
 */
public class AdapterSearchResultRecycleViewData {

    private String pid;
    private String img;
    private String name;
    private String shopprice;

    public AdapterSearchResultRecycleViewData(String pid, String img, String name, String shopprice) {
        this.pid = pid;
        this.img = img;
        this.name = name;
        this.shopprice = shopprice;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopprice() {
        return shopprice;
    }

    public void setShopprice(String shopprice) {
        this.shopprice = shopprice;
    }
}
