package platzi.play.plataforma;

import platzi.play.contenido.Pelicula;
import platzi.play.excepcion.PeliculaExistenteException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> peliculas;
    private List<Usuario> usuarios;

    public Plataforma(String nombre){
        this.nombre = nombre;
        this.peliculas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarPelicula(Pelicula pelicula){
        Pelicula contenido  = this.buscarPelicula(pelicula.getTitulo());
        if (contenido != null){
            throw new PeliculaExistenteException(pelicula.getTitulo());
        }
        this.peliculas.add(pelicula);
    }

    public void agregarUsuario(Usuario usuario){
        this.usuarios.add(usuario);
    }

    public Usuario iniciarSesion(String nombreUsuario) {
        return usuarios.stream()
                .filter(u -> u.getNombre().equals(nombreUsuario))
                .findFirst()
                .orElse(null);
    }

    public void mostrarPeliculas() {
        peliculas.forEach(pelicula ->
                System.out.println(pelicula.getId() + " - " + pelicula.getTitulo())
        );
    }

    public List<Pelicula> peliculasPorGenero(String genero) {
        return peliculas.stream()
                .filter(p -> p.getGenero().equalsIgnoreCase(genero)).toList();
    }

    public Pelicula buscarPelicula(String titulo) {
        return peliculas.stream()
                .filter(p -> p.getTitulo().toLowerCase().contains(titulo.toLowerCase())).findFirst().orElse(null);
    }

    public List<Pelicula> buscarPeliculas(String titulo) {
        return peliculas.stream()
                .filter(p -> p.getTitulo().toLowerCase().contains(titulo.toLowerCase())).toList();
    }

    public List<Pelicula> masPopulares(){
        return peliculas.stream().filter(pelicula -> pelicula.getCalificacion() >= 4).toList();
    }

    public Pelicula masLarga(){
        Pelicula p = peliculas.stream().max(Comparator.comparingInt(Pelicula::getDuracion)).get();
        if (p != null){
            System.out.println(p.fichaTecnica());
            return null;
        }
        return p;
    }

    public Pelicula masCorta(){
        Pelicula p = peliculas.stream().min(Comparator.comparingInt(Pelicula::getDuracion)).get();
        if (p != null){
            System.out.println(p.fichaTecnica());
            return null;
        }
        return p;
    }

    public void eliminarPelicula(int idPelicula) {
        peliculas.stream()
                .dropWhile(p -> p.getId() == idPelicula);
    }

    public boolean existeIdPelicula(int idPelicula) {
        return peliculas.stream().anyMatch(p -> p.getId() == idPelicula);
    }

}
