package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class JsonBeanMySecondHandDivisionDetails {

    /**
     * ret : 0
     * msg :
     * productsinfo : {"pid":"1","name":"恒大冰泉","shopprice":"2.00","time":"15:13:46","sate":"商家","contacts":"张经理","mobile":"15985412548","reviewedsate":"0","images":{"ret":"0","msg":"","count":"2","data":[{"img":"9011.png"},{"img":"9012.png"}]}}
     */

    private String ret;
    private String msg;
    /**
     * pid : 1
     * name : 恒大冰泉
     * shopprice : 2.00
     * time : 15:13:46
     * sate : 商家
     * contacts : 张经理
     * mobile : 15985412548
     * reviewedsate : 0
     * images : {"ret":"0","msg":"","count":"2","data":[{"img":"9011.png"},{"img":"9012.png"}]}
     */

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
        private String pid;
        private String name;
        private String shopprice;
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
