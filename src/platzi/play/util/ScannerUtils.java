package platzi.play.util;

import java.util.Scanner;

public class ScannerUtils {
    public static Scanner scanner = new Scanner(System.in);

    public String capturarTexto(String mensaje){
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    public int capturarInt(String mensaje){
        System.out.println(mensaje);
        int entero = scanner.nextInt();
        scanner.nextLine();
        return entero;
    }

    public double capturarDouble(String mensaje){
        System.out.println(mensaje);
        double decimal = scanner.nextInt();
        scanner.nextLine();
        return decimal;
    }

    public long capturarLong(String mensaje){
        System.out.println(mensaje);
        long largo = scanner.nextLong();
        scanner.nextLine();
        return largo;
    }
}
