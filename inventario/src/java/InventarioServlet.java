import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InventarioServlet")
public class InventarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBConnector.getConnection()) {
            if (conn != null) {
                // Hay conexión
                System.out.println("Conexión a la base de datos establecida correctamente.");

                // Aquí puedes manejar las operaciones del inventario
                // Por ejemplo, agregar un producto
                String nombre = request.getParameter("nombre");
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                double precio = Double.parseDouble(request.getParameter("precio"));

                String query = "INSERT INTO producto (nombre, cantidad, precio) VALUES (?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, nombre);
                    ps.setInt(2, cantidad);
                    ps.setDouble(3, precio);
                    ps.executeUpdate();

                    System.out.println("Producto agregado correctamente.");
                }
            } else {
                // No hay conexión
                System.out.println("Error: No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            // Manejar la excepción de SQL de manera adecuada
            e.printStackTrace();
            throw new ServletException("Error en la operación con la base de datos", e);
        }

        // Redirige a la página index.jsp en ambos casos
        response.sendRedirect("index.jsp");
    }
}
