/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.*;
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
//Load du lieu tu SQL Server
public class ProductDAO extends BaseDAO<Product> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Get the results returned

    //1 function to Load All Animals -> This function will have to return 1 List of Animals
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product";
        try {
            ps = connection.prepareStatement(query);//Throw the query to the SQL server 
            rs = ps.executeQuery();//Run the query, get the results returned

            //Now the command has been run, rs is the Result table -> Now have to get the data from the rs table and put it in the List
            while (rs.next()) {
                list.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink")));
            }
        } catch (Exception e) {
        }

        return list;
    }

    /**
     *
     * @return Product with the highest sold amount
     */
    public Product getHotProduct() {
        //Product with most amount
        String query = "select top 1 * from Product\n"
                + "order by Amount desc";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     *
     * @return get the first and the second product with most sold amount
     */
    public Product getFavoriteProduct() {
        //Product with second most amount
        String query = "select top 2 * from Product\n"
                + "order by Amount desc";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                rs.next();
                return new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * each seller will have a different ID, so they will have different
     * products
     *
     * @param id
     * @return the list of products of a particular seller
     */
    public List<Product> getProductBySellID(int id) { //Must be int type because when saving to Session, it is still int
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE SellerID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * Edit the information of a particular product, select by product ID
     *
     * @param id
     * @param name
     * @param description
     * @param price
     * @param imageLink
     * @param CategoryID
     * @param SellerID
     * @param amount
     */
    public void edit(String id, String name, String description, String price, String imageLink, String CategoryID, String SellerID, String amount) {
        String query = "UPDATE Product\n"
                + "SET ProductName = ?,\n"
                + "Description = ?,\n"
                + "SellPrice = ?,\n"
                + "imageLink = ?,\n"
                + "CategoryID = ?,\n"
                + "SellerID = ?,\n"
                + "Amount = ?\n"
                + "WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            //Set data to the "?"
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, price);
            ps.setString(4, imageLink);
            ps.setString(5, CategoryID);
            ps.setString(6, SellerID);
            ps.setString(7, amount);
            ps.setString(8, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    /**
     * Delete a product, select by its ID
     *
     * @param id
     */
    public void delete(String id) { //Leave the String type because when you get it, it's of type String -> Saves having to cast it
        String query = "DELETE FROM Cart WHERE ProductID = ?\n"
                + "DELETE FROM Product WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            //Put the id inside the first "?"
            ps.setString(1, id);
            ps.setString(2, id);
            //Execute: No Result table -> No RS, only executeUpdate execute
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    /**
     * Adding a new product to the database, the ID is no need to be added
     * because it is automatically added
     *
     * @param name
     * @param description
     * @param price
     * @param imageLink
     * @param CategoryID
     * @param SellerID
     * @param amount
     */
    public void add(String name, String description, String price, String imageLink, String CategoryID, String SellerID, String amount) {
        String query = "INSERT INTO Product VALUES (?, ?, 0, ?, 0, ?, ?, ?, ?, 1, 1);";
        try {
            ps = connection.prepareStatement(query);
            //Set data to the "?"
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, price);
            ps.setString(4, imageLink);
            ps.setString(5, CategoryID);
            ps.setString(6, SellerID);
            ps.setString(7, amount);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    /**
     *
     * @return the total number of products
     */
    public int countProduct() {
        String query = "SELECT COUNT(*) FROM Product";
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

    /**
     * Every 6 products will be displayed in a single page
     *
     * @param index
     * @return list of 6 products
     */
    public List<Product> pagingProduct(int index) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product ORDER BY ProductID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 6);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * categorizing products
     *
     * @param index
     * @param CategoryID
     * @return list of products with same category
     */
    public List<Product> pagingByCategory(int index, int CategoryID) {
        List<Product> list = new ArrayList<>();
        if (CategoryID == 0) {
            list = pagingProduct(index);
        } else {
            String query = "SELECT * FROM Product WHERE CategoryID = ? ORDER BY ProductID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, CategoryID);
                ps.setInt(2, (index - 1) * 6);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink")));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    /**
     *
     * @param index
     * @param SellerID
     * @return
     */
    public List<Product> pagingManagerProduct(int index, int SellerID) {
        List<Product> list = new ArrayList<>();
        if (SellerID == 0) {
            list = pagingProduct(index);
        } else {
            String query = "SELECT * FROM Product WHERE SellerID = ? ORDER BY ProductID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, SellerID);
                ps.setInt(2, (index - 1) * 6);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink")));
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
    }

//    public List<Product> searchProductInManager(int SellerID, String name) {
//        List<Product> list = new ArrayList<>();
//        String query = "select * from Product\n"
//                + "where SellerID = ? and ProductName like ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setInt(1, SellerID);
//            ps.setString(2, "%" + name + "%");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
    //count total product
    public int countProductByCategory(int CategoryID) {
        if (CategoryID == 0) {
            return countProduct();
        } else {
            String query = "SELECT COUNT(*) FROM Product WHERE CategoryID = ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, CategoryID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    /**
     * count the products of a particular seller
     *
     * @param SellerID
     * @return the number of product
     */
    public int countProductBySeller(int SellerID) {
        if (SellerID == 0) {
            return countProduct();
        } else {
            String query = "SELECT COUNT(*) FROM Product WHERE SellerID = ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, SellerID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public Product getProductByID(String id) { //Must be int type because when saving to Session, it is still int
        String query = "SELECT * FROM Product WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink")));
            }
        } catch (Exception e) {
        }
        return null;
    }

    //Get Product for Detail
    public ProductDetail getProductDetailByID(String id) { //Must be int type because when saving to Session, it is still int
        String query = "SELECT * \n"
                + "FROM Product INNER JOIN Manufacturer\n"
                + "ON Product.ManufacturerID = Manufacturer.ManufacturerID\n"
                + "WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new ProductDetail(rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getInt("SellPrice"),
                        rs.getString("imageLink"),
                        rs.getString("ManufacturerName")));
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Get a particular product
     *
     * @param id
     * @return a product
     */
    public ProductInManager getProductForManager(String id) { //Must be int type because when saving to Session, it is still int
        String query = "SELECT * FROM Product WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new ProductInManager(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink"), rs.getInt("CategoryID"), rs.getInt("SellerID"), rs.getInt("Amount")));
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Search for a particular product by filling its name
     *
     * @param name
     * @return a product
     */
    public List<Product> searchProductByName(String name) { //Must be int type because when saving to Session, it is still int
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where ProductName like ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * Search for a product in admin/moderator mode by filling its name
     *
     * @param name
     * @param SellerID
     * @return a product
     */
    public List<Product> searchProductInManager(String name, int SellerID) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where ProductName like ? and SellerID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, SellerID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * Select top 3 most sold products
     *
     * @return products
     */
    public List<ProductInManager> top3MostSell() {
        List<ProductInManager> list = new ArrayList<>();
        String query = "select top 3 * from Product order by Amount asc";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductInManager(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink"), rs.getInt("CategoryID"), rs.getInt("SellerID"), rs.getInt("Amount")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * select top 3 least sold products
     *
     * @return products
     */
    public List<ProductInManager> top3LeastSell() {
        List<ProductInManager> list = new ArrayList<>();
        String query = "select top 3 * from Product order by Amount desc";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductInManager(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink"), rs.getInt("CategoryID"), rs.getInt("SellerID"), rs.getInt("Amount")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        //Kiểm tra xem List đã có dữ liệu chưa
        ProductDAO dao = new ProductDAO();
//        List<Product> list = dao.getAllProduct();
//        for (Product o : list) {
//            System.out.println(o);
//        }

//        System.out.println(dao.getHotProduct());
//        System.out.println(dao.getFavoriteProduct());
//        List<Product> list = dao.getTop6();
//        for (Product o : list) {
//            System.out.println(o);
//        }
//        List<Category> list = dao.getAllCategory();
//        for (Category o : list) {
//            System.out.println(o);
//        }
//        Information x = dao.getInfor();
//        System.out.println(x);
//        System.out.println(dao.login("nguyenthegiang", "nguyenthegiang"));
//        List<Product> list = dao.getProductBySellID(1);
//        for (Product product : list) {
//            System.out.println(product);
//        }
//        dao.edit("1", "Webcam Logitech BRIO Ultra HD Pro", "6499000", "Webcam Logitech BRIO Ultra HD Pro.png", "4", "1");
//        dao.delete("1");
//        System.out.println(dao.countProduct());
//        List<Product> list = dao.pagingProduct(1);
//        for (Product o : list) {
//            System.out.println(o);
//        }
//        List<Product> list = dao.pagingByCategory(1, 1);
//        for (Product o : list) {
//            System.out.println(o);
//        }
//        System.out.println(dao.countProductByCategory(1));
//        System.out.println(dao.countProductByCategory(2));
//        System.out.println(dao.countProductByCategory(3));
//        System.out.println(dao.countProductByCategory(4));
//        List<Product> list = dao.searchProductByName("msi");
//        for (Product o : list) {
//            System.out.println(o);
//        }
//        ProductInManager p = dao.getProductForManager("1");
//        System.out.println(p);
//        List<Product> list = dao.pagingManagerProduct(1, 1);
//        for (Product o : list) {
//            System.out.println(o);
//        }
//        System.out.println(dao.countProductBySeller(1));
//        List<ProductInManager> list = dao.top3MostSell();
//        for (ProductInManager productInManager : list) {
//            System.out.println(productInManager);
//        }
//        List<Product> list = dao.searchProductInManager("asus", 1);
//        for (Product product : list) {
//            System.out.println(product);
//        }
//        List<Product> list = dao.pagingByCategory(1, 1);
//        for (Product product : list) {
//            System.out.println(product);
//        }
//        ProductInManager p = dao.getProductForManager("1");
//        System.out.println(p);
//        dao.add("a", "a", "1", "a", "1", "1", "1");
        System.out.println(dao.getProductDetailByID("1"));
    }
}
