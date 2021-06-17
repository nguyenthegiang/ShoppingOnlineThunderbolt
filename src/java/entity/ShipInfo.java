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
public class ShipInfo {
    
    private int id;
    private int orderId;
    private String customerName;
    private String shippingAddress;
    private int shipCityId;
    private String phoneNum;
    private String note;

    public ShipInfo() {
    }

    public ShipInfo(int id, int orderId, String customerName, String shippingAddress, int shipCityId, String phoneNum, String note) {
        this.id = id;
        this.orderId = orderId;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.shipCityId = shipCityId;
        this.phoneNum = phoneNum;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getShipCityId() {
        return shipCityId;
    }

    public void setShipCityId(int shipCityId) {
        this.shipCityId = shipCityId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ShipInfo{" + "id=" + id + ", orderId=" + orderId + ", customerName=" + customerName + ", shippingAddress=" + shippingAddress + ", shipCityId=" + shipCityId + ", phoneNum=" + phoneNum + ", note=" + note + '}';
    }
    
       
}
