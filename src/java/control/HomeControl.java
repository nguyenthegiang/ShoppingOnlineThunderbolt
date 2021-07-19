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

@WebServlet(name = "HomeControl", urlPatterns = {"/home"})
public class HomeControl extends HttpServlet {

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
            HttpSession session = request.getSession();
            if (session.getAttribute("acc") == null) {
                ProductDAO ProductDAO = new ProductDAO();
                InforDAO InforDAO = new InforDAO();
                CategoryDAO CategoryDAO = new CategoryDAO();
                BlogDAO BlogDAO = new BlogDAO();

                List<Category> listC = CategoryDAO.getAllCategory(); //Get List Category
                Information infor = InforDAO.getInfor(); //Get Information
                Blog news = BlogDAO.getHotBlog();//getHotBlog
                List<Product> list4ProductSale = ProductDAO.getTop4SalePercent();

                request.setAttribute("allCategory", listC);
                request.setAttribute("news", news);
                request.setAttribute("infor", infor);
                request.setAttribute("list4Sale", list4ProductSale);

                request.getRequestDispatcher("Home.jsp").forward(request, response);
            } else {
                ProductDAO ProductDAO = new ProductDAO();
                InforDAO InforDAO = new InforDAO();
                CategoryDAO CategoryDAO = new CategoryDAO();
                NotificationDAO notiDAO = new NotificationDAO();
                BlogDAO BlogDAO = new BlogDAO();
                Account user = (Account) session.getAttribute("acc");

                int userId = user.getId();
                int unreadNotifications = notiDAO.countUnreadNotifications(userId);
                List<Category> listC = CategoryDAO.getAllCategory(); //Get List Category
                Information infor = InforDAO.getInfor(); //Get Information
                List<Notification> notis = notiDAO.getTop5NotificationsByUserID(userId);
                int notiss = notiDAO.countNotifications(userId);
                Blog news = BlogDAO.getHotBlog();//getHotBlog
                List<Product> list4ProductSale = ProductDAO.getTop4SalePercent();

                request.setAttribute("allCategory", listC);
                request.setAttribute("news", news);
                request.setAttribute("infor", infor);
                request.setAttribute("list4Sale", list4ProductSale);

                request.setAttribute("unread", unreadNotifications);
                request.setAttribute("notis", notis);
                request.setAttribute("numberOfNotifications", notiss);
                request.setAttribute("acc",user);

                request.getRequestDispatcher("Home.jsp").forward(request, response);
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
