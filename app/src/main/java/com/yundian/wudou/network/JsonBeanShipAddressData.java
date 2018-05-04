package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class JsonBeanShipAddressData {
    /**
     * ret : 0
     * msg :
     * data : [{"said":"3","consignee":"小张","mobile":"459872287","address":"合肥市瑶海区"},{"said":"4","consignee":"小李","mobile":"18754221587","address":"南京市"},{"said":"5","consignee":"小李","mobile":"18754221587","address":"南京市"}]
     */

    private String ret;
    private String msg;
    /**
     * said : 3
     * consignee : 小张
     * mobile : 459872287
     * address : 合肥市瑶海区
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String said;
        private String consignee;
        private String mobile;
        private String address;

        public String getSaid() {
            return said;
        }

        public void setSaid(String said) {
            this.said = said;
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
    }


    /**
     * ret : 2
     * msg : 请先登录
     * data : []
     */

  /*  private String ret;
    private String msg;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
    */

}
