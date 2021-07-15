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
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

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


        <div class="container-fuild">
            <div class="row">
                <div class="col-md-2" style="background-color: #ebebf2">
                    <nav class="navbar navbar-expand-lg navbar-light flex-column">
                        <a class="navbar-brand" href="dashBoard"><img src="image/Other/Logo.jpg" width="200px"></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link" href="productList"><i class="fas fa-home"></i>Home</a>
                                <hr class="line">
                            </li>
                            <c:if test="${sessionScope.acc == null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="login">Login</a>
                                </li>
                            </c:if>
                            <c:if test = "${sessionScope.acc != null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="profile"><i class="fas fa-user-circle"></i>Hello ${sessionScope.acc.user}</a>
                                    <hr class="line">
                                </li>

                                <c:if test="${sessionScope.acc.isSell == 1}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="manager"><i class="fas fa-tasks"></i>Manager Product</a>
                                        <hr class="line">
                                        <a class="nav-link" href="blogManager"><i class="fas fa-tasks"></i>Manager Blog</a>
                                        <hr class="line">
                                        <a class="nav-link" href="manage-feedback"><i class="fas fa-tasks"></i>Manage Feedback</a>
                                        <hr class="line">
                                    </li> 
                                </c:if>

                                <c:if test="${sessionScope.acc.isAdmin == 1}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="accountManager"><i class="fas fa-tasks"></i>Manager Account</a>
                                        <hr class="line">
                                    </li> 
                                </c:if>
                                <li class="nav-item">
                                    <a class="nav-link" href="viewAllNotifications"><i class="fas fa-bell"></i>Notifications (${numberNoti})</a>
                                </li> 
                            </c:if>

                            <li class="nav-item">&nbsp;
                            </li> 
                        </ul>
                        <a class="nav-link" href="logout" style="position: fixed; right: 10px;">LogOut</a>
                    </nav>
                </div>

                <div class="col-md-10">
                    <hr>
                    <div class="row">

                        
                            <img src="image/${requestScope.feedback.product.imageLink}" style="width: 400px; border-radius: 50%; margin: auto; box-shadow: 10px 10px 5px #ddd; border-color: #000">
                            <div class="card-body p-5">
                                <h3 class="title mb-3">${requestScope.feedback.product.name}</h3>

                                <p class="price-detail-wrap"> 
                                    <span class="price h3 text-warning"> 
                                        <span class="currency">VND </span><span class="num">${requestScope.feedback.product.priceWithDot}</span>
                                    </span> 
                                </p> 

                            </div>
                        

                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">Feedback Detail:</h5>
                                <table class="table table-borderless">
                                    <tr style="padding:2px; ">
                                        <td class="col-md-3"><b>User:</b></td>
                                        <td class="col-md-9">${requestScope.feedback.user.user}</td>
                                    </tr>
                                    <tr style="padding:2px; ">
                                        <td class="col-md-3"><b>Order ID:</b></td>
                                        <td class="col-md-9">${requestScope.feedback.orderId}</td>
                                    </tr>
                                    <tr style="padding:2px; ">
                                        <td class="col-md-3"><b>Star:</b></td>
                                        <td class="col-md-9">
                                            <c:forEach begin="1" end="${requestScope.feedback.star}">
                                                <span>
                                                    <i class="fa fa-star checked" style="font-size: 10px"></i>
                                                </span>  
                                            </c:forEach>
                                        </td>
                                    </tr>
                                    <tr style="padding:2px; ">
                                        <td class="col-md-3"><b>Feedback Detail:</b></td>
                                        <td class="col-md-9">${requestScope.feedback.feedbackDetail}</td>
                                    </tr>                            
                                </table>
                            </div>
                        </div>
                        <div class="col-md-2"></div>
                    </div>


                </div>
            </div>
    </body>
</html>
