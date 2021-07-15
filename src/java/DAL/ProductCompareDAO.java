/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Product;
import entity.ProductCompare;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Thuan
 */
public class ProductCompareDAO extends BaseDAO<ProductCompare> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Get the results returned
    
    
    public ProductCompare getProductByID(String id) { //Must be int type because when saving to Session, it is still int
        String query = "SELECT * FROM Product WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new ProductCompare(rs.getInt("ProductID"),
                        rs.getString("ProductName"), 
                        rs.getString("Description"),
                        rs.getInt("SellPrice"), 
                        rs.getString("imageLink"),
                        rs.getFloat("height"),
                        rs.getFloat("width"),
                        rs.getFloat("weight")
                ));
            }
        } catch (Exception e) {
        }
        return null;
    }

}
