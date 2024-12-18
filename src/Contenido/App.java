package Contenido;

import java.util.Scanner;

import Contenido.Administradores.GenerarContrato;
import Contenido.Administradores.GenerarReporteAfi;
import Contenido.Administradores.IngresarAfi;
import Contenido.Administradores.IngresarEmp;
import Contenido.Administradores.IngresarIPS;
import Contenido.Administradores.IngresarOrden;
import Contenido.Administradores.ModificarAfi;
import Contenido.Administradores.ModificarContrato;
import Contenido.Administradores.ModificarEmp;
import Contenido.Administradores.ModificarIps;
import Contenido.Administradores.ModificarOrden;
import Contenido.Administradores.prueba1;
import Contenido.Bancos.IngresarPago;

public class App {
    public static void main(String[] args) {
        IngresarAfi admin = new IngresarAfi();
        IngresarEmp empre = new IngresarEmp(); 
        IngresarIPS Ips = new IngresarIPS();
        GenerarContrato contrato = new GenerarContrato();
        IngresarOrden orden = new IngresarOrden();
        ModificarAfi modificarAfi = new ModificarAfi();
        ModificarEmp modificarE = new ModificarEmp();
        ModificarIps modificarI = new ModificarIps();
        ModificarContrato modificarC = new ModificarContrato();
        ModificarOrden modificarO = new ModificarOrden();
        GenerarReporteAfi report = new GenerarReporteAfi();
        prueba1 prueba = new prueba1();
        IngresarPago pago = new IngresarPago();
        

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
                case 1:
                    admin.agregarAfiliado(); 
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    System.exit(0); 
                    break;
                case 2:
                    modificarAfi.Actualizar();
                    break;
                case 3:
                    empre.IngresarE();
                    break;
                case 4:
                    modificarE.modificarAtributoEmpresa();
                    break;
                case 5:
                    Ips.agregarIps();
                    break;
                case 6:
                    modificarI.modificarAtributoIps();
                    break;
                case 7:
                    contrato.IngresarContrato();
                    break;
                case 8:
                    modificarC.modificarAtributoContrato();
                    break;
                case 9:
                    orden.GenerarOrden();
                    break;
                case 10:
                    modificarO.modificarValorOrden();
                    break;
                case 11:
                    report.consola();
                    break;
                case 12:
                    prueba.pruebita();
                    break;
                case 13:
                    pago.agregarPago();
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
                    break;
            }
        }
    }
}


