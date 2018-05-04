package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class JsonBeanMyFavoriteData {

    /**
     * ret : 0
     * msg :
     * count : 2
     * data : [{"recordid":"332","name":"暗夜森林","starcount":"0","commentcount":"0","img":"3033.png","url":"13","urltype":"1","monthlysales":"0","productcount":"8"},{"recordid":"330","name":"力士德国啤酒","starcount":"0","commentcount":"0","img":"3025.png","url":"5","urltype":"1","monthlysales":"1","productcount":"10"}]
     */

    private String ret;
    private String msg;
    private String count;
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
        /**
         * recordid : 332
         * name : 暗夜森林
         * starcount : 0
         * commentcount : 0
         * img : 3033.png
         * url : 13
         * urltype : 1
         * monthlysales : 0
         * productcount : 8
         */

        private String recordid;
        private String name;
        private String starcount;
        private String commentcount;
        private String img;
        private String url;
        private String urltype;
        private String monthlysales;
        private String productcount;

        public String getRecordid() {
            return recordid;
        }

        public void setRecordid(String recordid) {
            this.recordid = recordid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStarcount() {
            return starcount;
        }

        public void setStarcount(String starcount) {
            this.starcount = starcount;
        }

        public String getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(String commentcount) {
            this.commentcount = commentcount;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrltype() {
            return urltype;
        }

        public void setUrltype(String urltype) {
            this.urltype = urltype;
        }

        public String getMonthlysales() {
            return monthlysales;
        }

        public void setMonthlysales(String monthlysales) {
            this.monthlysales = monthlysales;
        }

        public String getProductcount() {
            return productcount;
        }

        public void setProductcount(String productcount) {
            this.productcount = productcount;
        }
    }
}
