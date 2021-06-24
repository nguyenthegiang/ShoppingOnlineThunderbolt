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
public class OrderDetailWithImage extends OrderDetail {
    private String imageLink;

    public OrderDetailWithImage(String imageLink) {
        this.imageLink = imageLink;
    }

    public OrderDetailWithImage() {
    }

    
    public OrderDetailWithImage(String imageLink, int id, int orderID, int productID, String productName, int productPrice) {
        super(id, orderID, productID, productName, productPrice);
        this.imageLink = imageLink;
    }

    
    
    
    
    
    @Override
    public void setProductPrice(int productPrice) {
        super.setProductPrice(productPrice); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getProductPrice() {
        return super.getProductPrice(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setProductName(String productName) {
        super.setProductName(productName); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getProductName() {
        return super.getProductName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setProductID(int productID) {
        super.setProductID(productID); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getProductID() {
        return super.getProductID(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOrderID(int orderID) {
        super.setOrderID(orderID); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getOrderID() {
        return super.getOrderID(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
   
    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
    
    
    
}
