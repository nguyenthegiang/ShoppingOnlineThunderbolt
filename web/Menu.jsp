<%@page import="DAL.CartDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Computer ERA</title>
        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />
        <!--The Menu nav bar of Home Page-->
        <link href="css/Menu.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">


        <jsp:useBean id="c" class="DAL.CartDAO"></jsp:useBean>
        </head>
        <body>
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="navbar-brand" href="home"><img src="image/Other/Logo.jpg" width="200px"></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="home"><i class="fa fa-home" id="home"></i><span class="sr-only">(current)</span></a>
                        </li>
                        <!--Add thêm code Login ở đây-->
                        <!--Trong test là điều kiện của mình-->
                        <!--sessionScope: gọi đến Session : nếu acc = null thì hiển thị menu là Login còn nếu khác null thì hiển thị là Logout-->
                    <c:if test="${sessionScope.acc == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="login" id="linkHover">Login</a>
                        </li>
                    </c:if>

                    <!--Nếu acc khác null -> login rồi -> hiển thị cả 3 menu dưới-->
                    <c:if test = "${sessionScope.acc != null}">
                        <li class="nav-item">
                            <!--Link to user profile-->
                            <a class="nav-link" href="profile" id="linkHover">Hello ${sessionScope.acc.user}</a>
                        </li>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent" style="display: unset !important;">
                            <ul class="nav nav-pills mr-auto justify-content-end">           
                                <li class="nav-item dropdown " id="sup">
                                    <a onclick="notiRead(${sessionScope.acc.id})" class="nav-link" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fa fa-bell" id="bell">     
                                            <span id="unread">${unread}</span>
                                        </i>

                                    </a>

                                    <ul class="dropdown-menu" id="dropdown">
                                        <li class="head text-light bg-dark">
                                            <div class="row">
                                                <div class="col-lg-12 col-sm-12 col-12">
                                                    <span>Notifications (most recently)</span>
                                                    <a href="" class="float-right text-light">Mark all as read</a>
                                                </div>
                                        </li>
                                        
                                        <c:if test="${sessionScope.acc != null}">
                                            <c:forEach items="${notis}" var="item">
                                                <form action="viewInvoiceDetailAdmin" id="${item.orderId}1" style="display:none;">
                                                    <input type="hidden" value="${item.orderId}" name="id"/>
                                                    <input type="hidden" value="Waiting for Confirmation" name="status"/>
                                                </form>
                                                <form action="viewOrderDetail" id="${item.orderId}2" style="display:none;">
                                                    <input type="hidden" value="${item.orderId}" name="id"/>
                                                </form>
                                                <a 
                                                    <c:if test="${sessionScope.acc.isAdmin == 1}">
                                                        onclick="formAutoSubmit('${item.orderId}1')"
                                                    </c:if>
                                                    <c:if test="${sessionScope.acc.isAdmin != 1}">
                                                        onclick="formAutoSubmit('${item.orderId}2')"
                                                    </c:if>
                                                        style="text-decoration: none;cursor: pointer">
                                                    <li class="notification-box" style="cursor: pointer;" >
                                                        <div class="row" id="noticeContent">
                                                            <div class="col-lg-3 col-sm-3 col-3 text-center">
                                                                <img src="image/47734_tai_nghe_corsair_hs35_stereo_blue_0005_1.jpg" class="w-50 rounded-circle">
                                                            </div>    
                                                            <div class="col-lg-8 col-sm-8 col-8" >
                                                                <strong class="text-info">David John</strong>
                                                                <div>
                                                                    ${item.content}
                                                                </div>
                                                                <small class="text-warning">${item.time}</small>
                                                            </div>    
                                                        </div>
                                                    </li>
                                                </a>
                                            </c:forEach>
                                        </c:if>

                                        
                                        <li class="footer bg-dark text-center">
                                            <a href="" class="text-light">View All (${numberOfNotifications})</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <!--Kiểm tra xem có phải người bán ko: isSell = 1-->
                        <c:if test="${sessionScope.acc.isSell == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="dashBoard" id="linkHover">Dash Board</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="manager" id="linkHover">Manager Product</a>
                            </li> 
                        </c:if>
                        <!--Kiểm tra xem có phải admin ko: isAdmin == 1-->
                        <c:if test="${sessionScope.acc.isAdmin == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="accountManager" id="linkHover">Manager Account</a>
                            </li> 
                        </c:if>
                        <li class="nav-item">
                            <!--Sửa: khi ấn logout: dẫn -> servlet-->
                            <a class="nav-link" href="logout" id="linkHover">LogOut</a>
                        </li>
                    </c:if>                   
                </ul>                    
                <form class="form-inline my-2 my-lg-0" action="search" method="post">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search..." oninput="searchByName(this)" value="${txtS}" name="txt" id="searchText">
                    <button class="btn my-2 my-sm-0" type="submit" id="search"><i class="fa fa-search"></i></button>

                    <a class="btn btn-sm ml-4" href="show" id="cart">
                        <i class="fa fa-shopping-cart"></i> Cart
                        <span class="badge badge-light" id="CartNum">${c.countNumCart(sessionScope.acc.id)}</span>
                    </a>
                </form>
            </div>
        </nav>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <script>
                                                            function notiRead(userId) {
                                                                //Sử dụng Ajax
                                                                $.ajax({
                                                                    url: "/Assignment_ElectronicShop_Pro/notiRead",
                                                                    type: "get", //send it through get method
                                                                    data: {
                                                                        userId: userId
                                                                    },
                                                                    success: function () {
                                                                        //Change number of Product in cart
                                                                        document.getElementById("unread").innerHTML = 0;
                                                                    },
                                                                    error: function () {
                                                                    }
                                                                });
                                                            }

                                                            function formAutoSubmit(name) {

                                                                var frm = document.getElementById(name);

                                                                frm.submit();

                                                            }
        </script>

    </body>
</html>