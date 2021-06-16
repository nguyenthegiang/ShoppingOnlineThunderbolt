/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAL.ProductDAO;
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
 * @author Thuan
 */
@WebServlet(name = "CompareByAjax", urlPatterns = {"/compareByAjax"})
public class CompareByAjax extends HttpServlet {

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
            //Getting data from jsp page (from the search bar)
        String txtSearch = request.getParameter("txt");
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.searchProductByName(txtSearch);
        PrintWriter out = response.getWriter();
        
        /*Showing out 4 products with same name 
          as the user typed in the search bar*/
        for (int i = 0; i < 4; i++) {
            Product o = list.get(i);
            out.println("<a href=\"compareFinal?id="+o.getId()+"\">\n"
                    + "                            <div class=\"my-2 my-lg-0\" style=\"width:100%;\">\n"
                    + "                                <div style=\"padding:20px; padding-top:10px; padding-left:50px;margin-left:10px;\" class=\"\" id=\"\"> \n"
                    + "                                    <img style=\"float:left;margin-left:10px;\" width=\"20%;\" style=\"padding-top:10px;\" src=\"image/"+o.getImageLink()+"\"/>\n"
                    + "\n"
                    + "                                    <p style=\"  display: -webkit-box;\n"
                    + "                                       -webkit-box-orient: vertical;\n"
                    + "                                       -webkit-line-clamp: 5;  /* Number of lines displayed before it truncate */\n"
                    + "                                       overflow: hidden;\n"
                    + "                                       padding-top:10px;\n"
                    + "                                       \">"+o.getName()+"\n"
                    + "                                    </p>\n"
                    + "\n"
                    + "                                </div>\n"
                    + "                            </div><br><br><br>\n"
                    + "                        </a> ");
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
