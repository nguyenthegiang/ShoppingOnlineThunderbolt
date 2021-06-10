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
        <link href="css/confirmEmail.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>
    <body>
        <div class="container">
            <br><br><br>
            <div class="row" style="margin: auto; height: 600px">
                <div class="col-md-1"></div>
                <div class="col-md-5"  style="background-image: linear-gradient(to bottom right, #c165dd, #268bff);margin: 0px; -webkit-box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3)">
                    <br><br><br><br><br><br><br><br>
                    <h1 style="text-align: center; font-family: Brush Script MT">Confirm Your Email</h1>
                    <br>
                    <p style="text-align: center; color: black">Check your email for activation code</p>
                    <form action="confirm" method="post">
                        <label for="code" style="font-weight: 700; margin: 15px; text-align: center">Enter the code: </label>
                        <input type="text" name ="active-code" style="border-radius: 5px; border: none; height: 40px; width: 250px" placeholder="  Your Code"/>
                        <br><br>
                        <button type="submit" value="Submit" style="background-image: linear-gradient(to right, #00f79c, #04f1f5); color: black; width: 200px; height: 40px; margin: 0 0 0 100px; padding: 2px; border-radius: 8px; border: none">Submit</button>
                    </form>       
                    <p><a href="login" style="color: #343a40">Return to login page</a></p>
                </div>
                
                <div class="col-md-5" style="margin: 0px; -webkit-box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3); float: right; background-color: white; opacity: 100%">
                    <img src="image/Other/ConfirmEmail.jpg" alt="" id="img" style="width: 447px"/>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>
    </body>
</html>
