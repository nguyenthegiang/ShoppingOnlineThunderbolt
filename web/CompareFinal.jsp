<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
            <!--Phần sản phẩm-->
            <div class="container" style="background-color: #ffffff">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="productList">Product List</a></li>
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
                        <h3> Products Comparison <small class="">&nbsp;&nbsp;&nbsp;&nbsp; </small></h3>	
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
                                    <th></th>
                                    <th>                                     
                                        <img style="max-height: 25em; max-width: 20em; padding:0;margin:0;border-radius: 50%" src="image/${product1.imageLink}" alt="Product picture"/>
                                    <h4>${product1.name}</h4>
                                </th>
                                <th>                                     
                                    <img style="height: 25em; width: 20em; padding:0;margin:0;border-radius: 50%" src="image/${product2.imageLink}" alt="Product picture"/>
                                    <h4>${product2.name}</h4>

                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>&nbsp;</td>
                                <td>
                                    <p class="">
                                        ${product1.description}
                                    </p>
                                    <p><strong>
                                            <fmt:formatNumber type = "number" 
                                                              maxFractionDigits = "3" value = "${product1.price}"/> VND
                                        </strong></p>
                                    <br/>
                                    <div class="">
                                        <a onclick="addCart(${product1.id})" class=""><span class=" "></span> Add to cart</a> &emsp;&emsp;&emsp;&emsp;&emsp;

                                        <a href="detail?ProductID=${product1.id}" class="">VIEW</a>
                                    </div>
                                </td>
                                <td>
                                    <p class="">
                                        ${product2.description}
                                    </p>
                                    <p><strong>
                                            <fmt:formatNumber type = "number" 
                                                              maxFractionDigits = "3" value = "${product2.price}"/> VND
                                        </strong></p>
                                    <br/>
                                    <div class="">
                                        <a onclick="addCart(${product2.id})" class=""><span class=" "></span> Add to cart</a> &emsp;&emsp;&emsp;&emsp;&emsp;

                                        <a href="detail?ProductID=${product2.id}" class="">VIEW</a>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td><i class="fa fa-tags"></i>Price</td>
                                <td><strong>
                                        <fmt:formatNumber type = "number" 
                                                          maxFractionDigits = "3" value = "${product1.price}"/> VND
                                    </strong></td>
                                <td><strong>
                                        <fmt:formatNumber type = "number" 
                                                          maxFractionDigits = "3" value = "${product2.price}"/> VND
                                    </strong></td>
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
                                <td><i class="fa fa-balance-scale-left"></i>Weight</td>
                                <td>0.5kg</td>
                                <td>0.8kg</td>
                            </tr>
                            <tr>

                            </tr>
                        </tbody>
                    </table>


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
                                                    url: "/Assignment_ElectronicShop_Pro/compareByAjax",
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
                                            ;
                                            function submitForm() {
                                                document.forms['formFinal'].submit();
                                            }
                                            ;
        </script>  

    </body>
</html>
