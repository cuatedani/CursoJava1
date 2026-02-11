package platzi.play.contenido;

import java.time.LocalDate;

public record ResumenContenido(String titulo,
                               String descripcion,
                               String genero,
                               int duracion,
                               LocalDate anio
                               ) {
}
