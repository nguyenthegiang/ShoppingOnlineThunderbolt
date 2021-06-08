
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
        <jsp:include page="Menu.jsp"></jsp:include>

            <!--Slideshow-->
            <!--<section class="jumbotron text-center" style="background-color: white;">-->
            <div class="container" style="background-color: #ffffff">
                <div class="col-md-12">
                    <img class="mySlides" src="image/SlideShow/Intro.jpg" style="height: 100%; width: 100%; object-fit: contain; margin: auto">
                    <img class="mySlides" src="image/SlideShow/image3.jpg" style="height: 100%; width: 100%; object-fit: contain; margin: auto">
                    <img class="mySlides" src="image/SlideShow/image2.jpg" style="height: 100%; width: 100%; object-fit: contain; margin: auto">
                    <div class="w3-center w3-display-bottommiddle" style="width:100%">
                        <div class="w3-left" onclick="plusDivs(-1)">&#10094;</div>
                        <div class="w3-right" onclick="plusDivs(1)">&#10095;</div>
                        <span class="w3-badge demo w3-border" onclick="currentDiv(1)"></span>
                        <span class="w3-badge demo w3-border" onclick="currentDiv(2)"></span>
                        <span class="w3-badge demo w3-border" onclick="currentDiv(3)"></span>
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
            </div>
            <!--</section>-->

            <!--Danh mục sản phẩm-->
            <!--<div class="container" style="margin: 10px; background-color: #ffffff; margin: auto">
                <h2 style="font-family: Brush Script Std; text-align: center">Shop by category</h2>
                <div class="row">
                    <div class="col-md-2">
                        <img src="image/CATEGORY/laptopp.jpg"  id="category">
                    </div>
                    <div class="col-md-2">
                        <img src="image/CATEGORY/pc.jpg"  id="category">
                    </div>
                    <div class="col-md-2">
                        <img src="image/CATEGORY/printerr.jpg" id="category">
                    </div>
                    <div class="col-md-2">
                        <img src="image/CATEGORY/linhkien.jpg" id="category">
                    </div>
                    <div class="col-md-2">
                        <img src="image/CATEGORY/phone.jpg" id="category">
                    </div>
                    <div class="col-md-2">
                        <img src="image/CATEGORY/tablet.jpg" id="category">
                    </div>
                    <div class="col-md-2">
                        <img src="image/CATEGORY/headphonee.jpg" id="category">
                    </div>
                </div>
            </div>-->


            <!--Giới thiệu website-->
            <!--<div class="container" style="background-color: #ffffff; margin: auto; padding: 20px">
                <img src="image/SlideShow/Intro.jpg" alt="" style="height: 100%; width: 100%; object-fit: contain"/>
                <br><br><br>
            </div>-->

            <br>
            <!--Phần sản phẩm-->
            <div class="container" style="background-color: #ffffff">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="home">Home</a></li>
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
                            <!--List Product-->
                        <c:forEach items="${listP}" var="o">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card" id="product"> <!--class="card"-->
                                    <!--Product's image-->
                                    <a href="detail?ProductID=${o.id}" title="View Product"><img class="card-img-top" src="image/${o.imageLink}" alt="Card image cap"></a>
                                    <div class="card-body">
                                        <!--Product's name. Onlick: Product's Detail-->
                                        <h4 class="card-title show_txt"><a href="detail?ProductID=${o.id}" title="View Product">${o.name}</a></h4>
                                        <div class="row">
                                            <div class="col">
                                                <!--Product's Price-->
                                                <p class="btn btn-warning btn-block" id="price">${o.priceWithDot} VND</p>
                                            </div>
                                            <div class="col">
                                                <a onclick="addCart(${o.id})" class="btn btn-info btn-block" id="cart">Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                        
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>6</b> out of <b>${count}</b> entries</div>
                        <ul class="pagination pagination-circle">
                            <li class="page-item paging active"><a onclick="load(this, 1, ${CategoryID})"  class="page-link">1</a></li>
                                <c:forEach begin="2" end="${end}" var="i">
                                <li class="page-item paging"><a onclick="load(this, ${i}, ${CategoryID})"  class="page-link">${i}</a></li>
<!--<li class="page-item"><a style="cursor:pointer" onclick="load(${i}, ${CategoryID})" >${i}</a></li>-->   
<!--<li class="page-item ${tag == i?"active":""}"><a href="home?index=${i}&CategoryID=${CategoryID}" class="page-link">${i}</a></li>-->
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="Footer.jsp"></jsp:include>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script>
                                    function load(a_link, index, CateID) {
                                        var arr = document.getElementsByClassName('paging');
                                        for (var i = 0; i < arr.length; i++) {
                                            arr[i].classList.remove("active");
                                        }

                                        a_link.parentElement.classList.add("active");
                                        //Sử dụng Ajax
                                        $.ajax({
                                            url: "/Assignment_ElectronicShop_Pro/paging",
                                            type: "get", //send it through get method
                                            data: {
                                                index: index,
                                                CategoryID: CateID
                                            },
                                            success: function (responseData) {
                                                //Trg hợp này: Có dữ liệu trả về -> responseData là dữ liệu trả về
                                                //Dữ liệu trả về ko phải 1 List mà là từng khối <div>
                                                //Bao quanh tất cả các khối <div> sản phẩm là 1 khối div "content" => sửa ở đây
                                                document.getElementById("content").innerHTML = responseData;
                                            }
                                        });
                                    }


                                    function addCart(ProductID) {
            <c:if test="${sessionScope.acc != null}">
                                        //Sử dụng Ajax
                                        $.ajax({
                                            url: "/Assignment_ElectronicShop_Pro/addToCart",
                                            type: "get", //send it through get method
                                            data: {
                                                ProductID: ProductID
                                            },
                                            success: function (message) {
                                                alert(message);
                                            },
                                            error: function () {
                                            }
                                        });
            </c:if>
            <c:if test="${sessionScope.acc == null}">
                                        location.href = "login";
            </c:if>
                                    }

                                    function searchByName(param) {
                                        var txtSearch = param.value;
                                        $.ajax({
                                            url: "/Assignment_ElectronicShop_Pro/searchAjax",
                                            type: "get", //send it through get method
                                            data: {
                                                txt: txtSearch
                                            },
                                            success: function (data) {
                                                var row = document.getElementById("content");
                                                row.innerHTML = data;
                                            },
                                            error: function (xhr) {
                                                //Do Something to handle error
                                            }
                                        });
                                    }
        </script>  
    </body>
</html>
