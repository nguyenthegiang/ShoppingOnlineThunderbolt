/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.*;
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DashBoardControl", urlPatterns = {"/dashBoard"})
public class DashBoardControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    int max = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            ProductDAO ProductDAO = new ProductDAO();
            UserDAO UserDAO = new UserDAO();
            CartDAO CartDAO = new CartDAO();

            int totalAccount = UserDAO.countAllAccount();
            int totalProduct = ProductDAO.countProduct();
            int totalCart = CartDAO.countAllCart();

            List<ProductInManager> top3LeastSell = ProductDAO.top3LeastSell();
            List<DashBoardProduct> top3LeastSellD = amountToProportionLeast3(top3LeastSell);
            List<ProductInManager> top3MostSell = ProductDAO.top3MostSell();
            List<DashBoardProduct> top3MostSellD = amountToProportionMost3(top3MostSell);

            request.setAttribute("totalProduct", totalProduct);
            request.setAttribute("totalAccount", totalAccount);
            request.setAttribute("totalCart", totalCart);

            request.setAttribute("top3MostSellD", top3MostSellD);
            request.setAttribute("top3LeastSellD", top3LeastSellD);

            request.getRequestDispatcher("DashBoard.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("Error.jsp");
        }

    }

    public List<DashBoardProduct> amountToProportionLeast3(List<ProductInManager> listP) {
        List<DashBoardProduct> listD = new ArrayList<>();

        //get max amount
        for (ProductInManager o : listP) {
            int amount = o.getAmount();
            if (max < amount) {
                max = amount;
            }
        }

        //calculate Proportion of each Product
        for (ProductInManager o : listP) {
            double proportion = 100 - ((double) o.getAmount() / max) * 100;
            listD.add(new DashBoardProduct(o.getName(), max - o.getAmount(), (int) proportion));
        }

        return listD;
    }

    public List<DashBoardProduct> amountToProportionMost3(List<ProductInManager> listP) {
        List<DashBoardProduct> listD = new ArrayList<>();

        //calculate Proportion of each Product
        for (ProductInManager o : listP) {
            double proportion = 100 - ((double) o.getAmount() / max) * 100;
            listD.add(new DashBoardProduct(o.getName(), max - o.getAmount(), (int) proportion));
        }

        return listD;
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
