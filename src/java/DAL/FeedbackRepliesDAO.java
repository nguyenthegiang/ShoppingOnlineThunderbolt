/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.FeedbackReplies;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TRANTATDAT
 */
public class FeedbackRepliesDAO extends BaseDAO<FeedbackReplies>{
    
    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về
    
    /**
     * Get a list of replies by feedback id
     * @param feedbackId the id of the feedback
     * @return the list of feedback replies
     */
    public List<FeedbackReplies> getFeedbacksRepliesByFeedbackId(int feedbackId) {
        String query = "SELECT * FROM Feedback_Replies WHERE FeedbackID = ?";
        try {
            List<FeedbackReplies> lsFeedbackReplies = new ArrayList<>();
            ps = connection.prepareStatement(query);
            ps.setInt(1, feedbackId);
            rs = ps.executeQuery();
            while (rs.next()) {
                FeedbackReplies fr = new FeedbackReplies(
                        rs.getInt("ID"),
                        rs.getInt("FeedbackID"),
                        rs.getInt("UserID"),                       
                        rs.getString("RepliesText")
                );
                lsFeedbackReplies.add(fr);                
            }
            return lsFeedbackReplies;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Get a list of replies by user id
     * @param userId the id of the user
     * @return the list of feedback replies
     */
    public List<FeedbackReplies> getFeedbacksRepliesByUserId(int userId) {
        String query = "SELECT * FROM Feedback_Replies WHERE UserID = ?";
        try {
            List<FeedbackReplies> lsFeedbackReplies = new ArrayList<>();
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                FeedbackReplies fr = new FeedbackReplies(
                        rs.getInt("ID"),
                        rs.getInt("FeedbackID"),
                        rs.getInt("UserID"),                       
                        rs.getString("RepliesText")
                );
                lsFeedbackReplies.add(fr);                
            }
            return lsFeedbackReplies;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Add a replies to the database
     * @param theReplies the replies to add to database
     * @return true if add successful, else false
     */
    public boolean addReplies(FeedbackReplies theReplies) {
        String query = "INSERT INTO Feedback_Replies VALUES (?, ?, ?);";
        int check = 0;
        try {
            ps = connection.prepareStatement(query);
            //Set data to the "?"
            ps.setInt(1, theReplies.getFeedbackId());
            ps.setInt(2, theReplies.getUserId());
            ps.setString(3, theReplies.getRepliesText());
            
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            check = -1;
        }
        return check > 0;
    }
    
}
