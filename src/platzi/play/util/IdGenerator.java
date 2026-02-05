package platzi.play.util;

public class IdGenerator {

    private static int peliculaId = 0;
    private static int reseniaId = 0;
    private static int usuarioId = 0;

    public static int nextPeliculaId() {
        return ++peliculaId;
    }

    public static int nextReseniaId() {
        return ++reseniaId;
    }

    public static int nextUsuarioId() {
        return ++usuarioId;
    }
}
