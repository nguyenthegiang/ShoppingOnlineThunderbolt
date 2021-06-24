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
@WebServlet(name = "BuyServlet", urlPatterns = {"/buy"})
public class BuyControl extends HttpServlet {

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

    }

    public String getPriceWithDot(int price) {
        String priceDot = "" + price;
        int i = priceDot.length() - 3;
        while (i > 0) {
            priceDot = priceDot.substring(0, i) + "." + priceDot.substring(i, priceDot.length());
            i -= 3;
        }
        return priceDot;
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession(); //Dùng session để gọi đến id
            Account a = (Account) session.getAttribute("acc"); //Gọi đến account -> Phải ép kiểu để thành Object

            // create necessary DAO
            CartDAO cartDAO = new CartDAO();
            ShipDAO shipDAO = new ShipDAO();
            UserAddressDAO userAddressDAO = new UserAddressDAO();
            
            // get products in user's cart
            List<Cart> listCart = cartDAO.getCart(a.getId()); //Truyền vào id của account
            
            // get address of user
            UserAddress currentUserDefaultAddress = userAddressDAO.getAddressByUserId(a.getId());
            if (listCart.size() == 0) {
                response.sendRedirect("show");
            }

            //Get All Ships information
            List<Ship> listShip = shipDAO.getAllShip();
            Ship currentUserDefaultCity = shipDAO.getCityByCId(currentUserDefaultAddress.getShipCityId());

            // calculate total sum
            int total = 0;
            for (Cart cart : listCart) {
                total += cart.getP().getPrice() * cart.getAmount();
            }  
            total += currentUserDefaultCity.getShipPrice();
           
            // set attribute, send to Buy.jsp
            request.setAttribute("listCart", listCart);
            request.setAttribute("total", total);
            request.setAttribute("listShip", listShip);
            request.setAttribute("currentUserDefaultAddress", currentUserDefaultAddress);
            request.setAttribute("currentUserDefaultCity", currentUserDefaultCity);
            request.getRequestDispatcher("Buy.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        try {
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession(); //Dùng session để gọi đến id
            Account a = (Account) session.getAttribute("acc"); //Gọi đến account -> Phải ép kiểu để thành Object

            CartDAO CartDAO = new CartDAO();
            List<Cart> listCart = CartDAO.getCart(a.getId()); //Truyền vào id của account

            int total = 0;
            for (Cart cart : listCart) {
                total += cart.getP().getPrice() * cart.getAmount();
            }

            String cityName = request.getParameter("cityName");
            ShipDAO ShipDAO = new ShipDAO();
            int shipValue = ShipDAO.getShipPriceByCityName(cityName);

            PrintWriter out = response.getWriter();
            out.println(getPriceWithDot(total + shipValue) + " VND");

            request.setAttribute("total", getPriceWithDot(total + shipValue));
        } catch (Exception ex) {
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
