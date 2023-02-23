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
        <title>Login page</title>
    </head>
    <body>
        <c:if test="${not empty ROLE}">
            <c:redirect url="index.jsp"/>
        </c:if>
        <div class="container">
            <p class="page-title text-center mt-5">New World manage system</p>
            <h2 class="text-center" style="color: text;">${ERROR}</h2>
            <div class="wrapper w-50 m-auto mt-5">
                <div class="wrapper-header">
                    <p>Login</p>
                </div>
                <div class="wrapper-content">
                    <form action="Login" method="POST">
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" name="txtEmail" placeholder="Login with email">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" class="form-control" name="txtPassword" placeholder="Password">
                        </div>
                        <div class="row mt-3">
                            <div class="col-8">
                                
                                <a class="btn w-100 btn-primary btn-dark" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/LoginGoogle&response_type=code&client_id=117195839838-qvhtl6j4soq4je9cskeobmof6l4isrnb.apps.googleusercontent.com&approval_prompt=force">
                                    Login With Google
                                </a> 
                                
                            </div>
                            <div class="col-4">
                                <button type="submit" name="btnAction" value="LOGIN" class="btn btn-primary w-100">Login</button>
                            </div>
                        </div>
                        <div class="mt-3">
                            <a class="btn btn-outline-info btn-lg w-100" href="register.jsp">Not a member ? Register now</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
            
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>
