package platzi.play.plataforma;

import platzi.play.contenido.Contenido;
import platzi.play.contenido.ResumenContenido;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.util.FileUtils;

import java.util.*;

public class Plataforma {
    private String nombre;
    private List<Contenido> contenidos;
    private List<Usuario> usuarios;
    private Map<Contenido, Integer> vistas;

    public Plataforma(String nombre){
        this.nombre = nombre;
        this.contenidos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.vistas = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void reproducir(Contenido contenido){
        int conteoActual = vistas.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " ha sido reproducida " + conteoActual + " vez(es).");
        vistas.put(contenido, conteoActual++);
        contenido.reproducir();
    }

    public void agregarPelicula(Contenido pelicula){
        Contenido contenido  = this.buscarPelicula(pelicula.getTitulo());
        if (contenido != null){
            throw new PeliculaExistenteException(pelicula.getTitulo());
        }
        this.contenidos.add(pelicula);
        FileUtils.escribirContenido(pelicula);
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

    public List<ResumenContenido> getResumenes(){
        return contenidos.stream().map(p -> new ResumenContenido(p.getTitulo(), p.getDescripcion(), p.getGenero(), p.getDuracion(), p.getAnio()))
                .toList();
    }

    public void mostrarPeliculas() {
        contenidos.forEach(pelicula ->
                System.out.println(pelicula.getId() + " - " + pelicula.getTitulo())
        );
    }

    public List<Contenido> peliculasPorGenero(String genero) {
        return contenidos.stream()
                .filter(p -> p.getGenero().equalsIgnoreCase(genero)).toList();
    }

    public Contenido buscarPelicula(String titulo) {
        return contenidos.stream()
                .filter(p -> p.getTitulo().toLowerCase().contains(titulo.toLowerCase())).findFirst().orElse(null);
    }

    public List<Contenido> buscarPeliculas(String titulo) {
        return contenidos.stream()
                .filter(p -> p.getTitulo().toLowerCase().contains(titulo.toLowerCase())).toList();
    }

    public List<Contenido> masPopulares(){
        return contenidos.stream().filter(pelicula -> pelicula.getCalificacion() >= 4).toList();
    }

    public Contenido masLarga(){
        Contenido p = contenidos.stream().max(Comparator.comparingInt(Contenido::getDuracion)).get();
        if (p != null){
            System.out.println(p.fichaTecnica());
            return null;
        }
        return p;
    }

    public Contenido masCorta(){
        Contenido p = contenidos.stream().min(Comparator.comparingInt(Contenido::getDuracion)).get();
        if (p != null){
            System.out.println(p.fichaTecnica());
            return null;
        }
        return p;
    }

    public void eliminarPelicula(int idPelicula) {
        contenidos.stream()
                .dropWhile(p -> p.getId() == idPelicula);
    }

    public boolean existeIdPelicula(int idPelicula) {
        return contenidos.stream().anyMatch(p -> p.getId() == idPelicula);
    }

}
