package unrn.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RetweetTest {

    @Test
    @DisplayName("No permite crear retweet de tweet nulo")
    void crearRetweetDeTweetNulo_lanzaExcepcion() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario("retweetUser1", usuarios);
        // Ejercitación y Verificación
        var ex = assertThrows(RuntimeException.class, () -> new Retweet(null, usuario));
        assertEquals(Retweet.ERROR_TWEET_NULL, ex.getMessage(), "Debe lanzar error de tweet original nulo");
    }

    @Test
    @DisplayName("No permite crear retweet de un tweet propio")
    void crearRetweetDeTweetPropio_lanzaExcepcion() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario("retweetUser2", usuarios);
        Tweet tweetPropio = new Tweet("Mi tweet", usuario);
        // Ejercitación y Verificación
        var ex = assertThrows(RuntimeException.class, () -> new Retweet(tweetPropio, usuario));
        assertEquals(Retweet.ERROR_RETWEET_PROPIO, ex.getMessage(), "No debe permitir retweet propio");
    }

    @Test
    @DisplayName("Retweet referencia correctamente al tweet original y al autor del retweet")
    void retweetReferenciaCorrecta() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        Usuario autorOriginal = new Usuario("autorOriginal", usuarios);
        Usuario retweeter = new Usuario("retweeter", usuarios);
        Tweet tweetOriginal = new Tweet("Texto original", autorOriginal);
        // Ejercitación
        Retweet retweet = new Retweet(tweetOriginal, retweeter);
        // Verificación
        assertEquals(tweetOriginal, retweet.tweetOriginal(), "El retweet debe referenciar al tweet original");
        assertEquals(retweeter, retweet.autor(), "El autor del retweet debe ser correcto");
        assertEquals("Texto original", retweet.texto(), "El texto del retweet debe ser el del tweet original");
        assertTrue(retweet.esRetweet(), "Debe indicar que es un retweet");
    }
}

