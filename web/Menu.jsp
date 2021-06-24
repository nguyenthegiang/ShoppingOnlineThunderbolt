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
    </body>
</html>