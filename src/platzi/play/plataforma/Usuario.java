package platzi.play.plataforma;

import platzi.play.contenido.Calificacion;
import platzi.play.contenido.Pelicula;
import platzi.play.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nombre;
    private int edad;
    private List<Pelicula> favoritas;
    private List<Calificacion> resenias;

    //CONSTRUCTOR

    public Usuario(String nombre, int edad) {
        this.id = IdGenerator.nextUsuarioId();
        this.nombre = nombre;
        this.edad = edad;
        this.favoritas = new ArrayList<>();
        this.resenias = new ArrayList<>();
    }

    //METODOS

    public void makeResenia(Pelicula pelicula, double puntuacion, String resenia){
        Calificacion calif = pelicula.calificar(this.id, resenia, puntuacion);
        resenias.add(calif);
    }

    //GETTERS & SEDDERS

    public List<Calificacion> getResenias() {
        return resenias;
    }

    public void setResenias(List<Calificacion> resenias) {
        this.resenias = resenias;
    }

    public List<Pelicula> getFavoritas() {
        return favoritas;
    }

    public void setFavoritas(List<Pelicula> favoritas) {
        this.favoritas = favoritas;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
