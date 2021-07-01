/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.BlogDAO;
import DAL.CategoryDAO;
import DAL.InforDAO;
import DAL.NotificationDAO;
import DAL.ProductDAO;
import entity.Account;
import entity.Blog;
import entity.Category;
import entity.Information;
import entity.Notification;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thong
 */
@WebServlet(name = "BlogListControl", urlPatterns = {"/blogList"})
public class BlogListControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //Call to DAOs

            HttpSession session = request.getSession();
            if (session.getAttribute("acc") == null) {
                ProductDAO ProductDAO = new ProductDAO();
                InforDAO InforDAO = new InforDAO();
                CategoryDAO CategoryDAO = new CategoryDAO();
                BlogDAO BlogDAO = new BlogDAO();

                List<Category> listC = CategoryDAO.getAllCategory(); //Get List Category
                Product hot = ProductDAO.getHotProduct(); //Get First Product
                Product favor = ProductDAO.getFavoriteProduct(); //Get Last Product
                Information infor = InforDAO.getInfor(); //Get Information
                Blog news = BlogDAO.getHotBlog();//getHotBlog


                
                //List<Product> list = ProductDAO.pagingByCategory(index, CID);
                List<Blog> list = BlogDAO.getAllBlog();

                //Set Data to JSP
                request.setAttribute("allCategory", listC);
                request.setAttribute("hot", hot);
                request.setAttribute("favor", favor);
                request.setAttribute("infor", infor);

                request.setAttribute("listP", list); //List Blog                
                request.getRequestDispatcher("BlogList.jsp").forward(request, response);
            } else {
                ProductDAO ProductDAO = new ProductDAO();
                InforDAO InforDAO = new InforDAO();
                CategoryDAO CategoryDAO = new CategoryDAO();
                NotificationDAO notiDAO = new NotificationDAO();
                BlogDAO BlogDAO = new BlogDAO();
                Account user = (Account) session.getAttribute("acc");

                int userId = user.getId();
                int unreadNotifications = notiDAO.countUnreadNotifications(userId);
                List<Category> listC = CategoryDAO.getAllCategory(); //Get List Category
                Product hot = ProductDAO.getHotProduct(); //Get First Product
                Product favor = ProductDAO.getFavoriteProduct(); //Get Last Product
                Information infor = InforDAO.getInfor(); //Get Information
                List<Notification> notis = notiDAO.getTop5NotificationsByUserID(userId);
                int notiss = notiDAO.countNotifications(userId);
                Blog news = BlogDAO.getHotBlog();//getHotBlog

              
                //List of Product to Display after Paging by Category ID
                List<Blog> list = BlogDAO.getAllBlog();

                //Set Data to JSP
                request.setAttribute("allCategory", listC);
                request.setAttribute("hot", hot);
                request.setAttribute("favor", favor);
                request.setAttribute("infor", infor);

                request.setAttribute("listP", list); //List Blog
                
                request.setAttribute("unread", unreadNotifications);
                request.setAttribute("notis", notis);
                request.setAttribute("numberOfNotifications", notiss);                
                request.setAttribute("acc",user);
                

                request.getRequestDispatcher("BlogList.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("Error.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
