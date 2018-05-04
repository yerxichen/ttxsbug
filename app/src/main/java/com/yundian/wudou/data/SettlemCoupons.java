package com.yundian.wudou.data;

import java.util.List;

/**
 * Created by taozipc on 2017/12/11.
 */

public class SettlemCoupons {


    /**
     * ret : 0
     * msg :
     * count : 3
     * data : [{"couponid":"30","couponsn":"NO30","money":"5","orderamountlower":"15","usestarttime":"2017/12/8 0:00:00","useendtime":"2017/12/31 0:00:00","sate":"0","name":"5621"},{"couponid":"29","couponsn":"NO29","money":"2","orderamountlower":"10","usestarttime":"2017/12/11 0:00:00","useendtime":"2017/12/31 0:00:00","sate":"0","name":"258912"},{"couponid":"28","couponsn":"NO28","money":"1","orderamountlower":"5","usestarttime":"2017/12/8 0:00:00","useendtime":"2017/12/31 0:00:00","sate":"0","name":"14561561"}]
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
         * couponid : 30
         * couponsn : NO30
         * money : 5
         * orderamountlower : 15
         * usestarttime : 2017/12/8 0:00:00
         * useendtime : 2017/12/31 0:00:00
         * sate : 0
         * name : 5621
         */

        private String couponid;
        private String couponsn;
        private String money;
        private String orderamountlower;
        private String usestarttime;
        private String useendtime;
        private String sate;
        private String name;

        public String getCouponid() {
            return couponid;
        }

        public void setCouponid(String couponid) {
            this.couponid = couponid;
        }

        public String getCouponsn() {
            return couponsn;
        }

        public void setCouponsn(String couponsn) {
            this.couponsn = couponsn;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getOrderamountlower() {
            return orderamountlower;
        }

        public void setOrderamountlower(String orderamountlower) {
            this.orderamountlower = orderamountlower;
        }

        public String getUsestarttime() {
            return usestarttime;
        }

        public void setUsestarttime(String usestarttime) {
            this.usestarttime = usestarttime;
        }

        public String getUseendtime() {
            return useendtime;
        }

        public void setUseendtime(String useendtime) {
            this.useendtime = useendtime;
        }

        public String getSate() {
            return sate;
        }

        public void setSate(String sate) {
            this.sate = sate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
