package Contenido;

import java.util.Scanner;
import Contenido.Administradores.IngresarAfi;
import Contenido.Administradores.IngresarEmp;
import Contenido.Administradores.ModificarAfi;

public class App {
    public static void main(String[] args) {
        IngresarAfi admin = new IngresarAfi();
        ModificarAfi modificarAfi = new ModificarAfi();
        IngresarEmp empre = new IngresarEmp(); 
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
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    admin.agregarAfiliado(); 
                    break;
                case 9:
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
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
                    break;
            }
        }
    }
}


