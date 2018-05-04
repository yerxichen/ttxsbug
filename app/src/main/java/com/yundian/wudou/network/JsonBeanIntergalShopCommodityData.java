package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class JsonBeanIntergalShopCommodityData {


    /**
     * ret : 0
     * msg :
     * count : 9
     * data : [{"pid":"215","name":"奥朵客厅灯长方形led吸顶灯","shopprice":"367.00","img":"201609301825342534.jpg","stock":"50","exchangecount":"2006"},{"pid":"214","name":"阿里智能LED吸顶灯方形客厅灯","shopprice":"54.00","img":"201609301824292429.jpg","stock":"50","exchangecount":"2006"},{"pid":"213","name":"欧普照明led吸顶灯","shopprice":"453.00","img":"201609301823262326.jpg","stock":"50","exchangecount":"2006"},{"pid":"203","name":"圆型智能4USB多功能插座","shopprice":"26.00","img":"201609301549564956.jpg","stock":"50","exchangecount":"2006"},{"pid":"200","name":"方便粉丝粉条面非螺蛳","shopprice":"6.00","img":"20160930154704474.jpg","stock":"50","exchangecount":"2006"},{"pid":"199","name":"白家陈记 四川酸辣粉88克/杯*12杯","shopprice":"8.00","img":"20160930154605465.jpg","stock":"50","exchangecount":"2006"},{"pid":"197","name":"金鹏新鲜虾皮500gx2袋","shopprice":"58.00","img":"201609301543264326.jpg","stock":"50","exchangecount":"2006"},{"pid":"195","name":"绿帝干贝淡干 瑶柱大","shopprice":"89.00","img":"201609301542284228.jpg","stock":"50","exchangecount":"2006"},{"pid":"185","name":"山西水塔陈醋2.3L","shopprice":"54.00","img":"20160930153108318.jpg","stock":"50","exchangecount":"2006"}]
     */

    private String ret;
    private String msg;
    private String count;
    /**
     * pid : 215
     * name : 奥朵客厅灯长方形led吸顶灯
     * shopprice : 367.00
     * img : 201609301825342534.jpg
     * stock : 50
     * exchangecount : 2006
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
        private String stock;
        private String exchangecount;

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

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getExchangecount() {
            return exchangecount;
        }

        public void setExchangecount(String exchangecount) {
            this.exchangecount = exchangecount;
        }
    }
}
