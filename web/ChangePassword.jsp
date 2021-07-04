<%-- 
    Document   : ConfirmChangedPassword
    Created on : Jun 3, 2021, 11:05:22 PM
    Author     : TRANTATDAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Computer ERA</title>
        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/ChangePassword.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <br><br>
            </div>
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-5" id="image">
                    <img src="image/Other/ChangePass.jpg" alt=""/>
                </div>
                <div class="col-md-5" id="text">
                    <br><br><br><br><br><br>
                    <h1>Change Your Password</h1>
                    
                    <c:if test="${requestScope.message ne null}">
                        <p>${requestScope.message}</p>
                    </c:if>       
                    <br><br>
                    
<!--Remove Send Code function when Change Password-->
<%--
                    <c:if test="${requestScope.compare eq null}">
                        <p id="noti"><i class="fa fa-bell"></i>We have sent a code to your email at ${sessionScope.acc.email}<br> 
                            Please enter your code down below to change your password </p>
                        <form action="confirm-change-password" method="POST">
                            <input type="text" id="code" name="code" placeholder="Your Code" class="form-control">
                            <hr>
                            <button type="submit"><i class="fa fa-check-circle"></i>Confirm</button>
                        </form>
                    </c:if>
--%>

                    <!-- Password change form -->
                    <%--<c:if test="${requestScope.compare ne null}">--%>
                        <form action="change-password" method="POST">
                            <label for="pass">Enter old password:</label>
                            <input type="password" id="pass" name="pass" placeholder="Old Password" class="form-control">
                            <br><br>
                            <label for="pass">Enter new password:</label>
                            <input type="password" id="new-pass" name="new-pass" placeholder="New Password">
                            <br><br>
                            <label for="pass">Re-enter password:</label>
                            <input type="password" id="repeat-new-pass" name="repeat-new-pass" placeholder="New Password">
                            <hr>
                            <button class="btn btn-primary btn-block" type="submit"><i class="fa fa-check-circle"></i>Confirm</button>
                        </form>
                    <%--</c:if>--%>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>



    </body>
</html>
