<%-- 
    Document   : AddAddress
    Created on : Jun 24, 2021, 3:15:32 PM
    Author     : TRANTATDAT
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />
        <title>Computer ERA</title>
        <link href="css/addAddress.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <!--important link source from "https://bootsnipp.com/snippets/A36DP"-->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <title>Fill in an address</title>
    </head>
    <body>
        <!--        <h1>Add Shipping Information To Your Account</h1>
                <div class="container">
                    <form class="card-body" action="add-address" method="post">
        
                        Grid row
                        <div class="row">
        
                            Grid column
                            <div class="col-md-6 mb-2">
        
                                firstName
                                <div class="md-form ">
                                    <input type="text" id="firstName" name="firstName" class="form-control" required>
                                    <label for="firstName" class="">First name</label>
                                </div>
        
                            </div>
                            Grid column
        
                            Grid column
                            <div class="col-md-6 mb-2">
        
                                lastName
                                <div class="md-form">
                                    <input type="text" id="lastName" name="lastName" class="form-control" required>
                                    <label for="lastName" class="">Last name</label>
                                </div>
        
                            </div>
                            Grid column
        
                        </div>
                        Grid row
                        <br>                   
        
                        <div class="md-form mb-5">
                            <label for="phone" class="">Mobile:</label>
                            <input type="text" id="phone" name="phone" class="form-control" placeholder="Your Phone Number" required>
                        </div>
        
                        <div class="md-form mb-5">
                            <label for="city">City</label>           
                            <select class="form-select" aria-label="Default select example" name="city" id="city">
        <c:forEach items="${requestScope.listShip}" var="o">
            <option value="${o.id}">${o.cityName}</option>
        </c:forEach>
    </select>
</div>

address
<div class="md-form mb-5">
    <label for="address" class="">Address</label>
    <input type="text" id="address" name="address" class="form-control" placeholder="1234 Main St" required>                    
</div>
<hr>               
<button class="btn btn-info btn-lg btn-block" type="submit">Add Address</button>
</form>
</div>-->


        <div class="container">
            <div class="row">
                <br>
            </div>
            <div class="row" class="get-in-touch">
                <!--bootstrap-->
                <section >

                    <form class="contact-form row" action="add-address" method="post">
                        <h2>Add Shipping Information To Your Account</h2>
                        <br>
                        <div class="container">
                            <div class="row">
                                <div class="form-field col-lg-1"></div>
                                <div class="form-field col-lg-5">

                                    <input type="text" id="firstname" class="input-text js-input" name="firstName"  required>
                                    <label class="label" for="name">First name</label>
                                </div>
                                <div class="form-field col-lg-5">
                                    <input type="text" id="lastName" name="lastName" class="input-text js-input" required>
                                    <label class="label" for="email">Last name</label>
                                </div>
                                <div class="form-field col-lg-1"></div>
                                <div class="form-field col-lg-1"></div>
                                <div class="form-field col-lg-7">
                                    <input id="phone" name="phone" class="input-text js-input" type="text" required>
                                    <label class="label" for="phone">Contact Number</label>
                                </div>
                                <div class="form-field col-lg-4"></div>
                                <div class="form-field col-lg-1"></div>
                                <div class="form-field col-lg-7">
                                    <label for="city" id="cityLabel">City</label>           
                                    <select class="form-select" aria-label="Default select example" name="city" id="city">
                                        <c:forEach items="${requestScope.listShip}" var="o">
                                            <option value="${o.id}" id="option">${o.cityName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-field col-lg-4"></div>
                                <div class="form-field col-lg-1"></div>
                                <div class="form-field col-lg-7">
                                    <input type="text" id="address" name="address" class="input-text js-input" required>     
                                    <label class="label" for="message">Address</label>
                                </div>
                                <div class="col-lg-12">
                                    <input class="submit-btn" type="submit" value="Submit" id="submit">
                                </div>
                            </div>
                        </div>


                    </form>
                </section>
            </div>
        </div>
    </body>
</html>
