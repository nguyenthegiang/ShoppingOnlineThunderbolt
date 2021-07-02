/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.OrderDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thuan
 */
public class OrderDetailDAO extends BaseDAO<OrderDetail> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    /**
     * Adding many order_detail to a specific order by the order ID
     *
     * @param oderDetail: list of order details need to be inserted
     * @param Order_ID: the id of the order which would be inserted in
     * @throws Exception
     */
    public void addManyOrderDetails(int Order_ID, List<OrderDetail> oderDetail)
            throws Exception {
        OrderDetailDAO oddDao = new OrderDetailDAO();

        // my SQL INSERT statement
        String query = " INSERT INTO Order_Detail (Order_id, ProductID,"
                + " ProductName, ProductPrice)"
                + " values (?, ?, ?, ?)";

        // declare the preparedstatement reference
        try {
            // create the preparedstatement before the loop
            ps = connection.prepareStatement(query);

            // now loop through nearly 1,500 nodes in the list
            for (OrderDetail n : oderDetail) {
                ps.setInt(1, Order_ID);
                ps.setInt(2, n.getProductID());
                ps.setString(3, n.getProductName());
                ps.setInt(4, n.getProductPrice());
                ps.setInt(5, n.getQuantity());

                ps.execute();           // the INSERT happens here
                
                oddDao.deleteAmount(n.getProductID(), n.getQuantity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Getting products in an order by the order id
     *
     * @param id: ID of the order
     * @return OrderDetail
     */
    public List<OrderDetail> getOrderDetailByOrderID(int id) {
        List<OrderDetail> list = new ArrayList<>();
        String query = "SELECT * FROM Order_Detail"
                + " WHERE Order_ID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetail(
                        rs.getInt("ID"),
                        rs.getInt("Order_ID"),
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("ProductPrice"),
                        rs.getInt("Quantity")
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public void deleteAmount(int productId, int quantity) {
        String query = "UPDATE Product\n"
                + "SET Amount = Amount - ?\n"
                + "WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
         
}
