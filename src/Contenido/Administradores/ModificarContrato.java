package Contenido.Administradores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import Contenido.ConectividadSQL;

public class ModificarContrato {
    private Connection connection;

    public ModificarContrato() {
        connection = ConectividadSQL.obtenerConexion();
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
    
    public void modificarAtributoContrato() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese el numero de radicado del contrato a modificar: ");
            int nit = scanner.nextInt();
            scanner.nextLine();
            // es importante que no se pueda modificar el numero de radicado pues, si es modificado se daña toda la base de datos XD
            System.out.println("Seleccione el dato que desea modificar:");
            System.out.println("1. fecha del reporte");
            System.out.println("2. Salario base del cotizante");
            System.out.println("3. estado del contrato"); // en este caso no veo necesario modificar nada más, pues el tipo de cotizante y demás no deberian ser modificables
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            String field;
            switch (opcion) {
                case 1: 
                    field = "fecha_rec"; 
                    System.out.println("Recuerde ingresarlo YYYY-MM-DD");
                    break;
                case 2: 
                    field = "salario_base"; 
                    break;
                case 3:
                    field = "estado";
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
