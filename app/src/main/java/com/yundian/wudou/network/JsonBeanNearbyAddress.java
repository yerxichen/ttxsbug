package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21 0021.
 */

public class JsonBeanNearbyAddress {
    /**
     * ret : 0
     * msg :
     * count : 20
     * data : [{"name":"房产交易大厦","address":"人民东路42号2","addressinfo":"人民东路42号2 房产交易大厦"},{"name":"成功大厦","address":"工农路245号","addressinfo":"工农路245号 成功大厦"},{"name":"金融汇","address":"工农路与文教二路交汇处（南川园路口）","addressinfo":"工农路与文教二路交汇处（南川园路口） 金融汇"},{"name":"银星大厦","address":"工农路226-2","addressinfo":"工农路226-2 银星大厦"},{"name":"瑞景广场","address":"南通市崇川区人民东路159号瑞景广场6号楼1层","addressinfo":"南通市崇川区人民东路159号瑞景广场6号楼1层 瑞景广场"},{"name":"天鑫大厦","address":"崇川区工农路155号","addressinfo":"崇川区工农路155号 天鑫大厦"},{"name":"文峰大厦","address":"崇川区工农路168号","addressinfo":"崇川区工农路168号 文峰大厦"},{"name":"崇川区政务中心大楼","address":"青年东路94号","addressinfo":"青年东路94号 崇川区政务中心大楼"},{"name":"金唐大厦","address":"工农路198号","addressinfo":"工农路198号 金唐大厦"},{"name":"宝隆大厦","address":"宝隆大厦6A08室","addressinfo":"宝隆大厦6A08室 宝隆大厦"},{"name":"鼎典大厦","address":"江苏省南通市崇川区南大街189号","addressinfo":"江苏省南通市崇川区南大街189号 鼎典大厦"},{"name":"金谷大厦","address":"南大街72号","addressinfo":"南大街72号 金谷大厦"},{"name":"南通大厦","address":"人民中路28号","addressinfo":"人民中路28号 南通大厦"},{"name":"光华楼","address":"工农路177","addressinfo":"工农路177 光华楼"},{"name":"金树银花大厦","address":"崇川区南大街28号","addressinfo":"崇川区南大街28号 金树银花大厦"},{"name":"银河大厦","address":"南大街33号","addressinfo":"南大街33号 银河大厦"},{"name":"文峰大厦A座","address":"南通市崇川区工农路168","addressinfo":"南通市崇川区工农路168 文峰大厦A座"},{"name":"新景大厦","address":"工农路59号","addressinfo":"工农路59号 新景大厦"},{"name":"金路大厦","address":"人民东路227","addressinfo":"人民东路227 金路大厦"},{"name":"宏信大厦","address":"工农路445号","addressinfo":"工农路445号 宏信大厦"}]
     */

    private String ret;
    private String msg;
    private String count;
    /**
     * name : 房产交易大厦
     * address : 人民东路42号2
     * addressinfo : 人民东路42号2 房产交易大厦
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
        private String name;
        private String address;
        private String addressinfo;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddressinfo() {
            return addressinfo;
        }

        public void setAddressinfo(String addressinfo) {
            this.addressinfo = addressinfo;
        }
    }
}
