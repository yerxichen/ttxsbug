package com.yundian.wudou.network;

/**
 * Created by Administrator on 2016/9/26 0026.
 */
public class JsonBeanUserRegister {


    /**
     * ret : 0
     * msg :
     * username :
     * regionname :
     * access_token : 4A52290FEAC58AED81E5E1BC1061E4314415CC48F0C72540
     * expires_in : 72000
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
