package platzi.play.util;

import platzi.play.contenido.Contenido;
import platzi.play.contenido.Documental;
import platzi.play.contenido.Pelicula;

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
        List<Contenido> contenidoList = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_PELICULAS));
            lineas.forEach(l ->
            {
                String[] datos = l.split("\\" + SEPARADOR);
                String tipo = datos[0];
                Contenido contenido;

                if ("PELICULA".equals(tipo) && datos.length == 6) {
                    String titulo = datos[1];
                    String descripcion = datos[2];
                    String genero = datos[3];
                    int duracion = Integer.parseInt(datos[4]);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/aaaa");
                    LocalDate anio = LocalDate.parse(datos[5], formatter);

                    contenido = new Pelicula(titulo, descripcion, genero, duracion, anio);
                    contenidoList.add(contenido);
                }else if ("DOCUMENTAL".equals(tipo) && datos.length == 7){
                    String titulo = datos[1];
                    String descripcion = datos[2];
                    String genero = datos[3];
                    int duracion = Integer.parseInt(datos[4]);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/aaaa");
                    LocalDate anio = LocalDate.parse(datos[5], formatter);

                    String narrador = datos[6];

                    contenido = new Documental(titulo, descripcion, genero, duracion, anio, narrador);
                    contenidoList.add(contenido);
                }
            });
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
        }
        return contenidoList;
    }

    public static void escribirContenido(Contenido contenido){
        String linea = String.join(SEPARADOR,
                contenido.getTitulo(),
                contenido.getDescripcion(),
                contenido.getGenero(),
                String.valueOf(contenido.getDuracion()),
                String.valueOf(contenido.getAnio())
        );

        String lineaFinal;
        if(contenido instanceof Documental){
             Documental doc = (Documental) contenido;
             lineaFinal = "DOCUMENTAL" + SEPARADOR + linea + SEPARADOR + doc.getNarrador();
        }else {
            lineaFinal = "PELICULA" + SEPARADOR + linea;
        }

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
