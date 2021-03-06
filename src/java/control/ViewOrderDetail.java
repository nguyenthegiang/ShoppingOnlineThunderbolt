/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.FeedbackDAO;
import DAL.OrderDAO;
import DAL.OrderDetailWithImageDAO;
import entity.Feedback;
import entity.Order;
import entity.OrderDetailWithImage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thuan
 */
@WebServlet(name = "ViewOrderDetail", urlPatterns = {"/viewOrderDetail"})
public class ViewOrderDetail extends HttpServlet {

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
            int id = Integer.parseInt(request.getParameter("id"));

            OrderDetailWithImageDAO orderDAO = new OrderDetailWithImageDAO();
            OrderDAO odDAO = new OrderDAO();
            FeedbackDAO feedbackDAO = new FeedbackDAO();

            List<OrderDetailWithImage> orderDetails = orderDAO.getOrderDetail(id);
            Order order = odDAO.getOrderByOrderID(id);           
            
            if (order.getStatus().equals("Completed")) {
                List<Feedback> currentOrdersFeedback = feedbackDAO.getFeedbacksByOrderId(id);

                // get all product in order details that had feedback
                List<Integer> alreadyHaveFeedbackList = orderDetails.stream()
                        .filter(orderDetail -> currentOrdersFeedback.stream()
                        .anyMatch(feedback
                                -> feedback.getOrderId() == orderDetail.getOrderID()
                        && feedback.getProductId() == orderDetail.getProductID()
                        ))
                        .map(OrderDetailWithImage::getId)
                        .collect(Collectors.toList());
                request.setAttribute("alreadyHaveFeedbackList", alreadyHaveFeedbackList);
            }

            request.setAttribute("orderDetails", orderDetails);

            request.setAttribute("order", order);

            request.getRequestDispatcher("ViewOrderDetails.jsp").forward(request, response);

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
