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
public class Feedback_Replies {
    private int id;
    private int feedbackId;
    private int userId;
    private String repliesText;

    public Feedback_Replies() {
    }

    public Feedback_Replies(int id, int feedbackId, int userId, String repliesText) {
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

    @Override
    public String toString() {
        return "Feedback_Replies{" + "id=" + id + ", feedbackId=" + feedbackId + ", userId=" + userId + ", repliesText=" + repliesText + '}';
    }
    
    
}
