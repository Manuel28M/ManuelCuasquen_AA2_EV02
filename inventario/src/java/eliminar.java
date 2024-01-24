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

@WebServlet("/eliminar")
public class eliminar extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtén el ID del producto a eliminar desde los parámetros de la URL
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            
            // Llama al método de eliminación
            eliminarProducto(id, response);
        } else {
            response.getWriter().println("ID de producto no proporcionado.");
        }
    }

    private void eliminarProducto(int id, HttpServletResponse response) throws IOException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/inventario", "root", "")) {
            if (conn != null) {
                // Elimina el producto de la base de datos
                String query = "DELETE FROM producto WHERE id=?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setInt(1, id);

                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        // Éxito al eliminar, redirige a la página de lista de productos
                        response.sendRedirect("index.jsp");
                    } else {
                        // Fallo al eliminar
                        response.getWriter().println("Error al eliminar el producto.");
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
            throw new IOException("Error en la operación con la base de datos", e);
        }
    }
}
