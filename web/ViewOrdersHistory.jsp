<%-- 
    Document   : ViewOrdersHistory
    Created on : Jun 17, 2021, 2:56:02 PM
    Author     : Thuan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Computer ERA</title>
        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />
    </head>
    <body>
        <h1>Hello ${sessionScope.acc.user}</h1>
        <h2>View your order history!</h2>
        <div>
            <c:forEach items="${orders}" var="x">
                <a href="viewOrderDetail?id=${x.id}">${x}</a>
                <hr>
            </c:forEach>
        <div>

    </body>
</html>
