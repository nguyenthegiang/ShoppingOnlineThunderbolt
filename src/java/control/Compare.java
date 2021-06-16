/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.CartDAO;
import DAL.CategoryDAO;
import DAL.InforDAO;
import DAL.ProductDAO;
import DAL.UserDAO;
import entity.Account;
import entity.Category;
import entity.Information;
import entity.Product;
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
@WebServlet(name = "Compare", urlPatterns = {"/compare"})
public class Compare extends HttpServlet {

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

        //Get data from JSP
        String id = request.getParameter("id");

        ProductDAO ProductDAO = new ProductDAO();
        InforDAO InforDAO = new InforDAO();

        Product hot = ProductDAO.getHotProduct(); //Get First Product
        Product favor = ProductDAO.getFavoriteProduct(); //Get Last Product
        Information infor = InforDAO.getInfor(); //Get Information
        Product product = ProductDAO.getProductByID(id); //Get the selected Product infor
        List products = ProductDAO.getAllProduct();
        CategoryDAO CategoryDAO = new CategoryDAO();
        List<Category> listC = CategoryDAO.getAllCategory(); //Get List Category

        //Seding data to jsp page
        request.setAttribute("product", product);
        request.setAttribute("products", products);
        request.setAttribute("hot", hot);
        request.setAttribute("favor", favor);
        request.setAttribute("infor", infor);
        request.setAttribute("allCategory", listC);

        
        /*Sending first product's detail and ask user to 
        choose the other product to compare with
        */
        request.getRequestDispatcher("Compare.jsp").forward(request, response);

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
