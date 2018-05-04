package com.yundian.wudou.data;

/**
 * Created by Itachi on 2016/8/30.
 */
public class AdapterIntegralShopRightData {
    private  String NickName;
    private  int   UserPoint;



    public AdapterIntegralShopRightData(int  UserPoint, String UserName){

        this.NickName = UserName;
        this.UserPoint = UserPoint;
    }



    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public int getUserPoint() {
        return UserPoint;
    }

    public void setUserPoint(int userPoint) {
        UserPoint = userPoint;
    }


}
