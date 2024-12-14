package Contenido.Administradores;
import java.sql.*;
import java.util.Scanner;

import Contenido.ConectividadSQL;

public class IngresarAfi {
    private Connection connection; //esto es necesario para poder conectar con la base de datos
    public IngresarAfi() {
        connection = ConectividadSQL.obtenerConexion(); // Utilizamos ConectividadSQL para obtener la conexión
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        }
    }

    public void agregarAfiliado() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Numero de documento: "); //vamos declarando de uno en uno cada uno de los atributos que necesitamos
            Integer num_doc= scanner.nextInt();
            scanner.nextLine();

            System.out.print("Tipo de documento: ");
            String tipo_doc = scanner.nextLine();

            System.out.print("Apellidos: ");
            String apellidos = scanner.nextLine();

            System.out.print("Nombres: ");
            String nombres = scanner.nextLine();

            System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
            String fecha = scanner.nextLine();

            System.out.print("Genero: ");
            String genero = scanner.nextLine();

            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();

            System.out.print("Telefono: ");
            String telefono = scanner.nextLine();

            System.out.print("Ciudad: ");
            String ciudad = scanner.nextLine();

            System.out.print("Estado Civil: ");
            String civil = scanner.nextLine();

            System.out.print("Correo: ");
            String correo = scanner.nextLine();

            System.out.print("Estado (activo o inactivo): ");
            String estado = scanner.nextLine();

            System.out.print("Ingrese el Nit de su Ips: ");
            String nitIpsString = scanner.nextLine();

            if (nitIpsString == null || nitIpsString.trim().isEmpty()) {
                System.out.println("Error: NIT de IPS no puede ser vacío.");
                return;
            }

            int Nit_ips;
            try {
                Nit_ips = Integer.parseInt(nitIpsString);
            } catch (NumberFormatException e) {
                System.out.println("Error: El NIT de la IPS debe ser un número válido.");
                return;
            }

            //luego vamos a insertarlo dentro de la base de datos, para ello, ponemos en orden cada uno de los atributos, como normalmente se haria en un insert
            String query = "INSERT INTO afiliado (num_doc, tipo_doc, apellidos, nombres, fecha_nac, genero, direccion, telefono, ciudad, estado_civil, correo, estado, nit_ips) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, num_doc);
                statement.setString(2, tipo_doc);
                statement.setString(3, apellidos);
                statement.setString(4, nombres);
                statement.setDate(5, Date.valueOf(fecha));;
                statement.setString(6, genero);
                statement.setString(7, direccion);
                statement.setString(8, telefono);
                statement.setString(9, ciudad);
                statement.setString(10, civil);
                statement.setString(11, correo);
                statement.setString(12, estado);
                statement.setInt(13,Nit_ips);
                statement.executeUpdate();
                System.out.println("Afiliado agregado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el afiliado: " + e.getMessage());
        }
    }
    
}

