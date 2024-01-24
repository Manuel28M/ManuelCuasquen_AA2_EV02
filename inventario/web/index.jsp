<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1 {
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        a {
            color: #3498db;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Lista de Productos</h1>
    <table>
        <tr>
            <th><a href="nuevo.jsp">Nuevo</a></th>
            <th colspan="5">Tabla de Productos</th>
        </tr>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Cantidad</th>
            <th>Precio</th>
            <th>Editar</th>
            <th>Eliminar</th>
        </tr>

        <% 
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/inventario", "root", "");

                String query = "SELECT * FROM producto";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();

                while (rs.next()) {
        %>
                    <tr>
                        <td><%= rs.getInt("id") %></td>
                        <td><%= rs.getString("nombre") %></td>
                        <td><%= rs.getInt("cantidad") %></td>
                        <td><%= rs.getDouble("precio") %></td>
                        <td><a href="editar?id=<%= rs.getInt("id") %>&nombre=<%= rs.getString("nombre") %>&cantidad=<%= rs.getInt("cantidad") %>&precio=<%= rs.getDouble("precio") %>">Editar</a></td>

                            <td><a href="eliminar?id=<%= rs.getInt("id") %>">Eliminar</a></td>
                    </tr>
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Cerrar recursos
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        %>
    </table>
</body>
</html>
