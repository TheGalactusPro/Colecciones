package utilitario;

import java.util.Scanner;

public class Validacion {
    private static Scanner sc = new Scanner(System.in);

    public static String leerString (String mensaje){
        String valor;
        do {
            System.out.print(mensaje +": ");
            valor = sc.nextLine();
            if (valor == null || valor.trim().isEmpty()){
                System.out.println("No puede ser nulo, ni estar vacío");
            }
        }while(valor == null || valor.trim().isEmpty());
        return valor;
    }
}