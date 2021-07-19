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

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2" style="background-color: #ebebf2">
                    <nav class="navbar navbar-expand-lg navbar-light flex-column">
                        <a class="navbar-brand" href="dashBoard"><img src="image/Other/Logo.jpg" width="200px"></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link" href="home"><i class="fas fa-home"></i>Home</a>
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
                    <div class="row-fluid">
                        <div class="col-md-12">
                            <table id="feedback" style="margin-left:3em; border: 1px solid;">
                                <thead >
                                    <tr>
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
                                            <td><a href="view-detail-feedback?id=${item.id}">Detail</a></td>
                                        </tr>


                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>   
                    </div>


                </div>
            </div>      
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    </body>
</html>
