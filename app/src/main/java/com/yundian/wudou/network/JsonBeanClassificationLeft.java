package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public class JsonBeanClassificationLeft {


    /**
     * ret : 0
     * msg :
     * count : 10
     * data : [{"cateid":"2","catenumber":"001001","name":"果蔬生鲜"},{"cateid":"3","catenumber":"001002","name":"肉禽蛋奶"},{"cateid":"4","catenumber":"001003","name":"冷热速食"},{"cateid":"5","catenumber":"001004","name":"休闲食品"},{"cateid":"6","catenumber":"001005","name":"酒水饮料"},{"cateid":"7","catenumber":"001006","name":"粮油调味"},{"cateid":"8","catenumber":"001007","name":"清洁日化"},{"cateid":"9","catenumber":"001008","name":"家居用品"},{"cateid":"10","catenumber":"001009","name":"鲜花蛋糕"},{"cateid":"11","catenumber":"001010","name":"便民服务"}]
     */

    private String ret;
    private String msg;
    private String count;
    /**
     * cateid : 2
     * catenumber : 001001
     * name : 果蔬生鲜
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
        private String cateid;
        private String catenumber;
        private String name;

        public String getCateid() {
            return cateid;
        }

        public void setCateid(String cateid) {
            this.cateid = cateid;
        }

        public String getCatenumber() {
            return catenumber;
        }

        public void setCatenumber(String catenumber) {
            this.catenumber = catenumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
