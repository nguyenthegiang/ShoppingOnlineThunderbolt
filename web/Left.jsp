<%-- 
    Document   : Left
    Created on : Mar 2, 2021, 3:41:02 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Computer ERA</title>
    </head>
    <body>
        <div class="col-sm-3">
            <div class="card bg-light mb-3">
                <div class="card-header bg-info text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                <ul class="list-group category_block">
                    <c:forEach items="${allCategory}" var="o">
                        <!--Category nao dang dc chon thi se noi bat-->
                        <li class="list-group-item text-white ${CateID == o.id ? "active" : ""}">
                            <a href="home?CategoryID=${o.id}">${o.icon} ${o.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="card bg-light mb-3">
                <div class="card-header bg-info text-white text-uppercase">HOT product</div>
                <div class="card-body">
                    <img class="img-fluid" src="image/${hot.imageLink}" />
                    <h5 class="card-title">${hot.name}</h5>
                    <p class="bloc_left_price">${hot.priceWithDot} VND</p>
                </div>
            </div>
            <div class="card bg-light mb-3">
                <div class="card-header bg-info text-white text-uppercase">Most Favorite product</div>
                <div class="card-body">
                    <img class="img-fluid" src="image/${favor.imageLink}" />
                    <h5 class="card-title">${favor.name}</h5>
                    <p class="bloc_left_price">${favor.priceWithDot} VND</p>
                </div>
            </div>
        </div>
    </body>
</html>
