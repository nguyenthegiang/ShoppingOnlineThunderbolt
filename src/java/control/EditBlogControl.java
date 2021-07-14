/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.BlogDAO;
import DAL.CategoryDAO;
import DAL.ProductDAO;
import DAL.UserDAO;
import entity.Account;
import entity.BlogInManager;
import entity.Category;
import entity.ProductInManager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thong
 */
@WebServlet(name = "EditBlogControl", urlPatterns = {"/editBlog"})
public class EditBlogControl extends HttpServlet {

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
        try {
            //Get ID cua Blog tu JSP
            String id = request.getParameter("BlogID");
            BlogDAO BlogDAO = new BlogDAO();
            BlogInManager b =BlogDAO.getBlogForManager(id);
            
            //Call DAO
            CategoryDAO CategoryDAO = new CategoryDAO();
            List<Category> listC = CategoryDAO.getAllCategory();
            request.setAttribute("listC", listC);
            //Call DAO
            UserDAO UserDAO = new UserDAO();
            List<Account> listS = UserDAO.getAllAccounts();
            request.setAttribute("listS", listS);
            
            

            request.setAttribute("id", b.getId());
            request.setAttribute("title", b.getTitle());
            request.setAttribute("content", b.getContent());
            request.setAttribute("imageLink", b.getImageLink());
            request.setAttribute("SellerID", b.getSellerID());
         

            request.getRequestDispatcher("EditBlog.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("Error.jsp");
        }
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
        try {
            //Step 1: get data from jsp
            String title = request.getParameter("title"); //Get by name
            String content = request.getParameter("content");           
            String imageLink = request.getParameter("imageLink");
            String SellerID = request.getParameter("SellerID");
            String id = request.getParameter("id");

            //Step 2: set data to ProductDAO
            BlogDAO dao = new BlogDAO();
            dao.edit(title, content, imageLink, SellerID, id);
            response.sendRedirect("blogManager");
        } catch (Exception e) {
            response.sendRedirect("Error.jsp");
        }
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
