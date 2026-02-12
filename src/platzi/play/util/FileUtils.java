package platzi.play.util;

import platzi.play.contenido.Pelicula;
import platzi.play.plataforma.Plataforma;

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

    public static List<Pelicula> leerContenido() {
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

                    Pelicula pelicula = new Pelicula(titulo, descripcion, genero, duracion, anio);
                    peliculas.add(pelicula);
                }
            });
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
        }
        return peliculas;
    }

    public static void escribirContenido(Pelicula pelicula){
        String linea = String.join(SEPARADOR,
                pelicula.getTitulo(),
                pelicula.getDescripcion(),
                pelicula.getGenero(),
                String.valueOf(pelicula.getDuracion()),
                String.valueOf(pelicula.getAnio())
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
