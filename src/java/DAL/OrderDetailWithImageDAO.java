/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Order;
import entity.OrderDetailWithImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thuan
 */
public class OrderDetailWithImageDAO extends BaseDAO<OrderDetailWithImage> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    public List<OrderDetailWithImage> getOrderDetail(int orderId) {
        List<OrderDetailWithImage> list = new ArrayList<>();
        String query = "SELECT od.id, od.order_id, od.productId, od.productName, od.productPrice, p.imageLink\n"
                + "FROM Order_Detail od INNER JOIN Product p\n"
                + "ON od.ProductID = p.ProductID\n"
                + "WHERE od.Order_ID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new OrderDetailWithImage(
                        rs.getString("imageLink"),
                        rs.getInt("id"),
                        rs.getInt("order_Id"),
                        rs.getInt("ProductId"),
                        rs.getString("productName"),
                        rs.getInt("productPrice")
                ));
            }
        } catch (Exception e) {
        }

        return list;
    }

}
