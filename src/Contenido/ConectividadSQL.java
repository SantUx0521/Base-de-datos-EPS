package Contenido;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectividadSQL {
    public static Connection obtenerConexion() {
        String url = "jdbc:postgresql://localhost:5432/Proyecto%20BD";
        String user = "postgres"; 
        String password = "123"; 
        
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return conn; 
    }
}
