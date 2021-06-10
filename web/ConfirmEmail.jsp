<%-- 
    Document   : newjsp
    Created on : May 18, 2021, 5:29:59 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Email Confirmation</title>
    </head>
    <body>
        <h1>Confirm Your Email</h1>
        <p>Check your email for activation code</p>
        <form action="confirm" method="post">
            <label for="code">Enter the code: </label>
            <input type="text" name ="active-code" id="code" placeholder="Your Code"/>
            <button type="submit" value="Submit">Submit</button>
        </form>       
        <p><a href="login">Return to login page</a></p>
    </body>
</html>
