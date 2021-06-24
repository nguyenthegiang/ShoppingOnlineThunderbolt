/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.ShipDAO;
import DAL.UserAddressDAO;
import entity.Account;
import entity.Ship;
import entity.UserAddress;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "AddAddressControl", urlPatterns = {"/add-address"})
public class AddAddressControl extends HttpServlet {

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
            // get all necessary DAO
            ShipDAO shipDAO = new ShipDAO();

            // get the list of all ship city
            List<Ship> lsShips = shipDAO.getAllShip();

            // set attribute and send to jsp page
            request.setAttribute("listShip", lsShips);
            request.getRequestDispatcher("AddAddress.jsp").forward(request, response);
        } catch (Exception e) {

            // print error to console and send user to error page
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {

            // get current session
            HttpSession session = request.getSession();

            // get the current login account
            Account a = (Account) session.getAttribute("acc");

            // get address information from the form
            String customerName = request.getParameter("lastName")
                    + " "
                    + request.getParameter("firstName");
            String phoneNum = request.getParameter("phone");
            String shippingAddress = request.getParameter("address");
            int shippingCity = Integer.valueOf(request.getParameter("city"));

            // set information to user address
            UserAddress userAddress = new UserAddress();
            userAddress.setUserId(a.getId());
            userAddress.setShipName(customerName);
            userAddress.setPhoneNum(phoneNum);
            userAddress.setShipAddress(shippingAddress);
            userAddress.setShipCityId(shippingCity);

            // add information to the database
            new UserAddressDAO().add(userAddress);

            response.sendRedirect("buy");

        } catch (Exception e) {

            // print error to console and send user to error page
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
