/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.*;
import DAL.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DetailControl", urlPatterns = {"/detail"})
public class DetailControl extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            Account currentAccount = (Account) session.getAttribute("acc");

            //Get ID back
            String id = request.getParameter("ProductID");

            //Get Message Out of Stock (IF that have)
            try {
                String message = request.getParameter("message");
                request.setAttribute("message", message);
            } catch (Exception e) {
            }

            //CAll DAO
            ProductDAO dao = new ProductDAO();
            ProductDetail p = dao.getProductDetailByID(id);
            FeedbackDAO feedbackDao = new FeedbackDAO();
            UserDAO userDao = new UserDAO();
            FeedbackRepliesDAO feedbackRepliesDao = new FeedbackRepliesDAO();
            OrderDAO orderDAO = new OrderDAO();
            CategoryDAO CategoryDAO = new CategoryDAO();
            ProductDAO ProductDAO = new ProductDAO();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat(
                    "dd/MM/yyyy");

            // create list of categories, feedback, account that made feedback and account that replies
            List<Category> listC = CategoryDAO.getAllCategory();
            List<Feedback> lsFeedback
                    = feedbackDao.getFeedbacksByProductId(Integer.parseInt(id));
            List<Account> lsAccount = new ArrayList<>();
            List<Account> lsAccountReplies = new ArrayList<>();
            List<FeedbackReplies> lsFeedbackReplies = new ArrayList<>();

            // give data for list replies, list account made feedback and list of account that replies
            for (Feedback feedback : lsFeedback) {
                //get order of the feedback
                feedback.setOrder(
                        orderDAO.getOrderByOrderID(feedback.getOrderId())
                );
                
                // get order date of feedback
                Date orderDate = feedback.getOrder().getOrderDate();
                feedback.getOrder().setDate(sdf.format(orderDate));

                // get all replies of feedback
                lsFeedbackReplies = feedbackRepliesDao.
                        getFeedbacksByFeedbackId(feedback.getId());
                feedback.setListReplies(lsFeedbackReplies);

                //get all account that made replies
                for (FeedbackReplies lsFeedbackReply : lsFeedbackReplies) {
                    Account a = userDao.getAccountByID(
                            String.valueOf(
                                    lsFeedbackReply.getUserId()
                            ));
                    lsAccountReplies.add(a);
                }

                // get all account that made feedback
                Account a = userDao.getAccountByID(
                        String.valueOf(
                                feedback.getUserId()
                        ));
                lsAccount.add(a);
            }

            /**
             * Check if current login account can give feedback or not Change
             * Feedback to have order id of that feedback Check if order has
             * been deliverd and received or not If it has been deliverd, allow
             * feedback Write order date also
             */
            boolean addFeedbackFlag = false;
            if (currentAccount != null) {
                if (feedbackDao.getFeedbacksByUserIdAndProductId(
                        currentAccount.getId(),
                        Integer.parseInt(id)).size() != 0) {
                    addFeedbackFlag = true;
                }

                /*
                later: count number of times the customer order this product 
                if number of times the customer leave feedback is < number of
                times customer order this product, allow feedback
                 */
            }

            // get hot and favourite product
            Product hot = ProductDAO.getHotProduct();
            Product favor = ProductDAO.getFavoriteProduct();

            //PUSH to JSP
            request.setAttribute("allCategory", listC);
            request.setAttribute("lsFeedback", lsFeedback);
            request.setAttribute("lsAccount", lsAccount);

            request.setAttribute("lsAccountReplies", lsAccountReplies);
            request.setAttribute("addFeedbackFlag", addFeedbackFlag);
            request.setAttribute("hot", hot);
            request.setAttribute("favor", favor);

            request.setAttribute("detail", p);
            request.getRequestDispatcher("Detail.jsp").forward(request, response);
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
