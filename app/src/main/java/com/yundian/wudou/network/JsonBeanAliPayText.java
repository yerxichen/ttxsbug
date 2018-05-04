package com.yundian.wudou.network;

/**
 * Created by cookie on 2016/10/12.
 */
public class JsonBeanAliPayText {
    /**
     * ret : 0
     * msg :
     * signOrderStr : partner="2088221868910638"&seller_id="2886855@qq
     * .com"&out_trade_no="wudoll20161012113734949"&subject="商品标题"&body="商品描述"&total_fee="0.01"&notify_url="www.wudoll
     * .com"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="10m"&show_url="m.alipay
     * .com"&sign="l810lggSw0YEA1SK9aBu0EEyIjtC4mqLmEJAAGqJp7qpyYM8rCqLxNaEbqPlGwTiRJjju9krDwSRaanETO8CUJ3qoGfZROxpE
     * %2bH434TnzKHdC7WFj54unE3hnC11%2b2%2bEMsne3hEMjVyqhnNV60pN%2fbtKcGoZRx1GlAlhAERDWk4%3d"&sign_type="RSA"
     */

    private String ret;
    private String msg;
    private String signOrderStr;

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

    public String getSignOrderStr() {
        return signOrderStr;
    }

    public void setSignOrderStr(String signOrderStr) {
        this.signOrderStr = signOrderStr;
    }
}
