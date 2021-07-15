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
public class ProductCompare extends Product{
    
    private float height;
    private float width;
    private float weight;

    public ProductCompare() {
    }

    public ProductCompare(float height, float width, float weight) {
        this.height = height;
        this.width = width;
        this.weight = weight;
    }

    public ProductCompare( int id, String name, String description, int price, String imageLink,float height, float width, float weight) {
        super(id, name, description, price, imageLink);
        this.height = height;
        this.width = width;
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ProductCompare{" + "height=" + height + ", width=" + width + ", weight=" + weight + '}';
    }
    
    
    
            
    
}
