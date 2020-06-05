<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<html lang="en">
    <head>
        <title>Hosting City a Hosting Category Flat Bootstrap responsive Website Template | Registration :: w3layouts</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Hosting City Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <spring:url value="/resources/css/bootstrap.css" var="bootstrap"/>
        <spring:url value="/resources/css/style.css" var="style"/>
        <spring:url value="/resources/css/font-awesome.css" var="font"/>

        <spring:url value="/resources/js/jquery-1.11.1.min.js" var="jquery"/>
        <spring:url value="/resources/js/bootstrap.js" var="jsbootstrap"/>
        <spring:url value="/resources/js/SmoothScroll.min.js" var="smoothjs"/>
        
        <link href="${bootstrap}" rel="stylesheet" type="text/css" media="all" />
        
        <link rel="stylesheet" href="${style}" type="text/css" media="all" />
        
        <link href="${font}" rel="stylesheet"> 
        
        <link href="//fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
        <link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,700italic,700,400italic,300italic,300' rel='stylesheet' type='text/css'>
        
        <script src="${jquery}"></script>
        <script src="${jsbootstrap}"></script>
        <script src="${smoothjs}"></script>
        <script type="text/javascript">
            jQuery(document).ready(function ($) {
                $(".scroll").click(function (event) {
                    event.preventDefault();
                    $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
                });
            });
        </script>
        <style>  
            .error{color:red}  
        </style>  
    </head>
    <body>
        <!-- header-top -->
        <div class="header-top">
            <div class="container">
                <div class="w3layouts-address">
                    <ul>
                    </ul>
                </div>
                <div class="clearfix"> </div>
            </div>
        </div>
        <!-- //header-top -->
        <!-- header -->
        <div class="header">
            <div class="container">
                <nav class="navbar navbar-default">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header"> 
                        <div class="w3layouts-logo">
                            <h1><a>Universidad Catolica <span>Colombia</span></a></h1>
                        </div>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse nav-wil" id="bs-example-navbar-collapse-1">
                        <nav>
                        </nav>
                    </div>
                    <!-- /.navbar-collapse -->
                </nav>
            </div>
        </div>
        <!-- //header -->
        <!-- about-heading -->
        <div class="about-heading">
            <h2>POSTULAR <span>MONITORIA</span></h2>
        </div>

        <!-- //about-heading -->
        <div class="registration">
            <div class="container">
                <div class="signin-form profile">
                    <h3>:: MONITORIA ::</h3>
                    
                    <%
                        String err = String.valueOf(request.getAttribute("errores"));
                        if(err != null){
                            err = err.replace("[", "").replace("]", "");
                            System.out.println(err);
                        }
                    %>
                    <div class="login-form">
                        <span style="color:red">
                        <%if (request.getParameter("errores") != null) {%>
                            <%=err%>
                        <%}%>
                        </span>
                        
                    </div>

                    <div class="login-form">
                        <form:form action="personas/monitorias/validate" commandName="form" method="post">
                            <form:errors path="*" cssClass="error"> </form:errors>

                            <form:input type="text" name="identificacion" path="codigoEstudiante" placeholder="Codigo del estudiante" />


                            <form:input type="password" name="contrasena" path="claveEstudiante" placeholder="Clave"/>

                            <form:input type="text" name="codigoMateria" path="codigoMateria" placeholder="Codigo de la materia"/>

                            <input type="submit" value="POSTULAR">
                        </form:form>
                    </div>
                    <div class="login-form">
                        <%if (request.getParameter("aviso") != null) {%>
                        <%= request.getParameter("aviso")%>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>

        <!-- footer -->
        <div class="footer">        
            <div class="copyright">
                <p>Universidad Catolica de Colombia© Todos los derechos reservados 2020</p>
            </div>
        </div>
        <!-- //footer -->
        <script type="text/javascript" src="js/move-top.js"></script>
        <script type="text/javascript" src="js/easing.js"></script>
        <!-- here stars scrolling icon -->
        <script type="text/javascript">
            $(document).ready(function () {
                /*
                 var defaults = {
                 containerID: 'toTop', // fading element id
                 containerHoverID: 'toTopHover', // fading element hover id
                 scrollSpeed: 1200,
                 easingType: 'linear' 
                 };
                 */

                $().UItoTop({easingType: 'easeOutQuart'});

            });
        </script>
        <!-- //here ends scrolling icon -->
    </body>	
</html>
