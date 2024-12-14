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
            int num_doc = scanner.nextInt();
            scanner.nextLine();  

            String queryBase = "UPDATE afiliado SET ";
            String whereClause = " WHERE num_doc = ?";
            String field;
            String value;

            while (true) {
                System.out.println("\nSeleccione el dato que desea modificar:");
                System.out.println("1. Tipo de documento");
                System.out.println("2. Apellidos");
                System.out.println("3. Nombres");
                System.out.println("4. Fecha de nacimiento");
                System.out.println("5. Género");
                System.out.println("6. Dirección");
                System.out.println("7. Teléfono");
                System.out.println("8. Ciudad");
                System.out.println("9. Estado Civil");
                System.out.println("10. Correo");
                System.out.println("11. Estado (activo/inactivo)");
                System.out.println("12. Nit de la IPS");
                System.out.println("13. Salir");
                System.out.print("Opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); 

                if (opcion == 13) {
                    System.out.println("Modificación finalizada.");
                    break;
                }

                switch (opcion) {
                    case 1: field = "tipo_doc"; break;
                    case 2: field = "apellidos"; break;
                    case 3: field = "nombres"; break;
                    case 4: field = "fecha_nac"; break;
                    case 5: field = "genero"; break;
                    case 6: field = "direccion"; break;
                    case 7: field = "telefono"; break;
                    case 8: field = "ciudad"; break;
                    case 9: field = "estado_civil"; break;
                    case 10: field = "correo"; break;
                    case 11: field = "estado"; break;
                    case 12: field = "nit_ips"; break;
                    default: 
                        System.out.println("Opción no válida.");
                        continue;
                }

                System.out.print("Ingrese el nuevo valor para " + field + ": ");
                value = scanner.nextLine();

                String query = queryBase + field + " = ? " + whereClause;

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    if ("fecha_nac".equals(field)) {
                        if (value.isEmpty()) {
                            statement.setNull(1, java.sql.Types.DATE);
                        } else {
                            statement.setDate(1, Date.valueOf(value));
                        }
                    } else if ("nit_ips".equals(field)) {
                        statement.setInt(1, Integer.parseInt(value));
                    } else {
                        statement.setString(1, value.isEmpty() ? null : value);
                    }

                    statement.setInt(2, num_doc);
                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("El campo " + field + " fue modificado exitosamente.");
                    } else {
                        System.out.println("No se encontró un afiliado con ese número de documento.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error al modificar el campo: " + e.getMessage());
                }
            }
        }
    }
}
