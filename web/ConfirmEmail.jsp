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
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <br><br><br>
            <div class="row" style="margin: auto; height: 600px">
                <div class="col-md-1"></div>
                <div class="col-md-5"  style="background-image: linear-gradient(to bottom right, #c165dd, #268bff);margin: 0px; -webkit-box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3)">
                    <br><br><br><br><br>
                    <h1 class="h2 mb-3 font-weight-normal" style="text-align: center; font-family: Brush Script MT">Confirm Your Email</h1>
                    <br>
                    <p style="text-align: center; color: black">Check your email for activation code</p>
                    <form action="confirm" method="post" style="margin: auto">
                        <!--<label for="code" style="font-weight: 700; margin: 15px; text-align: center">Enter the code: </label>-->
                        <input type="text" class="form-control" name ="active-code" style="border-radius: 5px; border: none; height: 40px; width: 250px; text-align: center; margin: auto" placeholder="Enter Your Code"/>
                        <hr>
                        <button class="btn btn-primary btn-block" type="submit" value="Submit" style="background-image: linear-gradient(to right, #00f79c, #04f1f5); border-color: #268bff; color: black; width: 200px; height: 40px; margin: 0 0 0 100px; padding: 2px; border-radius: 8px; border: none"><i class="fas fa-paper-plane"></i> Submit</button>
                    </form>       
                    <br><br>
                    <p><a href="login" style="color: #343a40; text-decoration: underline">Return to login page</a></p>
                </div>

                <div class="col-md-5" style="margin: 0px; -webkit-box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3); float: right; background-color: white; opacity: 100%">
                    <img src="image/ConfirmEmail.gif" style="width: 447px" alt=""/>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>
    </body>
</html>
