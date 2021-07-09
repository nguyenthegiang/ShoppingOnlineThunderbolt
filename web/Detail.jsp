
<%@page import="entity.Feedback"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />
        <title>Computer ERA</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }

            /* display star rating */
            .checked {
                color: orange;
            }

            .heading {
                font-size: 25px;
                margin-right: 25px;
            }

            /* Three column layout */
            .side {
                float: left;
                width: 15%;
                margin-top: 10px;
            }

            .middle {
                float: left;
                width: 70%;
                margin-top: 10px;
            }

            /* Place text to the right */
            .right {
                text-align: right;
            }

            /* Clear floats after the columns */
            .row:after {
                content: "";
                display: table;
                clear: both;
            }

            /* The bar container */
            .bar-container {
                width: 100%;
                background-color: #f1f1f1;
                text-align: center;
                color: white;
            }

            /* Individual bars */
            .bar-5 { height: 18px; background-color: #04AA6D;}
            .bar-4 { height: 18px; background-color: #2196F3;}
            .bar-3 { height: 18px; background-color: #00bcd4;}
            .bar-2 { height: 18px; background-color: #ff9800;}
            .bar-1 { height: 18px; background-color: #f44336;}

            /* Responsive layout - make the columns stack on top of each other instead of next to each other */
            @media (max-width: 400px) {
                .side, .middle {
                    width: 100%;
                }
                /* Hide the right column on small screens */
                .right {
                    display: none;
                }
            }

        </style>

    </head>
    <body >

        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                <jsp:include page="Left.jsp"></jsp:include>
                    <div class="col-sm-9">
                        <div class="container">
                            <div class="card">
                                <div class="row">
                                    <aside class="col-sm-5 border-right">
                                        <article class="gallery-wrap"> 
                                            <div class="img-big-wrap">
                                                <div> <a href="#"><img src="image/${detail.imageLink}"></a></div>
                                        </div> <!-- slider-product.// -->
                                        <div class="img-small-wrap">
                                        </div> <!-- slider-nav.// -->
                                    </article> <!-- gallery-wrap .end// -->
                                </aside>
                                <aside class="col-sm-7">
                                    <h3 style="color: red">${message}</h3>
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${detail.name}</h3>

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="currency">VND </span><span class="num">${detail.priceWithDot}</span>
                                            </span> 
                                        </p> 
                                        <hr>
                                        <dl class="item-property">
                                            <dt>Manufacturer</dt>
                                            <dd><p>
                                                    ${detail.manufacturer}
                                                </p></dd>
                                        </dl>

                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <dl class="param param-inline">
                                                    <dt>Quantity: </dt>
                                                    <dd>
                                                        <select class="form-control form-control-sm" style="width:70px;" id="select_id">
                                                            <c:forEach begin="1" end="10" var="o">
                                                                <option> ${o} </option>
                                                            </c:forEach>
                                                        </select>
                                                    </dd>
                                                </dl>  <!-- item-property .// -->
                                            </div> <!-- col.// -->
                                        </div> <!-- row.// -->
                                        <hr>
                                        <button onclick="buy(${detail.id})" class="btn btn-lg btn-info text-uppercase"> Buy now </button>
                                        <button onclick="addCart2(${detail.id})" class="btn btn-lg btn-outline-info text-uppercase"> <i class="fas fa-shopping-cart"></i> Add to cart </button>

                                    </article> <!-- card-bodyylassdkljfghasdkjkdhjlaskdj.// -->                                   
                                </aside> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->
                        <br><br>

                        <!--User rating start here-->
                        <div class="user-rating">
                            <div class="card mb-5">
                                <div class="container mt-2 mb-2 ">

                                    <c:if test = "${requestScope.lsFeedback.size() ne 0}">
                                        <div class="row">
                                            <div class="col-4">

                                                <h3 style="color: #6c757d; text-align: center; margin-bottom: 0">Overall rating</h3>
                                                <h1 style="text-align: center; font-size: 100px; color: #000; margin-top: 5px; font-weight: lighter">
                                                    <fmt:formatNumber maxFractionDigits="2">${requestScope.averageStar}</fmt:formatNumber> 
                                                    </h1>
                                                    <div style="margin: auto; text-align: center">
                                                    <c:forEach begin="1" end="${requestScope.roundedAverageStar}">
                                                        <span><i class="fa fa-star checked" style="font-size: 40px"></i></span>
                                                        </c:forEach>
                                                        <c:forEach begin="1" end="${5-(requestScope.roundedAverageStar)}">
                                                        <span><i class="fa fa-star" style="font-size: 40px; color: #ddd"></i></span>
                                                        </c:forEach>
                                                </div>
                                                <p style="text-align: center; color: #6c757d;">
                                                    based on ${requestScope.lsFeedback.size()} reviews
                                                </p>
                                            </div>
                                            <div class="col-8" onload="fillBarLength()">
                                                <div class="container">
                                                    <br><br><br>

                                                    <!--5 stars-->
                                                    <div class="row">
                                                        <div class="col-sm-7 col-md-4">
                                                            <div class="rating-label">Excellent</div>
                                                        </div>
                                                        <div class="col-sm-4 col-md-7">
                                                            <div class="bar-container">
                                                                <div class="bar-5" id="bar-5"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1 col-md-1">
                                                            <div class="rating-label" id="5-star-value">${requestScope.fiveStar}</div>
                                                        </div>
                                                    </div>

                                                    <!--4 stars-->
                                                    <div class="row">
                                                        <div class="col-sm-7 col-md-4">
                                                            <div class="rating-label">Good</div>
                                                        </div>
                                                        <div class="col-sm-4 col-md-7">
                                                            <div class="bar-container">
                                                                <div class="bar-4" id="bar-4"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1 col-md-1">
                                                            <div class="rating-label" id="4-star-value">${requestScope.fourStar}</div>
                                                        </div>
                                                    </div>

                                                    <!--3 stars-->
                                                    <div class="row">
                                                        <div class="col-sm-7 col-md-4">
                                                            <div class="rating-label">Average</div>
                                                        </div>
                                                        <div class="col-sm-4 col-md-7">
                                                            <div class="bar-container">
                                                                <div class="bar-3" id="bar-3"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1 col-md-1">
                                                            <div id="3-star-value">${requestScope.threeStar}</div>
                                                        </div>
                                                    </div>

                                                    <!--2 stars-->
                                                    <div class="row">
                                                        <div class="col-sm-7 col-md-4">
                                                            <div class="rating-label">Below average</div>
                                                        </div>
                                                        <div class="col-sm-4 col-md-7">
                                                            <div class="bar-container">
                                                                <div class="bar-2" id="bar-2"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1 col-md-1">
                                                            <div class="rating-label" id="2-star-value">${requestScope.twoStar}</div>
                                                        </div>
                                                    </div>

                                                    <!--1 star-->
                                                    <div class="row">
                                                        <div class="col-sm-7 col-md-4">
                                                            <div class="rating-label">Poor</div>
                                                        </div>
                                                        <div class="col-sm-4 col-md-7">
                                                            <div class="bar-container">
                                                                <div class="bar-1" id="bar-1"></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1 col-md-1">
                                                            <div class="rating-label" id="1-star-value">${requestScope.oneStar}</div>
                                                        </div> 
                                                    </div>

                                                </div>
                                            </div>
                                        </div>



                                    </c:if>
                                    <c:if test = "${requestScope.lsFeedback.size() eq 0}">
                                        <c:forEach begin="1" end="5">
                                            <span class="fa fa-star"></span>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                        <!--Feedbacks start here-->
                        <div class="feedback">               
                            <div class="card">
                                <div class="title mt-2 ml-2">
                                    <h3 class="text-success">Feedbacks</h3>
                                    <p class="text-success">${requestScope.messageAddReplies}</p> 
                                </div>

                                <!--                            <div class="imagebg"></div>
                                                            <div class="row " style="margin-top: 50px">
                                                                <div class="col-md-6 col-md-offset-3 form-container">
                                                                    <p>
                                                                        Please provide your feedback below:
                                                                    </p>
                                                                    <form role="form" method="post" id="reused_form">
                                                                        <div class="row">
                                                                            <div class="col-sm-12 form-group">
                                                                                <label>How do you rate your overall experience?</label>
                                                                                <p>
                                                                                    <label class="radio-inline">
                                                                                        <input type="radio" name="experience" id="radio_experience" value="bad" >
                                                                                        Bad
                                                                                    </label>
                                
                                                                                    <label class="radio-inline">
                                                                                        <input type="radio" name="experience" id="radio_experience" value="average" >
                                                                                        Average
                                                                                    </label>
                                
                                                                                    <label class="radio-inline">
                                                                                        <input type="radio" name="experience" id="radio_experience" value="good" >
                                                                                        Good
                                                                                    </label>
                                                                                </p>
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-sm-12 form-group">
                                                                                <label for="comments">
                                                                                    Comments:</label>
                                                                                <textarea class="form-control" type="textarea" name="comments" id="comments" placeholder="Your Comments" maxlength="6000" rows="7"></textarea>
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-sm-6 form-group">
                                                                                <label for="name">
                                                                                    Your Name:</label>
                                                                                <input type="text" class="form-control" id="name" name="name" required>
                                                                            </div>
                                                                            <div class="col-sm-6 form-group">
                                                                                <label for="email">
                                                                                    Email:</label>
                                                                                <input type="email" class="form-control" id="email" name="email" required>
                                                                            </div>
                                                                        </div>
                                
                                                                        <div class="row">
                                                                            <div class="col-sm-12 form-group">
                                                                                <button type="submit" class="btn btn-lg btn-warning btn-block" >Post </button>
                                                                            </div>
                                                                        </div>
                                
                                                                    </form>
                                                                    <div id="success_message" style="width:100%; height:100%; display:none; ">
                                                                        <h3>Posted your feedback successfully!</h3>
                                                                    </div>
                                                                    <div id="error_message"
                                                                         style="width:100%; height:100%; display:none; ">
                                                                        <h3>Error</h3>
                                                                        Sorry there was an error sending your form.
                                
                                                                    </div>
                                                                </div>
                                                            </div>-->



                                <div class="feedback-list">
                                    <ul class="comments" style="display: block; list-style-type: none; margin-right: 10px">
                                        <c:if test = "${requestScope.lsFeedback.size() ne 0}">

                                            <c:set var="count" value="0"></c:set>


                                            <c:forEach items="${requestScope.lsFeedback}" var="f" varStatus="loop">
                                                <div class="card mb-3" style=" padding: 10px;">
                                                    <li>
                                                        <p>
                                                            <c:if test="${count%6==0}">
                                                                <img src="image/Other/ava1.jpg" style="border-radius: 50%; height: 50px"/>
                                                            </c:if>
                                                            <c:if test="${count%6==1}">
                                                                <img src="image/Other/ava2.jpg" style="border-radius: 50%; height: 50px"/>
                                                            </c:if>
                                                            <c:if test="${count%6==2}">
                                                                <img src="image/Other/ava3.jpg" style="border-radius: 50%; height: 50px"/>
                                                            </c:if>
                                                            <c:if test="${count%6==3}">
                                                                <img src="image/Other/ava4.jpg" style="border-radius: 50%; height: 50px"/>
                                                            </c:if>
                                                            <c:if test="${count%6==4}">
                                                                <img src="image/Other/ava5.jpg" style="border-radius: 50%; height: 50px"/>
                                                            </c:if>
                                                            <c:if test="${count%6==5}">
                                                                <img src="image/Other/ava6.jpg" style="border-radius: 50%; height: 50px"/>
                                                            </c:if>

                                                            <b>${f.user.user}&nbsp;&nbsp;</b>
                                                            <c:forEach begin="1" end="${f.star}">
                                                                <span class="fa fa-star checked"></span>
                                                            </c:forEach>
                                                            <a class="pull-right reply" data-toggle="collapse" href="#collapseRepliesForm" role="button" aria-expanded="false" aria-controls="collapseExample" onclick="getFeedbackId(${f.id})">
                                                                <span><i class="fa fa-reply"></i> reply</span>
                                                            </a> 
                                                        </p>

                                                        <c:if test="${f.order.status eq 'Completed'}">
                                                            <span>Already bought the product&nbsp;&nbsp;<i class="fas fa-check-square"></i>&nbsp;&nbsp;on ${f.order.date}</span>
                                                        </c:if>
                                                        <br><br>
                                                        <span>${f.feedbackDetail}</span>

                                                        <br><br>
                                                        <c:if test="${f.listReplies.size() ne 0}">
                                                            <h5>Replies:</h5>
                                                            <div class="container">
                                                                <ul style="background-color: #e9e9e9; list-style-type: none;">
                                                                    <c:forEach items="${f.listReplies}" var="fr" varStatus="loopReplies">
                                                                        <li>
                                                                            <p>
                                                                                <c:if test="${count%6==5}">
                                                                                    <img src="image/Other/ava1.jpg" style="border-radius: 50%; height: 50px"/>
                                                                                </c:if>
                                                                                <c:if test="${count%6==4}">
                                                                                    <img src="image/Other/ava2.jpg" style="border-radius: 50%; height: 50px"/>
                                                                                </c:if>
                                                                                <c:if test="${count%6==3}">
                                                                                    <img src="image/Other/ava3.jpg" style="border-radius: 50%; height: 50px"/>
                                                                                </c:if>
                                                                                <c:if test="${count%6==2}">
                                                                                    <img src="image/Other/ava4.jpg" style="border-radius: 50%; height: 50px"/>
                                                                                </c:if>
                                                                                <c:if test="${count%6==1}">
                                                                                    <img src="image/Other/ava5.jpg" style="border-radius: 50%; height: 50px"/>
                                                                                </c:if>
                                                                                <c:if test="${count%6==0}">
                                                                                    <img src="image/Other/ava6.jpg" style="border-radius: 50%; height: 50px"/>
                                                                                </c:if>
                                                                                <b>${fr.user.user}</b>
                                                                                <br>
                                                                                ${fr.repliesText}
                                                                            </p>
                                                                        </li>
                                                                    </c:forEach>
                                                                </ul>
                                                            </div>
                                                        </c:if>
                                                    </li>
                                                </div>
                                                <c:set var="count" value="${count + 1}"/>
                                            </c:forEach>
                                        </c:if>

                                        <c:if test = "${requestScope.lsFeedback.size() eq 0}">
                                            <p>There are no feedback on this product yet. Please help us add a feedback to this product by purchasing it!</p>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                            <c:if test="${requestScope.addFeedbackFlag eq true}">
                                <div class="card mt-5">
                                    <h3 class="text-success mt-2 ml-2">Add feedback</h3>
                                    <p>You have orders with this product that is not reviewed. Click the <a href="viewOrder?id=${sessionScope.acc.id}">link</a> to go to all your orders and add feedbacks</p>

                                </div>
                            </c:if>
                            <div class="reply-form mt-5 mb-3">
                                <div class="collapse" id="collapseRepliesForm">
                                    <form action="add-replies" method="POST" id="replies-form" onsubmit="submitRepliesForm(${detail.id})">
                                        <div class="form-group">
                                            <label for="replies-text">Your replies</label>
                                            <textarea class="form-control" name="replies-text" id="replies-text" rows="5"></textarea>
                                        </div>
                                        <input type="hidden" name="feedbackId" id="feedbackId">
                                        <input type="hidden" name="productId" id="productId">
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="Footer.jsp"></jsp:include>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script>
                                        function addCart2(ProductID) {
            <c:if test="${sessionScope.acc != null}">
                                            var select_value = document.getElementById("select_id").value;
                                            //Sử dụng Ajax
                                            $.ajax({
                                                url: "/Assignment_ElectronicShop_Pro/addMany",
                                                type: "get", //send it through get method
                                                data: {
                                                    ProductID: ProductID,
                                                    Quantity: select_value
                                                },
                                                success: function (message) {
                                                    alert(message);
                                                }
                                            });
            </c:if>
            <c:if test="${sessionScope.acc == null}">
                                            location.href = "login";
            </c:if>
                                        }

                                        function buy(ProductID) {
                                            var select_value = document.getElementById("select_id").value;
                                            location.href = "buyNow?ProductID=" + ProductID + "&Quantity=" + select_value;
                                        }

                                        let feedbackId = 0;
                                        function getFeedbackId(value) {
                                            feedbackId = value;
                                        }

                                        function submitRepliesForm(productId) {
                                            document.getElementById("productId").value = productId;
                                            document.getElementById("feedbackId").value = feedbackId;
                                            document.getElementById("replies-form").submit();
                                        }
            <% List<Feedback> list = (List<Feedback>) request.getAttribute("lsFeedback");
                int listSize = list.size();
                int fiveStar = (int) request.getAttribute("fiveStar");
                int fourStar = (int) request.getAttribute("fourStar");
                int threeStar = (int) request.getAttribute("threeStar");
                int twoStar = (int) request.getAttribute("twoStar");
                int oneStar = (int) request.getAttribute("oneStar");

            %>
                                        //calculate bar length
                                        const listSize = <%= listSize%>;
                                        if (listSize != 0) {
                                            const fiveStarBarLength = <%= fiveStar%> / listSize * 100;
                                            const fourStarBarLength = <%= fourStar%> / listSize * 100;
                                            const threeStarBarLength = <%= threeStar%> / listSize * 100;
                                            const twoStarBarLength = <%= twoStar%> / listSize * 100;
                                            const oneStarBarLength = <%= oneStar%> / listSize * 100;


                                            document.getElementById("bar-5").style.width = fiveStarBarLength + "%";
                                            document.getElementById("bar-4").style.width = fourStarBarLength + "%";
                                            document.getElementById("bar-3").style.width = threeStarBarLength + "%";
                                            document.getElementById("bar-2").style.width = twoStarBarLength + "%";
                                            document.getElementById("bar-1").style.width = oneStarBarLength + "%";
                                        }


        </script>  
    </body>
</html>
