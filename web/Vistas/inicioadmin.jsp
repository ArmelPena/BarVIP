<%-- 
    Document   : index
    Created on : 17/01/2022, 10:59:14 PM
    Author     : ARMEL_000
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>BarVIP - Tieda de licores</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-warning">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">BarVIP Admiinstrador</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="Controlador?accion=productos">Productos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="Controlador?accion=clientes">Clientes</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="Controlador?accion=clientes">Ventas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="Controlador?accion=clientes">Pagos</a>
                        </li>                        
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Sessión
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a> <img src="Img/usuarioLogeado_72px.png" height="80" width="80"></a></li>
                                <li><a> ${nom}</a></li>
                                <li><a> ${correo}</a></li>
                                <li></li>
                                <li><a href="Controlador?accion=Salir" class="dropdown">Salir</a></li>
                            </ul>
                        </li>                        
                    </ul>
                </div>
            </div>
        </nav>

        <div class = "container mt-2">
            <center>
                <img src="Img/banner-cervezas_1.jpg" alt="" />
            </center>
            
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>
