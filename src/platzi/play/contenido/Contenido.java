package platzi.play.contenido;

import platzi.play.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Contenido {
    private int id;
    private String titulo;
    private String descripcion;
    private String genero;
    private int duracion;
    private LocalDate anio;
    private List<Calificacion> resenias;
    private boolean disponibilidad;

    //CONSTRUCTOR

    public Contenido(String titulo, String descripcion, String genero, int duracion, LocalDate anio) {
        this.id = IdGenerator.nextPeliculaId();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.genero = genero;
        this.duracion = duracion;
        this.anio = anio;
        this.resenias = new ArrayList<>();
        this.disponibilidad = true;
    }

    //GETTERS Y SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public LocalDate getAnio() {
        return anio;
    }

    public void setAnio(LocalDate anio) {
        this.anio = anio;
    }

    public double getCalificacion() {
        return resenias.stream()
                .mapToDouble(Calificacion::getPuntuacion)
                .average()
                .orElse(0);
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    //METODOS

    public abstract void reproducir();

    public String fichaTecnica(){
        return "Titulo: " + titulo + "\n" +
               "Genero:" + genero + "\n" +
               "Descripcion:" + genero + "\n" +
               "Duracion:" + duracion + " minutos" ;
    }

    public String popularidad(){
        double calificacion = getCalificacion();

        if (calificacion == 0){
            return "Sin Registros";
        }

        if(calificacion >= 4){
            return "Muy Popular";
        }else if (calificacion >= 3){
            return "Popular";
        } else if (calificacion >= 2) {
            return "Poco Popular";
        } else if (calificacion >= 1) {
            return "Muy Poco Popular";
        }else {
            return "Inpopular";
        }
    }

    public Calificacion calificar(int idUsuario, String resenia, double puntuacion) {
        Calificacion calif = new Calificacion(
                                    idUsuario,
                                    this,
                                    resenia,
                                    puntuacion
                            );
        resenias.add(
                calif
        );

        return calif;
    }
}
