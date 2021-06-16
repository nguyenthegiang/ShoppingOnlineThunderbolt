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
    
}
