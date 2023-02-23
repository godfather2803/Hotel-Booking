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
                          <form id="logoutForm" action="LogoutController">
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
                      <a class="nav-link" href="index.jsp">Home</a>
                    </li>
                    <c:choose>
                      <c:when test="${empty ROLE}">
                          <a class="navbar-brand" href="login.jsp">Login</a>
                      </c:when>
                      <c:when test="${not empty ROLE}">
                          <form id="logoutForm" action="LogoutController">
                              <a id="logoutLink" class="navbar-brand" href="#">Logout</a>
                          </form>
                      </c:when>
                    </c:choose>
                  </ul>
                </div>
              </div>
            </nav>
            <!--End of navbar-->
            <div class="container">
                
            </div>
                <script>
                    document.getElementById("logoutLink").onclick = function(){
                        document.getElementById("logoutForm").submit();
                    }
                </script>
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>
