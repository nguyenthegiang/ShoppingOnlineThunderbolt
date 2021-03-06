/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Order;
import entity.OrderDetailAdmin;
import entity.OrderDetailWithImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thuan
 */
public class InvoicesDAO extends BaseDAO<OrderDetailAdmin> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    public List<OrderDetailAdmin> getInvoiceDetailByOrderID(int orderId) {
        List<OrderDetailAdmin> list = new ArrayList<>();
        String query = "SELECT od.id,u.UserId, od.Order_ID, u.username,od.productId,\n"
                + "p.productName,p.imageLink,p.SellPrice,od.quantity,"
                + " si.shippingAddress, si.phoneNum\n"
                + "FROM Order_Detail od INNER JOIN Orders o\n"
                + "ON od.Order_ID = o.ID\n"
                + "INNER JOIN Users u\n"
                + "ON o.UserID = u.UserID\n"
                + "INNER JOIN Product p\n"
                + "ON od.ProductID = p.ProductID\n"
                + "INNER JOIN ShipInfo si\n"
                + "ON o.ID = si.Order_ID\n"
                + "WHERE   od.Order_ID = ?\n"
                + "ORDER BY od.ID DESC";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new OrderDetailAdmin(
                        rs.getInt("ID"),
                        rs.getInt("userId"),
                        rs.getInt("Order_ID"),
                        rs.getString("username"),
                        rs.getInt("productID"),
                        rs.getString("productName"),
                        rs.getString("imageLink"),
                        rs.getInt("SellPrice"),
                        rs.getString("ShippingAddress"),
                        rs.getString("PhoneNum"),
                        rs.getInt("quantity")
                ));
            }
        } catch (Exception e) {
        }

        return list;
    }

    public List<OrderDetailAdmin> getInvoiceDetailBySellerID(int sellerId) {
        List<OrderDetailAdmin> list = new ArrayList<>();
        String query = "SELECT od.id,u.userId, od.Order_ID, u.username,od.productId,\n"
                + "                                p.productName,p.imageLink,"
                + "                                p.SellPrice, si.shippingAddress,"
                + "                                si.phoneNum, od.quantity\n"
                + "                                FROM Order_Detail od INNER JOIN Orders o\n"
                + "                                ON od.Order_ID = o.ID\n"
                + "                                INNER JOIN Users u\n"
                + "                                ON o.UserID = u.UserID\n"
                + "                                INNER JOIN Product p\n"
                + "                                ON od.ProductID = p.ProductID\n"
                + "                                INNER JOIN ShipInfo si\n"
                + "                                ON o.ID = si.Order_ID\n"
                + "                                WHERE   p.SellerID = ? and o.status <> 1 and o.Status<>4\n"
                + "                                ORDER BY od.ID DESC";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, sellerId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new OrderDetailAdmin(
                        rs.getInt("ID"),
                        rs.getInt("userID"),
                        rs.getInt("Order_ID"),
                        rs.getString("username"),
                        rs.getInt("productID"),
                        rs.getString("productName"),
                        rs.getString("imageLink"),
                        rs.getInt("SellPrice"),
                        rs.getString("ShippingAddress"),
                        rs.getString("PhoneNum"),
                        rs.getInt("quantity")
                ));
            }
        } catch (Exception e) {
        }

        return list;
    }

    public int countProductSoldOfSeller(int sellerId) {
        String query = "SELECT COUNT(*) FROM Order_Detail od INNER JOIN Orders o\n"
                + "                                ON od.Order_ID = o.ID\n"
                + "                                INNER JOIN Users u\n"
                + "                                ON o.UserID = u.UserID\n"
                + "                                INNER JOIN Product p\n"
                + "                                ON od.ProductID = p.ProductID\n"
                + "                                INNER JOIN ShipInfo si\n"
                + "                                ON o.ID = si.Order_ID\n"
                + "                                WHERE   p.SellerID = ? and o.status <> 1 and o.Status<>4";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, sellerId);

            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

}
