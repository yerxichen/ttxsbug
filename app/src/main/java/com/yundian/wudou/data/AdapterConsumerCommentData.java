package com.yundian.wudou.data;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class AdapterConsumerCommentData {
    private String starNum, userName, userComment, pubDate;

    public AdapterConsumerCommentData(String starNum, String userName, String userComment, String pubDate) {
        this.starNum = starNum;
        this.userName = userName;
        this.userComment = userComment;
        this.pubDate = pubDate;
    }

    public String getStarNum() {
        return starNum;
    }

    public void setStarNum(String starNum) {
        this.starNum = starNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
