package com.yundian.wudou.data;

import java.util.List;

/**
 * Created by cookie on 2016/8/15.
 */
public class AdapterMoreCommentData {

    private String consumerName, commentDate, commentRate, commentContent;
    private List<String> imgUrls;

    public AdapterMoreCommentData(String consumerName, String commentDate, String commentRate, String commentContent, List<String> imgUrls) {
        this.consumerName = consumerName;
        this.commentDate = commentDate;
        this.commentRate = commentRate;
        this.commentContent = commentContent;
        this.imgUrls = imgUrls;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentRate() {
        return commentRate;
    }

    public void setCommentRate(String commentRate) {
        this.commentRate = commentRate;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }
}
