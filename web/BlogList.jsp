<%-- 
    Document   : BlogList
    Created on : Jun 30, 2021, 5:32:33 PM
    Author     : thong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Computer ERA</title>
        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!--Using Bootstrap-->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/6c7ad27b5d.js" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <style>
            .col-sm-8 p {
                overflow: hidden;
                display: -webkit-box;
                -webkit-line-clamp: 3;
                -webkit-box-orient: vertical;
            }
        </style>
    </head>
    <body style="background-color: #ebebf2;">
        <jsp:include page="Menu.jsp"></jsp:include>



            <br>


            <div class="container" style="position: relative;">
                <div class="row" style="">
                    <img src="image/Blog/BlogBackground.jpg"  style="height: 500px"alt=""/>
                    <h3 style="position: absolute;top: 15%;left: 55%;">Lợi ích của việc ngồi ghế</h3>
                    <div style="position: absolute;top: 25%;left: 45%;right: 10%">
                        <p>
                            Bố trí phòng làm việc thế nào để có thể tạo ra được sự thoải mái và hứng khởi nhất cho người lao động là mối quan tâm hàng đầu của các chủ doanh nghiệp. 
                            Nhân viên văn phòng phải ngồi làm việc nhiều giờ sau bàn làm việc mỗi ngày nên cần có chỗ ngồi thoải mái, dễ chịu, chính vì vậy việc đầu tư vào nội thất
                            văn phòng để phục vụ lợi ích con người cũng như lợi ích công việc là điều cần thiết.
                        </p>
                        <p>
                            Việc ngồi lâu trên ghế văn phòng hàng ngày để làm việc sẽ có những tác hại cụ thế đối với sức khỏe của bạn. 
                            Tuy nhiên, nếu chúng ta sử dụng đúng cách, làm việc hợp lý thì có thể mang lại nhiều lợi ích.
                        </p>
                    </div>
                    <a href="detailBlog?ID=2" class="" style=" position: absolute;bottom: 30px;;right: 300px; border: #04AA6D; background-color: #17A2B8">Learn more</a>
                </div>
            </div>






            <!--Phần tin tức-->
            <div class="container">
                <!--                <div class="row">
                                    <div class="col">
                                        <nav aria-label="breadcrumb">
                                            <ol class="breadcrumb">
                                                <li class="breadcrumb-item"><a href="blogList">Blog List</a></li>
                                                <li class="breadcrumb-item active" aria-current="page">${CateName}</li>
                                        </ol>                   
                                    </nav>
                                </div>
                            </div>-->
        </div>
        <br>
        <div class="container">
            <c:forEach items="${listP}" var="o">
                <div class="row">
                    <div class="col-sm-4">
                        <a href="detailBlog?ID=${o.id}" class="">
                            <img class="col-sm-12" src="image/${o.imageLink}">
                        </a>
                    </div>
                    <div class="col-sm-8">
                        <h3 class="card-title show_txt"><a href="detailBlog?ID=${o.id}" title="View Blog">${o.title}</a></h3>                                   
                        <p>${o.content}</p>
                        <c:if test="${sessionScope.acc != null}">
                            <a href="like?aid=${o.id}" style="text-decoration: none; margin-right: 10px">
                                <span style="font-size:30px">&#128077;</span>
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.acc == null}">
                            <a href="Login.jsp" style="text-decoration: none; margin-right: 10px">
                                <span style="font-size:30px">&#128077;</span>
                            </a>
                        </c:if>

                    </div>
                </div>
            </c:forEach>

        </div>




    </body>
</html>
