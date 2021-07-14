<%-- 
   Document   : DashBoard
   Created on : Mar 31, 2021, 7:54:26 PM
   Author     : ADMIN
--%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="css/OrderTableStyle.css" type="text/css"/>

        <!--Favicon-->
        <link rel="icon" type="image/png" href="image/faviconLogo.png" />

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

        <title>Computer ERA</title>
    </head>
    <body>

        <a class="nav-link" href="logout" style="position: fixed; right: 10px;">LogOut</a>

        <div class="container">
            <div class="row">
                <div class="col-3">
                    <nav class="navbar navbar-expand-lg navbar-light bg-light flex-column">
                        <a class="navbar-brand" href="dashBoard"><img src="image/MenuLogo.png" width="200px"></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link" href="home">Home</a>
                            </li>
                            <c:if test="${sessionScope.acc == null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="login">Login</a>
                                </li>
                            </c:if>
                            <c:if test = "${sessionScope.acc != null}">
                                <li class="nav-item">
                                    <a class="nav-link text-info" href="login">Hello ${sessionScope.acc.user}</a>
                                </li>
                                <c:if test="${sessionScope.acc.isSell == 1}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="manager">Manager Product</a>
                                        <a class="nav-link" href="blogManager"><i class="fas fa-tasks"></i>Manager Blog</a>
                                        <a class="nav-link" href="manage-feedback"><i class="fas fa-tasks"></i>Manage Feedback</a>
                                    </li> 
                                </c:if>
                                <c:if test="${sessionScope.acc.isAdmin == 1}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="accountManager">Manager Account</a>
                                    </li> 
                                </c:if>
                            </c:if>

                            <li class="nav-item">&nbsp;
                            </li> 
                        </ul>
                    </nav>
                </div>
                <div class="col-6">
                    <section class="jumbotron text-center" style="background-color: white;">
                        <div class="container">
                            <h1 class="jumbotron-heading"><img src="image/MainLogo.png" alt="Main Logo" width="60%"/></h1>
                            <p class="lead text-muted mb-0">Điện thoại, Tablet, Laptop, Phụ kiện chính hãng giá tốt...</p>
                        </div>
                    </section>
                </div>
            </div>
            <div class="row" style="margin-top: 20px;">
                <!--                <div class="col-4">
                                    <div class="card mb-3" style="max-width: 540px;">
                                        <div class="row g-0">
                                            <div class="col-md-4">
                                                <img src="image/AccountIcon.png" alt="..." width="70%;">
                                            </div>
                                            <div class="col-md-8">
                                                <div class="card-body">
                                                    <h5 class="card-title">Total Accounts</h5>
                                                    <h5 class="card-title">${totalAccount}</h5>
                                                    <p class="card-text"><small class="text-muted">Last updated 1 mins ago</small></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="card mb-3" style="max-width: 540px;">
                                        <div class="row g-0">
                                            <div class="col-md-4">
                                                <img src="image/ProductIcon.png" alt="..." width="70%;">
                                            </div>
                                            <div class="col-md-8">
                                                <div class="card-body">
                                                    <h5 class="card-title">Total Products</h5>
                                                    <h5 class="card-title">${totalProduct}</h5>
                                                    <p class="card-text"><small class="text-muted">Last updated 1 mins ago</small></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>-->
                <div class="col-4">
                    <div class="card mb-3" style="max-width: 540px;">
                        <div class="row g-0">
                            <div class="col-md-4">
                                <img src="image/InvoiceIcon.png" alt="..." width="70%;" style="margin-top: 10px;">
                            </div>
                            <c:if test="${sessionScope.acc.isAdmin == 1}">
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title">Total Invoices</h5>
                                        <h5 class="card-title">${totalCart}</h5>
                                        <p class="card-text"><small class="text-muted">Last updated 1 mins ago</small></p>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${sessionScope.acc.isAdmin != 1}">
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-title">Total Products Sold</h5>
                                        <h5 class="card-title">${totalCart}</h5>
                                        <p class="card-text"><small class="text-muted">Last updated 1 mins ago</small></p>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <style>
                        td{
                            padding-right:3em;
                            padding:10px; border: 1px solid;
                        }
                    </style>
                    <c:if test="${sessionScope.acc.isAdmin == 1}">
                        <h2>Order ID: ${OrderId}
                            (${sta})</h2>
                        </c:if>
                        <c:if test="${sessionScope.acc.isAdmin != 1}">
                        <h2>Seller ID: ${sellerId} (Sold Product List)</h2>
                    </c:if>
                    <br/><br/>
                    <table id="customers" style="margin-left:3em; border: 1px solid;">
                        <thead >
                            <tr >
                                <th style="text-align: center;">No.</th>
                                <th style="text-align: center;">User Name</th>
                                <th style="text-align: center;">Product ID</th>
                                <th style="text-align: center;">Product Name</th>                               
                                <th style="text-align: center;">Product Price</th>                               
                                <th style="text-align: center;">Product Image</th>                               
                                <th style="text-align: center;">Quantity</th>                               
                                <th style="text-align: center;">Shipping Address</th>                               
                                <th style="text-align: center;">Customer's Phone Number</th>                               
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${invoiceDetail}" varStatus="x">
                                <tr style="padding:2px; border: 1px solid">
                                    <td>${x.index+1}</td>
                                    <td>${item.customerName}</td>
                                    <td>${item.productID}</td>
                                    <td>${item.productName}</td>
                                    <td>
                                        <fmt:formatNumber type = "number" maxFractionDigits = "1" value = "${item.productPrice}"/>VNĐ
                                    </td>
                                    <td><img height="250px" width="200px" src="image/${item.imageLink}"/></td>
                                    <td>${item.quantity}</td>
                                    <td>${item.shipAddress}</td>                      
                                    <td>${item.phoneNumber}</td>
                                    <c:set var="userId" value="${item.userId}"/>
                                    <c:set var="total" value="${total + item.productPrice}" />
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </br>
                    </hr>
                    <h1  style="color:red;">Total:                                        
                        <fmt:formatNumber type = "number" maxFractionDigits = "1" value = "${total}"/> 
                        VNĐ</h1>

                    <c:set var="n" value="Waiting for Confirmation"></c:set>
                    <c:if test="${sta eq n}">
                        <form action="approveOrder" id="form1">
                            <input type="hidden" value="${OrderId}" name="orderId"/>
                            <input type="hidden" value="${userId}" name="userId"/>
                        </form>
                        <div style="margin:10px; float:left">
                            <a  onclick="formAutoSubmit('form1')"><button>Approve</button></a>
                            <!--<a  href ="approveOrder?id=${OrderId}"><button>Approve</button></a>-->
                        </div>
                        <form action="cancelOrder" id="form2">
                            <input type="hidden" value="${OrderId}" name="orderId"/>
                            <input type="hidden" value="${userId}" name="userId"/>
                        </form>
                        <div style="margin:10px; float:right;">
                            <a  onclick="formAutoSubmit('form2')"><button>Cancel</button></a>
                        </div>
                    </c:if>

                    <c:set var="k" value="Packaging"></c:set>
                    <c:if test="${sta eq k}">
                        <form action="deliverOrder" id="form1">
                            <input type="hidden" value="${OrderId}" name="orderId"/>
                            <input type="hidden" value="${userId}" name="userId"/>
                        </form>
                        <div style="margin:10px; float:left">
                            <a  onclick="formAutoSubmit('form1')"><button>Deliver</button></a>
                            <!--<a  href ="approveOrder?id=${OrderId}"><button>Approve</button></a>-->
                        </div>                       
                    </c:if>


                </div>
                <div class="row">
                    <div class="col-6">
                        <h3>Most Selling Product</h3>
                        <c:forEach var="o" items="${top3MostSellD}">
                            <h6>${o.name}</h6>
                            <div class="progress">
                                <div class="progress-bar progress-bar-striped bg-info" role="progressbar" style="width: ${o.proportion}%" aria-valuenow="${o.proportion}" aria-valuemin="0" aria-valuemax="100">${o.amount}</div>
                            </div>
                            <br>
                        </c:forEach>
                    </div>
                    <div class="col-6">
                        <h3>Least Selling Product</h3>
                        <c:forEach var="o" items="${top3LeastSellD}">
                            <h6>${o.name}</h6>
                            <div class="progress">
                                <div class="progress-bar progress-bar-striped bg-info" role="progressbar" style="width: ${o.proportion}%" aria-valuenow="${o.proportion}" aria-valuemin="0" aria-valuemax="100">${o.amount}</div>
                            </div>
                            <br>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <script type="text/javascript">

                function formAutoSubmit(name) {

                    var frm = document.getElementById(name);

                    frm.submit();

                }
            </script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    </body>
</html>