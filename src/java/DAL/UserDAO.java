/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Account;
import entity.Information;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends BaseDAO<Account> {

    
    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    /**
     * For login an account to the website
     * @param user: username
     * @param pass: password
     * @return the account information of the account logging in,
     * null if username or password does not exist
     */
    public Account login(String user, String pass) {
        String query = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8));
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Use for signup an account to the website
     * @param user: username of the new account
     * @param pass: password of the new account
     * @param email: email of the new account
     * @param activeCode: active code of the new account
     */
    public void signUp(String user, String pass, String email, String activeCode) {
        String query = "INSERT INTO Users VALUES (?, ?, ?, ?, 0, 0, 2);";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.setString(4, activeCode);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    /**
     * Get all account from database
     * @return the list of account from the database
     */
    public List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Users";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Delete an account from the database
     * @param id: id of account to delete
     */
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

    /**
     * Update the information of an account
     * @param id: id of account to update
     * @param user: the updated username
     * @param pass: the updated password
     * @param email: the updated email
     * @param isSell: the updated seller status of the account
     * @param isAdmin : the updated admin status of the account
     */
    public void editAccount(String id, String user, String pass, String email, String isSell, String isAdmin) {
        String query = "UPDATE Users\n"
                + "SET Username = ?,\n"
                + "Password = ?,\n"
                + "email = ?"
                + "isSeller = ?,\n"
                + "isAdmin = ?\n"
                + "WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.setString(4, isSell);
            ps.setString(5, isAdmin);
            ps.setString(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    /**
     * Update the password of a user
     * @param id: of user
     * @param newPassword: new password of that user
     * @return true if update successfully
     */
    public boolean updatePassword(String id, String newPassword) {
        Account toChange = getAccountByID(id);
        String query = "UPDATE Users\n"
                + "SET Password = ?\n"
                + "WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, newPassword);
            ps.setString(2, id);           
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Find an account by id
     * @param id: id of account to find
     * @return the found account, null if the account does not exist in the
     * database
     */
    public Account getAccountByID(String id) {
        String query = "select * from Users where UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8));
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Find an account by email
     * @param id: email of account to find
     * @return the found account, null if the account does not exist in the
     * database
     */
    public Account getAccountByEmail(String userEmail) {
        String query = "select * from Users where email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userEmail);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    /**
     * Find an account by username
     * @param userName username of account to find
     * @return the found account, null if the account does not exist in the
     * database
     */
    public Account getAccountByUsername(String userName) {
        String query = "select * from Users where Username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Update status of an account
     * @param id: id of account to update status
     * @param status: the status to update to account
     * @return true if update successful
     */
    public boolean updateStatus(int id, int status) {
        String query = "Update Users set user_status = ? where UserID = ?";
        int check = 0;

        try {
            ps = connection.prepareStatement(query);
            ps.setObject(2, id);
            ps.setObject(1, status);

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }
        
    /**
     * @return the number of account in the database 
     */
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
//        UserDAO.editAccount("9", "alo", "loa", "1", "1")
        Account a = UserDAO.getAccountByEmail("a");
        System.out.println(a);
        System.out.println(UserDAO.countAllAccount());
    }
}
