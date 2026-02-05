package platzi.play;

import java.util.Scanner;

public class main {
    public static void main(String[] args){
        System.out.println("Hola Mundo en Java");

        Scanner scan = new Scanner(System.in);
        System.out.println("Cual es tu nombre?: ");
        String nombre = scan.nextLine();
        System.out.println("Saludos ingeniero " + nombre + " :)");
    }
}
