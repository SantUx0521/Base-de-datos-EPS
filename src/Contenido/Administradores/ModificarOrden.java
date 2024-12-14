package Contenido.Administradores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import Contenido.ConectividadSQL;

public class ModificarOrden {
    private Connection connection;

    public ModificarOrden() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
        
    public void modificarValorOrden() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese el codigo de la orden a modificar: ");
            int nit = scanner.nextInt();
            scanner.nextLine();
            // es importante que no se pueda modificar el codigo pues, si es modificado se daña toda la base de datos XD
            System.out.println("Seleccione el dato que desea modificar:");
            System.out.println("1. fecha");
            System.out.println("2. DNI del paciente");
            System.out.println("3. Medico encargado");
            System.out.println("4. diagnostico");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            String field;
            switch (opcion) {
                case 1: 
                    field = "fecha"; 
                    System.out.println("Recuerde ingresarlo YYYY-MM-DD");
                    break;
                case 2: 
                    field = "doc_paciente"; 
                    break;
                case 3:
                    field = "medico";
                    break;
                case 4:
                    field = "diagnostico";
                    break;
                default:
                    System.out.println("Opción no válida.");
                    return;
            }

            System.out.print("Ingrese el nuevo valor para " + field + ": ");
            String nuevoValor = scanner.nextLine();

            String query = "UPDATE ORDENES SET " + field + " = ? WHERE E_NIT = ?"; // declaramos el update para que el nuevo valor se agregue a la base de datos, modificando unicamente la empresa seleccionada

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
