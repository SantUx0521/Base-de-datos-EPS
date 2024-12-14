package Contenido.Administradores;
import java.sql.*;
import java.util.Scanner;
import Contenido.ConectividadSQL;

public class IngresarOrden {
    private Connection connection;

    public IngresarOrden() {
        connection = ConectividadSQL.obtenerConexion();  
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }
    }
    public void RegistrarOrden(Ordenes_servicio orden){
        String query = "INSERT INTO ordenes (cod_orden, fecha, doc_paciente, medico, diagnostico) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orden.getCod_orden());
            statement.setDate(2, orden.getFecha());
            statement.setInt(3, orden.getDoc_paciente());
            statement.setString(4, orden.getMedico());
            statement.setString(5, orden.getDiagnostico());
            statement.executeUpdate();
            System.out.println("Orden agregada correctamente.");
        }
        catch (SQLException e) {
            System.out.println("Error al registrar la Ips: " + e.getMessage());
        }
    }
    //todo lo independiente de la consola está arriba ^^
    public void GenerarOrden(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el codigo de la orden: ");
        int cod_orden = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Ingrese la fecha de la orden (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        Date fecha = Date.valueOf(fechaStr);

        System.out.println("Ingrese el DNI del paciente: ");
        int doc_paciente = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nombre del medico encargado: ");
        String medico = scanner.nextLine();

        System.out.println("Ingrese el diagnostico: ");
        String diagnostico = scanner.nextLine();

        Ordenes_servicio orden = new Ordenes_servicio(cod_orden, fecha, doc_paciente, medico, diagnostico);
        RegistrarOrden(orden); //esto creo que es unicamente necesario para cuando es por consola, asumo que por GUI se resuelve de forma distninta o no? ni idea pero funciona
    }
}

