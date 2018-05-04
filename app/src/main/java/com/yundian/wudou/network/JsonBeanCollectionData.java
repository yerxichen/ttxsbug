package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by cookie on 2016/9/19.
 */
public class JsonBeanCollectionData {
    /**
     * ret : 0
     * msg :
     * count : 8
     * data : [{"name":"双汇玉米热狗","starcount":"5","commentcount":"1268","img":"5014.png","url":"4","urltype":"2"},{"name":"可口可乐",
     * "starcount":"5","commentcount":"1268","img":"5013.png","url":"3","urltype":"2"},{"name":"红牛维生素","starcount":"5",
     * "commentcount":"1268","img":"5012.png","url":"2","urltype":"2"},{"name":"恒大冰泉","starcount":"5","commentcount":"1268","img":"5011
     * .png","url":"1","urltype":"2"},{"name":"世纪华联","starcount":"5","commentcount":"1268","img":"3024.png","url":"4","urltype":"1"},
     * {"name":"零食派","starcount":"5","commentcount":"1268","img":"3023.png","url":"3","urltype":"1"},{"name":"好又多超市","starcount":"5",
     * "commentcount":"1268","img":"3022.png","url":"2","urltype":"1"},{"name":"羽呈鲜农品屋","starcount":"5","commentcount":"1268","img":"3021
     * .png","url":"1","urltype":"1"}]
     */

    private String ret;
    private String msg;
    private String count;
    /**
     * name : 双汇玉米热狗
     * starcount : 5
     * commentcount : 1268
     * img : 5014.png
     * url : 4
     * urltype : 2
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
        private String name;
        private String starcount;
        private String commentcount;
        private String img;
        private String url;
        private String urltype;

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
    }
}
