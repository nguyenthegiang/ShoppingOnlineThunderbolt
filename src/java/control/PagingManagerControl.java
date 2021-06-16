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

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "PagingManagerControl", urlPatterns = {"/pagingManager"})
public class PagingManagerControl extends HttpServlet {

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
            ProductDAO dao = new ProductDAO();
        
        String CategoryID = request.getParameter("CategoryID");
        if (CategoryID == null) {
            CategoryID = "0"; //Lúc đầu Load dữ liệu -> Home
        }
//        request.setAttribute("CategoryID", CategoryID);
        int CID = Integer.parseInt(CategoryID); //Ép kiểu

        //Get biến tên là index về
        //Chỉ khi nhấn số 1, 2, 3... mới get đc index về. Chứ ban đầu chưa get đc index về -> Phải xử lý xem lúc đầu như nào
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1"; //Lúc đầu: Load dữ liệu cho trang 1
        }
        //Ép
        int index = Integer.parseInt(indexPage);
        
        List<Product> list = dao.pagingByCategory(index, CID);

        PrintWriter out = response.getWriter();
        for (Product o : list) {
            //Trả về những khối div -> Ko in ra từng cái o, mà in ra cả 1 khối div
            //Copy bên home.jsp rồi sửa: sửa những cái ${} thành " + o.get... + "
            out.println("<div class=\"col-12 col-md-6 col-lg-4\">\n" +
"                                <div class=\"card\">\n" +
"                                    <img class=\"card-img-top\" src=\"image/" + o.getImageLink() + "\" alt=\"Card image cap\">\n" +
"                                    <div class=\"card-body\">\n" +
"                                        <!--Xem chi tiet san pham-->\n" +
"                                        <h4 class=\"card-title show_txt\"><a href=\"detail?ProductID=" + o.getId() + "\" title=\"View Product\">" + o.getName() + "</a></h4>\n" +
"                                        <div class=\"row\">\n" +
"                                            <div class=\"col\">\n" +
"                                                <p class=\"btn btn-danger btn-block\">" + o.getPriceWithDot() + " VND</p>\n" +
"                                            </div>\n" +
"                                            <div class=\"col\">\n" +
"                                                <!--<a href=\"addToCart?ProductID=" + o.getId() + "\" class=\"btn btn-success btn-block\">Add to cart</a>-->\n" +
"                                                <a onclick=\"addCart(" + o.getId() + ")\" class=\"btn btn-success btn-block\" style=\"color: white\">Add to cart</a>\n" + 
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
