package platzi.play.util;

import platzi.play.contenido.Contenido;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static final String ARCHIVO_PELICULAS = "peliculas.txt";
    public static final String ARCHIVO_USUARIOS = "usuarios.txt";
    public static final String SEPARADOR = "|";

    public static List<Contenido> leerContenido() {
        List peliculas = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_PELICULAS));
            lineas.forEach(l ->
            {
                String[] datos = l.split("\\" + SEPARADOR);
                if (datos.length == 7) {
                    String titulo = datos[0];
                    String descripcion = datos[1];
                    String genero = datos[2];
                    int duracion = Integer.parseInt(datos[3]);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/aaaa");
                    LocalDate anio = LocalDate.parse(datos[4], formatter);

                    Contenido contenido = new Contenido(titulo, descripcion, genero, duracion, anio);
                    peliculas.add(contenido);
                }
            });
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
        }
        return peliculas;
    }

    public static void escribirContenido(Contenido contenido){
        String linea = String.join(SEPARADOR,
                contenido.getTitulo(),
                contenido.getDescripcion(),
                contenido.getGenero(),
                String.valueOf(contenido.getDuracion()),
                String.valueOf(contenido.getAnio())
        );

        try {
            Files.writeString(Paths.get(ARCHIVO_PELICULAS),
                    linea + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
                    );
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
