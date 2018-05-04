package com.yundian.wudou.data;

import java.util.List;

/**
 * Created by cookie on 2016/8/15.
 */
public class AdapterMyCommentData {

    private String productId,productName,commentDate,commentRate,commentContent;
    private List<String> imgUrls;

    public AdapterMyCommentData(String productId, String productName, String commentDate, String commentRate, String commentContent, List<String> imgUrls) {
        this.productId = productId;
        this.productName = productName;
        this.commentDate = commentDate;
        this.commentRate = commentRate;
        this.commentContent = commentContent;
        this.imgUrls = imgUrls;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
