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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%-- Display username --%>
        <h2>Username: ${sessionScope.acc.user}</h2>
        
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
        <c:if test="${sessionScope.acc.status == 1}">
            <span class="active">Active</span>
        </c:if>
        <c:if test="${sessionScope.acc.status != 1}">
            <span class="deactive">Locked</span>
        </c:if>
        <br><br>
        
        <%-- Link to change user password --%>
        <div class="change-password">
            <a href="confirm-change-password">Change password</a>
        </div>
        <br><br>
        
        <%-- Link to return to home page --%>
        <a href="home">Back to home page</a>
        <br><br>
        
        <%-- Link to view order history --%>
        <a href="#">Your orders</a>
        <br><br>

    </body>
</html>
