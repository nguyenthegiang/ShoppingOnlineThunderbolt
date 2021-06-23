<%-- 
    Document   : UserInfo
    Created on : Jun 3, 2021, 10:31:13 PM
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
        <!--Inserting Bootstrap link-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    </head>
    <body>
        <div class="container" style="margin: auto">
            <div class="row">
                <br><br>
            </div>
            <div class="row" style="margin: auto">
                <div class="col-md-1"></div>
                <div class="col-md-5" style="background-image: linear-gradient(to bottom right, #c165dd, #268bff)">
                    <br><br>
                    <%-- Display username --%>
                    <i class="far fa-user-circle" style="margin: 0 12em 0 12em"></i>
                    <h4 style="text-align: center">${sessionScope.acc.user}</h4>

                    <!-- Message for notification -->
                    <c:if test="${requestScope.message ne null}">
                        <h3>${requestScope.message}</h3>
                    </c:if>       

                    <%-- Display user email --%>
                    <h2>Email: ${sessionScope.acc.email}</h2>

                    <%-- Display user role --%>
                    <c:choose>
                        <c:when test="${sessionScope.acc.isSell==1}">
                            <h2>Role: Seller</h2>
                        </c:when>           
                        <c:otherwise>
                            <h2>Role: Customer</h2>
                        </c:otherwise>
                    </c:choose>

                    <%-- Display user status --%>
                    Status:
                    <c:choose>
                        <c:when test="${sessionScope.acc.status == 1}">
                            <span class="active">Active</span>
                        </c:when> 
                        <c:when test="${sessionScope.acc.status == 3}">
                            <span class="login-facebook">Login with Facebook</span>
                        </c:when>
                        <c:when test="${sessionScope.acc.status == 4}">
                            <span class="unverified">Unverified</span>
                        </c:when>
                    </c:choose>      
                    <br><br>

                    <%-- Link to change user password --%>
                    <!--                    <div class="change-password">-->
                    <a href="confirm-change-password">Change password</a>
                    <!--                    </div>-->
                    <br><br>

                    <%-- Link to view order history --%>
                    <a href="viewOrder?id=${sessionScope.acc.id}">Your orders</a>
                    <br><br>

                    <%-- Link to return to home page --%>
                    <a href="home">Back to home page</a>
                    <br><br>
                </div>
                <div class="col-md-3" style="">
                    <img src="image/Other/UserProfile.jpg" alt=""/>

                </div>
                <div class="col-md-2"></div>
            </div>
        </div>






    </body>
</html>
