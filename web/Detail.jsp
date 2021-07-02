
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

            /* for star rating */
            .rating {
                display: inline-block;
                position: relative;
                height: 50px;
                line-height: 50px;
                font-size: 50px;
            }

            .rating label {
                position: absolute;
                top: 0;
                left: 0;
                height: 100%;
                cursor: pointer;
            }

            .rating label:last-child {
                position: static;
            }

            .rating label:nth-child(1) {
                z-index: 5;
            }

            .rating label:nth-child(2) {
                z-index: 4;
            }

            .rating label:nth-child(3) {
                z-index: 3;
            }

            .rating label:nth-child(4) {
                z-index: 2;
            }

            .rating label:nth-child(5) {
                z-index: 1;
            }

            .rating label input {
                position: absolute;
                top: 0;
                left: 0;
                opacity: 0;
            }

            .rating label .icon {
                float: left;
                color: transparent;
            }

            .rating label:last-child .icon {
                color: #000;
            }

            .rating:not(:hover) label input:checked ~ .icon,
            .rating:hover label:hover input ~ .icon {
                color: #09f;
            }

            .rating label input:focus:not(:checked) ~ .icon:last-child {
                color: #000;
                text-shadow: 0 0 5px #09f;
            }


        </style>

        <script>
            /* for star rating */
            function starChange(value) {
                console.log('New star rating: ' + value);
            }
        </script>

    </head>
    <body>
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



                        <!--Feedbacks start here-->
                        <div class="feedback">               
                            <div class="card">
                                <div class="title mt-2 ml-2">
                                    <h3 class="text-success">Feedbacks</h3>
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
                                            <c:forEach items="${requestScope.lsFeedback}" var="f" varStatus="loop">
                                                <div class="card" style=" padding: 10px;">
                                                    <li>
                                                        <p><b>${requestScope.lsAccount[loop.index].user}&nbsp;&nbsp;</b>
                                                            <c:forEach begin="1" end="${f.star}">
                                                                <span class="fa fa-star checked"></span>
                                                            </c:forEach>
                                                            <br>
                                                            <c:if test="${f.order.status eq 'Completed'}">
                                                                Already bought the product&nbsp;&nbsp;<i class="fas fa-check-square"></i>&nbsp;&nbsp;on ${f.order.date}
                                                            </c:if>
                                                            <br><br>
                                                            ${f.feedbackDetail}
                                                        </p>
                                                        <br>
                                                        <c:if test="${f.listReplies.size() ne 0}">
                                                            <h5>Replies:</h5>
                                                            <div class="container">
                                                                <ul style="background-color: #e9e9e9; list-style-type: none;">
                                                                    <c:forEach items="${f.listReplies}" var="fr" varStatus="loopReplies">
                                                                        <li>
                                                                            <p>
                                                                                <b>${requestScope.lsAccountReplies[loopReplies.index].user}</b>
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
                                            </c:forEach>
                                        </c:if>

                                        <c:if test = "${requestScope.lsFeedback.size() eq 0}">
                                            <p>Chua co feedback</p>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                            <c:if test="${requestScope.addFeedbackFlag eq true}">
                                <div class="card mt-5">
                                    <h3 class="text-success">Add feedback</h3>

                                    <div class="add-feedback">
                                        <div class="star-rating">
                                            <form class="rating">
                                                <label>
                                                    <input type="radio" name="stars" value="1" onclick="starChange(this.value)" />
                                                    <span class="icon">★</span>
                                                </label>
                                                <label>
                                                    <input type="radio" name="stars" value="2" onclick="starChange(this.value)" />
                                                    <span class="icon">★</span>
                                                    <span class="icon">★</span>
                                                </label>
                                                <label>
                                                    <input type="radio" name="stars" value="3" onclick="starChange(this.value)" />
                                                    <span class="icon">★</span>
                                                    <span class="icon">★</span>
                                                    <span class="icon">★</span>   
                                                </label>
                                                <label>
                                                    <input type="radio" name="stars" value="4" onclick="starChange(this.value)" />
                                                    <span class="icon">★</span>
                                                    <span class="icon">★</span>
                                                    <span class="icon">★</span>
                                                    <span class="icon">★</span>
                                                </label>
                                                <label>
                                                    <input type="radio" name="stars" value="5" onclick="starChange(this.value)" />
                                                    <span class="icon">★</span>
                                                    <span class="icon">★</span>
                                                    <span class="icon">★</span>
                                                    <span class="icon">★</span>
                                                    <span class="icon">★</span>
                                                </label>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>

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
        </script>  
    </body>
</html>
