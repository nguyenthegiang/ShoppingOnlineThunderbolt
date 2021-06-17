/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.*;
import DAL.*;
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
 * @author alias
 */
@WebServlet(name = "ManagerControl", urlPatterns = {"/manager"})
public class ManagerControl extends HttpServlet {

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

            ProductDAO dao = new ProductDAO();

            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1"; //At first: Load data for page 1
            }
            int index = Integer.parseInt(indexPage);

            List<Product> list = dao.pagingManagerProduct(index, a.getId()); //Pass in the account id

            int count = dao.countProductBySeller(a.getId());
            int endPage = count / 6;
            if (count % 6 != 0) {
                //If the number of Product isn't divided by 3 -> Need 1 more Page
                endPage++;
            }

            CategoryDAO CategoryDAO = new CategoryDAO();
            List<Category> listC = CategoryDAO.getAllCategory();
            request.setAttribute("listC", listC);

            UserDAO UserDAO = new UserDAO();
            List<Account> listS = UserDAO.getAllAccounts();
            request.setAttribute("listS", listS);

            request.setAttribute("end", endPage);
            request.setAttribute("count", count);
            request.setAttribute("tag", index);
            request.setAttribute("list", list);
            request.getRequestDispatcher("Manager.jsp").forward(request, response);
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
