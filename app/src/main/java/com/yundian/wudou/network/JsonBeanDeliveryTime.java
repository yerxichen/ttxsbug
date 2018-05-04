package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public class JsonBeanDeliveryTime {
    /**
     * ret : 0
     * msg :
     * data : [{"autotimeid":"1","distributionDesn":"11-12点"},{"autotimeid":"2","distributionDesn":"12-13点"},{"autotimeid":"3","distributionDesn":"13-15点"}]
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
         * autotimeid : 1
         * distributionDesn : 11-12点
         */

        private String autotimeid;
        private String distributionDesn;

        public String getAutotimeid() {
            return autotimeid;
        }

        public void setAutotimeid(String autotimeid) {
            this.autotimeid = autotimeid;
        }

        public String getDistributionDesn() {
            return distributionDesn;
        }

        public void setDistributionDesn(String distributionDesn) {
            this.distributionDesn = distributionDesn;
        }
    }
}
