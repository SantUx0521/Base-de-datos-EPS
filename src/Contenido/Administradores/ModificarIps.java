package Contenido.Administradores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import Contenido.ConectividadSQL;

public class ModificarIps {
    private Connection connection;

    public ModificarIps() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
    public void modificarAtributoIps() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese el NIT de la IPS a modificar: ");
            int nit = scanner.nextInt();
            scanner.nextLine();
            // es importante que no se pueda modificar el NIT pues, si es modificado se daña toda la base de datos XD
            System.out.println("Seleccione el dato que desea modificar:");
            System.out.println("1. Razón Social");
            System.out.println("2. Nivel de atencion");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            String field;
            switch (opcion) {
                case 1: 
                    field = "razon_social"; 
                    break;
                case 2: 
                    field = "nivel_atencion"; 
                    break;
                default:
                    System.out.println("Opción no válida.");
                    return;
            }

            System.out.print("Ingrese el nuevo valor para " + field + ": ");
            String nuevoValor = scanner.nextLine();

            String query = "UPDATE IPS SET " + field + " = ? WHERE E_NIT = ?"; // declaramos el update para que el nuevo valor se agregue a la base de datos, modificando unicamente la empresa seleccionada

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nuevoValor); //declaramos el nuevo valor para el atributo como primer campo (SET X = nuevoValor)
                statement.setInt(2, nit); //WHERE E_NIT = nit de la empresa que deseamos modificar

                int filasActualizadas = statement.executeUpdate(); //actualizamos en la base de datos
                if (filasActualizadas > 0) {
                    System.out.println("El campo " + field + " fue modificado exitosamente.");
                } else {
                    System.out.println("No se encontró una IPS con el NIT ingresado.");
                }
            } catch (SQLException e) {
                System.err.println("Error al modificar el atributo de la IPS: " + e.getMessage());
            }
        }
    }
}
