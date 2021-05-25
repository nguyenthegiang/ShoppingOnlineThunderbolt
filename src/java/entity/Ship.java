/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ADMIN
 */
public class Ship {
    private String cityName;
    private int shipPrice;

    public Ship() {
    }

    public Ship(String cityName, int shipPrice) {
        this.cityName = cityName;
        this.shipPrice = shipPrice;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getShipPrice() {
        return shipPrice;
    }

    public void setShipPrice(int shipPrice) {
        this.shipPrice = shipPrice;
    }

    @Override
    public String toString() {
        return "Ship{" + "cityName=" + cityName + ", shipPrice=" + shipPrice + '}';
    }
    
    public String getShipPriceWithDot() {
        String priceDot = "" + shipPrice;
        int i = priceDot.length() - 3;
        while (i > 0) {
            priceDot = priceDot.substring(0, i) + "." + priceDot.substring(i, priceDot.length());
            i -= 3;
        }
        return priceDot;
    }
    
}
