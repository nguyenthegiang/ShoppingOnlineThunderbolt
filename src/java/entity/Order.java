/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Thuan
 */
public class Order {
    private int id;
    private int userId;
    private double totalPrice;
    private String note;
    private String status;
    private String date;
    
    private Date orderDate;
    
    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", userId=" + userId + ", totalPrice=" + totalPrice + ", note=" + note + ", status=" + status + ", date=" + date + '}';
    }

    
    
    public Order(){
        
    }
    
    
    public Order(int id, int userId, double totalPrice, String note, String status, String date) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.note = note;
        this.status = status;
        this.date = date;
    }

    public Order(int id, int userId, double totalPrice, String note, String status, String date, Date orderDate) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.note = note;
        this.status = status;
        this.date = date;
        this.orderDate = orderDate;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    
            
    
}
