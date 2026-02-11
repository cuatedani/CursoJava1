package platzi.play.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ScannerUtils {
    public static Scanner scanner = new Scanner(System.in);

    public String capturarTexto(String mensaje){
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    public int capturarInt(String mensaje){
        System.out.println(mensaje);

        while (!scanner.hasNextInt()){
            System.out.println("Dato no valido");
            scanner.next();
        }

        int entero = scanner.nextInt();
        scanner.nextLine();
        return entero;
    }

    public double capturarDouble(String mensaje){
        System.out.println(mensaje);

        while (!scanner.hasNextDouble()){
            System.out.println("Dato no valido");
            scanner.next();
        }

        double decimal = scanner.nextDouble();
        scanner.nextLine();
        return decimal;
    }

    public long capturarLong(String mensaje){
        System.out.println(mensaje);

        while (!scanner.hasNextLong()){
            System.out.println("Dato no valido");
            scanner.next();
        }

        long largo = scanner.nextLong();
        scanner.nextLine();
        return largo;
    }

    public LocalDate capturarFecha(String mensaje) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println(mensaje + " (dd/mm/yyyy)");

        while (true) {
            try {
                String input = scanner.nextLine();
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Usa el formato dd/mm/yyyy");
            }
        }
    }
}
