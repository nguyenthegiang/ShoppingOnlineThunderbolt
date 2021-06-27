/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.CartDAO;
import DAL.InvoicesDAO;
import DAL.NotificationDAO;
import DAL.OrderDAO;
import DAL.OrderDetailDAO;
import entity.Account;
import entity.Order;
import entity.OrderDetail;
import entity.OrderDetailAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thuan
 */
@WebServlet(name = "ViewInvoiceDetailAdmin", urlPatterns = {"/viewInvoiceDetailAdmin"})
public class ViewInvoiceDetailAdmin extends HttpServlet {

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
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("acc");
            if (a.getIsAdmin() == 1) {
                String status = request.getParameter("status");
                int id = Integer.parseInt(request.getParameter("id"));

                InvoicesDAO invoicesDAO = new InvoicesDAO();
                OrderDAO orderDAO = new OrderDAO();
                List<OrderDetailAdmin> invoiceDetail = invoicesDAO.getInvoiceDetailByOrderID(id);
                int totalCart = orderDAO.countOrders();

                request.setAttribute("invoiceDetail", invoiceDetail);
                request.setAttribute("totalCart", totalCart);
                request.setAttribute("OrderId", id);
                request.setAttribute("sta", status);

                request.getRequestDispatcher("ViewInvoiceDetail.jsp").forward(request, response);
            } else if(a.getIsAdmin()!= 1){
                String status = request.getParameter("status");
                int id = Integer.parseInt(request.getParameter("sellerId"));
                InvoicesDAO invoicesDAO = new InvoicesDAO();
                CartDAO CartDAO = new CartDAO();
                OrderDAO orderDAO = new OrderDAO();
                List<OrderDetailAdmin> invoiceDetail = invoicesDAO.getInvoiceDetailBySellerID(id);
                int totalCart = orderDAO.countOrders();                

                request.setAttribute("invoiceDetail", invoiceDetail);
                request.setAttribute("totalCart", totalCart);
                request.setAttribute("sellerId", id);
                request.setAttribute("sta", status);

                request.getRequestDispatcher("ViewInvoiceDetail.jsp").forward(request, response);
            }
        } catch (Exception ex) {

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
