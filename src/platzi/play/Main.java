package platzi.play;

import platzi.play.contenido.Pelicula;
import platzi.play.contenido.ResumenContenido;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.plataforma.Plataforma;
import platzi.play.plataforma.Usuario;
import platzi.play.util.FileUtils;
import platzi.play.util.ScannerUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Plataforma plataforma = new Plataforma("PLATZI PLAY");
        ScannerUtils scannerUtils = new ScannerUtils();
        Usuario sesion = null;

        System.out.println("BIENVENIDO A " + plataforma.getNombre());

        cargarPeliculas(plataforma);

        //Login
        while (true){
            int opcionLogin = scannerUtils.capturarInt("""
                    INICIO DE SESION
                    1. Iniciar Sesion
                    2. Nuevo Usuario
                    3. Salir
                    """);

            if(opcionLogin == 1){
                String userLogin = scannerUtils.capturarTexto("""
                    Ingresa tu Usuario:
                    """);

                sesion = plataforma.iniciarSesion(userLogin);

                if (sesion != null) {
                    System.out.println("Iniciando Sesion...");
                    break; // sales del login
                } else {
                    System.out.println("Usuario no Encontrado");
                }

            }
            if(opcionLogin == 2){
                String userNombre = scannerUtils.capturarTexto("""
                    Ingresa tu Nombre:
                    """);
                int userEdad = scannerUtils.capturarInt("""
                    Ingresa tu Edad:
                    """);
                plataforma.agregarUsuario(new Usuario(userNombre, userEdad));
            }
            if (opcionLogin == 3) {
                System.exit(0);
            }else {
                System.out.println("Opcion Invalida!!");
            }

        }

        if (sesion != null && sesion.getPermisos().equals("user")) {
            //Usuario Normal
            while (true){
                int opcionMenu = scannerUtils.capturarInt("""
                    MENU:
                    1. Mostrar Peliculas
                    2. Buscar por titulo
                    3. Buscar por genero
                    4. Reproducir
                    5. Mis Favoritos
                    6. Mis Reseñas
                    7. Cerrar Sesion
                    """);

                if(opcionMenu == 1){
                    List<ResumenContenido> contenidos = plataforma.getResumenes();
                    contenidos.forEach(c -> System.out.println(c.toString()));

                }

                if(opcionMenu == 2){
                    String titulo = scannerUtils.capturarTexto("Titulo: ");
                    plataforma.buscarPeliculas(titulo);
                }

                if(opcionMenu == 3){
                    String genero = scannerUtils.capturarTexto("Genero: ");
                    plataforma.peliculasPorGenero(genero);
                }

                if(opcionMenu == 4){
                    String titulo = scannerUtils.capturarTexto("Pelicula a Reproducir: ");
                    Pelicula p = plataforma.buscarPelicula(titulo);
                    if (p != null){
                        plataforma.reproducir(p);
                    }else {
                        System.out.println("No existe la pelicula: " + titulo);
                    }
                }

                if(opcionMenu == 5){
                    sesion.misFavoritas();
                }

                if(opcionMenu == 6){
                    sesion.misResenias();
                }

                if(opcionMenu == 7){
                    sesion = null;
                    break;
                }else if (opcionMenu > 7 || opcionMenu < 1){
                    System.out.println("Opcion Invalida!!");
                }
            }
        }else if(sesion != null && sesion.getPermisos().equals("admin")){
            //Usuario Admin
            while (true){
                int opcionMenu = scannerUtils.capturarInt("""
                    MENU:
                    1. Agregar Pelicula
                    2. ELiminar Pelicula
                    3. Cerrar Sesion
                    """);

                if (opcionMenu==1){
                    System.out.println("FORMULARIO DE PELICULA:");
                    String titulo = scannerUtils.capturarTexto("Titulo:");
                    String descripcion = scannerUtils.capturarTexto("Descripcion:");
                    String genero = scannerUtils.capturarTexto("Genero:");
                    int duracion = scannerUtils.capturarInt("Duracion:");
                    LocalDate anio = scannerUtils.capturarFecha("Año Estreno:");

                    try {
                        plataforma.agregarPelicula(new Pelicula(titulo, descripcion, genero, duracion, anio));
                    }catch (PeliculaExistenteException e){
                        System.out.println(e.getMessage());
                    }

                    System.out.println("Pelicula Agregada!");
                }

                if (opcionMenu==2){
                    System.out.println("ELIMINAR PELICULA:");
                    plataforma.mostrarPeliculas();
                    int idPelicula = scannerUtils.capturarInt("Pelicula a Eliminar: ");
                    if(plataforma.existeIdPelicula(idPelicula)){
                        plataforma.eliminarPelicula(idPelicula);
                        System.out.println("Pelicula Eliminada!");
                    } else {
                        System.out.println("No existe esa pelicula");
                    }
                }

                if(opcionMenu == 3){
                    sesion = null;
                    break;
                }else if (opcionMenu > 3 || opcionMenu < 1){
                    System.out.println("Opcion Invalida!!");
                }
            }
        }else {
            System.out.println("Sesion Invalida");
        }
    }

    private static void cargarPeliculas(Plataforma plataforma){
        List<Pelicula> peliculas = FileUtils.leerContenido();
        peliculas.forEach(p -> plataforma.agregarPelicula(p));
    }
}
