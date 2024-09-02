<%-- 
    Document   : login
    Created on : 1/09/2024, 4:44:55 p. m.
    Author     : Edison
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h2>Inicio de sesión</h2>
        <form action="LoginServlet" method="post">
        <label for="username">Usuario:</label>
        <input type="text" id="username" name="username" required>
        <br><br>
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required>
        <br><br>
        <button type="submit">Iniciar Sesión</button>
    </form>
    <div class="error">
        <c:if test="${not empty errorMessage}">
            <p>${errorMessage}</p>
        </c:if>
    </div>
    </body>
</html>