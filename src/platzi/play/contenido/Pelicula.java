package platzi.play.contenido;

import java.time.LocalDate;

public class Pelicula extends Contenido{
    public Pelicula(String titulo, String descripcion, String genero, int duracion, LocalDate anio) {
        super(titulo, descripcion, genero, duracion, anio);
    }
    
    @Override
    public void reproducir(){
        System.out.println("Reproduciendo Pelicula: " + getTitulo());
    }
}
