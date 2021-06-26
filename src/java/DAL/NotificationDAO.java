/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Notification;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thuan
 */
public class NotificationDAO extends BaseDAO<Notification> {
    
    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    /**
     * Adding a notification to a particular user by their ID
     *
     * @param userID: the user's ID
     * @param orderId: the order which related to the notification
     * @param content: content of the notification
     * @param status: status of the notification (read or unread)
     */
    public void addNotification(int userID, int orderId, String content,
            String status) {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String query = "INSERT INTO Notifications VALUES (?, ?, ?, ?, ?);";
        try {
            
            ps = connection.prepareStatement(query);
            //Set data to the ?
            ps.setInt(1, userID);
            ps.setInt(2, orderId);
            ps.setString(3, content);
            ps.setString(4, status);
            ps.setString(5, dtf.format(now));
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    /**
     * Get all the notifications of the user
     * @param userId: id of the user
     * @return list notifications
     */
    public List<Notification> getNotificationsByUserID(int userId) {
        List<Notification> list = new ArrayList<>();
        String query = "SELECT * FROM Notifications WHERE userID=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Notification(
                        rs.getInt("ID"),
                        rs.getInt("UserId"),
                        rs.getInt("OrderID"),
                        rs.getString("Content"),
                        rs.getString("Status"),
                        rs.getString("Time")
                ));
            }
        } catch (Exception e) {
        }

        return list;
    }
    
    
    /**
     * Get all the notifications of the user
     * @param userId: id of the user
     * @return list notifications
     */
    public List<Notification> getUnreadNotificationsByUserID(int userId) {
        List<Notification> list = new ArrayList<>();
        String query = "SELECT * FROM Notifications WHERE userID=? and "
                + "Status ='unread'";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Notification(
                        rs.getInt("ID"),
                        rs.getInt("UserId"),
                        rs.getInt("OrderID"),
                        rs.getString("Content"),
                        rs.getString("Status"),
                        rs.getString("Time")
                ));
            }
        } catch (Exception e) {
        }

        return list;
    }
    
    /**
     * change the status of the notification to read
     * @param userId 
     */
    public void read(int userId) {
        String query = "UPDATE Notifications\n"
                + "SET Status = 'read'\n"
                + "WHERE userID = ?";
        try {
            ps = connection.prepareStatement(query);
            //Set data to the ?
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    

    /**
     * count all the number of unread notifications in database of an user
     *
     * @return an integer number
     */
    public int countUnreadNotifications(int userId) {
        String query = "SELECT COUNT(*) FROM Notifications WHERE userId=? "
                + "and Status = 'unread'";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    
    /**
     * count all the number of unread notifications in database of an user
     *
     * @return an integer number
     */
    public int countNotifications(int userId) {
        String query = "SELECT COUNT(*) FROM Notifications WHERE userId=? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(5, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
}
