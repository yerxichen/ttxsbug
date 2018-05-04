package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public class JsonBeanDailySale {
    /**
     * ret : 0
     * msg :
     * count : 16
     * data : [{"pid":"83","name":"正宗德州产扒鸡","shopprice":"26.00","img":"201609291956305630.jpg","storeid":"9","storename":"晶码西餐"},{"pid":"82","name":"散养童子鸡600g/只","shopprice":"45.00","img":"201609291955315531.jpg","storeid":"15","storename":"东芝小镇蛋糕"}]
     */

    private String ret;
    private String msg;
    private String count;
    /**
     * pid : 83
     * name : 正宗德州产扒鸡
     * shopprice : 26.00
     * img : 201609291956305630.jpg
     * storeid : 9
     * storename : 晶码西餐
     */

    private List<DataBean> data;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String pid;
        private String name;
        private String shopprice;
        private String img;
        private String storeid;
        private String storename;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getStoreid() {
            return storeid;
        }

        public void setStoreid(String storeid) {
            this.storeid = storeid;
        }

        public String getStorename() {
            return storename;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }
    }
}
