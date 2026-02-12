package platzi.play.contenido;

import platzi.play.util.IdGenerator;

public class Calificacion {
    private int id;
    private int idUsuario;
    private Contenido contenido;
    private String resenia;
    private double puntuacion;

    public Calificacion(int idUsuario, Contenido contenido, String resenia, double puntuacion) {
        this.id = IdGenerator.nextReseniaId();
        this.idUsuario = idUsuario;
        this.contenido = contenido;
        this.resenia = resenia;
        this.puntuacion = puntuacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Contenido getPelicula() {
        return contenido;
    }

    public void setPelicula(Contenido contenido) {
        this.contenido = contenido;
    }

    public String getResenia() {
        return resenia;
    }

    public void setResenia(String resenia) {
        this.resenia = resenia;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    //METODOS
}
