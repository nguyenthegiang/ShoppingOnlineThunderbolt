/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.CategoryDAO;
import DAL.InforDAO;
import DAL.ProductDAO;
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
@WebServlet(name = "CompareFinal", urlPatterns = {"/compareFinal"})
public class CompareFinal extends HttpServlet {

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

            //Getting id of the second product to be compared
            String id1 = request.getParameter("id");
            CategoryDAO CategoryDAO = new CategoryDAO();
            //Getting id of the first product (which has already been stored in session)
            String id2 = String.valueOf(session.getAttribute("productSession"));
            List<Category> listC = CategoryDAO.getAllCategory(); //Get List Category

            ProductDAO ProductDAO = new ProductDAO();
            InforDAO InforDAO = new InforDAO();

            Product hot = ProductDAO.getHotProduct(); //Get First Product
            Product favor = ProductDAO.getFavoriteProduct(); //Get Last Product
            Information infor = InforDAO.getInfor(); //Get Information
            Product product1 = ProductDAO.getProductByID(id1); //Get the selected Product infor
            Product product2 = ProductDAO.getProductByID(id2); //Get the selected Product infor

            //Seding data to jsp page
            request.setAttribute("product1", product1);
            request.setAttribute("product2", product2);
            request.setAttribute("hot", hot);
            request.setAttribute("favor", favor);
            request.setAttribute("infor", infor);
            request.setAttribute("allCategory", listC);

            /*
        Sending data to final comparing page with 2 chosen product's details
             */
            request.getRequestDispatcher("CompareFinal.jsp").forward(request, response);
        } catch (Exception e) {
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
