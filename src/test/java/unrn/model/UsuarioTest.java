package unrn.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    @DisplayName("Crear usuario con userName válido crea el usuario correctamente")
    void crearUsuarioConUserNameValido_usuarioCreadoCorrectamente() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        // Ejercitación
        Usuario usuario = new Usuario("usuarioValido", usuarios);
        // Verificación
        assertNotNull(usuario, "El usuario no debe ser nulo");
    }

    @Test
    @DisplayName("No permite crear usuario con userName duplicado")
    void crearUsuarioConUserNameDuplicado_lanzaExcepcion() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("usuario1", new ArrayList<>()));
        // Ejercitación y Verificación
        var ex = assertThrows(RuntimeException.class, () -> new Usuario("usuario1", usuarios));
        assertEquals(Usuario.ERROR_USERNAME_DUPLICADO, ex.getMessage(), "Debe lanzar error de userName duplicado");
    }

    @Test
    @DisplayName("No permite crear usuario con userName menor a 5 caracteres")
    void crearUsuarioConUserNameCorto_lanzaExcepcion() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        // Ejercitación y Verificación
        var ex = assertThrows(RuntimeException.class, () -> new Usuario("abc", usuarios));
        assertEquals(Usuario.ERROR_USERNAME_LONGITUD, ex.getMessage(), "Debe lanzar error de longitud de userName");
    }

    @Test
    @DisplayName("No permite crear usuario con userName mayor a 25 caracteres")
    void crearUsuarioConUserNameLargo_lanzaExcepcion() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        String largo = "a".repeat(26);
        // Ejercitación y Verificación
        var ex = assertThrows(RuntimeException.class, () -> new Usuario(largo, usuarios));
        assertEquals(Usuario.ERROR_USERNAME_LONGITUD, ex.getMessage(), "Debe lanzar error de longitud de userName");
    }

    @Test
    @DisplayName("No permite crear usuario con userName nulo")
    void crearUsuarioConUserNameNulo_lanzaExcepcion() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        // Ejercitación y Verificación
        var ex = assertThrows(RuntimeException.class, () -> new Usuario(null, usuarios));
        assertEquals(Usuario.ERROR_USERNAME_NULL, ex.getMessage(), "Debe lanzar error de userName nulo");
    }

    @Test
    @DisplayName("Publicar tweet agrega el tweet al timeline del usuario")
    void publicarTweet_agregaTweetAlTimeline() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario("usuarioTweet", usuarios);
        // Ejercitación
        usuario.publicarTweet("Hola mundo");
        // Verificación
        assertEquals(1, usuario.timeline().size(), "El tweet debe estar en el timeline");
    }

    @Test
    @DisplayName("Eliminar usuario elimina todos sus tweets")
    void eliminarUsuario_eliminaTodosSusTweets() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario("usuarioBorrar", usuarios);
        usuario.publicarTweet("Tweet 1");
        usuario.publicarTweet("Tweet 2");
        // Ejercitación
        usuario.eliminar();
        // Verificación
        assertTrue(usuario.timeline().isEmpty(), "El timeline debe estar vacío tras eliminar usuario");
    }

    @Test
    @DisplayName("No permite crear re-tweet de un tweet propio")
    void retwittearTweetPropio_lanzaExcepcion() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario("usuarioRT", usuarios);
        usuario.publicarTweet("Tweet propio");
        Tweet tweetPropio = usuario.timeline().get(0);
        // Ejercitación y Verificación
        var ex = assertThrows(RuntimeException.class, () -> usuario.retwittear(tweetPropio));
        assertEquals(Tweet.ERROR_RETWEET_PROPIO, ex.getMessage(), "No debe permitir retweet propio");
    }

    @Test
    @DisplayName("Permite hacer re-tweet de un tweet de otro usuario")
    void retwittearTweetDeOtroUsuario_agregaRetweetAlTimeline() {
        // Setup
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario("usuario1RT", usuarios);
        usuarios.add(usuario1);
        Usuario usuario2 = new Usuario("usuario2RT", usuarios);
        usuario1.publicarTweet("Tweet original");
        Tweet tweetOriginal = usuario1.timeline().get(0);
        // Ejercitación
        usuario2.retwittear(tweetOriginal);
        // Verificación
        assertEquals(1, usuario2.timeline().size(), "El retweet debe estar en el timeline del usuario");
    }
}

