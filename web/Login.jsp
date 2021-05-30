<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />
        <title>Computer ERA</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <!--                <img src="image/Background.jpg" alt=""/>-->
                <br><br>
            </div>
            <div class="row" >
                <div class="col-md-1"></div>
                <div class="col-md-5" style="margin: 0px; -webkit-box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3); float: right; background-color: white; opacity: 100%">
                    <img style="height: 585px; width: 375px; opacity: 100%" src="image/Login.gif" alt=""/>
                </div>
                <div class="col-md-5" id="logreg-forms" style="background-image: linear-gradient(to bottom right, #c165dd, #268bff); margin: 0px; -webkit-box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3); opacity: 100%">
                    <form  class="form-signin" action="login" method="post">
                        <br><br><br><br>
                        <h1 class="h3 mb-3 font-weight-normal" style="text-align: center; font-family: Brush Script Std"> Sign in</h1>
                        <p class="text-danger">${mess}</p>
                        <input style="margin-bottom: 8px; border-radius: 8px" name="user" value="${username}" type="text" id="inputEmail" class="form-control" placeholder="Username" required="" autofocus="">
                        <input style="margin-bottom: 8px; border-radius: 8px" name="pass" value="${password}" type="password" id="inputPassword" class="form-control" placeholder="Password" required="">

                        <div class="form-group form-check">
                            <input name="remember" value="1" type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Remember me</label>
                        </div>

                        <form action="signup" method="post" class="form-signup">
                            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign up</h1>
                            <input name="user" type="text" id="user-name" class="form-control" placeholder="User name" required="" autofocus="">
                            <input name="pass" type="password" id="user-pass" class="form-control" placeholder="Password" required autofocus="">
                            <input name="email" type="email" id="email" class="form-control" placeholder="email" required autofocus="">
                            <input name="repass" type="password" id="user-repeatpass" class="form-control" placeholder="Repeat Password" required autofocus="">
                            <div class="form-group">
                                <label class="form-check-label"><input type="checkbox" required="required"> I accept the <a href="https://policies.google.com/terms?gl=VN&hl=vi" style="display: inline">Terms &amp; Conditions</a></label>

                                <button class="btn btn-success btn-block" type="submit" style="background-image: linear-gradient(to right, #00f79c, #04f1f5); color: black; width: 200px; margin: auto; padding: 10px; border-radius: 8px"><i class="fas fa-sign-in-alt"></i> Sign in</button>
                                <hr>
                                <button class="btn btn-primary btn-block" type="button" style="background-image: linear-gradient(to right, #00f79c, #04f1f5); color: black; width: 200px; margin: auto; padding: 10px; border-radius: 8px" id="btn-signup"><i class="fas fa-user-plus"></i> Sign up</button>
                        </form>

                        <form action="signup" method="post" class="form-signup">
                            <br><br><br><br>
                            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center; font-family: Brush Script Std"> Sign up</h1>
                            <input name="user" type="text" id="user-name" class="form-control" placeholder="User name" required="" autofocus="" style="margin-bottom: 8px; border-radius: 8px">
                            <input name="pass" type="password" id="user-pass" class="form-control" placeholder="Passwords" required autofocus="" style="margin-bottom: 8px; border-radius: 8px">
                            <input name="repass" type="password" id="user-repeatpass" class="form-control" placeholder="Repeat Password" required autofocus="" style="margin-bottom: 8px; border-radius: 8px">
                            <div class="form-group">
                                <label class="form-check-label"><input type="checkbox" required="required"> I accept the <a href="https://policies.google.com/terms?gl=VN&hl=vi" style="display: inline; color: #434e65">Terms &amp; Conditions</a></label>
                            </div>

                            <button class="btn btn-primary btn-block" type="submit" style="background-image: linear-gradient(to right, #00f79c, #04f1f5); color: black; width: 200px; margin: auto; padding: 10px; border-radius: 8px"><i class="fas fa-user-plus"></i> Sign Up</button>
                            <a href="#" id="cancel_signup" style="color: #434e65"><i class="fas fa-angle-left"></i> Back</a>
                        </form>
                        <br>

                        </div>
                        
                </div>
                <div class="row">
                    <br><br>
                </div>
            </div>

            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
            <script>
                function toggleResetPswd(e) {
                    e.preventDefault();
                    $('#logreg-forms .form-signin').toggle() // display:block or none
                    $('#logreg-forms .form-reset').toggle() // display:block or none
                }

                function toggleSignUp(e) {
                    e.preventDefault();
                    $('#logreg-forms .form-signin').toggle(); // display:block or none
                    $('#logreg-forms .form-signup').toggle(); // display:block or none
                }

                $(() => {
                    // Login Register Form
                    $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
                    $('#logreg-forms #cancel_reset').click(toggleResetPswd);
                    $('#logreg-forms #btn-signup').click(toggleSignUp);
                    $('#logreg-forms #cancel_signup').click(toggleSignUp);
                })
            </script>
    </body>
</html>
