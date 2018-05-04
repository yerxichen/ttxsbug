package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by cookie on 2016/9/29.
 */
public class JsonBeanMyAllOrder {

    /**
     * ret : 0
     * msg :
     * data : [{"storeid":"14","storename":"积分商城","storeimg":"3025.png","paysate":"1","orderstatusdescription":"已付款","oid":"544","osn":"wudoll201701141701214302109","orderamount":"0.00","surplusmoney":"0.00","addtime":"2017/1/14 17:01:21","isreviews":"1","reviewstext":"去评论","ordertype":"1","creditsvalue":"1","productsdata":[{"pid":"2","img":"201611090955575557.JPG"}]},{"storeid":"14","storename":"积分商城","storeimg":"3025.png","paysate":"1","orderstatusdescription":"已付款","oid":"541","osn":"wudoll201701141650191320409","orderamount":"0.00","surplusmoney":"0.00","addtime":"2017/1/14 16:50:19","isreviews":"1","reviewstext":"去评论","ordertype":"1","creditsvalue":"1","productsdata":[{"pid":"2","img":"201611090955575557.JPG"}]},{"storeid":"17","storename":"花语城市超市","storeimg":"3025.png","paysate":"0","orderstatusdescription":"待付款","oid":"535","osn":"wudoll201701090950430132409","orderamount":"1272.80","surplusmoney":"1272.80","addtime":"2017/1/9 9:50:43","isreviews":"1","reviewstext":"去评论","ordertype":"0","creditsvalue":"0","productsdata":[{"pid":"95","img":"201609292043254325.jpg"},{"pid":"86","img":"20160929200111111.jpg"},{"pid":"124","img":"201609301026282628.png"},{"pid":"157","img":"201609301447584758.png"},{"pid":"163","img":"201609301456245624.jpg"},{"pid":"166","img":"20160930150050050.jpg"},{"pid":"187","img":"201609301532143214.png"},{"pid":"193","img":"201609301540174017.png"},{"pid":"207","img":"20160930155905595.png"},{"pid":"203","img":"201609301549564956.jpg"},{"pid":"19","img":"201609291448474847.png"},{"pid":"20","img":"201609291451255125.png"},{"pid":"21","img":"20160929145402542.png"},{"pid":"22","img":"201609291457405740.png"}]},{"storeid":"9","storename":"晶码西餐","storeimg":"3025.png","paysate":"0","orderstatusdescription":"待付款","oid":"534","osn":"wudoll201701090945510413209","orderamount":"906.00","surplusmoney":"906.00","addtime":"2017/1/9 9:45:51","isreviews":"1","reviewstext":"去评论","ordertype":"0","creditsvalue":"0","productsdata":[{"pid":"213","img":"201609301823262326.jpg"}]},{"storeid":"14","storename":"物兜自营店","storeimg":"3025.png","paysate":"0","orderstatusdescription":"待付款","oid":"530","osn":"wudoll201701071821513421009","orderamount":"1203.00","surplusmoney":"1203.00","addtime":"2017/1/7 18:21:51","isreviews":"1","reviewstext":"去评论","ordertype":"0","creditsvalue":"0","productsdata":[{"pid":"215","img":"201609301825342534.jpg"},{"pid":"224","img":"201701071538483848.png"},{"pid":"225","img":"201701071554525452.png"},{"pid":"226","img":"201701071558565856.png"},{"pid":"227","img":"20170107160736736.png"}]},{"storeid":"7","storename":"零食工坊","storeimg":"3025.png","paysate":"1","orderstatusdescription":"已付款","oid":"519","osn":"wudoll201701031816322134029","orderamount":"714.00","surplusmoney":"714.00","addtime":"2017/1/3 18:16:32","isreviews":"1","reviewstext":"去评论","ordertype":"0","creditsvalue":"0","productsdata":[{"pid":"206","img":"201609301554565456.jpg"}]},{"storeid":"15","storename":"东芝小镇蛋糕","storeimg":"3025.png","paysate":"1","orderstatusdescription":"已付款","oid":"518","osn":"wudoll201701031816322134019","orderamount":"49.00","surplusmoney":"49.00","addtime":"2017/1/3 18:16:32","isreviews":"1","reviewstext":"去评论","ordertype":"0","creditsvalue":"0","productsdata":[{"pid":"200","img":"20160930154704474.jpg"}]},{"storeid":"14","storename":"物兜自营店铺","storeimg":"3025.png","paysate":"1","orderstatusdescription":"已付款","oid":"517","osn":"wudoll201701031816322134009","orderamount":"737.00","surplusmoney":"737.00","addtime":"2017/1/3 18:16:32","isreviews":"1","reviewstext":"去评论","ordertype":"0","creditsvalue":"0","productsdata":[{"pid":"215","img":"201609301825342534.jpg"}]},{"storeid":"17","storename":"花语城市超市","storeimg":"3025.png","paysate":"1","orderstatusdescription":"退款申请中","oid":"482","osn":"wudoll201612261815272341009","orderamount":"94.00","surplusmoney":"94.00","addtime":"2016/12/26 18:15:27","isreviews":"1","reviewstext":"去评论","ordertype":"0","creditsvalue":"0","productsdata":[{"pid":"95","img":"201609292043254325.jpg"}]}]
     */

    private String ret;
    private String msg;
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
        /**
         * storeid : 14
         * storename : 积分商城
         * storeimg : 3025.png
         * paysate : 1
         * orderstatusdescription : 已付款
         * oid : 544
         * osn : wudoll201701141701214302109
         * orderamount : 0.00
         * surplusmoney : 0.00
         * addtime : 2017/1/14 17:01:21
         * isreviews : 1
         * reviewstext : 去评论
         * ordertype : 1
         * creditsvalue : 1
         * productsdata : [{"pid":"2","img":"201611090955575557.JPG"}]
         */

        private String storeid;
        private String storename;
        private String storeimg;
        private String paysate;
        private String orderstatusdescription;
        private String oid;
        private String osn;
        private String orderamount;
        private String surplusmoney;
        private String addtime;
        private String isreviews;
        private String reviewstext;
        private String ordertype;
        private String creditsvalue;
        private List<ProductsdataBean> productsdata;

        public String getStoreid() {
            return storeid;
        }

        public void setStoreid(String storeid) {
            this.storeid = storeid;
        }

        public String getStorename() {
            return storename;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public String getStoreimg() {
            return storeimg;
        }

        public void setStoreimg(String storeimg) {
            this.storeimg = storeimg;
        }

        public String getPaysate() {
            return paysate;
        }

        public void setPaysate(String paysate) {
            this.paysate = paysate;
        }

        public String getOrderstatusdescription() {
            return orderstatusdescription;
        }

        public void setOrderstatusdescription(String orderstatusdescription) {
            this.orderstatusdescription = orderstatusdescription;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getOsn() {
            return osn;
        }

        public void setOsn(String osn) {
            this.osn = osn;
        }

        public String getOrderamount() {
            return orderamount;
        }

        public void setOrderamount(String orderamount) {
            this.orderamount = orderamount;
        }

        public String getSurplusmoney() {
            return surplusmoney;
        }

        public void setSurplusmoney(String surplusmoney) {
            this.surplusmoney = surplusmoney;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getIsreviews() {
            return isreviews;
        }

        public void setIsreviews(String isreviews) {
            this.isreviews = isreviews;
        }

        public String getReviewstext() {
            return reviewstext;
        }

        public void setReviewstext(String reviewstext) {
            this.reviewstext = reviewstext;
        }

        public String getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(String ordertype) {
            this.ordertype = ordertype;
        }

        public String getCreditsvalue() {
            return creditsvalue;
        }

        public void setCreditsvalue(String creditsvalue) {
            this.creditsvalue = creditsvalue;
        }

        public List<ProductsdataBean> getProductsdata() {
            return productsdata;
        }

        public void setProductsdata(List<ProductsdataBean> productsdata) {
            this.productsdata = productsdata;
        }

        public static class ProductsdataBean {
            /**
             * pid : 2
             * img : 201611090955575557.JPG
             */

            private String pid;
            private String img;

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
