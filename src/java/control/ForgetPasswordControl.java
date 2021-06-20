/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.UserDAO;
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
 * @author ADMIN
 */
@WebServlet(name = "ForgetPasswordControl", urlPatterns = {"/forgetPassword"})
public class ForgetPasswordControl extends HttpServlet {

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

        String username = request.getParameter("username");
        String email = request.getParameter("email");

        //Check if user has entered correct information
        UserDAO UserDAO = new UserDAO();
        int checkID = UserDAO.checkForgetPassword(username, email);
        if (checkID == 0) {
            request.setAttribute("wrongEmail", "You have input wrong Email or wrong Username! Please try again");
            request.getRequestDispatcher("ForgetPass.jsp").forward(request, response);
        } else {
            try {
                HttpSession session = request.getSession();

                //Send email with confirmation code to reset password
                String confirmCode = util.GenerateRandomString.generateString(10);
                String subject = "Confirmation code for account at Computer ERA";
                String message = "Your confirmation code at Computer ERA is: " + confirmCode;
                new SendEmail(email, subject, message);

                //Set the confirmation code to the Session
                session.setAttribute("code", confirmCode);
                request.setAttribute("email", email);
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            } catch (Exception e) {
                response.sendRedirect("Error.jsp");
            }
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
