package Contenido.Administradores;
import java.sql.*;
import java.util.Scanner;

import Contenido.ConectividadSQL;

public class IngresarAfi {
    private Connection connection;

    public IngresarAfi() {
        connection = ConectividadSQL.obtenerConexion(); //Utilizamos esta funcion para conectarnos con la base de datos
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        }
    }

    public void agregarAfiliado() { //vamos agregando uno por uno los atributos de la tabla
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Numero de documento: ");
            Integer num_doc = scanner.nextInt();
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

            System.out.print("Estado (Activo/Inactivo/Retirado): ");
            String estado = scanner.nextLine();

            System.out.print("Ingrese el Nit de su IPS: "); // importante que el Nit sea valido debido a que esta es la llave foranea y si es diferente no va a crear al afiliado
            String nitIpsString = scanner.nextLine();

            if (nitIpsString == null || nitIpsString.trim().isEmpty()) {
                System.out.println("Error: NIT de IPS no puede ser vacío."); //Aqui garantizamos que este espacio no quede vacio, el Nit no puede ser NULL
                return;
            }

            int Nit_ips;
            try {
                Nit_ips = Integer.parseInt(nitIpsString);
            } catch (NumberFormatException e) {
                System.out.println("Error: El NIT de la IPS debe ser un número válido.");
                return;
            }

            //Insertamos bajo una consulta SQL a la tabla, importante que mantenga el mismo orden y al final los ? tengan la misma cantidad de atributos
            String query = "INSERT INTO Afiliado (Num_Doc, Tipo_Doc, Apellidos, Nombres, Fecha_Nac, Genero, Direccion, Telefono, Ciudad, Estado_Civil, Correo, Estado, Nit_ips) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, num_doc);
                statement.setString(2, tipo_doc);
                statement.setString(3, apellidos);
                statement.setString(4, nombres);
                statement.setDate(5, Date.valueOf(fecha));
                statement.setString(6, genero);
                statement.setString(7, direccion); // importante que el numero antes de la variable tenga el mismo orden que en la consulta (luego encontre un metodo mejor)
                statement.setString(8, telefono);
                statement.setString(9, ciudad);
                statement.setString(10, civil);
                statement.setString(11, correo);
                statement.setString(12, estado);
                statement.setInt(13, Nit_ips);
                statement.executeUpdate(); //necesario para subir correctamente a la base de datos la informacion
                System.out.println("Afiliado agregado correctamente.");
            }

            System.out.println("Elija que es el afiliado.");
            System.out.println("1. Cotizante");
            System.out.println("2. Beneficiario");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();

            switch (opcion) { //indicamos si el afiliado es beneficiario o cotizante y dependiendo se completa la informacion faltante
                case 1:
                    System.out.print("Fecha de afiliación (YYYY-MM-DD): ");
                    String fechaAfil = scanner.next();

                    System.out.print("Salario: ");
                    float salario = scanner.nextFloat();
                    scanner.nextLine();

                    String rango = Rango(salario);

                    crearCotizante(num_doc, tipo_doc, fechaAfil, salario, rango);
                    break;

                case 2:
                    System.out.print("Número de documento del cotizante: ");
                    int c_num_doc = scanner.nextInt();

                    scanner.nextLine();
                    System.out.print("Parentesco con el cotizante: ");
                    String parentesco = scanner.nextLine();

                    crearBeneficiario(num_doc, tipo_doc, c_num_doc, parentesco);
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar el afiliado: " + e.getMessage());
        }
    }
    public String Rango(float salario) { //debido a que en el enunciado especifica el rango salarial, lo podemos automatizar con esto
        final float salarioMinimo = 1300000; 
        float salarioMin2 = salarioMinimo * 2; 
        float salarioMin5 = salarioMinimo * 5; 
    
        if (salario < salarioMin2) {
            return "A";  
        } else if (salario >= salarioMin2 && salario <= salarioMin5) {
            return "B";  
        } else {
            return "C";  
        }
    }
    // de igual forma hacemos la consulta sql en este caso conservando el numero y tipo de documento del afiliado anteriormente creado
    public void crearCotizante(Integer num_doc, String tipo_doc, String fechaAfil, float salario, String rango) {
        String query = "INSERT INTO Cotizante (Num_Doc, Tipo_Doc, Fecha_Afil, Salario, Rango) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, num_doc);
            statement.setString(2, tipo_doc);
            statement.setDate(3, Date.valueOf(fechaAfil));
            statement.setFloat(4, salario);
            statement.setString(5, rango);
            statement.executeUpdate();
            System.out.println("Cotizante registrado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar el cotizante: " + e.getMessage());
        }
    }
    // lo mismo con el beneficiario
    public void crearBeneficiario(Integer num_doc, String tipo_doc, int c_num_doc, String parentesco) {
        String query = "INSERT INTO Beneficiario (Num_Doc, Tipo_Doc, C_Num_Doc, Parentesco) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, num_doc);
            statement.setString(2, tipo_doc);
            statement.setInt(3, c_num_doc);
            statement.setString(4, parentesco);
            statement.executeUpdate();
            System.out.println("Beneficiario registrado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar el beneficiario: " + e.getMessage());
        }
    }
}
