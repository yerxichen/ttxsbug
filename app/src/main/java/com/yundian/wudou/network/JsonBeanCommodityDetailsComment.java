package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/19 0019.
 */

public class JsonBeanCommodityDetailsComment {

    /**
     * ret : 0
     * msg :
     * treviewscount : 4
     * percentage : 80%
     * data : [{"star":"4","username":"物兜","message":"下次不买了","time":"2016/9/24 17:27:17"},{"star":"3","username":"物兜","message":"给个赞","time":"2016/9/24 17:27:15"},{"star":"2","username":"物兜","message":"非常好的效果","time":"2016/9/24 17:27:14"},{"star":"1","username":"物兜","message":"质量一般","time":"2016/9/24 17:27:13"}]
     */

    private String ret;
    private String msg;
    private String treviewscount;
    private String percentage;
    /**
     * star : 4
     * username : 物兜
     * message : 下次不买了
     * time : 2016/9/24 17:27:17
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

    public String getTreviewscount() {
        return treviewscount;
    }

    public void setTreviewscount(String treviewscount) {
        this.treviewscount = treviewscount;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String star;
        private String username;
        private String message;
        private String time;

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
