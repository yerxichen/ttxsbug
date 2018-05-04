package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public class JsonBeanMyCouponsData {


    /**
     * ret : 0
     * msg :
     * count : 4
     * data : [{"couponid":"5","couponsn":"wudoll005","money":"20","orderamountlower":"200","usestarttime":"2016-09-09","useendtime":"2016-12-12"},{"couponid":"4","couponsn":"wudoll004","money":"150","orderamountlower":"200","usestarttime":"2016-09-09","useendtime":"2016-12-12"},{"couponid":"3","couponsn":"wudoll003","money":"30","orderamountlower":"200","usestarttime":"2016-09-09","useendtime":"2016-12-12"},{"couponid":"2","couponsn":"wudoll002","money":"50","orderamountlower":"200","usestarttime":"2016-09-09","useendtime":"2016-12-12"}]
     */

    private String ret;
    private String msg;
    private String count;
    /**
     * couponid : 5
     * couponsn : wudoll005
     * money : 20
     * orderamountlower : 200
     * usestarttime : 2016-09-09
     * useendtime : 2016-12-12
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
        private String couponid;
        private String couponsn;
        private String money;
        private String orderamountlower;
        private String usestarttime;
        private String useendtime;

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
    }
}
