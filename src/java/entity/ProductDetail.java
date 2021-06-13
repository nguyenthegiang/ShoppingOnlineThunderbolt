/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * Product Detail is a type of Product, it has 1 more Attribute
 * Only used in Product Detail Page
 */
public class ProductDetail extends Product{
    private String manufacturer;

    public ProductDetail() {
    }

    public ProductDetail(int id, String name, String description, int price, String imageLink, String manufacturer) {
        super(id, name, description, price, imageLink);
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    @Override
    public String toString() {
        return "Product{" + "id=" + getId() + ", name=" + getName() + ", price=" + getPrice() + ", imageLink=" + getImageLink()
                + ", manufacturer=" + getManufacturer() + '}';
    }
}
