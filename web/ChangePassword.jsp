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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Change Your Password</h1>
        <!-- Message for notification -->
        <c:if test="${requestScope.message ne null}">
            <h3>${requestScope.message}</h3>
        </c:if>       
        <br><br>
        <!-- Input code form -->
        <c:if test="${requestScope.compare eq null}"><h3>We have sent a code to your email at ${sessionScope.acc.email}. 
                Please enter your code down below to change your password </h3>
            <form action="confirm-change-password" method="POST">
                <label for="code">Enter code:</label>
                <input type="text" id="code" name="code" placeholder="Your Code">
                <br><br>
                <button type="submit">Confirm</button>
            </form>
        </c:if>
        
        <!-- Password change form -->
        <c:if test="${requestScope.compare ne null}">
            <form action="change-password" method="POST">
                <label for="pass">Enter old password:</label>
                <input type="text" id="pass" name="pass" placeholder="Old Password">
                <br><br>
                <label for="pass">Enter new password:</label>
                <input type="text" id="new-pass" name="new-pass" placeholder="New Password">
                <br><br>
                <label for="pass">Re-enter password:</label>
                <input type="text" id="repeat-new-pass" name="repeat-new-pass" placeholder="New Password">
                <br><br>
                <button type="submit">Confirm</button>
            </form>
        </c:if>

    </body>
</html>
