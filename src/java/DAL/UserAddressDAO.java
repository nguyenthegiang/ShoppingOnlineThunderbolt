/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.UserAddress;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TRANTATDAT
 */
public class UserAddressDAO extends BaseDAO<UserAddress> {

    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * get all default ship address of user
     *
     * @return a list of ship address
     */
    public List<UserAddress> getAllUserAddress() {
        List<UserAddress> list = new ArrayList<>();
        String query = "SELECT * FROM UserAddress";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new UserAddress(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getString("ShipName"),
                        rs.getString("ShipAddress"),
                        rs.getInt("ShipCityID"),
                        rs.getString("PhoneNum")
                ));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * get an address by id
     *
     * @param userAddressId
     * @return an address
     */
    public UserAddress getAddressById(int userAddressId) {
        String query = "SELECT * FROM UserAddress WHERE ID = ?";
        UserAddress theAddress = new UserAddress();
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userAddressId);
            rs = ps.executeQuery();
            while (rs.next()) {
                theAddress = new UserAddress(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getString("ShipName"),
                        rs.getString("ShipAddress"),
                        rs.getInt("ShipCityID"),
                        rs.getString("PhoneNum")
                );
            }
            ps.close();
            return theAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get an address by user id
     *
     * @param userId
     * @return an address
     */
    public UserAddress getAddressByUserId(int userId) {
        String query = "SELECT * FROM UserAddress WHERE UserID = ?";
        UserAddress theAddress = new UserAddress();
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                theAddress = new UserAddress(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getString("ShipName"),
                        rs.getString("ShipAddress"),
                        rs.getInt("ShipCityID"),
                        rs.getString("PhoneNum")
                );
            }
            ps.close();
            return theAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void add(UserAddress userAddress) {
        String query = "INSERT INTO UserAddress VALUES (?, ?, ?, ?, ?);";
        try {
            ps = connection.prepareStatement(query);
            //Set data to the "?"
            ps.setInt(1, userAddress.getUserId());
            ps.setString(2, userAddress.getShipName());
            ps.setString(3, userAddress.getShipAddress());
            ps.setInt(4, userAddress.getShipCityId());
            ps.setString(5, userAddress.getPhoneNum());        
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
          
}
