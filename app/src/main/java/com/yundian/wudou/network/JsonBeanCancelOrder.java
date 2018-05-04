package com.yundian.wudou.network;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class JsonBeanCancelOrder {


    /**
     * ret : 0
     * msg : 订单取消成功
     */

    private String ret;
    private String msg;

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
}
