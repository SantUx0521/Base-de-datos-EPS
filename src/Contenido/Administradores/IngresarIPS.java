package Contenido.Administradores;

import java.sql.*;
import java.util.Scanner;

import Contenido.ConectividadSQL;

public class IngresarIPS {
    private Connection connection;
    public IngresarIPS() {
        connection = ConectividadSQL.obtenerConexion();  
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }
    }
    public void registrarIPS(ips Ips){

        String query = "INSERT INTO ips (nit, razon_social, nivel_atencion) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Ips.getNit());
            statement.setString(2, Ips.getRazonSocial());
            statement.setString(3, Ips.getNivelAtencion());
            statement.executeUpdate();
            System.out.println("Ips agregada correctamente.");
        }
        catch (SQLException e) {
            System.out.println("Error al registrar la Ips: " + e.getMessage());
        }
    }
    public void agregarIps(){
         Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el NIT de la Ips:");
        int nit = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese la razón social de la Ips:");
        String razonSocial = scanner.nextLine();

        System.out.println("Ingrese el nivel de atencion de la IPS: ");
        String nivel_atencion = scanner.nextLine();

        ips Ips = new ips(nit,razonSocial,nivel_atencion);
        registrarIPS(Ips);
    }
}
