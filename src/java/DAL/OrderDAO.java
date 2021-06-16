/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Order;
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
     
    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        try {
            ps = connection.prepareStatement(query);//ném query sang bên SQL server
            rs = ps.executeQuery();//Chạy câu lệnh query, nhận kết quả trả về

            //Giờ đây, câu lệnh đã đc chạy, rs là bảng Result -> Giờ phải lấy dữ liệu từ bảng rs và cho vào List
            while (rs.next()) {
                list.add(new Order(rs.getInt("ID"), rs.getInt("UserId"), rs.getFloat("TotalPrice"), 
                        rs.getString("Note"), rs.getInt("Status")));
            }
        } catch (Exception e) {
        }

        return list;
    }
    
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
    
    public Order getOrderByID(String id) { //Phải để kiểu int vì khi lưu lên Session thì nó vẫn là kiểu int
        String query = "SELECT * FROM Orders WHERE ID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new Order(rs.getInt("ID"), rs.getInt("UserId"), rs.getFloat("TotalPrice"), 
                        rs.getString("Note"), rs.getInt("Status")));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    
}
