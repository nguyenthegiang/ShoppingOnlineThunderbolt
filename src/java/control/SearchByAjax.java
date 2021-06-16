/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.*;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author trinh
 */
@WebServlet(name = "SearchByAjax", urlPatterns = {"/searchAjax"})
public class SearchByAjax extends HttpServlet {

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
            request.setCharacterEncoding("UTF-8");
        String txtSearch = request.getParameter("txt");
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.searchProductByName(txtSearch);
        PrintWriter out = response.getWriter();
        for (Product o : list) {
            out.println("<div class=\"col-12 col-md-6 col-lg-4\">\n" +
"                                <div class=\"card\">\n" +
"                                    <a href=\"detail?ProductID=" + o.getId() + "\" title=\"View Product\"><img class=\"card-img-top\" src=\"image/" + o.getImageLink() + "\" alt=\"Card image cap\"></a>\n" +
"                                    <div class=\"card-body\">\n" +
"                                        <!--Xem chi tiet san pham-->\n" +
"                                        <h4 class=\"card-title show_txt\"><a href=\"detail?ProductID=" + o.getId() + "\" title=\"View Product\">" + o.getName() + "</a></h4>\n" +
"                                        <div class=\"row\">\n" +
"                                            <div class=\"col\">\n" +
"                                                <a onclick=\"detail(" + o.getId() + ")\" ><p class=\"btn btn-warning btn-block\" id=\"price\">"+o.getPriceWithDot()+ " VND</p></a>\n" + 
"                                            </div>\n" +
"                                            <div class=\"col\">\n" +
"                                                <a onclick=\"addCart(" + o.getId() + ")\" class=\"btn btn-info btn-block\" style=\"color: white\">Add to cart</a>\n" + 
"                                            </div>\n" +
"                                        </div>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                            </div>");
        }

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
