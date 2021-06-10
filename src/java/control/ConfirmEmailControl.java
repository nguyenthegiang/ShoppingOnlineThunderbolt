/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.UserDAO;
import entity.Account;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRANTATDAT
 */
@WebServlet(name = "ConfirmEmailControl", urlPatterns = {"/confirm"})
public class ConfirmEmailControl extends HttpServlet {

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
            // Get the account need active
            HttpSession session = request.getSession();
            Account accountNeedActive = (Account) session.getAttribute("newAccount");
            // Get the active code input by user
            String activeCode = request.getParameter("active-code");

            // Redirect if session timeout or can't find account to active
            if (accountNeedActive == null) {
                request.setAttribute("message", "Active Failed!");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

            if (accountNeedActive != null) {
                // Get the account with the newly registerd email from the database
                Account accountWithActiveCode = new UserDAO().getAccountByEmail(accountNeedActive.getEmail());
                // Compare the code from account need active with code from account got from database
                if (accountWithActiveCode.getActiveCode().equals(activeCode)) {
                    // Active the account
                    boolean active = new UserDAO().updateStatus(accountNeedActive.getId(), 1);
                    if (active) {
                        // Active success, redirect to login                       
                        session.removeAttribute("newAccount");
                        response.sendRedirect("Login.jsp");
                    }
                }
                if (!accountWithActiveCode.getActiveCode().equals(activeCode)) {
                    // Active failed, redirect to login
                    request.setAttribute("message", "Active Failed!");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            // Redirect to error page if exception happend
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
