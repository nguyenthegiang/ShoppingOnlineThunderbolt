<%-- 
    Document   : DashBoard
    Created on : Mar 31, 2021, 7:54:26 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

        <title>Computer ERA</title>
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
                                <a class="nav-link" href="home">Home</a>
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
            <div class="row" style="margin-top: 20px;">
                <div class="col-4">
                    <div class="card mb-3" style="max-width: 540px;">
                        <div class="row g-0">
                            <div class="col-md-4">
                                <img src="image/AccountIcon.png" alt="..." width="70%;">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title">Total Accounts</h5>
                                    <h5 class="card-title">${totalAccount}</h5>
                                    <p class="card-text"><small class="text-muted">Last updated 1 mins ago</small></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <div class="card mb-3" style="max-width: 540px;">
                        <div class="row g-0">
                            <div class="col-md-4">
                                <img src="image/ProductIcon.png" alt="..." width="70%;">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title">Total Products</h5>
                                    <h5 class="card-title">${totalProduct}</h5>
                                    <p class="card-text"><small class="text-muted">Last updated 1 mins ago</small></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <div class="card mb-3" style="max-width: 540px;">
                        <div class="row g-0">
                            <div class="col-md-4">
                                <img src="image/InvoiceIcon.png" alt="..." width="70%;" style="margin-top: 10px;">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title">Total Invoices</h5>
                                    <h5 class="card-title">${totalCart}</h5>
                                    <p class="card-text"><small class="text-muted">Last updated 1 mins ago</small></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <h3>Most Selling Product</h3>
                    <c:forEach var="o" items="${top3MostSellD}">
                        <h6>${o.name}</h6>
                        <div class="progress">
                            <div class="progress-bar progress-bar-striped bg-info" role="progressbar" style="width: ${o.proportion}%" aria-valuenow="${o.proportion}" aria-valuemin="0" aria-valuemax="100">${o.amount}</div>
                        </div>
                        <br>
                    </c:forEach>
                </div>
                <div class="col-6">
                    <h3>Least Selling Product</h3>
                    <c:forEach var="o" items="${top3LeastSellD}">
                        <h6>${o.name}</h6>
                        <div class="progress">
                            <div class="progress-bar progress-bar-striped bg-info" role="progressbar" style="width: ${o.proportion}%" aria-valuenow="${o.proportion}" aria-valuemin="0" aria-valuemax="100">${o.amount}</div>
                        </div>
                        <br>
                    </c:forEach>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    </body>
</html>