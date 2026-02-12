package platzi.play.contenido;

import java.time.LocalDate;

public class Documental extends Contenido{
    private String narrador;

    public Documental(String titulo, String descripcion, String genero, int duracion, LocalDate anio, String narrador) {
        super(titulo, descripcion, genero, duracion, anio);
        this.narrador = narrador;
    }

    public String getNarrador() {
        return narrador;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo Documental: " + getTitulo() + " narrado por: " + getNarrador());
    }
}
