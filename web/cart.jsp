<%-- 
    Document   : index
    Created on : Dec 13, 2020, 4:37:59 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="stylesheet" href="/css/style.css">-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <style>
            .page-title {
                font-size: 32px;
            }

            .wrapper {
                border-radius: 6px;
                border: 1px solid #ddd;
                background: white;
                transition: 1s;
            }

            .wrapper-header {
                background: #eee;
                border-bottom: 1px solid #ddd;
                border-radius: 6px 6px 0 0;
                width: 100%;
                height: 50px;
                padding: 0.8rem 0.8rem 0 0.8rem;
            }

                .wrapper-header p{
                    font-weight: 500;
                    color: darkorange;
                    font-size: 16px;
                }

            .wrapper-content {
                padding: 1rem;
            }
            .button-link {
              background: none!important;
              border: none;
              padding: 0!important;
              color: #069;
              text-decoration: underline;
              cursor: pointer;
            }

            /*End of wrapeer*/
        </style>
        <title>Cart</title>
    </head>
    <body>
        <c:if test="${ROLE != 'CUSTOMER'}">
            <c:redirect url="index.jsp"/>
        </c:if>
            <!--Navbar-->
            <nav class="navbar navbar-dark bg-primary navbar-expand-lg">
              <div class="container">
                  <c:choose>
                      <c:when test="${empty ROLE}">
                          <a class="navbar-brand" href="login.jsp">Login</a>
                      </c:when>
                      <c:when test="${not empty ROLE}">
                          <form id="logoutForm" action="Logout">
                              <a id="logoutLink" class="navbar-brand" href="#">Logout</a>
                          </form>
                      </c:when>
                  </c:choose>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                  <ul class="navbar-nav">
                    <li class="nav-item">
                      <a class="nav-link" href="SearchRoom">Home</a>
                    </li>
                    <c:choose>
                      <c:when test="${ROLE == 'ADMIN'}">
                          <li class="nav-item">
                            <a class="nav-link" href="create_discount.jsp">Add discount</a>
                          </li>
                      </c:when>
                      <c:when test="${ROLE == 'CUSTOMER'}">
                          <li class="nav-item">
                            <a class="nav-link" href="cart.jsp">Your cart</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" href="BookingHistory">Booking history</a>
                          </li>
                      </c:when>
                    </c:choose>
                  </ul>
                </div>
              </div>
            </nav>
            <!--End of navbar-->
            <div class="container">
                <div class="wrapper mt-4">
                    <h3>${APPLY_DISCOUNT_MSG}</h3>
                    <h3>${REMOVE_FROM_CART}</h3>
                    <div class="wrapper-header">
                        <p>YOUR CART </p>
                    </div>
                    <div class="wrapper-content">
                        <c:choose>
                            <c:when test="${empty Cart}">
                                <h3>Your cart is empty than your knowledge :(</h3>
                            </c:when>
                            <c:when test="${not empty Cart}">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ROOM</th>
                                            <th>TYPE</th>
                                            <th>Check In Date</th>
                                            <th>Check Out Date</th>
                                            <th>1 day</th>
                                            <th>Price</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${Cart}" var="room">
                                            <tr>
                                                <td>${room.roomID}</td>
                                                <td>${room.roomType}</td>
                                                <td>${room.checkInDate}</td>
                                                <td>${room.checkOutDate}</td>
                                                <td>${room.oneDayPrice}</td>
                                                <td>${room.price}</td>
                                                <td>
                                                    <form action="RemoveFromCart">
                                                        <input type="hidden" value="${room.roomID}" name="txtRoomID">
                                                        <button type="submit" class="btn btn-outline-danger" name="btnAction" value="REMOVE FROM CART">Remove</button>
                                                    </form>
                                                </td>
                                            </tr>
                                            <c:set var="total" value="${total + room.price}" />
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                        </c:choose>
                    </div>
                    <c:if test="${not empty Cart}">
                        <div class="wrapper-content pt-0">
                            <form action="ApplyDiscount" method="POST">
                                <div class="row">
                                    <div class="col-7"></div>
                                    <div class="col-3">
                                        <input type="text" name="txtDiscountID" class="form-control" placeholder="INPUT YOUR DISCOUNT CODE">
                                    </div>
                                    <div class="col-2">
                                        <button class="btn btn-primary" type="submit" value="APPLY DISCOUNT" name="btnAction">Apply</button>
                                    </div>
                                </div>
                            </form>
                            <form action="Booking" method="POST">
                                <div class="row mt-4">
                                    <div class="col-7"></div>
                                    <div class="col-3">
                                        <c:if test="${not empty DISCOUNT_CODE}">
                                            <p>Your booking is discounted ${DISCOUNT_CODE.percent}%</p>
                                            <c:set var="total" value="${total / 100 * (100 - DISCOUNT_CODE.percent)}"/>
                                        </c:if>
                                        <span><b>Total price: ${total}</b></span>
                                    </div>
                                    <div class="col-2 d-flex align-items-end">
                                        <div>
                                            <input type="hidden" value="${DISCOUNT_CODE.discountID}" name="txtDiscountID">
                                            <input type="hidden" value="${total}" name="txtTotalPrice">
                                            <button style="submit" class="btn btn-success">Check out</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            
                        </div>
                    </c:if>
                </div>
            </div>
                <script>
                    document.getElementById("logoutLink").onclick = function(){
                        document.getElementById("logoutForm").submit();
                    }
                    
                </script>
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>
