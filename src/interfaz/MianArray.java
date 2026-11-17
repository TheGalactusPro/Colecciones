package interfaz;

import modelo.AppStore;
import modelo.Juego;
import utilitario.Validacion;

import javax.swing.*;
import java.util.Scanner;

public class MianArray {

    private static Scanner sc = new Scanner(System.in);
    private static AppStore tienda = new AppStore();

    public static void main(String[] args) {
        int opc;
        int opc2 = 0;

        tienda.cargarDatos();

        do {
            menu();
            opc = Integer.parseInt(sc.nextLine());
            switch (opc) {
                case 1: {
                    ingresarJuegos();
                } break;

                case 2: {
                    mostrarJuegos();
                } break;

                case 3: {
                    buscarJuego();
                } break;

                case 4: {
                    comprarJuego();
                } break;

                case 5: {
                    venderJuego();
                } break;

                case 6: {
                    juegoMasVendido();
                } break;

                case 7: {
                    System.out.println("Gracias por usar nuestros servicios");
                } break;

                default:
                    System.out.println("Opción no válida");
            }

            // Ayuda de la IA
            if (opc != 7) {
                System.out.println("\n¿Desea continuar?");
                System.out.println("1. Sí");
                System.out.println("2. No");
                System.out.print("Seleccione una opción: ");

                opc2 = Integer.parseInt(sc.nextLine());

                if (opc2 == 2) {
                    opc = 7;
                    System.out.println("Gracias por usar nuestros servicios");
                }
            }

        } while (opc != 7);
    }

    public static void menu() {
        System.out.println("--------------------AppStore Udla--------------------");
        System.out.println("1. Ingresar Juego");
        System.out.println("2. Mostrar juegos");
        System.out.println("3. Buscar juego");
        System.out.println("4. Comprar");
        System.out.println("5. Vender");
        System.out.println("6. Juego más vendido");
        System.out.println("7. Salir");
        System.out.print("Ingrese una opcion: ");
    }

    public static void ingresarJuegos() {
        System.out.println("INGRESO DE DATOS");

        String nombre = Validacion.leerString("Ingrese el nombre");
        String categoria = Validacion.leerString("Ingrese la categoría");

        System.out.print("Ingrese el tamanio: ");
        int tamanio = Integer.parseInt(sc.nextLine());

        System.out.print("Ingrese el precio: ");
        double precio = Double.parseDouble(sc.nextLine());

        System.out.print("Ingrese el stock inicial: ");
        int licenciasDisponibles = Integer.parseInt(sc.nextLine());

        if (tienda.agregarJuego(new Juego(nombre, categoria, tamanio, precio, licenciasDisponibles))) {
            JOptionPane.showMessageDialog(null, "El juego se ha agregado con éxito!!");
        } else {
            JOptionPane.showMessageDialog(null, "El juego no pudo ser agregado");
        }
    }

    public static void mostrarJuegos() {

        if (!tienda.getJuegos().isEmpty()) {
            for (Juego j : tienda.getJuegos()) {
                System.out.println(j);
            }
        }else{
            System.out.println("No existen juegos para mostrar");
        }
    }

    public static void buscarJuego() {
        String nombre = Validacion.leerString("Ingrese el nombre del juego");

        Juego j = tienda.buscarJuego(nombre);

        if (j != null) {
            System.out.println("Juego encontrado:");
            System.out.println(j);
        }else{
            System.out.println("El juego no existe");
        }
    }

    public static void comprarJuego() {
        String nombre = Validacion.leerString("Ingrese el nombre del juego");

        Juego j = tienda.buscarJuego(nombre);

        if (j != null) {
            System.out.print("Ingrese la cantidad a comprar: ");
            int cantidad = Integer.parseInt(sc.nextLine());

            if (tienda.comprar(nombre, cantidad)) {
                JOptionPane.showMessageDialog(null, "Compra realizada con éxito");
            }else{
                JOptionPane.showMessageDialog(null, "Cantidad no válida");
            }
        } else {
            System.out.println("El juego no existe");
        }
    }

    public static void venderJuego() {
        String nombre = Validacion.leerString("Ingrese el nombre del juego");

        System.out.print("Ingrese la cantidad a vender: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        double total = tienda.vender(nombre, cantidad);

        if (total > 0) {
            JOptionPane.showMessageDialog(null,
                    "Venta realizada con éxito\nTotal: $" + total);
        }else{
            JOptionPane.showMessageDialog(null,
                    "No se pudo realizar la venta");
        }
    }

    public static void juegoMasVendido() {
        if (!tienda.getJuegos().isEmpty()) {
            String nombre = tienda.masVendido();
            if (!nombre.isEmpty()) {
                System.out.println("JUEGO MÁS VENDIDO: " + nombre);
            }else{
                System.out.println("No hay juegos vendidos todavía");
            }
        }else{
            System.out.println("No existen juegos registrados");
        }
    }
}
