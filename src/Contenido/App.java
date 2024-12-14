package Contenido;

import java.util.Scanner;
import Contenido.Administradores.IngresarAfi;
import Contenido.Administradores.ModificarAfi;

public class App {
    public static void main(String[] args) {
        IngresarAfi admin = new IngresarAfi();
        ModificarAfi modificarAfi = new ModificarAfi(); 
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Agregar afiliado");
            System.out.println("2. Modificar afiliado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    admin.agregarAfiliado(); 
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    System.exit(0); 
                    break;
                case 2:
                    modificarAfi.Actualizar();
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
                    break;
            }
        }
    }
}


