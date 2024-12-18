package Contenido.Bancos;

import Contenido.ConectividadSQL;
import Contenido.Administradores.ips;
import Contenido.Bancos.Pago;
import java.sql.*;
import java.util.Scanner;

public class IngresarPago {

    private Connection connection;

    public IngresarPago() {
        connection = ConectividadSQL.obtenerConexion();  
    }

    public boolean registrarPagos(Pago pago){
        String query = "INSERT INTO pago_aporte (codigo_pago, num_rad, fecha_pago, valor) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pago.getCodigoPago()); //al momento de especificar los atributos de la tabla se llama a la clase creada anteriormente con sus respectivos getters y setters
            statement.setInt(2, pago.getNumRadicado());
            statement.setDate(3, (Date) pago.getFechaPago());
            statement.setDouble(4, pago.getValor()); // de esta forma esta más organizado y seguramente de menos problemas
            statement.executeUpdate();
            return true;
    }catch (SQLException e) {
        System.out.println("Error al registrar la empresa: " + e.getMessage());
        return false;
    }
}
    public void agregarPago(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el codigo del pago:");
        int codigo_pago = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el numero de radicado del contrato:");
        int num_rad = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese la fecha del pago (YYYY-MM-DD):");
        String fecha1 = scanner.nextLine();
        Date fecha = Date.valueOf(fecha1);

        System.out.println("Ingrese el valor del pago: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Pago paga = new Pago(codigo_pago, num_rad, fecha, valor);
        registrarPagos(paga);
    }
}
