package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class JsonBeanCreditRanking {

    /**
     * ret : 0
     * msg :
     * data : [{"code":"20160","name":"物兜用户0","creditvalue":"8284"},{"code":"20161","name":"物兜用户1","creditvalue":"8284"},{"code":"20162","name":"物兜用户2","creditvalue":"8284"},{"code":"20163","name":"物兜用户3","creditvalue":"8284"},{"code":"20164","name":"物兜用户4","creditvalue":"8284"}]
     */
    private String ret;
    private String msg;
    /**
     * code : 20160
     * name : 物兜用户0
     * creditvalue : 8284
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
        private String code;
        private String name;
        private String creditvalue;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreditvalue() {
            return creditvalue;
        }

        public void setCreditvalue(String creditvalue) {
            this.creditvalue = creditvalue;
        }
    }
}
