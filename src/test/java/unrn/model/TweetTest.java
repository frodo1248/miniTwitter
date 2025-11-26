package unrn.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TweetTest {

    @Test
    @DisplayName("Crear tweet con texto válido crea el tweet correctamente")
    void crearTweetConTextoValido_tweetCreadoCorrectamente() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario("tweetUser1", usuarios);
        // Ejercitación
        Tweet tweet = new Tweet("Hola Twitter!", usuario);
        // Verificación
        assertNotNull(tweet, "El tweet no debe ser nulo");
    }

    @Test
    @DisplayName("No permite crear tweet con texto nulo")
    void crearTweetConTextoNulo_lanzaExcepcion() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario("tweetUser2", usuarios);
        // Ejercitación y Verificación
        var ex = assertThrows(RuntimeException.class, () -> new Tweet(null, usuario));
        assertEquals(Tweet.ERROR_TEXTO_LONGITUD, ex.getMessage(), "Debe lanzar error de texto nulo");
    }

    @Test
    @DisplayName("No permite crear tweet con texto vacío")
    void crearTweetConTextoVacio_lanzaExcepcion() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario("tweetUser3", usuarios);
        // Ejercitación y Verificación
        var ex = assertThrows(RuntimeException.class, () -> new Tweet("", usuario));
        assertEquals(Tweet.ERROR_TEXTO_LONGITUD, ex.getMessage(), "Debe lanzar error de texto vacío");
    }

    @Test
    @DisplayName("No permite crear tweet con texto mayor a 280 caracteres")
    void crearTweetConTextoLargo_lanzaExcepcion() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario("tweetUser4", usuarios);
        String textoLargo = "a".repeat(281);
        // Ejercitación y Verificación
        var ex = assertThrows(RuntimeException.class, () -> new Tweet(textoLargo, usuario));
        assertEquals(Tweet.ERROR_TEXTO_LONGITUD, ex.getMessage(), "Debe lanzar error de texto largo");
    }
}
