/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
     *
     * @return list order
     */
    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        String query = "SELECT o.ID,o.USERID,o.TotalPrice, o.Note, os.Name, o.Daybuy\n"
                + "FROM Orders o INNER JOIN Order_Status os\n"
                + "ON o.Status = os.ID";
        try {
            ps = connection.prepareStatement(query);//Throw the query to the SQL server 
            rs = ps.executeQuery();//Run the query, get the results returned

            //Now, the command has been run, rs is the Result version -> Now have to get the data from the rs table and put it in the List
            while (rs.next()) {
                list.add(new Order(rs.getInt("ID"), rs.getInt("UserId"), rs.getFloat("TotalPrice"),
                        rs.getString("Note"), rs.getString("Name"), rs.getString("Daybuy")));
            }
        } catch (Exception e) {
        }

        return list;
    }

    /**
     * adding a new order to database
     *
     * @param userID
     * @param totalPrice
     * @param note
     * @param status
     * @param date
     */
    public void add(int userID, double totalPrice, String note, int status, String date) {
        String query = "INSERT INTO Orders VALUES (?, ?, ?, ?,?);";
        try {

            ps = connection.prepareStatement(query);
            //Set data to the ?
            ps.setInt(1, userID);
            ps.setDouble(2, totalPrice);
            ps.setString(3, note);
            ps.setInt(4, status);
            ps.setString(5, date);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    /**
     * adding a new order to database, return the order Id
     *
     * @param order order to add
     * @param statusId status id of the order to add
     * @return id of the order to add
     */
    public int addOrder(Order order, int statusId) {

        String query = "INSERT INTO Orders VALUES(?, ?, ?, ?, ?)";
        int check = 0;

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, order.getUserId());
            ps.setObject(2, order.getTotalPrice());
            ps.setObject(3, order.getNote());
            ps.setObject(4, statusId);
            ps.setObject(5, order.getDate());
            check = ps.executeUpdate();
            if (check > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return 0;
    }

    /**
     * change the status of an specific order by the order id
     *
     * @param id: id of the order to be changed
     */
    public void packaging(int id) {
        String query = "UPDATE Orders\n"
                + "                SET Status = 2\n"
                + "                WHERE ID = ?";
        try {
            ps = connection.prepareStatement(query);
            //Set data to the "?"
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void delivering(int id) {
        String query = "UPDATE Orders\n"
                + "SET Status = 3\n"
                + "WHERE ID = ?";
        try {
            ps = connection.prepareStatement(query);
            //Set data to the ?
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void canceled(int id) {
        String query = "UPDATE Orders\n"
                + "SET Status = 4\n"
                + "WHERE ID = ?";
        try {
            ps = connection.prepareStatement(query);
            //Set data to the ?
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void finished(int id) {
        String query = "UPDATE Orders\n"
                + "SET Status = 5\n"
                + "WHERE ID = ?";
        try {
            ps = connection.prepareStatement(query);
            //Set data to the ?
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    /**
     * count all the number of orders in database
     *
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
     * Get status of an order with its ID
     * @param orderId
     * @return int status
     */
    public int getStatusOfAnOrder(int orderId) {
        String query = "SELECT status FROM Orders WHERE ID=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
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
     *
     * @param id: the id of the order
     * @return Order
     */
    public Order getOrderByOrderID(int id) {
        String query = "SELECT o.ID,o.UserId,o.TotalPrice, o.Note, o.DayBuy, os.Name \n"
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
                        rs.getString("Name"),
                        rs.getString("DayBuy"),
                        rs.getDate("DayBuy")
                ));
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * get the newest order id
     * @return the newest order id
     */
    public int getNewestOrderID() {
        String query = "SELECT TOP(1) o.id,o.userId,o.totalPrice, o.note, os.name, o.daybuy \n"
                + "FROM Orders o INNER JOIN Order_Status os\n"
                + "ON o.Status = os.ID\n"
                + "ORDER BY o.id DESC";
        try {
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {
                Order a = new Order(
                        rs.getInt("ID"),
                        rs.getInt("UserId"),
                        rs.getFloat("TotalPrice"),
                        rs.getString("Note"),
                        rs.getString("name"),
                        rs.getString("daybuy")
                );
                return a.getId();
            }
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * get list of orders of a specific user
     *
     * @param userId: id of the user
     * @return List Order
     */
    public List<Order> getOrderByUserID(int userId) {
        List<Order> list = new ArrayList<>();
        String query = "SELECT o.ID, o.UserID, "
                + "o.TotalPrice, o.Note, os.Name, "
                + "o.DayBuy "
                + "FROM Orders o  INNER JOIN Order_Status os\n"
                + " ON o.Status = os.ID\n"
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
                        rs.getString("Name"),
                        rs.getString("Daybuy")
                ));
            }
        } catch (Exception e) {
        }

        return list;
    }

//    public List<Order> getOrderBySellerID(int sellerId) {
//        List<Order> list = new ArrayList<>();
//        String query = "SELECT o.ID, o.UserID, "
//                + "o.TotalPrice, o.Note, os.Name, "
//                + "o.DayBuy "
//                + "FROM Orders o  INNER JOIN Order_Status os\n"
//                + " ON o.Status = os.ID\n"
//                + "WHERE o.UserId = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setInt(1, sellerId);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                list.add(new Order(
//                        rs.getInt("ID"),
//                        rs.getInt("UserId"),
//                        rs.getFloat("TotalPrice"),
//                        rs.getString("Note"),
//                        rs.getString("Name"),
//                        rs.getString("Daybuy")
//                ));
//            }
//        } catch (Exception e) {
//        }
//
//        return list;
//    }
    /**
     * delete a specific order by the order id
     *
     * @param id: id of the order
     */
    public void delete(int id) {
        String query = "DELETE FROM Order_detail WHERE Order_ID = ?\n"
                + "DELETE FROM Orders WHERE ID = ?";
        try {
            ps = connection.prepareStatement(query);
            //Put the id inside the first "?"
            ps.setInt(1, id);
            ps.setInt(2, id);
            //Execute: No Result table -> No RS, only executeUpdate execute
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Order> getRecentOrder() {
        List<Order> list = new ArrayList<>();
        String query = "declare @format varchar(100) = 'yyyy/MM/dd'\n"
                + "SELECT COUNT(*) as Amount, format(DayBuy,@format) as OrderDate\n"
                + "FROM Orders\n"
                + "WHERE DayBuy >= GETDATE()-30\n"
                + "GROUP BY format(DayBuy,@format)\n"
                + "ORDER BY format(DayBuy,@format)";
        try {
            ps = connection.prepareStatement(query);//Throw the query to the SQL server 
            rs = ps.executeQuery();//Run the query, get the results returned

            //Now, the command has been run, rs is the Result version -> Now have to get the data from the rs table and put it in the List
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("Amount"));
                o.setDate(rs.getString("OrderDate"));
                list.add(o);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> list = orderDAO.getRecentOrder();
        for (Order order : list) {
            System.out.println(order);
        }
    }

}
