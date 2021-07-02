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
public class OrderDetail {
    private int id;
    private int orderID;
    private int productID;
    private String productName;
    private int productPrice;
    private int quantity;

    @Override
    public String toString() {
        return "OrderDetail{" + "id=" + id + ", orderID=" + orderID + ", productID=" + productID + ", productName=" + productName + ", productPrice=" + productPrice + ", quantity=" + quantity + '}';
    }
       
    public OrderDetail(){
        
    }

    public OrderDetail(int id, int orderID, int productID, String productName, int productPrice, int quantity) {
        this.id = id;
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
         
}

