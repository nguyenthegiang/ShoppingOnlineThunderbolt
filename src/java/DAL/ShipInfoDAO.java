/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.ShipInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TRANTATDAT
 */
public class ShipInfoDAO extends BaseDAO<ShipInfo>{
    
    // TO DO:  get by order ID, get by city ID, add 
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    /**
     * get shiping info by order id
     * @param orderId
     * @return a shipping info
     */
    public ShipInfo getShipInfoByOrderId(int orderId) {
        String query = "SELECT * FROM ShipInfo WHERE OrderID = ?";
        ShipInfo theShipInfo = new ShipInfo();
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                theShipInfo = new ShipInfo(
                        rs.getInt("ID"),
                        rs.getInt("OrderID"),
                        rs.getString("CustomerName"),
                        rs.getString("ShippingAddress"),
                        rs.getInt("ShipCityID"),
                        rs.getString("PhoneNum"),
                        rs.getString("Note")
                );
            }
            ps.close();
            return theShipInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * get a list of shiping info by city id
     * @param cityId
     * @return a list of shiping info
     */
    public List<ShipInfo> getShipInfoByCityId(int cityId) {
        String query = "SELECT * FROM ShipInfo WHERE CityID = ?";
        List<ShipInfo> lsShipInfo = new ArrayList<>();
        ShipInfo theShipInfo = new ShipInfo();
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, cityId);
            rs = ps.executeQuery();
            while (rs.next()) {
                theShipInfo = new ShipInfo(
                        rs.getInt("ID"),
                        rs.getInt("OrderID"),
                        rs.getString("CustomerName"),
                        rs.getString("ShippingAddress"),
                        rs.getInt("ShipCityID"),
                        rs.getString("PhoneNum"),
                        rs.getString("Note")
                );
                lsShipInfo.add(theShipInfo);
            }
            ps.close();
            return lsShipInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * add a ship info to database
     * @param ShipInfo to add
     * 
     */
    public void addShipInfo(ShipInfo shipInfo) {
        String query = "INSERT INTO ShipInfo VALUES (?, ?, ?, ?, ?, ?);";
        try {
            ps = connection.prepareStatement(query);
            //Set dữ liệu vào dấu ?
            ps.setInt(1, shipInfo.getOrderId());
            ps.setString(2, shipInfo.getCustomerName());
            ps.setString(3, shipInfo.getShippingAddress());
            ps.setInt(4, shipInfo.getShipCityId());
            ps.setString(5, shipInfo.getPhoneNum());
            ps.setString(6, shipInfo.getNote());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
