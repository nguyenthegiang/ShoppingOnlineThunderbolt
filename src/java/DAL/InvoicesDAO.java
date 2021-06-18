/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Invoices;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thuan
 */
public class InvoicesDAO extends BaseDAO<Invoices> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    public List<Invoices> getAllInvoices() {
        List<Invoices> list = new ArrayList<>();
        String query = "SELECT users.Username,UserAddress.ShipAddress\n"
                + ",product.ProductName,product.imageLink, product.SellPrice,UserAddress.PhoneNum \n"
                + "                FROM users  INNER JOIN UserAddress \n"
                + "                ON users.UserID = UserAddress.UserID\n"
                + "                INNER JOIN Orders \n"
                + "                ON Orders.UserID = Users.UserID\n"
                + "                INNER JOIN Order_Detail\n"
                + "                ON Order_Detail.Order_ID = Orders.ID\n"
                + "                INNER JOIN Product\n"
                + "                ON Order_Detail.ProductID = Product.ProductID ";
        try {
            ps = connection.prepareStatement(query);//Throw the query to the SQL server 
            rs = ps.executeQuery();//Run the query, get the results returned

            //Now, the command has been run, rs is the Result version -> Now have to get the data from the rs table and put it in the List
            while (rs.next()) {
                list.add(new Invoices(
                        rs.getString("username"),
                        rs.getString("ShipAddress"),
                        rs.getString("ProductName"),
                        rs.getString("imageLink"), 
                        rs.getInt("SellPrice"),
                        rs.getString("PhoneNum")
                ));
            }
        } catch (Exception e) {
        }

        return list;
    }
}
