/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.*;
import DAL.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EditControl", urlPatterns = {"/edit"})
public class EditControl extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //Get ID cua Product tu JSP
        String id = request.getParameter("ProductID");
        ProductDAO ProductDAO = new ProductDAO();
        ProductInManager p = ProductDAO.getProductForManager(id);
        //Call DAO
        CategoryDAO CategoryDAO = new CategoryDAO();
        List<Category> listC = CategoryDAO.getAllCategory();
        request.setAttribute("listC", listC);
        //Call DAO
        UserDAO UserDAO = new UserDAO();
        List<Account> listS = UserDAO.getAllAccounts();
        request.setAttribute("listS", listS);
        
        request.setAttribute("id", p.getId());
        request.setAttribute("name", p.getName());
        request.setAttribute("imageLink", p.getImageLink());
        request.setAttribute("price", p.getPrice());
        request.setAttribute("description", p.getDescription());
        request.setAttribute("TagCategoryID", p.getCategoryID());
        request.setAttribute("SellerID", p.getSellerID());
        request.setAttribute("amount", p.getAmount());
        
        request.getRequestDispatcher("Edit.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //Bước 1: get data from jsp
        String id = request.getParameter("id");
        String name = request.getParameter("name"); //Get by name
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String imageLink = request.getParameter("imageLink");
        String CategoryID = request.getParameter("CategoryID");
        String SellerID = request.getParameter("SellerID");
        String amount = request.getParameter("amount");

        //Bước 2: set data to ProductDAO
        ProductDAO dao = new ProductDAO();
        dao.edit(id, name, description, price, imageLink, CategoryID, SellerID, amount);
        response.sendRedirect("manager");
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
