/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Thuan
 */
public class Notification {

    private int id;
    private int userId;
    private int orderId;
    private String content;
    private String status;
    private String time;

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", userId=" + userId + ", orderId=" + orderId + ", content=" + content + ", status=" + status + ", time=" + time + '}';
    }

    public Notification() {
    }

    public Notification(int id, int userId, int orderId, String content, String status, String time) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.content = content;
        this.status = status;
        this.time = time;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
