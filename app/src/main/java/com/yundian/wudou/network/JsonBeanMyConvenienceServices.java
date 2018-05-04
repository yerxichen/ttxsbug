package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class JsonBeanMyConvenienceServices {

    /**
     * ret : 0
     * msg :
     * news : {"ret":"0","msg":"","count":"9","data":[{"newsid":"14","name":"有机小白菜","img":"20160928163606366.jpg","time":"16:10:00","region":"南通市","reviewedsate":"0"},{"newsid":"12","name":"风干牛肉","img":"5022.png","time":"16:10:00","region":"南通市","reviewedsate":"1"}]}
     */

    private String ret;
    private String msg;
    /**
     * ret : 0
     * msg :
     * count : 9
     * data : [{"newsid":"14","name":"有机小白菜","img":"20160928163606366.jpg","time":"16:10:00","region":"南通市","reviewedsate":"0"},{"newsid":"12","name":"风干牛肉","img":"5022.png","time":"16:10:00","region":"南通市","reviewedsate":"1"}]
     */

    private NewsBean news;

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

    public NewsBean getNews() {
        return news;
    }

    public void setNews(NewsBean news) {
        this.news = news;
    }

    public static class NewsBean {
        private String ret;
        private String msg;
        private String count;
        /**
         * newsid : 14
         * name : 有机小白菜
         * img : 20160928163606366.jpg
         * time : 16:10:00
         * region : 南通市
         * reviewedsate : 0
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
            private String newsid;
            private String name;
            private String img;
            private String time;
            private String region;
            private String reviewedsate;

            public String getNewsid() {
                return newsid;
            }

            public void setNewsid(String newsid) {
                this.newsid = newsid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getReviewedsate() {
                return reviewedsate;
            }

            public void setReviewedsate(String reviewedsate) {
                this.reviewedsate = reviewedsate;
            }
        }
    }
}
