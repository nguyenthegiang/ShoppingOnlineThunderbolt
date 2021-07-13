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
    private int orderId;
    private int star;
    private String feedbackDetail;
    private Account user;
    private Order order;
    private Product product;
    private List<FeedbackReplies> listReplies;

    public Feedback() {
    }

    public Feedback(int id, int userId, int productId, int orderId, int star, String feedbackDetail) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.orderId = orderId;
        this.star = star;
        this.feedbackDetail = feedbackDetail;
    }

    public Feedback(int id, int userId, int productId, int orderId, int star, String feedbackDetail, List<FeedbackReplies> listReplies) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.orderId = orderId;
        this.star = star;
        this.feedbackDetail = feedbackDetail;
        this.listReplies = listReplies;
    }    

    public Feedback(int id, int userId, int productId, int orderId, int star, String feedbackDetail, Account user, Order order, List<FeedbackReplies> listReplies) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.orderId = orderId;
        this.star = star;
        this.feedbackDetail = feedbackDetail;
        this.user = user;
        this.order = order;
        this.listReplies = listReplies;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
       
    public List<FeedbackReplies> getListReplies() {
        return this.listReplies;
    }

    public void setListReplies(List<FeedbackReplies> lsReplies) {
        this.listReplies = lsReplies;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
              
    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", userId=" + userId + ", productId=" + productId + ", star=" + star + ", feedbackDetail=" + feedbackDetail + ", lsReplies=" + listReplies + '}';
    }

}
