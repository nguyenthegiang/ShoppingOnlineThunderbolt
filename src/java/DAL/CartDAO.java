/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CartDAO extends BaseDAO<Account> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về
/**
 * 
 * @param id
 * @return list of products in cart of a particular customer
 */
    public List<Cart> getCart(int id) {
        List<Cart> list = new ArrayList<>();
        String query = "select Product.ProductID, Product.ProductName, Product.Description, Product.SellPrice, Product.imageLink, Cart.Amount\n"
                + "from Cart inner join Product\n"
                + "on Cart.ProductID = Product.ProductID\n"
                + "where Cart.UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink"));
                int amount = rs.getInt("Amount");
                list.add(new Cart(p, amount));
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * 
     * @param id: id of user
     * @return number of different items in cart 
     */
    public int countCart(int id) {
        int count = 0;
        String query = "select count(*)\n"
                + "from Cart inner join Product \n"
                + "on Cart.ProductID = Product.ProductID\n"
                + "where Cart.UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

    /**
     * 
     * @return the number of all products in cart 
     */
    public int countAllCart() {
        int count = 0;
        String query = "select count(*)\n"
                + "from Cart group by UserID";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

    //Function returns a boolean to inform whether the product is added to cart or not
    //If Product is out of stock
    public boolean addToCart(int userID, int productID, int amount) {
        //Check if Product to add already has at least 1 in cart
        CartDAO dao = new CartDAO();

        //Before add to cart: check if product is out of stock
        if (countAmountProduct(productID) == 0) {
            return false;
        } else {
            List<Cart> list = dao.getCart(userID);
            for (Cart cart : list) {
                if (cart.getP().getId() == productID) {
                    String query = "update Cart\n"
                            + "set Amount = ?\n"
                            + "where UserID = ? and ProductID = ?";
                    try {
                        ps = connection.prepareStatement(query);
                        ps.setInt(1, cart.getAmount() + amount);
                        ps.setInt(2, userID);
                        ps.setInt(3, productID);
                        ps.executeUpdate();
                    } catch (Exception e) {
                    }
                    //Call to delete1amount
                    dao.delete1Amount(productID);
                    return true;
                }
            }
            //Else: Add 1 product into cart
            String query = "INSERT INTO Cart VALUES (?, ?, ?);";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, userID);
                ps.setInt(2, productID);
                ps.setInt(3, amount);
                ps.executeUpdate();
            } catch (Exception e) {
            }
            delete1Amount(productID);
            return true;
        }
    }

    //Minus 1 amount from Product after 1 Customer Add to cart
    public void delete1Amount(int ProductID) {
        String query = "update Product\n"
                + "set Amount = Amount - 1\n"
                + "where ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, ProductID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    //Count the amount of a product to see if it is out of stock
    public int countAmountProduct(int ProductID) {
        String query = "select amount\n"
                + "from Product\n"
                + "where ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, ProductID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 
     * @param userID
     * @return number of products in cart of a particular user
     */
    public int countNumCart(int userID) {
        String query = "select count(*) from Cart where UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * delete all products in cart of a particular user 
     * @param UserID 
     */
    public void deleteCart(int UserID) {
        String query = "delete from Cart where UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, UserID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    /**
     * Delete a particular product in cart of a particular user
     * @param UserID
     * @param ProductID 
     */
    public void deleteProductCart(int UserID, int ProductID) {
        String query = "delete from cart where UserID = ? and ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, UserID);
            ps.setInt(2, ProductID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        CartDAO CartDAO = new CartDAO();
        //int count = CartDAO.countCart(1);
        //System.out.println(count);
        //CartDAO.addToCart(2, 1);
//        System.out.println(CartDAO.countNumCart(1));

        //CartDAO.addToCart(1, 5);
        //CartDAO.deleteCart(1);
//        List<Cart> list = CartDAO.getCart(9);
//        for (Cart cart : list) {
//            System.out.println(cart);
//        }
//        CartDAO.deleteProductCart(8, 2);
//        CartDAO.addToCart(1, 20, 1);

        System.out.println(CartDAO.countAllCart());
    }
}
