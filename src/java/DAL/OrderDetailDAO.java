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
public class OrderDetailDAO extends BaseDAO<OrderDetail> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    /**
     * Adding many oder_detail to a specific order by the order ID
     *
     * @param oderDetail: list of order details need to be inserted
     * @param Order_ID: the id of the order which would be inserted in
     * @throws Exception
     */
    public void addManyOrderDetails(int Order_ID, List<OrderDetail> oderDetail)
            throws Exception {

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

                ps.execute();           // the INSERT happens here
            }
        } catch (Exception se) {
        }
    }

    /**
     * getting detailed orders by the order id
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
                        rs.getInt("ProductPrice")
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    
    

}
