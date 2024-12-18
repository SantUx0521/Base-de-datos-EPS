package Contenido.Administradores;


import Contenido.Bancos.GenerarReportePago;
import Contenido.Bancos.Pago;
import Contenido.ConectividadSQL;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class prueba1 {
    public void pruebita() {
        Connection connection = ConectividadSQL.obtenerConexion();
        if (connection != null) {
            GenerarReportePago repo = new GenerarReportePago(connection);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el número de documento del afiliado: ");
                int numeroDocumentoAfiliado = scanner.nextInt();

                System.out.println("Ingrese la fecha de inicio (YYYY-MM-DD): ");
                Date fechaInicio = Date.valueOf(scanner.next());

                System.out.println("Ingrese la fecha de fin (YYYY-MM-DD): ");
                Date fechaFin = Date.valueOf(scanner.next());

                List<Pago> pagos = repo.listarPagosPorAfiliadoYRangoFechas(numeroDocumentoAfiliado, fechaInicio, fechaFin);

                if (pagos.isEmpty()) {
                    System.out.println("No se encontraron pagos para el afiliado especificado en el rango de fechas dado.");
                } else {
                    System.out.println("=== Reporte de Pagos ===");
                    pagos.forEach(System.out::println);
                }
        }
    }
}
