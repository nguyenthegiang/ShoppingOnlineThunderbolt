/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Feedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TRANTATDAT
 */
public class FeedbackDAO extends BaseDAO<Feedback> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    /**
     * Get a list of feedback by product id
     * @param productId the id of the product
     * @return a list of feedback
     */
    public List<Feedback> getFeedbacksByProductId(int productId) {
        String query = "SELECT * FROM Feedback WHERE ProductID = ?";
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            ps = connection.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getInt("ProductID"),
                        rs.getInt("OrderID"),
                        rs.getInt("Star"),
                        rs.getString("FeedbackDetail")
                );
                lsFeedback.add(f);                
            }
            return lsFeedback;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Get a list of feedback by user id
     * @param userId the id of the user
     * @return a list of feedback
     */
    public List<Feedback> getFeedbacksByUserId(int userId) {
        String query = "SELECT * FROM Feedback WHERE UserID = ?";
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getInt("ProductID"),
                        rs.getInt("OrderID"),
                        rs.getInt("Star"),
                        rs.getString("FeedbackDetail")
                );
                lsFeedback.add(f);                
            }
            return lsFeedback;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Get a list of feedback by user id
     * @param userId the id of the user
     * @return a list of feedback
     */
    public List<Feedback> getFeedbacksByUserIdAndProductId(int userId, int productId) {
        String query = "SELECT * FROM Feedback WHERE UserID = ? AND ProductID = ?";
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getInt("ProductID"),
                        rs.getInt("OrderID"),
                        rs.getInt("Star"),
                        rs.getString("FeedbackDetail")
                );
                lsFeedback.add(f);                
            }
            return lsFeedback;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
