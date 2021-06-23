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
public class OrderDetailAdmin {
    private int id;
    private int orderID;
    private String customerName;
    private int productID;
    private String productName;
    private String imageLink;
    private int productPrice;
    private String shipAddress;
    private String phoneNumber;

    @Override
    public String toString() {
        return "OrderDetailAdmin{" + "id=" + id + ", orderID=" + orderID + ", customerName=" + customerName + ", productID=" + productID + ", productName=" + productName + ", imageLink=" + imageLink + ", productPrice=" + productPrice + ", shipAddress=" + shipAddress + ", phoneNumber=" + phoneNumber + '}';
    }

    
    
    
    public OrderDetailAdmin() {
    } 
    
    public OrderDetailAdmin(int id, int orderID, String customerName, int productID, String productName, String imageLink, int productPrice, String shipAddress, String phoneNumber) {
        this.id = id;
        this.orderID = orderID;
        this.customerName = customerName;
        this.productID = productID;
        this.productName = productName;
        this.imageLink = imageLink;
        this.productPrice = productPrice;
        this.shipAddress = shipAddress;
        this.phoneNumber = phoneNumber;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
    
}
