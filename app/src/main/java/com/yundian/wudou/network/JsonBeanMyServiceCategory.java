package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public class JsonBeanMyServiceCategory {

    /**
     * ret : 0
     * msg :
     * count : 9
     * data : [{"code":"","name":"类别"},{"code":"1","name":"家政服务"},{"code":"2","name":"装修建材"},{"code":"3","name":"汽车服务"},{"code":"4","name":"婚庆摄影"},{"code":"5","name":"旅游度假"},{"code":"6","name":"休闲娱乐"},{"code":"7","name":"教育培训"},{"code":"8","name":"其他服务"}]
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
         * code :
         * name : 类别
         */

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
