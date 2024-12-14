package Contenido.Administradores;

import java.sql.*;
import java.util.Scanner;
import Contenido.ConectividadSQL;

public class IngresarEmp {
    private Connection connection;
    public IngresarEmp() {
        connection = ConectividadSQL.obtenerConexion();  
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }
    }

    public boolean registrarEmpresa(Empresa empresa) {
        // Preparar el query para la inserción
        String query = "INSERT INTO EMPRESA (e_nit, razon_social, ciudad, direccion, nombre_contacto) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, empresa.getNit());
            statement.setString(2, empresa.getRazonSocial());
            statement.setString(3, empresa.getCiudad());
            statement.setString(4, empresa.getDireccion());
            statement.setString(5, empresa.getNombreContacto());

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Empresa registrada correctamente.");
                return true;
            } else {
                System.out.println("No se pudo registrar la empresa.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar la empresa: " + e.getMessage());
            return false;
        }
    }

    // Método que solicita la información de la empresa e invoca el registro
    public void IngresarE() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos
        System.out.println("Ingrese el NIT de la empresa:");
        int nit = scanner.nextInt();
        scanner.nextLine();  // Consumir salto de línea

        System.out.println("Ingrese la razón social de la empresa:");
        String razonSocial = scanner.nextLine();

        System.out.println("Ingrese la ciudad de la empresa:");
        String ciudad = scanner.nextLine();

        System.out.println("Ingrese la dirección de la empresa:");
        String direccion = scanner.nextLine();

        System.out.println("Ingrese el nombre del contacto:");
        String nombreContacto = scanner.nextLine();

        // Crear la entidad Empresa
        Empresa empresa = new Empresa(nit, razonSocial, ciudad, direccion, nombreContacto);

        // Intentar registrar la empresa
        try {
            if (registrarEmpresa(empresa)) {
                System.out.println("La empresa fue registrada con éxito.");
            } else {
                System.out.println("No se pudo registrar la empresa.");
            }
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
