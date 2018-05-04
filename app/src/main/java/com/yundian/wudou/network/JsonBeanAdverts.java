package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by taozipc on 2017/11/19.
 */

public class JsonBeanAdverts {
    /**
     * ret : 0
     * msg :
     * count : 2
     * data : [{"img":"20161102181008108.jpg","url":"6","urltype":"2"},{"img":"201611021810321032.jpg","url":"5","urltype":"1"}]
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
         * img : 20161102181008108.jpg
         * url : 6
         * urltype : 2
         */

        private String img;
        private String url;
        private String urltype;

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
