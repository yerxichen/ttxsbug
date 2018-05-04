package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class JsonBeanSecondHandDivision {
    /**
     * ret : 0
     * msg :
     * region : {"ret":"0","msg":"","count":"9","data":[{"code":"001001001","name":"南通市"},{"code":"001001001001","name":"崇川区"}]}
     * money : {"ret":"0","msg":"","count":"7","data":[{"code":"10","name":"0-10元"},{"code":"50","name":"10-50元"}]}
     * sate : {"ret":"0","msg":"","count":"3","data":[{"code":"01","name":"不限"},{"code":"0101","name":"个人"},{"code":"0102","name":"企业"}]}
     * products : {"ret":"0","msg":"","count":"9","data":[{"pid":"14","name":"有机小白菜","shopprice":"7.60","img":"20160928163606366.jpg","time":"19:51:14","region":"南通市"},{"pid":"12","name":"风干牛肉","shopprice":"30.00","img":"5022.png","time":"19:51:14","region":"南通市"}]}
     */

    private String ret;
    private String msg;
    /**
     * ret : 0
     * msg :
     * count : 9
     * data : [{"code":"001001001","name":"南通市"},{"code":"001001001001","name":"崇川区"}]
     */

    private RegionBean region;
    /**
     * ret : 0
     * msg :
     * count : 7
     * data : [{"code":"10","name":"0-10元"},{"code":"50","name":"10-50元"}]
     */

    private MoneyBean money;
    /**
     * ret : 0
     * msg :
     * count : 3
     * data : [{"code":"01","name":"不限"},{"code":"0101","name":"个人"},{"code":"0102","name":"企业"}]
     */

    private SateBean sate;
    /**
     * ret : 0
     * msg :
     * count : 9
     * data : [{"pid":"14","name":"有机小白菜","shopprice":"7.60","img":"20160928163606366.jpg","time":"19:51:14","region":"南通市"},{"pid":"12","name":"风干牛肉","shopprice":"30.00","img":"5022.png","time":"19:51:14","region":"南通市"}]
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

    public RegionBean getRegion() {
        return region;
    }

    public void setRegion(RegionBean region) {
        this.region = region;
    }

    public MoneyBean getMoney() {
        return money;
    }

    public void setMoney(MoneyBean money) {
        this.money = money;
    }

    public SateBean getSate() {
        return sate;
    }

    public void setSate(SateBean sate) {
        this.sate = sate;
    }

    public ProductsBean getProducts() {
        return products;
    }

    public void setProducts(ProductsBean products) {
        this.products = products;
    }

    public static class RegionBean {
        private String ret;
        private String msg;
        private String count;
        /**
         * code : 001001001
         * name : 南通市
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
            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class MoneyBean {
        private String ret;
        private String msg;
        private String count;
        /**
         * code : 10
         * name : 0-10元
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
            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class SateBean {
        private String ret;
        private String msg;
        private String count;
        /**
         * code : 01
         * name : 不限
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
            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
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
         * time : 19:51:14
         * region : 南通市
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
        }
    }
}
