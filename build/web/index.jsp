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
        <title>Index page</title>
    </head>
    <body>
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
                <p class="page-title mt-2">Hello ${ROLE}</p>
                <c:if test="${ADD_TO_CART_MSG == 'Existed'}">
                    <h3>You book phòng này rùi :(</h3>
                </c:if>
                    <h3>${BOOKING_MESSAGE}</h3>
                <h2 style="color: red">${ERROR}</h2>
                <div class="wrapper mt-2">
                    <div class="wrapper-header">
                        <p>Search room</p>
                    </div>
                    <div class="wrapper-content">
                        <form action="SearchRoom" method="POST">
                            <div class="row">
                                <div class="col-6">
                                    <div class="form-group">
                                        <label>Check-in date</label>
                                        <input type="date" name="txtCheckInDate" class="form-control" value="${CHECK_IN_DATE}" required>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-group">
                                        <label>Check-out date</label>
                                        <input type="date" name="txtCheckOutDate" class="form-control" value="${CHECK_OUT_DATE}" required> 
                                    </div>
                                </div>
                            </div>
                            <div class="row d-flex align-items-end">
                                <div class="col-10">
                                    <div class="form-group">
                                        <label>Room type</label>
                                        <select class="form-control" name="selectRoomType">
                                            <option value="0">SELECT ROOM TYPE</option>
                                            <c:forEach items="${ROOM_TYPE_LIST}" var="roomType">
                                                <option value="${roomType.roomTypeID}">
                                                    ${roomType.roomType}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-2 d-flex justify-content-end">
                                    <button type="submit" name="btnAction" value="ROOM SEARCH" class="btn btn-primary w-100">Search</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="wrapper mt-3">
                    <div class="wrapper-content">
                        <table class="table table-striped">
                            <thead>
                              <tr>
                                <th scope="col">Room number</th>
                                <th scope="col">Room type</th>
                                <th scope="col">Description</th>
                                <th scope="col">Price</th>
                                <th scope="col">Action</th>
                              </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${LIST_ROOM}" var="room">
                                    <tr>
                                        <th scope="row">${room.roomID}</th>
                                        <td>${room.roomType}</td>
                                        <td>${room.description}</td>
                                        <td>${room.price}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${empty ROLE}">
                                                    <span>Login to booking</span>
                                                </c:when>
                                                <c:when test="${ROLE == 'CUSTOMER'}">
                                                    <form action="AddToCart" method="POST">
                                                        <input type="hidden" name="txtRoomID" value="${room.roomID}">
                                                        <button type="submit" class="btn btn-success" name="btnAction" value="ADD TO CART">Add to cart</button>
                                                        
                                                    </form>
                                                </c:when>
                                                <c:when test="${ROLE == 'ADMIN'}">
                                                    <span>ADMIN</span>
                                                </c:when>
                                            </c:choose>
                                            
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
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
