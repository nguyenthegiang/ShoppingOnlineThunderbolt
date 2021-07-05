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
     *
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
     *
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
     * Get a list of feedback by user id and product id
     * @param userId the id of the user
     * @param productId the id of the product
     * @return  list of feedback with the user id and product id
     */
   
    public List<Feedback> getFeedbacksByUserIdAndProductId(int userId, int productId) {
        String query = "SELECT * FROM Feedback WHERE UserID = ?"
                + " AND ProductID = ?";
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

    /**
     * Get a feedback by order id
     * @param orderId the id of the order
     * @return a list of feedback with the order id
     */
    public List<Feedback> getFeedbacksByOrderId(int orderId) {
        String query = "SELECT * FROM Feedback WHERE OrderID = ? ";              
        try {
            List<Feedback> lsFeedback = new ArrayList<>();
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            
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

    public boolean addFeedback(Feedback theFeedback) {
        String query = "INSERT INTO Feedback VALUES (?, ?, ?, ?, ?);";
        int check = 0;
        try {
            ps = connection.prepareStatement(query);
            //Set data to the "?"
            ps.setInt(1, theFeedback.getUserId());
            ps.setInt(2, theFeedback.getProductId());
            ps.setInt(3, theFeedback.getOrderId());
            ps.setInt(4, theFeedback.getStar());
            ps.setString(5, theFeedback.getFeedbackDetail());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            check = -1;
        }
        return check > 0;
    }

}
