package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by cookie on 2016/9/13.
 */
public class JsonBeanStoreDetailsCommodityData {
    /**
     * ret : 0
     * msg :
     * count : 2
     * data : [{"pid":"1","name":"恒大冰泉","shopprice":"2.00","img":"5011.png"},{"pid":"4","name":"双汇玉米热狗","shopprice":"12.00","img":"5014
     * .png"}]
     */

    private String ret;
    private String msg;
    private String count;
    /**
     * pid : 1
     * name : 恒大冰泉
     * shopprice : 2.00
     * img : 5011.png
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
    }
}
