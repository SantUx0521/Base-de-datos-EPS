package Contenido.Administradores;

import java.sql.*;
import java.util.Scanner;
import Contenido.ConectividadSQL;

public class GenerarContrato {
    private Connection connection;

    public GenerarContrato() {
        connection = ConectividadSQL.obtenerConexion();  
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error en la conexión a la base de datos.");
        }
    }

    public boolean registrarContrato(Contrato contrato) {
        String query = "INSERT INTO contrato (num_radicado, cot_num, empre_nit, fecha_rec, salario_base, estado, tipo_cot, nit_empresa, rut) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, contrato.getNumRadicado());
            statement.setInt(2, contrato.getCotNum());
            statement.setInt(3, contrato.getEmpreNit());
            statement.setDate(4, new java.sql.Date(contrato.getFechaRec().getTime()));  
            statement.setDouble(5, contrato.getSalarioBase());
            statement.setString(6, contrato.getEstado());
            statement.setString(7, contrato.getTipoContrato());

            if (contrato.getTipoContrato().equalsIgnoreCase("Dependiente")) { //en caso de que sea dependiente se ingresa el Nit de la empresa
                statement.setInt(8, contrato.getNitEmpresa()); 
                statement.setNull(9, Types.INTEGER);  
            } else if (contrato.getTipoContrato().equalsIgnoreCase("Independiente")) { // en caso de que no, se ingresa el rut del independiente
                statement.setNull(8, Types.INTEGER);  //en ambos casos lo que hace es poner NULL al atributo que no le corresponda
                statement.setInt(9, contrato.getRut()); 
            }

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Contrato registrado correctamente.");
                return true;
            } else {
                System.out.println("No se pudo registrar el contrato.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar el contrato: " + e.getMessage());
            return false;
        }
    }

    public void IngresarContrato() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el número de radicado:");
        int numRadicado = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Ingrese el número de documento del cotizante:");
        int cotNum = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el NIT de la empresa:");
        int empreNit = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese la fecha de recepción (YYYY-MM-DD):");
        String fechaRecStr = scanner.nextLine();
        Date fechaRec = Date.valueOf(fechaRecStr);

        System.out.println("Ingrese el salario base:");
        double salarioBase = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Ingrese el estado (Activo/Inactivo/Retirado):");
        String estado = scanner.nextLine();

        System.out.println("Ingrese el tipo de contrato (Dependiente/Independiente):");
        String tipoContrato = scanner.nextLine();

        int nitEmpresa = 0;
        int rut = 0;
        if (tipoContrato.equalsIgnoreCase("Dependiente")) { //se solicita al administrador especificar el tipo de contrario y solicita lo correspondiente en alguno de los dos casos
            System.out.println("Ingrese el NIT de la empresa del cotizante:");
            nitEmpresa = scanner.nextInt();
            scanner.nextLine(); 
        } else if (tipoContrato.equalsIgnoreCase("Independiente")) {
            System.out.println("Ingrese el RUT del cotizante:");
            rut = scanner.nextInt();
            scanner.nextLine(); 
        }
        // declaramos el contrato con los nuevos atributos que se le agregan, siendo NULL aquellos que no correspondan 
        Contrato contrato = new Contrato(numRadicado, cotNum, empreNit, fechaRec, salarioBase, estado, tipoContrato, nitEmpresa, rut);
        try {
            if (registrarContrato(contrato)) {
                System.out.println("Contrato ingresado correctamente.");
            } else {
                System.out.println("No se pudo ingresar el contrato.");
            }
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
