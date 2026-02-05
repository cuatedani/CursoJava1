package platzi.play;

import java.util.Scanner;

public class main {
    public static void main(String[] args){
        final int id;

        System.out.println("BIENVENIDO A PLATZI PLAY");

        Scanner scan = new Scanner(System.in);
        System.out.println("Cual es tu nombre? ");
        String nombre = scan.nextLine();
        System.out.println("Cual es tu edad? ");
        int edad = scan.nextInt();
        System.out.println("Saludos ingeniero " + nombre + " :)");

        if(edad < 18){
            System.out.println("Tu se aplicará restriccion de edad a la plataforma");
        } else {
            System.out.println("Bienvenido disfruta de todo el catalogo");
        }
    }
}
