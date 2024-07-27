package src.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utilidades {
    public Connection getConnection(String path) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", path));
            System.out.println("Se ha establecido la conexion");
            
        } catch (SQLException err) {
            System.out.println("Error en la conexion a base de datos");
            err.printStackTrace();
        }
        return conn;
    }
}
