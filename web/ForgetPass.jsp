<%-- 
    Document   : ForgetPass.jsp
    Created on : Jun 17, 2021, 3:12:42 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="forgetPassword">
            <h1 name="wrongEmail">${wrongEmail}</h1>
            <h1>Please enter your Username and Email to receive Verification Code:</h1>
            <label for="username">Email</label> <br>
            <input type="text" name="username" placeholder="Your Username" id="username"> <br>
            <label for="email">Email</label> <br>
            <input type="text" name="email" placeholder="Your Email" id="email"> <br>
            <input type="submit" value="OK">
        </form>
    </body>
</html>
