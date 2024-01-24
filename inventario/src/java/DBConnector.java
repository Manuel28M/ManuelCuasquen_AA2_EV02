import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3307/inventario";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "";

    static {
        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar el controlador JDBC");
        }
    }

    public static Connection getConnection() {
        try {
            // Establecer la conexión
            Connection connection = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("Conexión a la base de datos establecida correctamente.");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al establecer la conexión a la base de datos: " + e.getMessage());
            return null;
        }
    }
}
