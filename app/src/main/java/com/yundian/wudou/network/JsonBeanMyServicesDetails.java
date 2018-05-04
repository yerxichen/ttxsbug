package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class JsonBeanMyServicesDetails {

    /**
     * ret : 0
     * msg :
     * newsinfo : {"newsid":"1","name":"恒大冰泉","time":"16:21:47","sate":"商家","contacts":"张经理","mobile":"15985412548","reviewedsate":"1","images":{"ret":"0","msg":"","count":"2","data":[{"img":"9011.png"},{"img":"9012.png"}]}}
     */

    private String ret;
    private String msg;
    /**
     * newsid : 1
     * name : 恒大冰泉
     * time : 16:21:47
     * sate : 商家
     * contacts : 张经理
     * mobile : 15985412548
     * reviewedsate : 1
     * images : {"ret":"0","msg":"","count":"2","data":[{"img":"9011.png"},{"img":"9012.png"}]}
     */

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
        private String newsid;
        private String name;
        private String time;
        private String sate;
        private String contacts;
        private String mobile;
        private String reviewedsate;
        /**
         * ret : 0
         * msg :
         * count : 2
         * data : [{"img":"9011.png"},{"img":"9012.png"}]
         */

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

        public String getReviewedsate() {
            return reviewedsate;
        }

        public void setReviewedsate(String reviewedsate) {
            this.reviewedsate = reviewedsate;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public static class ImagesBean {
            private String ret;
            private String msg;
            private String count;
            /**
             * img : 9011.png
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
