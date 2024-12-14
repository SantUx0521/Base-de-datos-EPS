package Contenido.Administradores;
import java.sql.*;
import java.util.Scanner;
import Contenido.ConectividadSQL;

public class GenerarContrato {
    private Connection connection;
    public GenerarContrato() {
        connection = ConectividadSQL.obtenerConexion();  
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }
    }
    public void IngresarContrato(){
        String query = "INSERT INTO contrato (num_radicado, cot_num, empre_nit, ) VALUES (?, ?, ?)";
    }
    
}
