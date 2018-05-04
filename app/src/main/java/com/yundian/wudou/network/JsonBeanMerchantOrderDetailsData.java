package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class JsonBeanMerchantOrderDetailsData {
    /**
     * ret : 0
     * msg :
     * oid : 535
     * osn : wudoll201701090950430132409
     * addtime : 2017/1/9 9:50:43
     * paysystemtype :
     * consignee : 佘晓涛
     * mobile : 15298392130
     * address : 教育路与通京大道交叉口往南学苑大厦
     * buyerremark :
     * surplusmoney : 1272.80
     * paysate : 0
     * orderstatusdescription : 待付款
     * distributionDesn : 上午11点-12点配送
     * couponmoney : 0
     * orderamount : 1272.80
     * productamount : 1270.80
     * shipfee : 2.00
     * ordertype : 0
     * creditsvalue : 0
     * data : [{"pid":"95","name":"红心猕猴桃 30粒","shopprice":"46.00","img":"201609292043254325.jpg","buycount":"2","ordertype":"0","creditsvalue":"0"},{"pid":"86","name":"恒蜂源旗舰店 精品鸭 恒沃3足年老鸭","shopprice":"76.00","img":"20160929200111111.jpg","buycount":"2","ordertype":"0","creditsvalue":"0"},{"pid":"124","name":"闽乐手撕面包2斤","shopprice":"35.00","img":"201609301026282628.png","buycount":"2","ordertype":"0","creditsvalue":"0"},{"pid":"157","name":"西湖牌龙井茶 2016新茶 茶叶绿茶","shopprice":"60.00","img":"201609301447584758.png","buycount":"2","ordertype":"0","creditsvalue":"0"},{"pid":"163","name":"福临门 葵花籽原香食用调和油","shopprice":"76.00","img":"201609301456245624.jpg","buycount":"2","ordertype":"0","creditsvalue":"0"},{"pid":"166","name":"鲁花5S一级花生油5.436L","shopprice":"43.00","img":"20160930150050050.jpg","buycount":"2","ordertype":"0","creditsvalue":"0"},{"pid":"187","name":"日本进口资生堂洗颜专科洗面奶","shopprice":"48.00","img":"201609301532143214.png","buycount":"2","ordertype":"0","creditsvalue":"0"},{"pid":"193","name":"郁美净浴后乳液220ml","shopprice":"15.00","img":"201609301540174017.png","buycount":"2","ordertype":"0","creditsvalue":"0"},{"pid":"207","name":"欧莱雅护发奇焕润发精油100ml","shopprice":"79.00","img":"20160930155905595.png","buycount":"2","ordertype":"0","creditsvalue":"0"},{"pid":"203","name":"圆型智能4USB多功能插座","shopprice":"26.00","img":"201609301549564956.jpg","buycount":"2","ordertype":"0","creditsvalue":"0"},{"pid":"19","name":"仿真花 绢花 高端仿真玫瑰","shopprice":"58.00","img":"201609291448474847.png","buycount":"2","ordertype":"0","creditsvalue":"0"},{"pid":"20","name":"盆栽花卉红掌四季开花办公室","shopprice":"29.00","img":"201609291451255125.png","buycount":"2","ordertype":"0","creditsvalue":"0"},{"pid":"21","name":"栀子花文竹铁海棠沙漠玫瑰","shopprice":"25.90","img":"20160929145402542.png","buycount":"2","ordertype":"0","creditsvalue":"0"},{"pid":"22","name":"云南野生石斛兰花枫","shopprice":"18.50","img":"201609291457405740.png","buycount":"2","ordertype":"0","creditsvalue":"0"}]
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
         * pid : 95
         * name : 红心猕猴桃 30粒
         * shopprice : 46.00
         * img : 201609292043254325.jpg
         * buycount : 2
         * ordertype : 0
         * creditsvalue : 0
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
