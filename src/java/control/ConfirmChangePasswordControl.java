/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.SendEmail;

/**
 *
 * @author TRANTATDAT
 */
@WebServlet(name = "ConfirmChangePassword", urlPatterns = {"/confirm-change-password"})
public class ConfirmChangePasswordControl extends HttpServlet {

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
//Remove Send Code function when Change Password
            
//            // Get the account need to change password
//            HttpSession session = request.getSession();
//            Account accountChangePass = (Account) session.getAttribute("acc");
//
//            //Send email with confirmation code to change password
//            String confirmCode = util.GenerateRandomString.generateString(10);
//            String subject = "Confirmation code for account at Computer ERA";
//            String message = "Your confirmation code at Computer ERA is: "
//                    + confirmCode;
//            new SendEmail(accountChangePass.getEmail(), subject, message);
//
//            session.setAttribute("code", confirmCode);
//            request.getRequestDispatcher("ChangePassword.jsp")
//                    .forward(request, response);

            response.sendRedirect("ChangePassword.jsp");
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
        response.setContentType("text/html;charset=UTF-8");
        
//Remove Send Code function when Change Password

//        try {
//            // Get the confirm code from session
//            HttpSession session = request.getSession();
//            String accountConfirmCode = (String) session.getAttribute("code");
//
//            // Get the confirm code from user
//            String userEnteredCode = request.getParameter("code");
//
//            // Compare the 2 code
//            boolean compare = accountConfirmCode.equals(userEnteredCode);
//
//            if (compare) {
//                // The 2 codes are identical, forward to change password
//                request.setAttribute("compare", compare);
//            } else {
//                // The 2 codes are not identical, notify wrong code, user re-enter
//                request.setAttribute("message", "Wrong Code!");
//            }
//            request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
//        } catch (Exception e) {
//            // Redirect to error page if exception happend
//            response.sendRedirect("Error.jsp");
//        }
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
