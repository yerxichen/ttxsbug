package com.yundian.wudou.network;

/**
 * Created by Administrator on 2016/10/21 0021.
 */

public class JsonBeanUploadImage {
    /**
     * ret : 0
     * msg :
     * media_ids : 3,4,5
     */

    private String ret;
    private String msg;
    private String media_ids;

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

    public String getMedia_ids() {
        return media_ids;
    }

    public void setMedia_ids(String media_ids) {
        this.media_ids = media_ids;
    }
}
