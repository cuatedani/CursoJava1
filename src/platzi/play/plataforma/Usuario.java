package platzi.play.plataforma;

import platzi.play.contenido.Calificacion;
import platzi.play.contenido.Contenido;
import platzi.play.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nombre;
    private int edad;
    private String permisos;
    private List<Contenido> favoritas;
    private List<Calificacion> resenias;

    //CONSTRUCTOR

    public Usuario(String nombre, int edad, boolean admin) {
        this.id = IdGenerator.nextUsuarioId();
        this.nombre = nombre;
        this.edad = edad;
        this.permisos = "admin";
        this.favoritas = new ArrayList<>();
        this.resenias = new ArrayList<>();
    }

    public Usuario(String nombre, int edad ){
        this.id = IdGenerator.nextUsuarioId();
        this.nombre = nombre;
        this.edad = edad;
        this.permisos = "user";
        this.favoritas = new ArrayList<>();
        this.resenias = new ArrayList<>();
    }

    //METODOS

    public void makeResenia(Contenido contenido, double puntuacion, String resenia){
        Calificacion calif = contenido.calificar(this.id, resenia, puntuacion);
        resenias.add(calif);
    }

    //GETTERS & SEDDERS

    public List<Calificacion> getResenias() {
        return resenias;
    }

    public void setResenias(List<Calificacion> resenias) {
        this.resenias = resenias;
    }

    public List<Contenido> getFavoritas() {
        return favoritas;
    }

    public void setFavoritas(List<Contenido> favoritas) {
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

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void misFavoritas(){
        if(favoritas.isEmpty()){
            System.out.println("Sin Peliculas Favoritas");
        }else {
            favoritas.forEach(pelicula -> {
                System.out.println(pelicula.getId() + " - " + pelicula.getTitulo());
            });
        }
    }

    public void misResenias(){
        if(resenias.isEmpty()){
            System.out.println("Sin Reseñas de Peliculas");
        }else {
            resenias.forEach(r -> {
                System.out.println(r.getId() + "- " + r.getPelicula().getTitulo() + " - " + r.getPuntuacion() + " - " + r.getResenia());
            });
        }
    }
}
