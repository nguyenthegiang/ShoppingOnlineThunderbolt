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
    private int userId;
    private int orderID;
    private String customerName;
    private int productID;
    private String productName;
    private String imageLink;
    private int productPrice;
    private String shipAddress;
    private String phoneNumber;
    private int status;

    public OrderDetailAdmin(int id, int userId, int orderID, 
            String customerName, int productID, String productName,
            String imageLink, int productPrice, String shipAddress,
            String phoneNumber, int status) {
        this.id = id;
        this.userId = userId;
        this.orderID = orderID;
        this.customerName = customerName;
        this.productID = productID;
        this.productName = productName;
        this.imageLink = imageLink;
        this.productPrice = productPrice;
        this.shipAddress = shipAddress;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDetailAdmin{" + "id=" + id + ", userId=" + userId + ", orderID=" + orderID + ", customerName=" + customerName + ", productID=" + productID + ", productName=" + productName + ", imageLink=" + imageLink + ", productPrice=" + productPrice + ", shipAddress=" + shipAddress + ", phoneNumber=" + phoneNumber + ", status=" + status + '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OrderDetailAdmin() {
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
