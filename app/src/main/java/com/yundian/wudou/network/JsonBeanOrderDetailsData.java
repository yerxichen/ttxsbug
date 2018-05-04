package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by cookie on 2016/9/29.
 */
public class JsonBeanOrderDetailsData {

    /**
     * ret : 0
     * msg :
     * oid : 544
     * osn : wudoll201701141701214302109
     * addtime : 2017/1/14 17:01:21
     * paysystemtype : 积分
     * consignee : 佘晓涛
     * mobile : 15298392130
     * address : 教育路与通京大道交叉口往南学苑大厦
     * buyerremark :
     * surplusmoney : 0.00
     * paysate : 1
     * orderstatusdescription : 已付款
     * distributionDesn :
     * couponmoney : 0
     * orderamount : 0.00
     * productamount : 0.00
     * shipfee : 3.00
     * ordertype : 1
     * creditsvalue : 1
     * data : [{"pid":"2","name":"攀能钓鱼凳野餐包","shopprice":"0.00","img":"201611090955575557.JPG","buycount":"1","ordertype":"1","creditsvalue":"1"}]
     */

    private String ret;
    private String msg;
    private String oid;
    private String osn;
    private String addtime;
    private String paysystemtype;
    private String consignee;
    private String mobile;
    private String address;
    private String buyerremark;
    private String surplusmoney;
    private String paysate;
    private String orderstatusdescription;
    private String distributionDesn;
    private String couponmoney;
    private String orderamount;
    private String productamount;
    private String shipfee;
    private String ordertype;
    private String creditsvalue;
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

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getPaysystemtype() {
        return paysystemtype;
    }

    public void setPaysystemtype(String paysystemtype) {
        this.paysystemtype = paysystemtype;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuyerremark() {
        return buyerremark;
    }

    public void setBuyerremark(String buyerremark) {
        this.buyerremark = buyerremark;
    }

    public String getSurplusmoney() {
        return surplusmoney;
    }

    public void setSurplusmoney(String surplusmoney) {
        this.surplusmoney = surplusmoney;
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

    public String getDistributionDesn() {
        return distributionDesn;
    }

    public void setDistributionDesn(String distributionDesn) {
        this.distributionDesn = distributionDesn;
    }

    public String getCouponmoney() {
        return couponmoney;
    }

    public void setCouponmoney(String couponmoney) {
        this.couponmoney = couponmoney;
    }

    public String getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(String orderamount) {
        this.orderamount = orderamount;
    }

    public String getProductamount() {
        return productamount;
    }

    public void setProductamount(String productamount) {
        this.productamount = productamount;
    }

    public String getShipfee() {
        return shipfee;
    }

    public void setShipfee(String shipfee) {
        this.shipfee = shipfee;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pid : 2
         * name : 攀能钓鱼凳野餐包
         * shopprice : 0.00
         * img : 201611090955575557.JPG
         * buycount : 1
         * ordertype : 1
         * creditsvalue : 1
         */

        private String pid;
        private String name;
        private String shopprice;
        private String img;
        private String buycount;
        private String ordertype;
        private String creditsvalue;

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

        public String getBuycount() {
            return buycount;
        }

        public void setBuycount(String buycount) {
            this.buycount = buycount;
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
    }
}
