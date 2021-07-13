<%-- 
    Document   : ViewAllFeedbacks
    Created on : Jul 13, 2021, 5:05:06 PM
    Author     : TRANTATDAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
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
                <div class="col-md-2">
                    <p>Choose your sort options and order: </p>
                    <form class="sort-form" action="manage-feedback" method="POST">
                        <input type="hidden" name="sort-flag" value="true" />
                        <input type="radio" id="sort-star" name="sort-order" value="1" checked="true">
                        <label for="sort-star">Sort by star</label><br>
                        <input type="radio" id="sort-name" name="sort-order" value="2">
                        <label for="sort-name">Sort by user name</label><br>
                        <input type="radio" id="sort-product" name="sort-order" value="3">
                        <label for="sort-product">Sort by product</label>
                        <br><br>
                        <input type="radio" id="sort-order" name="sort-by-order" value="1" checked="true">
                        <label for="sort-order">Ascending</label><br>
                        <input type="radio" id="sort-order" name="sort-by-order" value="2">
                        <label for="sort-order">Descending</label><br>
                        <br>
                        <input type="submit" value="Submit">
                    </form>
                </div>
                <div class="col-md-10">
                    <table id="feedback" style="margin-left:3em; border: 1px solid;">
                        <thead >
                            <tr >
                                <th style="text-align: center;">FeedbackID</th>
                                <th style="text-align: center;">User</th>
                                <th style="text-align: center;">Star</th>
                                <th style="text-align: center;">Product</th>
                                <th style="text-align: center;">Detail</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${requestScope.lsFeedback}" varStatus="x">
                                <tr style="padding:2px; border: 1px solid">
                                    <td>${item.id}</td>
                                    <td>${item.user.user}</td>
                                    <td>
                                        <c:forEach begin="1" end="${item.star}">
                                            <span><i class="fa fa-star checked" style="font-size: 10px"></i></span>  
                                            </c:forEach>
                                    </td>
                                    <td>
                                        ${item.product.name}
                                    </td>
                                    <td>${item.feedbackDetail}</td>
                                </tr>


                            </c:forEach>
                        </tbody>
                    </table>
                </div>                              
            </div>
        </div>      
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    </body>
</html>
