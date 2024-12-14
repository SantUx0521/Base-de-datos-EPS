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
    //A partir de ingresar EMP encontre un metodo mucho mejor y más sencillo para conectar con la base de datos y que probablemente de menos problemas luego jajaj
    public boolean registrarEmpresa(Empresa empresa) {
        String query = "INSERT INTO EMPRESA (e_nit, razon_social, ciudad, direccion, nombre_contacto) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, empresa.getNit()); //al momento de especificar los atributos de la tabla se llama a la clase creada anteriormente con sus respectivos getters y setters
            statement.setString(2, empresa.getRazonSocial());
            statement.setString(3, empresa.getCiudad());
            statement.setString(4, empresa.getDireccion()); // de esta forma esta más organizado y seguramente de menos problemas
            statement.setString(5, empresa.getNombreContacto());

            int filasAfectadas = statement.executeUpdate(); //aqui me tocó mover cosas porque no queria funcionar pero no es obligatorio, lo unico importante es el executeUpdate

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
    // de igual forma hice algo sencillo para irlo probando, lo importante es lo que está arriba 
    public void IngresarE() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el NIT de la empresa:");
        int nit = scanner.nextInt();
        scanner.nextLine();  

        System.out.println("Ingrese la razón social de la empresa:");
        String razonSocial = scanner.nextLine();

        System.out.println("Ingrese la ciudad de la empresa:");
        String ciudad = scanner.nextLine();

        System.out.println("Ingrese la dirección de la empresa:");
        String direccion = scanner.nextLine();

        System.out.println("Ingrese el nombre del contacto:");
        String nombreContacto = scanner.nextLine();

        Empresa empresa = new Empresa(nit, razonSocial, ciudad, direccion, nombreContacto); //en este caso toca definirlo para que la entrada corresponda a los getters
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
