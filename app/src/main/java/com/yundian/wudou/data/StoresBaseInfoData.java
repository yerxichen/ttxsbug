package com.yundian.wudou.data;

import java.io.Serializable;

/**
 * Created by 乐阳 on 2018/2/3.
 */

public class StoresBaseInfoData implements Serializable {


    /**
     * ret : 0
     * msg :
     * storesinfo : {"storeid":"27","name":"蔬心便利","img":"201707011052165216.jpg","startvalue":"8.00","startfee":"0.00","monthlysales":"3964","productscount":"118","hasfavorite":"1","hascoupons":"0","isopen":"0","storemodel":"自营","isDistributioning":"2","isDistributioningMsg":""}
     */

    private String ret;
    private String msg;
    private StoresinfoBean storesinfo;

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

    public StoresinfoBean getStoresinfo() {
        return storesinfo;
    }

    public void setStoresinfo(StoresinfoBean storesinfo) {
        this.storesinfo = storesinfo;
    }

    public static class StoresinfoBean {
        /**
         * storeid : 27
         * name : 蔬心便利
         * img : 201707011052165216.jpg
         * startvalue : 8.00
         * startfee : 0.00
         * monthlysales : 3964
         * productscount : 118
         * hasfavorite : 1
         * hascoupons : 0
         * isopen : 0
         * storemodel : 自营
         * isDistributioning : 2
         * isDistributioningMsg :
         */

        private String storeid;
        private String name;
        private String img;
        private String startvalue;
        private String startfee;
        private String monthlysales;
        private String productscount;
        private String hasfavorite;
        private String hascoupons;
        private String isopen;
        private String storemodel;
        private String isDistributioning;
        private String isDistributioningMsg;

        public String getStoreid() {
            return storeid;
        }

        public void setStoreid(String storeid) {
            this.storeid = storeid;
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

        public String getStartvalue() {
            return startvalue;
        }

        public void setStartvalue(String startvalue) {
            this.startvalue = startvalue;
        }

        public String getStartfee() {
            return startfee;
        }

        public void setStartfee(String startfee) {
            this.startfee = startfee;
        }

        public String getMonthlysales() {
            return monthlysales;
        }

        public void setMonthlysales(String monthlysales) {
            this.monthlysales = monthlysales;
        }

        public String getProductscount() {
            return productscount;
        }

        public void setProductscount(String productscount) {
            this.productscount = productscount;
        }

        public String getHasfavorite() {
            return hasfavorite;
        }

        public void setHasfavorite(String hasfavorite) {
            this.hasfavorite = hasfavorite;
        }

        public String getHascoupons() {
            return hascoupons;
        }

        public void setHascoupons(String hascoupons) {
            this.hascoupons = hascoupons;
        }

        public String getIsopen() {
            return isopen;
        }

        public void setIsopen(String isopen) {
            this.isopen = isopen;
        }

        public String getStoremodel() {
            return storemodel;
        }

        public void setStoremodel(String storemodel) {
            this.storemodel = storemodel;
        }

        public String getIsDistributioning() {
            return isDistributioning;
        }

        public void setIsDistributioning(String isDistributioning) {
            this.isDistributioning = isDistributioning;
        }

        public String getIsDistributioningMsg() {
            return isDistributioningMsg;
        }

        public void setIsDistributioningMsg(String isDistributioningMsg) {
            this.isDistributioningMsg = isDistributioningMsg;
        }

        @Override
        public String toString() {
            return "StoresinfoBean{" +
                    "storeid='" + storeid + '\'' +
                    ", name='" + name + '\'' +
                    ", img='" + img + '\'' +
                    ", startvalue='" + startvalue + '\'' +
                    ", startfee='" + startfee + '\'' +
                    ", monthlysales='" + monthlysales + '\'' +
                    ", productscount='" + productscount + '\'' +
                    ", hasfavorite='" + hasfavorite + '\'' +
                    ", hascoupons='" + hascoupons + '\'' +
                    ", isopen='" + isopen + '\'' +
                    ", storemodel='" + storemodel + '\'' +
                    ", isDistributioning='" + isDistributioning + '\'' +
                    ", isDistributioningMsg='" + isDistributioningMsg + '\'' +
                    '}';
        }
    }
}
