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
        <link href="css/UserProfile.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <br><br>
            </div>
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-5" id="form">
                    <br><br>
                    <%-- Display username --%>
                    <i class="fa fa-user-circle" id="profileIcon"></i>
                    <h4 style="text-align: center">${sessionScope.acc.user}</h4>

                    <!-- Message for notification -->
                    <c:if test="${requestScope.message ne null}">
                        <h5>${requestScope.message}</h5>
                    </c:if>       

                    <%-- Display user email --%>
                    <h5>Email: ${acc.email}</h5>

                    <%-- Display user role --%>
                    <h5 style="display: inline-block">Role: </h5>
                    <c:choose>
                        <c:when test="${acc.isSell==1}">
                            <input type="checkbox" checked="checked" disabled="disabled" style="display: inline-block">
                            <label>Seller</label>
                            <input type="checkbox" disabled="disabled" style="display: inline-block">
                            <label>Customer</label>
                        </c:when>           
                        <c:otherwise>
                            <input type="checkbox" disabled="disabled" style="display: inline-block">
                            <label>Seller</label>
                            <input type="checkbox" checked="checked" disabled="disabled" style="display: inline-block">
                            <label>Customer</label>
                        </c:otherwise>
                    </c:choose>

                    <%-- Display user status --%>
                    <br>
                    <h5 style="display: inline-block">Status: </h5>
                    <c:choose>
                        <c:when test="${acc.status == 1}">
                            <span class="active">Active</span>
                        </c:when> 
                        <c:when test="${acc.status == 3}">
                            <span class="login-facebook">Login with Facebook</span>
                        </c:when>
                        <c:when test="${acc.status == 4}">
                            <span class="unverified">Unverified</span>
                        </c:when>
                    </c:choose>      
                    <br><br>

                    <%-- Link to change user password --%>
                    <a class="btn btn-primary btn-block" href="confirm-change-password" id="link">Change password</a>

                    <br><br>

                    <%-- Link to view order history --%>
                    <a class="btn btn-primary btn-block" href="viewOrder?id=${acc.id}" id="link">Your orders</a>
                    <br><br>

                    <%-- Link to return to home page --%>
                    <a href="home" id="back">Back to home page</a>
                    <br><br>
                </div>
                <div class="col-md-3" id="image">
                    <img src="image/Other/UserProfile.jpg" alt=""/>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>






    </body>
</html>
