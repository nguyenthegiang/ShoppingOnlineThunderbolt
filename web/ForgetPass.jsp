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
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/ChangePassword.css" rel="stylesheet" type="text/css"/>
    </head>
    <div class="container">
        <div class="row">
            <br><br>
        </div>
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-5" id="image">
                <img src="image/Other/ForgotPass.jpg" alt=""/>
            </div>
            <div class="col-md-5" id="text">
                <br><br><br><br><br><br>
                <form action="forgetPassword">
                    <h1 name="wrongEmail">${wrongEmail}</h1>
                    <h1>Please enter your Username and Email to receive Verification Code:</h1>
                    <label for="username">Username:</label> <br>
                    <input type="text" name="username" placeholder="Your Username" id="username"> <br>
                    <label for="email">Email:</label> <br>
                    <input type="text" name="email" placeholder="Your Email" id="email"> <br>
                    <input type="submit" value="OK">
                </form>
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>



</body>

</html>
