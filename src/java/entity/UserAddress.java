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
public class UserAddress {
    private int id;
    private int userId;
    private String shipName;
    private String shipAddress;
    private int shipCityId;
    private String phoneNum;

    public UserAddress() {
    }

    public UserAddress(int id, int userId, String shipName, String shipAddress, int shipCityId, String phoneNum) {
        this.id = id;
        this.userId = userId;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipCityId = shipCityId;
        this.phoneNum = phoneNum;
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

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
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

    @Override
    public String toString() {
        return "UserAddress{" + "id=" + id + ", userId=" + userId + ", shipName=" + shipName + ", shipAddress=" + shipAddress + ", shipCityId=" + shipCityId + ", phoneNum=" + phoneNum + '}';
    }
    
}
