/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.FeedbackDAO;
import DAL.ProductDAO;
import DAL.UserDAO;
import entity.Account;
import entity.Feedback;
import entity.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
@WebServlet(name = "ManageFeedbackControl", urlPatterns = {"/manage-feedback"})
public class ManageFeedbackControl extends HttpServlet {

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
            // get current user
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("acc");

            // get all dao
            ProductDAO productDao = new ProductDAO();
            FeedbackDAO feedbackDao = new FeedbackDAO();
            UserDAO userDao = new UserDAO();

            // get all feedback of product of this seller
            List<Product> lsProduct = productDao.getProductBySellID(a.getId());
            List<Integer> lsId = lsProduct.stream().map(Product::getId).collect(Collectors.toList());
            List<Feedback> lsFeedback = new ArrayList<>();
            for (int id : lsId) {
                lsFeedback.addAll(feedbackDao.getFeedbacksByProductId(id));
            }

            for (Feedback feedback : lsFeedback) {
                // get all account that made feedback
                Account accountMadeFeedback = userDao.getAccountByID(
                        String.valueOf(
                                feedback.getUserId()
                        ));
                feedback.setUser(accountMadeFeedback);

                // get all product of feedback
                Product productWithFeedback
                        = productDao.getProductByID(
                                String.valueOf(feedback.getProductId())
                        );
                feedback.setProduct(productWithFeedback);

            }

            // allow sort by name, product, star
            if (request.getParameter("sort-flag")!=null) {
                int sortOption = Integer.parseInt(request.getParameter("sort-order"));
                int sortOrder = Integer.parseInt(request.getParameter("sort-by-order"));
                switch (sortOption) {
                    // sort by star
                    case 1: {
                        if (sortOrder == 1) {
                            // sort ascending
                            lsFeedback.sort(Comparator.comparing((Feedback::getStar)));
                        } else {
                            // sort descending
                            lsFeedback.sort(Comparator.comparing((Feedback::getStar)).reversed());
                        }
                        break;
                    }
                    // sort by user
                    case 2: {
                        if (sortOrder == 1) {
                            // sort ascending
                            lsFeedback.sort(Comparator.comparing((x -> x.getUser().getUser())));
                        } else {
                            // sort descending
                            lsFeedback.sort(Comparator.comparing((x -> x.getUser().getUser())));
                            Collections.reverse(lsFeedback);
                        }
                        break;
                    }
                    // sort by product
                    case 3: {
                        if (sortOrder == 1) {
                            // sort ascending
                            lsFeedback.sort(Comparator.comparing((x -> x.getProduct().getName())));
                        } else {
                            // sort descending
                            lsFeedback.sort(Comparator.comparing((x -> x.getProduct().getName())));
                            Collections.reverse(lsFeedback);
                        }
                        break;
                    }

                }
            }

            request.setAttribute("lsFeedback", lsFeedback);
            request.getRequestDispatcher("ViewAllFeedbacks.jsp").forward(request, response);
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
