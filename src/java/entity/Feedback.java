/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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

    public Feedback() {
    }

    public Feedback(int id, int userId, int productId, int star, String feedbackDetail) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.star = star;
        this.feedbackDetail = feedbackDetail;
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

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", userId=" + userId + ", productId=" + productId + ", star=" + star + ", feedbackDetail=" + feedbackDetail + '}';
    }
    
    
}
