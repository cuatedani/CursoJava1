package platzi.play.util;

import platzi.play.contenido.Pelicula;
import platzi.play.plataforma.Plataforma;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
                    int id = Integer.parseInt(datos[0]);
                    String titulo = datos[1];
                    String descripcion = datos[2];
                    String gener = datos[3];
                    int duracion = Integer.parseInt(datos[4]);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/aaaa");
                    LocalDate anio = LocalDate.parse(datos[5], formatter);
                    boolean disponible = Boolean.parseBoolean(datos[6]);

                    Pelicula pelicula = new Pelicula(titulo, descripcion, gener, duracion, anio);
                    peliculas.add(pelicula);
                }
            });
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
        }
        return peliculas;
    }
}
