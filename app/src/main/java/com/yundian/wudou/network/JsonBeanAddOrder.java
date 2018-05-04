package com.yundian.wudou.network;

/**
 * Created by cookie on 2016/9/30.
 */
public class JsonBeanAddOrder {

    /**
     * ret : 0
     * msg :
     * osn : wudoll20161014120027770,wudoll20161014120027770,wudoll20161014120027770
     * paysn : pay20161014120027770
     */

    private String ret;
    private String msg;
    private String osn;
    private String paysn;

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

    public String getOsn() {
        return osn;
    }

    public void setOsn(String osn) {
        this.osn = osn;
    }

    public String getPaysn() {
        return paysn;
    }

    public void setPaysn(String paysn) {
        this.paysn = paysn;
    }
}
