/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.FeedbackRepliesDAO;
import entity.Account;
import entity.FeedbackReplies;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "RepliesControl", urlPatterns = {"/add-replies"})
public class RepliesControl extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        try {
            // get current account
            HttpSession session = request.getSession();
            Account currentAccount = (Account) session.getAttribute("acc");

            // get feedback id, product id and replies text           
            String feedbackId = request.getParameter("feedbackId");
            String productId = request.getParameter("productId");
            String repliesText = request.getParameter("replies-text");
            System.out.println(productId);

            // call DAO
            FeedbackRepliesDAO feedbackRepliesDAO = new FeedbackRepliesDAO();

            // create feedback replies object
            FeedbackReplies theReplies = new FeedbackReplies();
            theReplies.setFeedbackId(Integer.parseInt(feedbackId));
            theReplies.setRepliesText(repliesText);
            theReplies.setUserId(currentAccount.getId());

            // add to database
            boolean addFlag = feedbackRepliesDAO.addReplies(theReplies);
            System.out.println(addFlag);

            // if success, return to detail.jsp, add successful message
            if (addFlag) {
                request.setAttribute("ProductID", productId);
                request.setAttribute("messageAddReplies", "Add replies successful!");
            }

            // if fail, return to detail.jsp, add fail message
            if (!addFlag) {
                request.setAttribute("ProductID", productId);
                request.setAttribute("messageAddReplies",
                        "Fail to add replies. Please try again later ");
            }

            // send to detail.jsp.
            request.getRequestDispatcher("detail").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
