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
public class DashBoardProduct {
    private String name;
    private int amount;
    private int proportion;

    public DashBoardProduct() {
    }

    public DashBoardProduct(String name, int amount, int proportion) {
        this.name = name;
        this.amount = amount;
        this.proportion = proportion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getProportion() {
        return proportion;
    }

    public void setProportion(int proportion) {
        this.proportion = proportion;
    }

    @Override
    public String toString() {
        return "DashBoardProduct{" + "name=" + name + ", amount=" + amount + ", proportion=" + proportion + '}';
    }
}
