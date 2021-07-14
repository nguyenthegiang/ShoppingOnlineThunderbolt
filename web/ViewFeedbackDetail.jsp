<%-- 
    Document   : ViewFeedbackDetail
    Created on : Jul 14, 2021, 8:50:45 PM
    Author     : TRANTATDAT
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />

        <!--Table Styling-->
        <link rel="stylesheet" href="css/OrderTableStyle.css" type="text/css"/>

        <!--Custom Styling-->
        <link rel="stylesheet" href="css/ViewAllFeedbackStyle.css" type="text/css"/>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <title>Computer ERA</title>
        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />
    </head>
    <body>
        <a class="nav-link" href="logout" style="position: fixed; right: 10px;">LogOut</a>

        <div class="container">
            <div class="row">
                <div class="col-3">
                    <nav class="navbar navbar-expand-lg navbar-light bg-light flex-column">
                        <a class="navbar-brand" href="dashBoard"><img src="image/MenuLogo.png" width="200px"></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link" href="productList">Product List</a>
                            </li>
                            <c:if test="${sessionScope.acc == null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="login">Login</a>
                                </li>
                            </c:if>
                            <c:if test = "${sessionScope.acc != null}">
                                <li class="nav-item">
                                    <a class="nav-link text-info" href="login">Hello ${sessionScope.acc.user}</a>
                                </li>
                                <c:if test="${sessionScope.acc.isSell == 1}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="manager">Manager Product</a>
                                    </li> 
                                </c:if>
                                <c:if test="${sessionScope.acc.isAdmin == 1}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="accountManager">Manager Account</a>
                                    </li> 
                                </c:if>
                            </c:if>

                            <li class="nav-item">&nbsp;
                            </li> 
                        </ul>
                    </nav>
                </div>
                <div class="col-6">
                    <section class="jumbotron text-center" style="background-color: white;">
                        <div class="container">
                            <h1 class="jumbotron-heading"><img src="image/MainLogo.png" alt="Main Logo" width="60%"/></h1>
                            <p class="lead text-muted mb-0">Điện thoại, Tablet, Laptop, Phụ kiện chính hãng giá tốt...</p>
                        </div>
                    </section>
                </div>
            </div>

            <hr>
            <div class="row">

                <div class="col-md-6">
                    <img src="image/${requestScope.feedback.product.imageLink}" style="width: 100px">
                    <div class="card-body p-5">
                        <h3 class="title mb-3">${requestScope.feedback.product.name}</h3>

                        <p class="price-detail-wrap"> 
                            <span class="price h3 text-warning"> 
                                <span class="currency">VND </span><span class="num">${requestScope.feedback.product.priceWithDot}</span>
                            </span> 
                        </p> 
                        
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card-body">
                        <h5 class="card-title">Feedback Detail:</h5>
                        <table class="table table-borderless">
                            <tr style="padding:2px; ">
                                <td>User:</td>
                                <td>${requestScope.feedback.user.user}</td>
                            </tr>
                            <tr style="padding:2px; ">
                                <td>Order ID:</td>
                                <td>${requestScope.feedback.orderId}</td>
                            </tr>
                            <tr style="padding:2px; ">
                                <td>Star:</td>
                                <td>
                                    <c:forEach begin="1" end="${requestScope.feedback.star}">
                                        <span>
                                            <i class="fa fa-star checked" style="font-size: 10px"></i>
                                        </span>  
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr style="padding:2px; ">
                                <td>Feedback Detail:</td>
                                <td>${requestScope.feedback.feedbackDetail}</td>
                            </tr>                            
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
