package com.yundian.wudou.network;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class JsonBeanUserInformationData {


    /**
     * ret : 0
     * msg :
     * username : 123
     * avatar : 2016845.png
     * credits : 205
     * sex : 男
     */

    private String ret;
    private String msg;
    private String username;
    private String avatar;
    private String credits;
    private String sex;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
