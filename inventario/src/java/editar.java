import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editar")
public class editar extends HttpServlet {
    
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Obtén los parámetros de la URL
    String idParam = request.getParameter("id");
    String nombreParam = request.getParameter("nombre");
    String cantidadParam = request.getParameter("cantidad");
    String precioParam = request.getParameter("precio");

    // Establece los atributos en el request
    request.setAttribute("id", idParam);
    request.setAttribute("nombre", nombreParam);
    request.setAttribute("cantidad", cantidadParam);
    request.setAttribute("precio", precioParam);

    // Muestra el formulario de edición con los datos actuales
    request.getRequestDispatcher("/editarProducto.jsp").forward(request, response);
}



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/inventario", "root", "")) {
            if (conn != null) {
                // Obtén los parámetros del formulario
                String idParam = request.getParameter("id");
                int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;

                String nombre = request.getParameter("nombre");
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                double precio = Double.parseDouble(request.getParameter("precio"));

                // Actualiza los datos del producto en la base de datos
                String query = "UPDATE producto SET nombre=?, cantidad=?, precio=? WHERE id=?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, nombre);
                    ps.setInt(2, cantidad);
                    ps.setDouble(3, precio);
                    ps.setInt(4, id);

                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        // Éxito al actualizar, redirige a la página de lista de productos
                        response.sendRedirect("index.jsp");
                    } else {
                        // Fallo al actualizar
                        response.getWriter().println("Error al actualizar el producto.");
                    }
                }
            } else {
                // No hay conexión
                System.out.println("Error: No se pudo establecer la conexión a la base de datos.");
                response.getWriter().println("Error interno del servidor.");
            }
        } catch (SQLException | NumberFormatException e) {
            // Manejar la excepción de manera adecuada
            e.printStackTrace();
            throw new ServletException("Error en la operación con la base de datos", e);
        }
    }
}
