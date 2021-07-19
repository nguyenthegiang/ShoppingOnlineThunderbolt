<%-- 
    Document   : Home
    Created on : Jun 30, 2021, 9:50:26 AM
    Author     : ADMIN
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

        <!--Style for Home Page-->
        <link href="css/HomeStyle.css" rel="stylesheet" type="text/css"/>
    </head>
    <body style="background-color: #D9DDDC">

        <jsp:include page="Menu.jsp"></jsp:include>

            <div class="container-fluid" style="background-color: #ebebf2">
                <div class="row"></div>
                <div class="col-md-12">
                    <img class="mySlides" src="image/SlideShow/Intro1.jpg" style="height: 80%; width: 80%; object-fit: contain; margin: auto">
                    <img class="mySlides" src="image/SlideShow/Intro2.jpg" style="height: 80%; width: 80%; object-fit: contain; margin: auto">
                    <img class="mySlides" src="image/SlideShow/Intro3.jpg" style="height: 80%; width: 80%; object-fit: contain; margin: auto">
                    <div class="w3-center w3-display-bottommiddle" style="width:100%">
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

            <div class="container" style="margin: 10px; background-color: #ffffff; margin: auto">

                <!--List Category-->
                <br> <br>
                <div class="row">
                    <div class="col-10">
                        <h2 style="font-family: sans-serif; display: inline-block;">SHOP BY CATEGORY</h2>
                    </div>
                    <div class="col-2">
                        <!--Link to Product List-->
                        <a href="productList" style="font-size: 150%;">View all</a>
                    </div>
                </div>
                <br>
                <div class="row">
                <c:forEach items="${allCategory}" var="o">
                    <div class="col">
                        <a href="productList?CategoryID=${o.id}" style="color: black;">
                            <div style="font-size: 400%;">${o.icon}</div>
                            <div>${o.name}</div>
                        </a>
                    </div>
                </c:forEach>
            </div>

            <!--Blog-->
            <br> <br>
            <div class="row">
                <div class="col-10">
                    <h2 style="font-family: sans-serif;">BLOG</h2>
                </div>
                <div class="col-2">
                    <!--Link to Blog List-->
                    <a href="blogList" style="font-size: 150%;">See all</a>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <img src="image/Blog/BlogBackground_HomePage.jpg" style="height: 500px; display: inline-block;" alt=""/>
                </div>
                <div class="col">
                    <h3>Lợi ích của việc ngồi ghế</h3>
                    <div id="firstContent">
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
                    <a href="detailBlog?ID=2" class="learnMoreButton">Learn more</a>
                </div>
            </div>

            <!--Hot Products-->
            <br> <br>
            <div class="row">
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
                                                <a onclick="addCart(${o.id})"><p class="btn btn-warning btn-block" id="price">${o.priceWithDot} VND</p></a>

                                                <!--<p class="btn btn-warning btn-block" id="price">${o.priceWithDot} VND</p>-->
                                            </div>
                                            <div class="col">
                                                <a onclick="addCart(${o.id})" class="btn btn-info btn-block" id="cart">Add to cart</a>
                                            </div>
                                            <div class="col">
                                                <a onmouseover="this.style.textDecoration = 'none';" href="compare?id=${o.id}}"><p class="btn btn-warning btn-block">Add to Compare</p></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
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
                                                                var res = message.split("|");
                                                                //Change number of Product in cart
                                                                document.getElementById("CartNum").innerHTML = res[0];
                                                                alert(res[1]);
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
