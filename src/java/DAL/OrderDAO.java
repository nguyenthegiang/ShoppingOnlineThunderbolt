/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Order;
import entity.OrderDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thuan
 */
public class OrderDAO extends BaseDAO<Order> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    /**
     * get all the current orders in database
     * @return list order
     */
    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        String query = "SELECT o.ID,o.USERID,o.TotalPrice, o.Note, os.Name \n"
                + "FROM Orders o INNER JOIN Order_Status os\n"
                + "ON o.Status = os.ID";
        try {
            ps = connection.prepareStatement(query);//ném query sang bên SQL server
            rs = ps.executeQuery();//Chạy câu lệnh query, nhận kết quả trả về

            //Giờ đây, câu lệnh đã đc chạy, rs là bảng Result -> Giờ phải lấy dữ liệu từ bảng rs và cho vào List
            while (rs.next()) {
                list.add(new Order(rs.getInt("ID"), rs.getInt("UserId"), rs.getFloat("TotalPrice"),
                        rs.getString("Note"), rs.getString("Name")));
            }
        } catch (Exception e) {
        }

        return list;
    }

    
    /**
     * adding a new order to database
     * @param userID
     * @param totalPrice
     * @param note
     * @param status 
     */
    public void add(int userID, float totalPrice, String note, int status) {
        String query = "INSERT INTO Orders VALUES (?, ?, ?, ?);";
        try {
            ps = connection.prepareStatement(query);
            //Set dữ liệu vào dấu ?
            ps.setInt(1, userID);
            ps.setFloat(2, totalPrice);
            ps.setString(3, note);
            ps.setInt(4, status);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    
   /**
    * change the status of an specific order by the order id
    * @param id: id of the order to be changed
    * @param status: new status
    */
    public void updateStatus(int id, int status) {
        String query = "UPDATE Orders\n"
                + "SET Status = ?,\n"
                + "WHERE ID = ?";
        try {
            ps = connection.prepareStatement(query);
            //Set dữ liệu vào dấu ?
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    /**
     * count all the number of orders in database
     * @return an integer number
     */
    public int countOrders() {
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

    /**
     * get a particular order by its id
     * @param id: the id of the order
     * @return Order
     */
    public Order getOrderByOrderID(int id) { 
        String query = "SELECT o.id,o.userId,o.totalPrice, o.note, os.name \n"
                + "FROM Orders o INNER JOIN Order_Status os\n"
                + "ON o.Status = os.ID\n"
                + "WHERE o.ID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new Order(
                        rs.getInt("ID"),
                        rs.getInt("UserId"),
                        rs.getFloat("TotalPrice"),
                        rs.getString("Note"),
                        rs.getString("name")
                ));
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * get list of orders of a specific user
     * @param userId: id of the user
     * @return List Order
     */
    public List<Order> getOrderByUserID(int userId) {
        List<Order> list = new ArrayList<>();
        String query = "SELECT o.ID,o.USERID,o.TotalPrice, o.Note, os.Name \n"
                + "FROM Orders o INNER JOIN Order_Status os\n"
                + "ON o.Status = os.ID"
                + "WHERE o.UserId = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            
            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("ID"),
                        rs.getInt("UserId"),
                        rs.getFloat("TotalPrice"),
                        rs.getString("Note"),
                        rs.getString("Name")
                ));
            }
        } catch (Exception e) {
        }

        return list;
    }

    /**
     * adding many orders
     * @param orders (list of products in user's cart)
     * @throws Exception 
     */
    public void addOrderList(List<Order> orders)
            throws Exception {

        // my SQL INSERT statement
        String query = " INSERT INTO Orders (userId, totalPrice, "
                + "note, status)"
                + " VALUES (?, ?, ?, ?)";

        // declare the preparedstatement reference
        try {
            // create the preparedstatement before the loop
            ps = connection.prepareStatement(query);

            // now loop through nearly 1,500 nodes in the list
            for (Order n : orders) {
                ps.setInt(1, n.getUserId());
                ps.setFloat(2, n.getTotalPrice());
                ps.setString(3, n.getNote());
                ps.setString(4, n.getStatus());
                ps.execute();           // the INSERT happens here
            }
        } catch (Exception se) {
        } finally {
            // close the statement when all the INSERT's are finished
            ps.close();
        }
    }

    /**
     * getting detailed order by the order id
     * @param id: ID of the order
     * @return OrderDetail 
     */
    public OrderDetail getOrderDetailByOrderID(int id) {
        String query = "SELECT * FROM Order_Detail"
                + " WHERE Order_ID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new OrderDetail(rs.getInt("ID"), rs.getInt("Order_ID"), rs.getInt("ProductID"),
                        rs.getString("ProductName"), rs.getInt("ProductPrice")));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    
    /**
     * delete a specific order by the order id
     * @param id: id of the order
     */
    public void delete(int id) {
        String query = "DELETE FROM Order_detail WHERE Order_ID = ?\n"
                + "DELETE FROM Orders WHERE ID = ?";
        try {
            ps = connection.prepareStatement(query);
            //Đẩy id vào trong dấu ? thứ nhất
            ps.setInt(1, id);
            ps.setInt(2, id);
            //Execute: Ko có bảng Result -> Ko dùng RS, chỉ dùng executeUpdate
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    
}
