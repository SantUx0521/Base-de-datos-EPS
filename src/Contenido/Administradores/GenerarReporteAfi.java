package Contenido.Administradores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Contenido.ConectividadSQL;

public class GenerarReporteAfi {
        private Connection connection;

        public GenerarReporteAfi() {
            connection = ConectividadSQL.obtenerConexion();  
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("Error en la conexión a la base de datos.");
            }
        }
        public void consola(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Que desea?");
            System.out.println("1. Listado Activos");
            System.out.println("2. Listado Inactivos");
            System.out.println("opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1: 
                    GenerarReporteAct();
                    break;
                case 2:
                    GenerarReporteInact();
                    break;
            }
        }
    
        public void GenerarReporteAct(){
            String query = "SELECT estado, nombres FROM afiliado GROUP BY estado, nombres having estado = 'Activo' ";
    
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
    
                System.out.println("Reporte:");
                System.out.println("Estado | Nombre");
                while (resultSet.next()) {
                    String estado = resultSet.getString("estado");
                    String nombre = resultSet.getString("nombres");
                    System.out.println(estado + " | " + nombre);
                }
            } catch (SQLException e) {
                System.err.println("Error al generar el reporte: " + e.getMessage());
            }
        }
        public void GenerarReporteInact(){
            String query = "SELECT estado, nombres FROM afiliado GROUP BY estado, nombres having estado = 'Inactivo' ";
    
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
    
                System.out.println("Reporte:");
                System.out.println("Estado | Nombre");
                while (resultSet.next()) {
                    String estado = resultSet.getString("estado");
                    String nombre = resultSet.getString("nombres");
                    System.out.println(estado + " | " + nombre);
                }
            } catch (SQLException e) {
                System.err.println("Error al generar el reporte: " + e.getMessage());
            }
        }
}
        

