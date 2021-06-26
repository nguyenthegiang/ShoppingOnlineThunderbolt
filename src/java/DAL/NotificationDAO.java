/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Notification;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Thuan
 */
public class NotificationDAO extends BaseDAO<Notification> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    public void addNotification(int userID, int orderId, String content,
            String status, String time) {
        String query = "INSERT INTO Notifications VALUES (?, ?, ?, ?, ?);";
        try {

            ps = connection.prepareStatement(query);
            //Set data to the ?
            ps.setInt(1, userID);
            ps.setInt(2, orderId);
            ps.setString(3, content);
            ps.setString(4, status);
            ps.setString(5, time);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    
    /**
     * count all the number of unread notifications in database of an user
     *
     * @return an integer number
     */
    public int countNotifications(int userId) {
        String query = "SELECT COUNT(*) FROM Orders";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
}
