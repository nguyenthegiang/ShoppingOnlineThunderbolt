/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Category;
import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO extends BaseDAO<Category> {

    PreparedStatement ps = null; 
    ResultSet rs = null; 

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>(); 
        String query = "SELECT * FROM Category"; 
        try {
            ps = connection.prepareStatement(query); 
            rs = ps.executeQuery(); 
            while (rs.next()) {
                list.add(new Category(rs.getInt("CategoryID"), rs.getString("CategoryName"), rs.getString("icon")));
            }
        } catch (Exception e) {
        }

        return list;
    }

    /**
     * Select a specified category by its ID, used for categorizing products in homepage
     * @param id
     * @return 
     */
    public String getCateNameByID(int id) {
        String query = "SELECT CategoryName FROM Category\n"
                + "WHERE CategoryID = ?"; 
        try {
            ps = connection.prepareStatement(query); 
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
        
        /*---------Test Case for getAllCategory() method---------*/
//        List<Category> list = dao.getAllCategory();
//        for (Category category : list) {
//            System.out.println(category);
//        }
        
        /*---------Test Case for getAllCategory() method---------*/
        System.out.println(dao.getCateNameByID(1));        
    }
}
