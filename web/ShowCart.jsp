
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />
        <title>Computer ERA</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/ShowCart.css" rel="stylesheet" type="text/css"/><link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>
            .container table img{
                height: 100px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <h1>Shopping Cart</h1>
            <div class="container">
                <a type="button" href="deleteCart" class="btn btn-danger" style="float: right; margin: 10px"><i class="fa fa-trash"></i>Delete Cart</a>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Image</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Amount</th>
                            <th scope="col"><!--Action--></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="o">
                        <tr  class="spacer">
                            <td><img src="image/${o.p.imageLink}" id="pImage"></td>
                            <td>${o.p.name}</td>
                            <td>${o.p.priceWithDot} VND</td>
                            <td>${o.amount}</td>
                            <td><a href="deleteProductInCart?ProductID=${o.p.id}"><i class="fa fa-times-circle" style="color: #dddddd; font-size: x-large; margin: "></i></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="container">
                <div class="row">
                    <div class="col-md-8">
                        <br><br><br><br><br>
                        <p><a href="home" id="back"><i class="fa fa-angle-left"></i>Continue shopping</a></p>
                    </div>
                    <div class="col-md-4">
                        <h5 id="sub">Subtotal:</h5>  
                        <h3 style="float: right;font-family: Helvetica"> ${total} VNĐ</h3>
                        <br>
                        <hr>
                        <h5 style="float: right; font-family: Brush Script MT; font-style: italic; font-size: 2.2em">Thanks for shopping with us</h5>
                        <br><br>
                        <hr>
                        <a type="button" href="buy" class="btn btn-info btn-lg" style="float: right; padding: 10px 5em 10px 5em; margin: 0 0 50px 0">Check out</a>
                    </div>
                </div>
            </div>

            <!-- <div class="btn-group" role="group" aria-label="Basic outlined example">
                 <button type="button" class="btn btn-warning">Total</button>
                 <button type="button" class="btn btn-outline-primary">${total} VND</button>
             </div>
            
             <div>&emsp;</div>
             <div><a type="button" href="buy" class="btn btn-info btn-lg">Pay</a></div>
            
             <a type="button" href="deleteCart" class="btn btn-danger" style="float: right">Delete Cart</a>
             <div>&emsp;</div><div>&emsp;</div><div>&emsp;</div>
             <p><a href="home" id="back"><i class="fa fa-angle-left"></i>Continue shopping</a></p>
         </div>-->
    </body>
</html>
