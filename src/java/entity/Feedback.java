/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author TRANTATDAT
 */
public class Feedback {

    private int id;
    private int userId;
    private int productId;
    private int star;
    private String feedbackDetail;
    private List<Feedback_Replies> lsReplies;

    public Feedback() {
    }

    public Feedback(int id, int userId, int productId, int star, String feedbackDetail, List<Feedback_Replies> lsReplies) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.star = star;
        this.feedbackDetail = feedbackDetail;
        this.lsReplies = lsReplies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getFeedbackDetail() {
        return feedbackDetail;
    }

    public void setFeedbackDetail(String feedbackDetail) {
        this.feedbackDetail = feedbackDetail;
    }

    public List<Feedback_Replies> getLsReplies() {
        return lsReplies;
    }

    public void setLsReplies(List<Feedback_Replies> lsReplies) {
        this.lsReplies = lsReplies;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", userId=" + userId + ", productId=" + productId + ", star=" + star + ", feedbackDetail=" + feedbackDetail + ", lsReplies=" + lsReplies + '}';
    }

}
