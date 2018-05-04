package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class JsonBeanSecondHandDivisionDetails {

    /**
     * ret : 0
     * msg :
     * productsinfo : {"pid":"15","name":"宏基i3处理器 4G内存 500G硬盘 独显","shopprice":"1200.00","time":"10.19 14:52","sate":"企业","contacts":"陈同洋","mobile":"","creditsvalue":"1","images":{"ret":"0","msg":"","count":"2","data":[{"img":"20161019145206526.jpg"},{"img":"201610191452115211.jpg"}]}}
     */

    private String ret;
    private String msg;
    private ProductsinfoBean productsinfo;

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

    public ProductsinfoBean getProductsinfo() {
        return productsinfo;
    }

    public void setProductsinfo(ProductsinfoBean productsinfo) {
        this.productsinfo = productsinfo;
    }

    public static class ProductsinfoBean {
        /**
         * pid : 15
         * name : 宏基i3处理器 4G内存 500G硬盘 独显
         * shopprice : 1200.00
         * time : 10.19 14:52
         * sate : 企业
         * contacts : 陈同洋
         * mobile :
         * creditsvalue : 1
         * images : {"ret":"0","msg":"","count":"2","data":[{"img":"20161019145206526.jpg"},{"img":"201610191452115211.jpg"}]}
         */

        private String pid;
        private String name;
        private String shopprice;
        private String time;
        private String sate;
        private String contacts;
        private String mobile;
        private String creditsvalue;
        private ImagesBean images;

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
             * data : [{"img":"20161019145206526.jpg"},{"img":"201610191452115211.jpg"}]
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
                 * img : 20161019145206526.jpg
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
