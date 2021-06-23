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
public class Invoices {
    
    private String userName;
    private String productName;
    private String imageLink;
    private int sellPrice;
    private String phoneNum;

    @Override
    public String toString() {
        return "Invoices{" + "userName=" + userName + ", productName=" + productName + ", imageLink=" + imageLink + ", sellPrice=" + sellPrice + ", phoneNum=" + phoneNum + '}';
    }

    
    
    public Invoices (){
        
    }
    
    public Invoices(String userName, String productName, String imageLink, int sellPrice, String phoneNum) {
        this.userName = userName;
        this.productName = productName;
        this.imageLink = imageLink;
        this.sellPrice = sellPrice;
        this.phoneNum = phoneNum;
    }

    
    
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    
    
}
