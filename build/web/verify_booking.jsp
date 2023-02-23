<%-- 
    Document   : index
    Created on : Dec 13, 2020, 4:37:59 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="stylesheet" href="/css/style.css">-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
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
        <div class="container d-flex justify-content-center">
            <div class="wrapper w-50 mt-5">
                <div><h4>${VERIFY_MSG}</h4></div>
                <div class="wrapper-header">
                    <p>Verify</p>
                </div>
                <div class="wrapper-content">
                    <form id="verifyForm" action="VerifyBooking">
                        <div class="row">
                            <div class="col-10">
                                <div class="form-group">
                                    <label>Verify code</label>
                                    <input type="text" class="form-control" name="txtVerifyCode" placeholder="CODE">
                                </div>
                            </div>
                            <div class="col-2 d-flex align-items-end">
                                <div>
                                    <%
                                        ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LfUtAwaAAAAAPwIDbK8xnT4aiRtg05g0bNCXNkf", "6LfUtAwaAAAAAMCcElnNF7Jmq2u8oCZwxoD6_9Ht", false);
                                        out.print(c.createRecaptchaHtml(null, null));
                                    %>
                                    <button type="submit" class="btn btn-primary">Verify</button>
                                </div>
                            </div>
                        </div>
                        
                        <div class="g-recaptcha" data-sitekey="6LdVuAwaAAAAAMGhzI6IcvyADT79Zzxb_8Opk_iJ"></div>
                    </form>
                </div>
            </div>
        </div>
        <script>
        </script>
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>
