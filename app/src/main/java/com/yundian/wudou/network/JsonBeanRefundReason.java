package com.yundian.wudou.network;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class JsonBeanRefundReason {

    /**
     * ret : 0
     * msg :
     * refund_reason : 其他
     * refund_mark : abc
     * refund_feedback :
     * id : 7
     * data : [{"id":"1","text":"收到商品破损"},{"id":"2","text":"商品错发/漏发"},{"id":"3","text":"收到商品与描述不符"},{"id":"4","text":"商品质量问题"},{"id":"5","text":"未按约定时间发货"},{"id":"6","text":"拍错了"},{"id":"7","text":"其他"}]
     */

    private String ret;
    private String msg;
    private String refund_reason;
    private String refund_mark;
    private String refund_feedback;
    private String id;
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

    public String getRefund_reason() {
        return refund_reason;
    }

    public void setRefund_reason(String refund_reason) {
        this.refund_reason = refund_reason;
    }

    public String getRefund_mark() {
        return refund_mark;
    }

    public void setRefund_mark(String refund_mark) {
        this.refund_mark = refund_mark;
    }

    public String getRefund_feedback() {
        return refund_feedback;
    }

    public void setRefund_feedback(String refund_feedback) {
        this.refund_feedback = refund_feedback;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * text : 收到商品破损
         */

        private String id;
        private String text;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
