
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
        //Call to DAOs
        ProductDAO ProductDAO = new ProductDAO();
        InforDAO InforDAO = new InforDAO();
        CategoryDAO CategoryDAO = new CategoryDAO();
        UserDAO UserDAO = new UserDAO();
        
        List<Category> listC = CategoryDAO.getAllCategory(); //Get List Category
        Product hot = ProductDAO.getHotProduct(); //Get First Product
        Product favor = ProductDAO.getFavoriteProduct(); //Get Last Product
        Information infor = InforDAO.getInfor(); //Get Information
        
        //Paging By CategoryID
        String CategoryID = request.getParameter("CategoryID");
        if (CategoryID == null) { //On Load: User hasn't choosen Category
            CategoryID = "0";
        }
        //Set Category ID back on JSP
        request.setAttribute("CategoryID", CategoryID);
        
        int CID = Integer.parseInt(CategoryID);

        //Get Page number from JSP
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            //On load: Page 1
            indexPage = "1";
        }

        int index = Integer.parseInt(indexPage);

        //Count number of Product According to the Category -> Number of Pages
        int count = ProductDAO.countProductByCategory(CID);
        int endPage = count / 6;
        if (count % 6 != 0) {
            //If the number of Product isn't divided by 3 -> Need 1 more Page
            endPage++;
        }
        
        //List of Product to Display after Paging by Category ID
        List<Product> list = ProductDAO.pagingByCategory(index, CID);
        
        //Set Data to JSP
        request.setAttribute("allCategory", listC);
        request.setAttribute("hot", hot);
        request.setAttribute("favor", favor);
        request.setAttribute("infor", infor);
        
        request.setAttribute("listP", list); //List Product
        request.setAttribute("end", endPage);
        request.setAttribute("tag", index); //Page number
        request.setAttribute("count", count); 
        request.setAttribute("CateID", CID);
        request.setAttribute("CateName", CategoryDAO.getCateNameByID(CID));
        
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
