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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EditAccountControl", urlPatterns = {"/editAccount"})
public class EditAccountControl extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
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
        try {
            //Get ID from jsp
            String id = request.getParameter("UserID");
            UserDAO dao = new UserDAO();
            UserAddressDAO UserAddressDAO = new UserAddressDAO();
            
            
            Account x = dao.getAccountByID(id);
            
            //Push
            request.setAttribute("id", x.getId());
            request.setAttribute("user", x.getUser());
            request.setAttribute("pass", x.getPass());
            request.setAttribute("email", x.getEmail());
            request.setAttribute("Seller", x.getIsSell());
            request.setAttribute("Admin", x.getIsAdmin());
           

            request.getRequestDispatcher("EditAccount.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            //Step 1: get data from jsp
            String id = request.getParameter("id");
            String user = request.getParameter("user"); //Get by name
            String password = request.getParameter("pass");
            String email = request.getParameter("email");
            String isSell = request.getParameter("Seller");
            String isAdmin = request.getParameter("Admin");

            if (!"1".equals(isSell)) {
                isSell = "0";
            }
            if (!"1".equals(isAdmin)) {
                isAdmin = "0";
            }

            //Step 2: set data to ProductDAO
            UserDAO dao = new UserDAO();
            //dao.editAccount(id, user, password, isSell, isAdmin);
            response.sendRedirect("accountManager");
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
