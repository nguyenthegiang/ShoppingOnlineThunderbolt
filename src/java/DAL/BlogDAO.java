/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entity.Blog;
import entity.BlogDetail;
import entity.ProductDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * test push 2
 * @author thong
 */
public class BlogDAO extends BaseDAO<BlogDAO> {
    PreparedStatement ps = null; //...
    ResultSet rs = null; //Get the results returned
    
    public Blog getHotBlog() {
        //Product with most amount
        String query = "select top 1 * from Blog\n"
                + "order by id desc";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Blog(rs.getInt("ID"),rs.getString("Title"),rs.getString("Content"),rs.getString("imageLink"));
            }
        } catch (Exception e) {
        }
        return null;
    }
   public List<Blog> getAllBlog() {
        List<Blog> list = new ArrayList<>();
        String query = "SELECT * FROM Blog";
        try {
            ps = connection.prepareStatement(query);//Throw the query to the SQL server 
            rs = ps.executeQuery();//Run the query, get the results returned

            //Now the command has been run, rs is the Result table -> Now have to get the data from the rs table and put it in the List
            while (rs.next()) {
                list.add(new Blog(rs.getInt("ID"),rs.getString("Title"),rs.getString("Content"),rs.getString("imageLink")));
            }
        } catch (Exception e) {
        }

        return list;
    }
   public BlogDetail getBlogByID(String id) { //Must be int type because when saving to Session, it is still int
        String query = "SELECT * FROM Blog WHERE ID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new BlogDetail(rs.getInt("ID"),rs.getString("Title"),rs.getString("Content"),rs.getString("imageLink"));
            }
        } catch (Exception e) {
        }
        return null;
    }
   
    public static void main(String[] args) {
        BlogDAO dao = new BlogDAO();
      //  System.out.println(dao.getHotBlog());
        // List<Blog> list = dao.getAllBlog();
       //for (Blog o : list) {
         //   System.out.println(o);
        //}
        System.out.println(dao.getBlogByID("1"));
       
       
        
    }
    
}
