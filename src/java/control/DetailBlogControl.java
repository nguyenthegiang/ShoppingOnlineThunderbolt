/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.BlogDAO;
import DAL.CategoryDAO;
import DAL.ProductDAO;
import entity.BlogDetail;
import entity.Category;
import entity.Product;
import entity.ProductDetail;
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
@WebServlet(name = "DetailBlogControl", urlPatterns = {"/detailBlog"})
public class DetailBlogControl extends HttpServlet {

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
            //Get ID back
            String id = request.getParameter("ID");
            
            
            //Get Message Out of Stock (IF that have)
            try {
                String message = request.getParameter("message");
                request.setAttribute("message", message);
            } catch (Exception e) {
            }

            //CAll DAO
            CategoryDAO CategoryDAO = new CategoryDAO();
            ProductDAO ProductDAO = new ProductDAO();
            
            BlogDAO BlogDAO = new BlogDAO();
            BlogDetail b = BlogDAO.getBlogByID(id);
            
            List<Category> listC = CategoryDAO.getAllCategory();
                          
            Product hot = ProductDAO.getHotProduct();
            Product favor = ProductDAO.getFavoriteProduct();

            //PUSH to JSP
            request.setAttribute("allCategory", listC);
           
            request.setAttribute("hot", hot);
            request.setAttribute("favor", favor);

            request.setAttribute("detailBlog", b);
            request.getRequestDispatcher("BlogDetail.jsp").forward(request, response);
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
