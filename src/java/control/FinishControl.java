/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.*;
import entity.Account;
import entity.Cart;
import entity.Order;
import entity.OrderDetail;
import entity.ShipInfo;
import entity.UserAddress;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "FinishControl", urlPatterns = {"/finish"})
public class FinishControl extends HttpServlet {

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

            HttpSession session = request.getSession(); //Use session to call id
            Account a = (Account) session.getAttribute("acc"); //Call to account -> Must cast to Object

            // get all necessary dao
            CartDAO cartDAO = new CartDAO();
            OrderDAO orderDao = new OrderDAO();
            OrderDetailDAO odDao = new OrderDetailDAO();
            ShipDAO shipDAO = new ShipDAO();
            ShipInfoDAO shipInfoDAO = new ShipInfoDAO();
            UserAddressDAO userAddDAO = new UserAddressDAO();

            // get list of product in cart
            List<Cart> listCart = cartDAO.getCart(a.getId()); //Truyền vào id của account

            // get shipping info of the order
            boolean shipFlag = true;
            shipFlag = Boolean.valueOf(request.getParameter("btnOtherAddress"));
            String cityName = "";
            ShipInfo shipInfo = new ShipInfo();

            // do ship to an other address
            if (shipFlag) {

                // get shipping info from form
                String customerName = request.getParameter("lastName")
                        + " "
                        + request.getParameter("firstName");
                String phoneNum = request.getParameter("phone");
                String shippingAddress = request.getParameter("address");
                int shippingCity = Integer.valueOf(request.getParameter("city"));

                // get city name of the city address from form 
                cityName = shipDAO.getCityByCId(shippingCity).getCityName();

                // set shipping info to shipping address from form
                shipInfo.setCustomerName(customerName);
                shipInfo.setPhoneNum(phoneNum);
                shipInfo.setShippingAddress(shippingAddress);
                shipInfo.setShipCityId(shippingCity);
            }

            // ship to default address 
            if (!shipFlag) {

                // get default address
                UserAddress defaulShipAddress
                        = userAddDAO.getAddressByUserId(a.getId());

                // get default address city
                cityName = shipDAO.getCityByCId(
                        defaulShipAddress.getShipCityId()).getCityName();

                // set shipping info to default address
                shipInfo.setCustomerName(defaulShipAddress.getShipName());
                shipInfo.setPhoneNum(defaulShipAddress.getPhoneNum());
                shipInfo.setShipCityId(defaulShipAddress.getShipCityId());
                shipInfo.setShippingAddress(defaulShipAddress.getShipAddress());

            }
            shipInfo.setNote(request.getParameter("note"));

            // calculate total price of the order
            double total = 0;
            List<OrderDetail> lsProductInOrder = new ArrayList<>();
            for (Cart cart : listCart) {
                total += cart.getP().getPrice() * cart.getAmount();
            }

            int shipValue = shipDAO.getShipPriceByCityName(cityName);
            System.out.println(shipValue);
            total += Double.valueOf(shipValue);

            // add order to database
            Order userOrder = new Order();
            userOrder.setUserId(a.getId());
            userOrder.setTotalPrice(total);
            userOrder.setNote("");
            userOrder.setStatus("Waiting for Confirmation");

            orderDao.add(
                    userOrder.getUserId(),
                    userOrder.getTotalPrice(),
                    userOrder.getNote(),
                    1);

            // get the order id
            int newOrderId = orderDao.getNewestOrderID();

            // add list of product of the order to the database
            for (Cart cart : listCart) {
                OrderDetail od = new OrderDetail();
                od.setOrderID(newOrderId);
                od.setProductID(cart.getP().getId());
                od.setProductName(cart.getP().getName());
                od.setProductPrice(cart.getP().getPrice());
                lsProductInOrder.add(od);
            }
            odDao.addManyOrderDetails(newOrderId, lsProductInOrder);

            // add ship info to the database
            shipInfo.setOrderId(newOrderId);
            shipInfoDAO.addShipInfo(shipInfo);

            // send order information to the buyer
            // remove the cart of the order
            cartDAO.deleteCart(a.getId());

            response.sendRedirect("Finish.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String createOrderInfo(Order order) {
        return  "Order Id: "+
                "\nNote: "
                + order.getNote() +
                "\nStatus: " +
                order.getStatus();
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
