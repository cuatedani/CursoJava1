package platzi.play.plataforma;

import platzi.play.contenido.Pelicula;

import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> peliculas;
    private List<Usuario> usuarios;

    Plataforma(String nombre){
        this.nombre = nombre;
    }

    public void agregarPelicula(Pelicula pelicula){
        this.peliculas.add(pelicula);
    }

    public void agregarUsuario(Usuario usuario){
        this.usuarios.add(usuario);
    }
}
