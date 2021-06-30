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
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script src="https://kit.fontawesome.com/6c7ad27b5d.js" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body style="background-color: #D9DDDC">
        <!--Split Home page to many JSP pages for easier managing-->

        <!--Thanh menu phía trên cùng-->
        <!--hello-->
        <jsp:include page="Menu.jsp"></jsp:include>

            <!--Slideshow-->
            <!--<section class="jumbotron text-center" style="background-color: white;">-->
            <div class="container-fluid" style="background-color: #ebebf2">
                <div class="row"></div>
                <div class="col-md-12">
                    <img class="mySlides" src="image/SlideShow/Intro1.jpg" style="height: 80%; width: 80%; object-fit: contain; margin: auto">
                    <img class="mySlides" src="image/SlideShow/Intro2.jpg" style="height: 80%; width: 80%; object-fit: contain; margin: auto">
                    <img class="mySlides" src="image/SlideShow/Intro3.jpg" style="height: 80%; width: 80%; object-fit: contain; margin: auto">
                    <div class="w3-center w3-display-bottommiddle" style="width:100%">
                        <!--                        <div class="w3-left" onclick="plusDivs(-1)">&#10094;</div>
                                                <div class="w3-right" onclick="plusDivs(1)">&#10095;</div>-->
                        <span class="w3-badge demo w3-border" onclick="currentDiv(1)"></span>
                        <span class="w3-badge demo w3-border" onclick="currentDiv(2)"></span>
                        <span class="w3-badge demo w3-border" onclick="currentDiv(3)"></span>
                    </div>
                </div>
            </div>

            <script>
                var myIndex = 0;
                carousel();

                function carousel() {
                    var i;
                    var x = document.getElementsByClassName("mySlides");
                    for (i = 0; i < x.length; i++) {
                        x[i].style.display = "none";
                    }
                    myIndex++;
                    if (myIndex > x.length) {
                        myIndex = 1
                    }
                    x[myIndex - 1].style.display = "block";
                    setTimeout(carousel, 2000); // Change image every 2 seconds
                }
            </script>
            <br>
            <!--Phần tin tức-->
            <div class="container">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="blogList">Blog List</a></li>
                                <li class="breadcrumb-item active" aria-current="page">${CateName}</li>
                        </ol>                   
                    </nav>
                </div>
            </div>
        </div>
        <br>
        <div class="container">
            <div class="row">
                <jsp:include page="Left.jsp"></jsp:include>

                <div class="col-md-9 col-sm-9">
                    <div class="row" id="content">
                        <!--List Blog-->
                         <c:forEach items="${listP}" var="o">
                        <div class="col-12 col-md-6 col-lg-4">
                            <div class="card" id="product"> <!--class="card"-->
                                <!--Blog's image-->
                                <a href="detailBlog?ID=${o.id}" title="View Blog"><img class="card-img-top" src="image/${o.imageLink}" alt="Card image cap"></a>
                                <div class="card-body">
                                    <!--Blog's title. Onlick: Blog's Detail-->
                                    <h4 class="card-title show_txt"><a href="detail?ProductID=${o.id}" title="View Product">${o.title}</a></h4>
                                    <div class="row">
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
