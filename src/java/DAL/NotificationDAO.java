 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import control.NotiRead;
import entity.Notification;
import entity.OrderDetail;
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
     * Adding a notification to a particular user by their ID that their order
     * has been approved
     *
     * @param userID: the user's ID
     * @param orderId: the order which related to the notification
     */
    public void approveOrderAdminNoti(int userID, int orderId) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime now = LocalDateTime.now();
        String query = "INSERT INTO Notifications VALUES (?, ?, ?, ?, ?);";
        try {

            ps = connection.prepareStatement(query);
            //Set data to the ?
            ps.setInt(1, userID);
            ps.setInt(2, orderId);
            ps.setString(3, "Your order with ID " + orderId + " has been approved, now being packaged"
                    + " and soon will be delivered..!");
            ps.setString(4, "unread");
            ps.setString(5, dtf.format(now) + " at " + dtf1.format(now));
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    /**
     * send a notification to admin only, that the customer with userId has received
     * their order successfully
     * @param userID: id of the customer
     * @param orderId: id of the order of the customer
     */
     public void finishOrderUserNoti(int userID, int orderId) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime now = LocalDateTime.now();
        String query = "INSERT INTO Notifications VALUES (?, ?, ?, ?, ?);";
        try {

            ps = connection.prepareStatement(query);
            //Set data to the ?
            ps.setInt(1, 1);
            ps.setInt(2, orderId);
            ps.setString(3, "Your customer with ID: "+ userID +" has successfully "
                    + "received the order with ID: "
                    + orderId + ". Check it out!");
            ps.setString(4, "unread");
            ps.setString(5, dtf.format(now) + " at " + dtf1.format(now));
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
     /**
      * send a notification to the customer with specified id that their 
      * order has been packaged and now being delivered
      * @param userID: customer id
      * @param orderId: their order id
      */
    public void deliverOrderAdminNoti(int userID, int orderId) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime now = LocalDateTime.now();
        String query = "INSERT INTO Notifications VALUES (?, ?, ?, ?, ?);";
        try {

            ps = connection.prepareStatement(query);
            //Set data to the ?
            ps.setInt(1, userID);
            ps.setInt(2, orderId);
            ps.setString(3, "Your order with ID " + orderId + " has been packaged, now being delivered"
                    + " please wait..!");
            ps.setString(4, "unread");
            ps.setString(5, dtf.format(now) + " at " + dtf1.format(now));
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    /**
     * Inform to the seller and admin that the order has been paid successfully!
     * @param userId: id of the buyer
     * @param orderId
     * @param adminSellerId: id of admin and the seller of the product
     * @throws Exception 
     */
    public void userReceivedOrderNoti(int userId, int orderId, List<Integer> adminSellerId)
            throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime now = LocalDateTime.now();
        // my SQL INSERT statement
        String query = " INSERT INTO Notifications "
                + " VALUES (?, ?, ?, ?, ?)";

        // declare the preparedstatement reference
        try {
            // create the preparedstatement before the loop
            ps = connection.prepareStatement(query);

            // now loop through nearly 1,500 nodes in the list
            for (Integer n : adminSellerId) {
                ps.setInt(1, n);
                ps.setInt(2, orderId);
                ps.setString(3, "The customer with ID " + userId + " has received"
                        + "their products. Transaction complete! ");
                ps.setString(4, "unread");
                ps.setString(5, dtf.format(now) + " at " + dtf1.format(now));

                ps.execute();           // the INSERT happens here
            }
        } catch (Exception se) {
        }
    }
    
    public void userBuyNoti(int userId, int orderId, List<Integer> sellerId)
            throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime now = LocalDateTime.now();
        // my SQL INSERT statement
        String query = " INSERT INTO Notifications "
                + " VALUES (?, ?, ?, ?, ?)";

        // declare the preparedstatement reference
        try {
            // create the preparedstatement before the loop
            ps = connection.prepareStatement(query);

            // now loop through nearly 1,500 nodes in the list
            for (Integer n : sellerId) {
                ps.setInt(1, n);
                ps.setInt(2, orderId);
                ps.setString(3, "The customer with ID " + userId + " has placed an order"
                        + " with OrderID: "+ orderId
                        + ". Waiting for admin to approve! Check our your completed order! ");
                ps.setString(4, "unread");
                ps.setString(5, dtf.format(now) + " at " + dtf1.format(now));

                ps.execute();           // the INSERT happens here
            }
        } catch (Exception se) {
        }
    }
    
    
    public void userBuyNotiAdmin(int userId, int orderId)
            throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime now = LocalDateTime.now();
        // my SQL INSERT statement
        String query = " INSERT INTO Notifications "
                + " VALUES (?, ?, ?, ?, ?)";

        // declare the preparedstatement reference
        try {
            // create the preparedstatement before the loop
            ps = connection.prepareStatement(query);

            // now loop through nearly 1,500 nodes in the list
                ps.setInt(1, 1);
                ps.setInt(2, orderId);
                ps.setString(3, "The customer with ID " + userId + " has placed an order"
                        + " with OrderID: "+ orderId
                        + ". Check it out! ");
                ps.setString(4, "unread");
                ps.setString(5, dtf.format(now) + " at " + dtf1.format(now));

                ps.execute();           // the INSERT happens here
            
        } catch (Exception se) {
        }
    }
    

    /**
     * Adding a notification to user by admin that their order has been canceled
     *
     * @param userID: the user's ID
     * @param orderId: the order which related to the notification
     */
    public void cancelOrderNoti(int userID, int orderId) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String query = "INSERT INTO Notifications VALUES (?, ?, ?, ?, ?);";
        try {

            ps = connection.prepareStatement(query);
            //Set data to the ?
            ps.setInt(1, userID);
            ps.setInt(2, orderId);
            ps.setString(3, "Your order with ID " + orderId + " has been canceled, "
                    + "We are very sorry for this unconvinience!");
            ps.setString(4, "unread");
            ps.setString(5, dtf.format(now) + " at " + dtf1.format(now));
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    /**
     * Get 5 latest notifications of the user
     *
     * @param userId: id of the user
     * @return list notifications
     */
    public List<Notification> getTop5NotificationsByUserID(int userId) {
        List<Notification> list = new ArrayList<>();
        String query = " SELECT TOP 5 * FROM Notifications "
                + "WHERE userID=? ORDER BY id DESC";
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
     *
     * @param userId: id of the user
     * @return list notifications
     */
    public List<Notification> getAllNotificationsByUserID(int userId) {
        List<Notification> list = new ArrayList<>();
        String query = " SELECT * FROM Notifications "
                + "WHERE userID=? ORDER BY id DESC";
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
     *
     * @param userId: id of the user
     * @return list notifications
     */
    public List<Notification> getUnreadNotificationsByUserID(int userId) {
        List<Notification> list = new ArrayList<>();
        String query = "SELECT * FROM Notifications WHERE userID=? and "
                + "Status ='unread'"
                + "ORDER BY id DESC";
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
     *
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
    
    public void readOneNoti(int userId,int orderId) {
        String query = "UPDATE Notifications\n"
                + "SET Status = 'read'\n"
                + "WHERE userID = ? AND orderID=?";
        try {
            ps = connection.prepareStatement(query);
            //Set data to the ?
            ps.setInt(1, userId);
            ps.setInt(2, orderId);
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
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    
    public void adminApproveNotiSeller(int userId, int orderId, List<Integer> sellerId)
            throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime now = LocalDateTime.now();
        // my SQL INSERT statement
        String query = " INSERT INTO Notifications "
                + " VALUES (?, ?, ?, ?, ?)";

        // declare the preparedstatement reference
        try {
            // create the preparedstatement before the loop
            ps = connection.prepareStatement(query);

            // now loop through nearly 1,500 nodes in the list
            for (Integer n : sellerId) {
                ps.setInt(1, n);
                ps.setInt(2, orderId);
                ps.setString(3, "Admin has approved your product(s) from buyer with ID  " 
                        + userId 
                        + ", and the OrderID: "+ orderId
                        + ". Check it out! ");
                ps.setString(4, "unread");
                ps.setString(5, dtf.format(now) + " at " + dtf1.format(now));

                ps.execute();           // the INSERT happens here
            }
        } catch (Exception se) {
        }
    }
    
    
    public void userReceivedNotiSeller(int userId, int orderId, List<Integer> sellerId)
            throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime now = LocalDateTime.now();
        // my SQL INSERT statement
        String query = " INSERT INTO Notifications "
                + " VALUES (?, ?, ?, ?, ?)";

        // declare the preparedstatement reference
        try {
            // create the preparedstatement before the loop
            ps = connection.prepareStatement(query);

            // now loop through nearly 1,500 nodes in the list
            for (Integer n : sellerId) {
                ps.setInt(1, n);
                ps.setInt(2, orderId);
                ps.setString(3, "Your order from Order from buyer with ID  " 
                        + userId 
                        + ", and the OrderID: "+ orderId
                        + " has been shipped successfully! Check it out! ");
                ps.setString(4, "unread");
                ps.setString(5, dtf.format(now) + " at " + dtf1.format(now));

                ps.execute();           // the INSERT happens here
            }
        } catch (Exception se) {
        }
    }
}

