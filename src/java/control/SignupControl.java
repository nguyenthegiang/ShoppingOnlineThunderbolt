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
import javax.servlet.http.HttpSession;
import util.GenerateRandomString;
import util.SendEmail;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SignupControl", urlPatterns = {"/signup"})
public class SignupControl extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();
            UserDAO dao = new UserDAO();

            //sign up for account login with facebook
            String loginFb = request.getParameter("loginFB");
            if (loginFb != null || !loginFb.trim().equals("")) {
                String username = request.getParameter("user");
                String email = request.getParameter("email");
                dao.signUpFB(username, email);
                Account a = dao.getAccountByEmail(email);
                session.setAttribute("acc", a);
                response.sendRedirect("home");
                
            } else {
                // Normal account signup
                // Get new user information
                String username = request.getParameter("user");
                String password = request.getParameter("pass");
                String email = request.getParameter("email");
                String repass = request.getParameter("repass");
                String activeCode = GenerateRandomString.generateString(10);

                // Check if password is confirmed
                if (password.equals(repass)) {

                    // Check if email and username is not existed
                    if (dao.getAccountByEmail(email) == null
                            && dao.getAccountByUsername(username) == null) {

                        // Send email with active code
                        String subject = "Active code for account at Computer ERA";
                        String message = "Your active code at Computer ERA is: " + activeCode;
                        new SendEmail(email, subject, message);
                        // Sign up the account
                        dao.signUp(username, password, email, activeCode);
                        Account newAccount = dao.getAccountByEmail(email);
                        // Get the signed up account, put into session
                        session.setAttribute("newAccount", newAccount);
                        // Redirect to confirm email page
                        response.sendRedirect("ConfirmEmail.jsp");
                    }
                } else {
                    // Redirect to login page if password is not confirmed or email existed
                    response.sendRedirect("Login.jsp");
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
