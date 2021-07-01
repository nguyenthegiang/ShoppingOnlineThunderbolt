/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author thong
 */
public class UserEditAddress extends Account {
    private String shipAddress;

    public UserEditAddress() {
    }

    public UserEditAddress(String shipAddress, int id, String user, String pass, String email, String activeCode, int isSell, int isAdmin, int status) {
        super(id, user, pass, email, activeCode, isSell, isAdmin, status);
        this.shipAddress = shipAddress;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + getId() + ", user=" + getUser() + ", pass=" + getPass() + ", email=" + getEmail() + ", activeCode=" + getActiveCode() + ", isSell=" + getIsSell() + ", isAdmin=" + getIsAdmin() + ", status=" + getStatus() +"shipAddress=" + getShipAddress()+ '}';
        //return "UserEditAddress{" + "shipAddress=" + shipAddress + '}';
    }
    
}
