package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class JsonBeanMySecondHandDivision {

    /**
     * ret : 0
     * msg :
     * products : {"ret":"0","msg":"","count":"9","data":[{"pid":"14","name":"有机小白菜","shopprice":"7.60","img":"20160928163606366.jpg","time":"14:56:02","region":"南通市","reviewedsate":"0"},{"pid":"12","name":"风干牛肉","shopprice":"30.00","img":"5022.png","time":"14:56:02","region":"南通市","reviewedsate ":"1"}]}
     */

    private String ret;
    private String msg;
    /**
     * ret : 0
     * msg :
     * count : 9
     * data : [{"pid":"14","name":"有机小白菜","shopprice":"7.60","img":"20160928163606366.jpg","time":"14:56:02","region":"南通市","reviewedsate":"0"},{"pid":"12","name":"风干牛肉","shopprice":"30.00","img":"5022.png","time":"14:56:02","region":"南通市","reviewedsate ":"1"}]
     */

    private ProductsBean products;

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

    public ProductsBean getProducts() {
        return products;
    }

    public void setProducts(ProductsBean products) {
        this.products = products;
    }

    public static class ProductsBean {
        private String ret;
        private String msg;
        private String count;
        /**
         * pid : 14
         * name : 有机小白菜
         * shopprice : 7.60
         * img : 20160928163606366.jpg
         * time : 14:56:02
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
            private String pid;
            private String name;
            private String shopprice;
            private String img;
            private String time;
            private String region;
            private String reviewedsate;

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
