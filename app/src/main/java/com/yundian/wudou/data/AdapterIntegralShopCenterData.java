package com.yundian.wudou.data;

/**
 * Created by Itachi on 2016/8/30.
 */
public class AdapterIntegralShopCenterData {

    private  String UserName;
    private   String  UserPoint;
    private  String  UserCode;



    public AdapterIntegralShopCenterData(String  UserPoint, String UserName,String UserCode){

        this.UserName = UserName;
        this.UserPoint = UserPoint;
        this.UserCode = UserCode;

    }



    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userNmame) {
        UserName = userNmame;
    }

    public String getUserPoint() {
        return UserPoint;
    }

    public void setUserPoint(String userPoint) {
        UserPoint = userPoint;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

}
