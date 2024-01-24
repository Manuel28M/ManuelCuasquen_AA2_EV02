<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Producto - <%= request.getParameter("nombre") %></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 800px;
            width: 100%;
        }

        h1 {
            color: #333;
        }

        form {
            margin-top: 20px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: #fff;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Editar Producto - <%= request.getAttribute("nombre") %></h1>

        <form action="editar" method="post">
            <input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" value="<%= request.getAttribute("nombre") %>">
            <label for="cantidad">Cantidad:</label>
            <input type="text" name="cantidad" value="<%= request.getAttribute("cantidad") %>">
            <label for="precio">Precio:</label>
            <input type="text" name="precio" value="<%= request.getAttribute("precio") %>">
            <input type="submit" value="Guardar Cambios">
        </form>
    </div>
</body>
</html>
