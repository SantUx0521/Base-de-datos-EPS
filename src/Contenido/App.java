package Contenido;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Administrador admin = new Administrador(); 
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Agregar afiliado");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    admin.agregarAfiliado(); 
                    break;
                case 2:
                    System.out.println("¡Hasta luego!");
                    System.exit(0); 
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
                    break;
            }
        }
    }
}


