package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class JsonBeanConvenienceServicesBanner {
    /**
     * ret : 0
     * msg :
     * data : [{"img":"2014.png","url":"3","urltype":"2"},{"img":"2013.png","url":"2","urltype":"2"},{"img":"2012.png","url":"1","urltype":"1"}]
     */

    private String ret;
    private String msg;
    /**
     * img : 2014.png
     * url : 3
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
