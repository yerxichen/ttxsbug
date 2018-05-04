package com.yundian.wudou.network;

/**
 * Created by Administrator on 2016/10/24 0024.
 */

public class JsonBeanForgetPassword {


    /**
     * ret : 1
     * msg : 验证码错误
     * username :
     * regionname :
     * access_token : 26087D29F9094E6D
     * expires_in :
     */

    private String ret;
    private String msg;
    private String username;
    private String regionname;
    private String access_token;
    private String expires_in;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
