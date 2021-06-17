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
        <script>

            $(document).ready(function ($) {
                FB.getLoginStatus(function (response) {
                    statusChangeCallback(response);
                    console.log(response);
                });
            });
            function statusChangeCallback(response) {  // Called with the results from FB.getLoginStatus().
                console.log('statusChangeCallback');
                let userId = response.authResponse.userID;
                console.log(response);                   // The current login status of the person.
                if (response.status === 'connected') {   // Logged into your webpage and Facebook.
                    getUserInfo(userId);
//                    testAPI();
                } else {                                 // Not logged into your webpage or we are unable to tell.
                    //                    document.getElementById('status').innerHTML = 'Please log ' +
                    //                            'into this webpage.';
                }
            }

            function getUserInfo(userId) {
                // body...
                let id = userId;
                FB.api(
                        '/' + userId + '/?fields=id,name,email',
                        'GET',
                        {},
                        function (response) {
                            // Insert your code here
                            // console.log(response);
                            let name = response.name;
                            let email = response.email;
                            let id1 = id;
                            testAPI(name, id1, email);
                        }
                );
            }


            function checkLoginState() {               // Called when a person is finished with the Login Button.
                FB.getLoginStatus(function (response) {   // See the onlogin handler
                    statusChangeCallback(response);
                });
            }


            window.fbAsyncInit = function () {
                FB.init({
                    appId: '1145614429291918',
                    cookie: true, // Enable cookies to allow the server to access the session.
                    xfbml: true, // Parse social plugins on this webpage.
                    version: 'v10.0'           // Use this Graph API version for this call.
                });


                FB.getLoginStatus(function (response) {   // Called after the JS SDK has been initialized.
                    statusChangeCallback(response);        // Returns the login status.
                });
            };

            function testAPI(name, id, mail) {                      // Testing Graph API after login.  See statusChangeCallback() for when this call is made.
                console.log('Welcome!  Fetching your information.... ');

                let email = mail;
                FB.api('/me', function (response) {
                    submitInfo(email, name);
                });



            }
            function submitInfo(mail, name) {
                document.getElementById('user').value = name;
                document.getElementById('email').value = mail;
                document.getElementById('loginFB').value = "true";
                document.getElementById('login-form').submit();
            }


        </script>



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
                    <form class="form-signin" id="login-form" action="login" method="post">
                        <br><br><br><br>
                        <h1 class="h3 mb-3 font-weight-normal" style="text-align: center; font-family: Brush Script Std"> Sign in</h1>
                        <p class="text-danger">${mess}</p>
                        <input style="margin-bottom: 8px; border-radius: 8px" name="user" value="${username}" type="text" id="user" class="form-control" placeholder="Username" required="" autofocus="">
                        <input style="margin-bottom: 8px; border-radius: 8px" name="pass" value="${password}" type="password" id="password" class="form-control" placeholder="Password" required="">

                        <div class="form-group form-check">
                            <input name="remember" value="1" type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Remember me</label>
                        </div>
                        <!-- For fb login -->
                        <input type="text" id="email" name="email" style="display: none;">
                        <input type="text" id="loginFB" name="loginFB" value="false" style="display: none;">

                        <div class="form-group">                           
                            <button class="btn btn-success btn-block" id="btnSubmit" type="submit" style="background-image: linear-gradient(to right, #00f79c, #04f1f5); color: black; width: 200px; margin: auto; padding: 10px; border-radius: 8px"><i class="fas fa-sign-in-alt"></i> Sign in</button>
                            <a href="ForgetPass.jsp">Forget Password</a>
                            <hr>
                            <button class="btn btn-primary btn-block" type="button" style="background-image: linear-gradient(to right, #00f79c, #04f1f5); color: black; width: 200px; margin: auto; padding: 10px; border-radius: 8px" id="btn-signup"><i class="fas fa-user-plus"></i> Sign up</button>
                        </div>
                    </form>
                    <form action="signup" id="sign-up-form" method="post" class="form-signup">
                        <br><br><br><br>
                        <h1 class="h3 mb-3 font-weight-normal" style="text-align: center; font-family: Brush Script Std"> Sign up</h1>
                        <input name="user" id="user" type="text" id="user-name" class="form-control" placeholder="User name" required="" autofocus="" style="margin-bottom: 8px; border-radius: 8px">
                        <input name="email" id="email" type="email" id="email" class="form-control" placeholder="Email" required autofocus="" style="margin-bottom: 8px; border-radius: 8px">
                        <input name="pass" id="pass" type="password" id="user-pass" class="form-control" placeholder="Passwords" required autofocus="" style="margin-bottom: 8px; border-radius: 8px">
                        <input type="text" id="loginFB" name="loginFB" value="false" style="display: none;">
                        <input name="repass" id="repass" type="password" id="user-repeatpass" class="form-control" placeholder="Repeat Password" required autofocus="" style="margin-bottom: 8px; border-radius: 8px">
                        <div class="form-group">
                            <label class="form-check-label"><input id="check" type="checkbox" required="required"> I accept the <a href="https://policies.google.com/terms?gl=VN&hl=vi" style="display: inline; color: #434e65">Terms &amp; Conditions</a></label>
                        </div>

                        <button class="btn btn-primary btn-block" type="submit" style="background-image: linear-gradient(to right, #00f79c, #04f1f5); color: black; width: 200px; margin: auto; padding: 10px; border-radius: 8px"><i class="fas fa-user-plus"></i> Sign Up</button>
                        <a href="#" id="cancel_signup" style="color: #434e65"><i class="fas fa-angle-left"></i> Back</a>
                    </form>
                    <br>
                    <fb:login-button style="margin-left:45%;" scope="public_profile,email" onlogin="checkLoginState();">
                    </fb:login-button>
                    <div id="status">
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
                <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js"></script>

                </body>
                </html>
