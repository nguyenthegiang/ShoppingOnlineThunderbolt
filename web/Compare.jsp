
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
                    <div class="col-md-9 col-sm-9" style="display:inline;">
                        <h3> Products Compairsition <small class=""> 2 products are compaired </small></h3>	
                        <hr class=""/>
 <style>
      table,
      th,
      td {
        padding: 5px;
        border: 1px solid black;
        border-collapse: collapse;
      }
    </style>
                        <table id="" class="" style="">
                            <thead>
                                <tr>
                                    <th>Features</th>
                                    <th>Product1 name here </th>
                                    <th>
                                    <form class="form-inline my-2 my-lg-0" action="searchToCompare" method="post">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search a product" aria-label="Search..." oninput="searchByName(this)" value="${txtS}" name="txt">
                    <button class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
                                    </form> 
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>
                                        <p class="">
                                            with the latest fashion tendencies - that is why our 
                                            goods are so popular and we have a great number of faithful customers all over the country.
                                        </p>
                                        <img src="assets/img/d.jpg" alt=""/>
                                        <form class="">
                                            <h3> $140.00</h3><br/>
                                            <div class="">
                                                <a href="product_details.html" class=""><span class=" "></span> Add to cart</a>
                                                <a href="product_details.html" class="">VIEW</a>
                                            </div>
                                        </form>
                                    </td>
                                    <td>
                                        <p class="">
                                            with the latest fashion tendencies - that is why our 
                                            goods are so popular and we have a great number of faithful customers all over the country.
                                        </p>
                                        <img src="assets/img/e.jpg" alt=""/>

                                        <form class="">
                                            <h3> $140.00</h3>
                                            <br/>
                                            <div class="">
                                                <a href="product_details.html" class=""><span class=""></span> Add to cart</a>
                                                <a href="product_details.html" class="">VIEW</a>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Height</td>
                                    <td>5"</td>
                                    <td>15"</td>
                                </tr>
                                <tr>
                                    <td>Deepth</td>
                                    <td>5"</td>
                                    <td>5"</td>
                                </tr>
                                <tr>
                                    <td>Size</td>
                                    <td>XXL</td>
                                    <td>XL</td>
                                </tr>
                                <tr>
                                    <td>Width</td>
                                    <td>6.5"</td>
                                    <td>6"</td>
                                </tr>
                                <tr>
                                    <td>Weight</td>
                                    <td>0.5kg</td>
                                    <td>0.8kg</td>
                                </tr>
                            </tbody>
                        </table>		
                        <div class=""><a href="products.html" class="">Back to Products Page</a></div>
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
