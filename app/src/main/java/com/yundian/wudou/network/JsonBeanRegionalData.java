package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class JsonBeanRegionalData {
    /**
     * ret : 0
     * msg :
     * count : 9
     * data : [{"code":"001001001","name":"南通市"},{"code":"001001001001","name":"崇川区"},{"code":"001001001002","name":"港闸区"},{"code":"001001001003","name":"通州区"},{"code":"001001001004","name":"海安县"},{"code":"001001001005","name":"如东县"},{"code":"001001001006","name":"如皋"},{"code":"001001001007","name":"海门"},{"code":"001001001008","name":"启东"}]
     */

    private String ret;
    private String msg;
    private String count;
    /**
     * code : 001001001
     * name : 南通市
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
        private String code;
        private String name;

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
    }
}
