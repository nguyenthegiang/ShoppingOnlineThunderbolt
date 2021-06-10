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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginControl", urlPatterns = {"/login"})
public class LoginControl extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");
//        //Lấy dữ liệu từ jsp
//        String username = request.getParameter("user");
//        String password = request.getParameter("pass");
//
//        //Kết nối vs DB
//        UserDAO dao = new UserDAO();
//        Account a = dao.login(username, password);
//        //Kiểm tra
//        if (a == null) {
//            //login fail -> Đẩy về trang Login.jsp (nhập lại)
//            //Message thông báo Login sai: thay đổi giá trị của biến mess
//            request.setAttribute("mess", "Login fail");
//            //ko thì quay trở lại trang login.jsp
//            //Yêu cầu người dùng Login lại
//            request.getRequestDispatcher("Login.jsp").forward(request, response);
//        } else {
//            //Đẩy a lên Session
//            HttpSession session = request.getSession(); //Câu lệnh khởi tạo Session
//            session.setAttribute("acc", a); //Đẩy a lên trên Session, có tên biến là "acc" -> acc chính là a
//
//            //login thành công -> Đẩy về trang HomeControl.java
//            //Vì trang HomeControl lấy dữ liệu từ ProductDAO, và đẩy về trang jsp; nếu chuyển về trang jsp thì nó sẽ ko có dữ liệu
//            response.sendRedirect("home"); //đg dẫn là home
//        }
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
        //Phần doGet của Servlet Login sẽ có nhiệm vụ lấy thông tin của User và Pass từ Cookie và đẩy lên trang Login.jsp để hiển thị

        //Bước 1: get user, pass from cookie
        //Cookie là 1 Mảng, gồm nhiều thành phần, bao gồm cả thông tin của Browser để cho mình biết là đang Login ở đâu
        Cookie arr[] = request.getCookies();
        if (arr != null) {
            //Xử lý trg hợp: Khi mình đóng trình duyệt mà vào Login từ đường Link -> Cookie rỗng -> Vòng for bên dưới bị lỗi
            //Còn nếu vào từ /home thì dù vẫn chưa có userC và passC nhưng Cookie vẫn ko rỗng (vì có Cookie của Browser) nên cx ko lỗi
            for (Cookie o : arr) {
                if (o.getName().equals("userC")) { //Tìm đến thằng Cookie lưu về Username
                    request.setAttribute("username", o.getValue()); //Đẩy Value của nó lên ô Username ở trang Login.jsp
                }
                if (o.getName().equals("passC")) {
                    request.setAttribute("password", o.getValue());
                }
            }
        }

        //Bước 2: set user, pass to Login form
        request.getRequestDispatcher("Login.jsp").forward(request, response);
        //Chuyển dữ liệu lên trang Login.jsp để mở giao diện
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserDAO dao = new UserDAO();

        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        String loginFb = request.getParameter("loginFB");

        if (loginFb != null || !loginFb.trim().equals("")) {
            String email = request.getParameter("email");
            System.out.println(username);
            System.out.println(email);
            Account a = dao.getAccountByEmail(email);
            // not first time login with fb
            if (a != null) {
                session.setAttribute("acc", a);
                response.sendRedirect("home");
            }

            // first time login with fb
            if (a == null) {
                request.setAttribute("loginFB", "true");
                request.setAttribute("user", username);
                request.setAttribute("email", email);
                request.getRequestDispatcher("signup").forward(request, response);
            }
        } else {
//Code phần remember me
            String remember = request.getParameter("remember");
//        Lấy về username và password, nếu tồn tại thì đẩy về home
           
            Account a = dao.login(username, password);
            if (a == null) {
                request.setAttribute("mess", "Wrong username or password");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                
                session.setAttribute("acc", a);

//            Bắt đầu code ở đây
                //Lưu Account lên trên Cookie
                Cookie u = new Cookie("userC", username);
                Cookie p = new Cookie("passC", password);

                //Code Remember Me
                u.setMaxAge(60 * 60);
                if (remember != null) {
                    p.setMaxAge(60 * 60); //Nếu ng dùng Click Remember Me -> Lưu Password
                } else {
                    p.setMaxAge(0); //Ko -> Lưu Username thôi
                }
                //Xét thời gian tồn tại cho Cookie

                //Lưu cookie lên trên trình duyệt: trg hợp này là Chrome
                response.addCookie(u);
                response.addCookie(p);

                if (a.getIsAdmin() == 1 || a.getIsSell() == 1) {
                    //Nếu là Admin thì chuyển về trang DashBoard
                    response.sendRedirect("dashBoard");
                } else {
                    response.sendRedirect("home");
                }
            }
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
