/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Account;
import entity.Information;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends BaseDAO<Account> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    public Account login(String user, String pass) {
        String query = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void signUp(String user, String pass) {
        String query = "INSERT INTO Users VALUES (?, ?, 0, 0);";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Users";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deleteAccount(String id) {
        String query = "delete from Cart where UserID = ?;\n"
                + "delete from Cart where ProductID IN (select ProductID from Product where SellerID = ?);\n"
                + "delete from Product where SellerID = ?;\n"
                + "delete from Users where UserID = ?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, id);
            ps.setString(3, id);
            ps.setString(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return;
    }

    public void editAccount(String id, String user, String pass, String isSell, String isAdmin) {
        String query = "UPDATE Users\n"
                + "SET Username = ?,\n"
                + "Password = ?,\n"
                + "isSeller = ?,\n"
                + "isAdmin = ?\n"
                + "WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, isSell);
            ps.setString(4, isAdmin);
            ps.setString(5, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public Account getAccountByID(String id) {
        String query = "select * from Users where UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public int countAllAccount() {
        String query = "select count(*) from Users";
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

    public static void main(String[] args) {
        UserDAO UserDAO = new UserDAO();
        //UserDAO.signUp("dinhthanhhoang", "dinhthanhhoang");

//        List<Account> list = UserDAO.getAllAccounts();
//        for (Account account : list) {
//            System.out.println(account);
//        }
        //UserDAO.deleteAccount("6");
        //UserDAO.editAccount("8", "hi", "123", "1", "0");
        //UserDAO.deleteAccount("4");
//        Account x = UserDAO.getAccountByID("1");
//        System.out.println(x);
        
//        UserDAO.editAccount("9", "alo", "loa", "1", "1");

        System.out.println(UserDAO.countAllAccount());
    }
}
