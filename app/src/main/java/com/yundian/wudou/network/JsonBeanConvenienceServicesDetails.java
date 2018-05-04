package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class JsonBeanConvenienceServicesDetails {

    /**
     * ret : 0
     * msg :
     * newsinfo : {"newsid":"14","name":"一诺今生助您幸福","time":"01.07 14:04","sate":"企业","contacts":"陈同洋","mobile":"","creditsvalue":"1","images":{"ret":"0","msg":"","count":"2","data":[{"img":"201610191459265926.jpg"},{"img":"201610191459305930.jpg"}]}}
     */

    private String ret;
    private String msg;
    private NewsinfoBean newsinfo;

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

    public NewsinfoBean getNewsinfo() {
        return newsinfo;
    }

    public void setNewsinfo(NewsinfoBean newsinfo) {
        this.newsinfo = newsinfo;
    }

    public static class NewsinfoBean {
        /**
         * newsid : 14
         * name : 一诺今生助您幸福
         * time : 01.07 14:04
         * sate : 企业
         * contacts : 陈同洋
         * mobile :
         * creditsvalue : 1
         * images : {"ret":"0","msg":"","count":"2","data":[{"img":"201610191459265926.jpg"},{"img":"201610191459305930.jpg"}]}
         */

        private String newsid;
        private String name;
        private String time;
        private String sate;
        private String contacts;
        private String mobile;
        private String creditsvalue;
        private ImagesBean images;

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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSate() {
            return sate;
        }

        public void setSate(String sate) {
            this.sate = sate;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCreditsvalue() {
            return creditsvalue;
        }

        public void setCreditsvalue(String creditsvalue) {
            this.creditsvalue = creditsvalue;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public static class ImagesBean {
            /**
             * ret : 0
             * msg :
             * count : 2
             * data : [{"img":"201610191459265926.jpg"},{"img":"201610191459305930.jpg"}]
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
                 * img : 201610191459265926.jpg
                 */

                private String img;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }
        }
    }
}
