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
public class FeedbackReplies {
    private int id;
    private int feedbackId;
    private int userId;
    private String repliesText;
    private Account user;

    public FeedbackReplies() {
    }

    public FeedbackReplies(int id, int feedbackId, int userId, String repliesText) {
        this.id = id;
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.repliesText = repliesText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRepliesText() {
        return repliesText;
    }

    public void setRepliesText(String repliesText) {
        this.repliesText = repliesText;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }
       
    @Override
    public String toString() {
        return "Feedback_Replies{" + "id=" + id + ", feedbackId=" + feedbackId + ", userId=" + userId + ", repliesText=" + repliesText + '}';
    }
    
    
}
