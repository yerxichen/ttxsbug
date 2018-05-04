package com.yundian.wudou.network;

/**
 * Created by cookie on 2016/9/12.
 */
public class JsonBeanGetToken {

    /**
     * ret : 0
     * msg :
     * username :
     * regionname :
     * access_token : 303C79365C24416D516817484285C44E94D8D505C1728E33
     * push_alias : 13675113207
     * expires_in : 72000
     */

    private String ret;
    private String msg;
    private String username;
    private String regionname;
    private String access_token;
    private String push_alias;
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

    public String getPush_alias() {
        return push_alias;
    }

    public void setPush_alias(String push_alias) {
        this.push_alias = push_alias;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
