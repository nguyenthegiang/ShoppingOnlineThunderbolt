/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.FeedbackDAO;
import DAL.ProductDAO;
import entity.Account;
import entity.Feedback;
import entity.Product;
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
@WebServlet(name = "SubmitFeedbackControl", urlPatterns = {"/submit-feedback"})
public class SubmitFeedbackControl extends HttpServlet {

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
            ProductDAO productDao = new ProductDAO();

            String orderId = request.getParameter("orderId");
            String productId = request.getParameter("productId");

            Product p = productDao.getProductByID(productId);

            request.setAttribute("product", p);
            request.setAttribute("orderId", orderId);
            request.getRequestDispatcher("FeedbackForm.jsp").forward(request, response);

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
        response.setContentType("text/html;charset=UTF-8");
        try {

            // get current user account
            HttpSession session = request.getSession();
            Account currentAccount = (Account) session.getAttribute("acc");

            // get FeedbackDAO
            FeedbackDAO feedbackDAO = new FeedbackDAO();

            // get current product id
            int productId = Integer.parseInt(request.getParameter("productId"));

            // get input rating
            String star = request.getParameter("star-value");
            String feedback = request.getParameter("feedback-text");

            //get order id
            String orderId = request.getParameter("orderId");

            // create feedback
            Feedback userFeedback = new Feedback();
            userFeedback.setProductId(productId);
            userFeedback.setUserId(currentAccount.getId());
            userFeedback.setStar(Integer.parseInt(star));
            userFeedback.setOrderId(Integer.parseInt(orderId));
            userFeedback.setFeedbackDetail(feedback);
            System.out.println(userFeedback);

            // add feedback to database
            boolean addFeedback = feedbackDAO.addFeedback(userFeedback);
            
            // redirect to Home
            if(addFeedback) {
                request.getRequestDispatcher("home").forward(request, response);
            }           
        } catch (Exception e) {
            e.printStackTrace();
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
