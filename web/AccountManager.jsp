<%-- 
    Document   : AccountManager
    Created on : Mar 25, 2021, 4:47:17 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />
        <title>Computer ERA</title>
        <link href="css/Dashboard.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link href="css/AccountManager.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
            function search() {
                var searchText = document.getElementById("search_Account").value;
                location.href = "searchInAccount?text=" + searchText;
                return false;
            }
        </script>
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-2" style="background-color: #ebebf2;">
                    <a class="navbar-brand" href="dashBoard"><img src="image/Other/Logo.jpg" width="200px"></a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link" href="home"><i class="fas fa-home"></i>Home</a>
                            <hr class="line">
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="dashBoard"><i class="far fa-chart-bar"></i>Dashboard overview</a>
                            <hr class="line">
                        </li>
                        <c:if test="${sessionScope.acc == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="login">Login</a>
                            </li>
                        </c:if>
                        <c:if test = "${sessionScope.acc != null}">
                            <li id="drop">
                                <a class="nav-link" href="profile" id="profileOption"><i class="fas fa-user-circle"></i>Hello ${sessionScope.acc.user}</a>
                                <div class="dropdownContent">
                                    <a href="profile">View my profile</a>
                                    <a href="logout">LogOut</a>
                                </div>
                                <hr class="line">
                            </li>

                            <c:if test="${sessionScope.acc.isSell == 1}">
                                <li class="nav-item">
                                    <a class="nav-link" href="manager"><i class="fas fa-tasks"></i>Manager Product</a>
                                    <a class="nav-link" href="blogManager"><i class="fas fa-tasks"></i>Manager Blog</a>
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

                </div>
                <div class="col-10">
                    <div class="container">
                        <div class="d-flex justify-content-center h-100">
                            <div class="searchbar">
                                <input class="search_input" type="text" id="search_Account" placeholder="Search...">
                                <button onclick="search();" class="search_icon"><i class="fas fa-search"></i></button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="table-responsive">
                                <div class="table-wrapper">
                                    <h2 style="font-family: Brush Script MT; text-align: center; font-size: 60px">Account manager</h2>
                                    <br>
                                    <table class="table table-striped table-hover">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>UserName</th>						
                                                <th>Role</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${list}" var="o">
                                                <tr>
                                                    <td>${o.id}</td>
                                                    <td><a href="#">${o.user}</a></td>
                                                    <td style="width: 200px;">
                                                        <c:if test="${o.isSell==1}">
                                                            <a href="#" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true">Seller</a>
                                                        </c:if>
                                                        <c:if test="${o.isAdmin==1}">
                                                            <a href="#" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true">Admin</a>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <a target="blank" href="editAccount?UserID=${o.id}"><button type="button" class="btn btn-outline-info  btn-sm">Edit</button></a>
                                                        <form action="deleteAccount" method="POST" style="display:inline">
                                                            <a> <input type="submit" class="btn btn-outline-danger btn-sm" value="Delete" onclick="if (confirm('Are you sure you want to delete?'))
                                                                        form.action = 'deleteAccount?UserID=${o.id}';
                                                                    else
                                                                        return false;"/>  
                                                            </a>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>  
            </div>
        </div>
    </body>
</html>