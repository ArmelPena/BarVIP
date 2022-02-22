<%-- 
    Document   : index
    Created on : 25/01/2022, 11:08:42 PM
    Author     : ARMEL_000
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <title>BarVIP - Registro de Usuario</title>
    </head>
    <body>
        <div id="div1" class="container mt-6 col-lg-3  bg-warning"><br>
            <form action="Controlador">
                <div class="form-group text-center">
                    <img src="Img/usuario_72px.png" higth="80" width="80"/>
                    <p><strong>Bienvenidos a Login de BarVIP</strong></p>
                </div>
                <div class="form-group">
                    <label>Identificación:</label>
                    <input class="form-control" type="text" name="txtident" placeholder="Registre su identificación">
                </div>
                <div class="form-group">
                    <label>Nombres:</label>
                    <input class="form-control" type="text" name="txtnom" placeholder="Registre nombre usuario">
                </div>
                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="txtCorreo" placeholder="example@gmail.com" class="form-control">
                </div>
                <div class="form-group">
                    <label>Dirección residencia:</label>
                    <input class="form-control" type="text" name="txtdireccion" placeholder="Registre su direccion">
                </div>                
                <br>
                <div>
                    <input type="submit" class="btn btn-success btn-block"  name="accion" value="Crear usuario">
                </div>
                <br>
                <div class="sign-up text-center text-dark">
                    Registra más tarde? <a href="Controlador?accion=Salir">Salir</a>
		</div> 
                <br>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>
