package Contenido;

import java.util.Scanner;

import Contenido.Administradores.GenerarContrato;
import Contenido.Administradores.IngresarIPS;
import Contenido.Administradores.IngresarOrden;
import Contenido.Administradores.ListadoCitas;
import Contenido.Administradores.ListarIndepGUI;
import Contenido.Bancos.GenerarReportePago;
import Contenido.Bancos.IngresarPago;
import Contenido.Cotizantes.info_cotizantes;

public class App {
    public static void main(String[] args) {
        IngresarIPS Ips = new IngresarIPS();
        ModificarAfiGUI ma = new ModificarAfiGUI();
        GenerarContrato contrato = new GenerarContrato();
        IngresarOrden orden = new IngresarOrden();
        GenerarReportePago paguito = new GenerarReportePago();
        IngresarPago pago = new IngresarPago();
        info_cotizantes cotiInfo = new info_cotizantes();
        ListadoCitas lista = new ListadoCitas();
        ListarIndepGUI list = new ListarIndepGUI();
        

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Agregar afiliado");
            System.out.println("2. Modificar afiliado");
            System.out.println("3. Ingresar empresa");
            System.out.println("4. Modificar Empresa");
            System.out.println("5. Ingresar Ips");
            System.out.println("6. Modificar Ips");
            System.out.println("7. Registrar Contrato");
            System.out.println("8. Modificar Contrato");
            System.out.println("9. Ingresar Orden de servicio");
            System.out.println("10. Modificar Orden de servicio");
            System.out.println("11. Generar Reporte de afiliados por estado");
            System.out.println("12. Generar Reporte de pago entre fechas");
            System.out.println("13. Listado de citas en una fecha e IPS en particular.");
            System.out.println("14. agregar pago");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("¡Hasta luego!");
                    System.exit(0); 
                    break;
                case 5:
                    list.mostrarAfiliadosIndependientes(ma);
                    break;
                case 7:
                    contrato.IngresarContrato();
                    break;
                case 9:
                    cotiInfo.seleccionarCotizanteSwing();;
                    break;
                case 12:
                    lista.listar();
                    break;
                case 13:
                    pago.ingresarPago(ma);
                    break;
                case 14:
                System.out.println("Ejecutando seleccionarCotizanteSwing");
                cotiInfo.seleccionarCotizanteSwing();
                case 15:
                    paguito.generarReporteGrafico(ma);
                break;

                default:
                    System.out.println("Opción inválida, intente de nuevo.");
                    break;
            }
        }
    }
}


