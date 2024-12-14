package Contenido.Administradores;

import java.sql.*;
import java.util.Scanner;
import Contenido.ConectividadSQL;

public class ModificarAfi {
    private Connection connection;

    public ModificarAfi() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
    
    public void Actualizar() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese el Número de Documento del afiliado a modificar: ");
            Integer num_doc = scanner.nextInt();
            scanner.nextLine(); 
            
            System.out.println("Modificar los siguientes datos del afiliado (deje en blanco si no desea modificar):");
    
            System.out.print("Tipo de documento: ");
            String tipo_doc = scanner.nextLine();
    
            System.out.print("Apellidos: ");
            String apellidos = scanner.nextLine();
    
            System.out.print("Nombres: ");
            String nombres = scanner.nextLine();
    
            System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
            String fecha = scanner.nextLine();
    
            System.out.print("Género: ");
            String genero = scanner.nextLine();
    
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();
    
            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();
    
            System.out.print("Ciudad: ");
            String ciudad = scanner.nextLine();
    
            System.out.print("Estado Civil: ");
            String civil = scanner.nextLine();
    
            System.out.print("Correo: ");
            String correo = scanner.nextLine();
    
            System.out.print("Estado (activo o inactivo): ");
            String estado = scanner.nextLine();
    
            System.out.print("Ingrese el Nit de su IPS: ");
            int Nit_ips = scanner.nextInt();
            scanner.nextLine();  
    
            String query = "UPDATE afiliado SET tipo_doc = ?, apellidos = ?, nombres = ?, fecha_nac = ?, " +
                           "genero = ?, direccion = ?, telefono = ?, ciudad = ?, estado_civil = ?, correo = ?, " +
                           "estado = ?, nit_ips = ? WHERE num_doc = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tipo_doc.isEmpty() ? null : tipo_doc);
                statement.setString(2, apellidos.isEmpty() ? null : apellidos);
                statement.setString(3, nombres.isEmpty() ? null : nombres);
                if (fecha.isEmpty()) {
                    statement.setNull(4, java.sql.Types.DATE);
                } else {
                    statement.setDate(4, Date.valueOf(fecha));
                }
                statement.setString(5, genero.isEmpty() ? null : genero);
                statement.setString(6, direccion.isEmpty() ? null : direccion);
                statement.setString(7, telefono.isEmpty() ? null : telefono);
                statement.setString(8, ciudad.isEmpty() ? null : ciudad);
                statement.setString(9, civil.isEmpty() ? null : civil);
                statement.setString(10, correo.isEmpty() ? null : correo);
                statement.setString(11, estado.isEmpty() ? null : estado);
                statement.setInt(12, Nit_ips);
                statement.setInt(13, num_doc);  
                
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Afiliado modificado exitosamente.");
                } else {
                    System.out.println("No se encontró un afiliado con ese número de documento.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar el afiliado: " + e.getMessage());
        }
    }
}    