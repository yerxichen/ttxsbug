package com.yundian.wudou.data;

/**
 * Created by Administrator on 2016/9/27 0027.
 */
public class AdapterPaymentData {

     private String title,name;
     private int imageid;


    public AdapterPaymentData(int imageid, String title, String name) {

        this.imageid = imageid;
        this.title = title;
        this.name = name;

    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }




}
