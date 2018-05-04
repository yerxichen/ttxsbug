package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class JsonBeanLoadAddress {
    /**
     * ret : 0
     * msg :
     * data : [{"said":"1","consignee":"张晓","mobile":"18754665845","address":"南京市雨花区风文路18号"},{"said":"2","consignee":"","mobile":"16958457745","address":"南京市宣武区爱上大街17号"}]
     */

    private String ret;
    private String msg;
    /**
     * said : 1
     * consignee : 张晓
     * mobile : 18754665845
     * address : 南京市雨花区风文路18号
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
        private String addressmark;

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

        public String getAddressmark() {
            return addressmark;
        }

        public void setAddressmark(String addressmark) {
            this.addressmark = addressmark;
        }
    }
}
