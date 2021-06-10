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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
//Load du lieu tu SQL Server
public class ProductDAO extends BaseDAO<Product> {

    PreparedStatement ps = null; //...
    ResultSet rs = null; //Nhận kết quả trả về

    //1 hàm để Load Tất cả các Animal lên -> Hàm này sẽ phải trả về 1 List các Animal
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product";
        try {
            ps = connection.prepareStatement(query);//ném query sang bên SQL server
            rs = ps.executeQuery();//Chạy câu lệnh query, nhận kết quả trả về

            //Giờ đây, câu lệnh đã đc chạy, rs là bảng Result -> Giờ phải lấy dữ liệu từ bảng rs và cho vào List
            while (rs.next()) {
                list.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("Description"), rs.getInt("SellPrice"), rs.getString("imageLink")));
            }
        } catch (Exception e) {
        }

        return list;
    }

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

    public List<Product> getProductBySellID(int id) { //Phải để kiểu int vì khi lưu lên Session thì nó vẫn là kiểu int
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
            //Set dữ liệu vào dấu ?
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

    public void delete(String id) { //Để kiểu String vì khi get về nó là kiểu String -> Đỡ phải ép kiểu
        String query = "DELETE FROM Cart WHERE ProductID = ?\n"
                + "DELETE FROM Product WHERE ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            //Đẩy id vào trong dấu ? thứ nhất
            ps.setString(1, id);
            ps.setString(2, id);
            //Execute: Ko có bảng Result -> Ko dùng RS, chỉ dùng executeUpdate
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void add(String name, String description, String price, String imageLink, String CategoryID, String SellerID, String amount) {
        String query = "INSERT INTO Product VALUES (?, ?, 0, ?, 0, ?, ?, ?, ?, 1, 1);";
        try {
            ps = connection.prepareStatement(query);
            //Set dữ liệu vào dấu ?
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

    //count total product
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
            } catch (Exception e) {
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

    //Get Product for Detail
    public Product getProductByID(String id) { //Phải để kiểu int vì khi lưu lên Session thì nó vẫn là kiểu int
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

    public ProductInManager getProductForManager(String id) { //Phải để kiểu int vì khi lưu lên Session thì nó vẫn là kiểu int
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

    public List<Product> searchProductByName(String name) { //Phải để kiểu int vì khi lưu lên Session thì nó vẫn là kiểu int
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
        
        dao.add("a", "a", "1", "a", "1", "1", "1");
    }
}
