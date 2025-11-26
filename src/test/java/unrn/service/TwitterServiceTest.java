package unrn.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import unrn.model.Tweet;
import unrn.model.Usuario;
import unrn.repository.TweetRepository;
import unrn.repository.UsuarioRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests unitarios para TwitterService")
class TwitterServiceTest {

    private TwitterService twitterService;
    private UsuarioRepository usuarioRepo;
    private TweetRepository tweetRepo;

    @BeforeEach
    void setUp() {
        usuarioRepo = mock(UsuarioRepository.class);
        tweetRepo = mock(TweetRepository.class);
        twitterService = new TwitterService(usuarioRepo, tweetRepo);
    }

    @Test
    @DisplayName("Registrar usuario válido guarda el usuario correctamente")
    void registrarUsuarioValido_guardaCorrectamente() {
        // Setup
        when(usuarioRepo.buscarPorUserName("usuario123")).thenReturn(null);

        // Ejercitación
        twitterService.registrarUsuario("usuario123");

        // Verificación
        verify(usuarioRepo, times(1)).buscarPorUserName("usuario123");
        verify(usuarioRepo, times(1)).guardar(any(Usuario.class));
    }

    @Test
    @DisplayName("Registrar usuario con userName nulo lanza excepción")
    void registrarUsuarioConUserNameNulo_lanzaExcepcion() {
        // Ejercitación y Verificación
        var ex = assertThrows(RuntimeException.class, () -> {
            twitterService.registrarUsuario(null);
        });
        assertEquals(Usuario.ERROR_USERNAME_NULL, ex.getMessage(), "Debe lanzar error de userName nulo");
        verify(usuarioRepo, never()).guardar(any(Usuario.class));
    }

    @Test
    @DisplayName("Registrar usuario duplicado lanza excepción")
    void registrarUsuarioDuplicado_lanzaExcepcion() {
        // Setup
        Usuario usuarioExistente = new Usuario("usuario123", List.of());
        when(usuarioRepo.buscarPorUserName("usuario123")).thenReturn(usuarioExistente);

        // Ejercitación y Verificación
        var ex = assertThrows(RuntimeException.class, () -> {
            twitterService.registrarUsuario("usuario123");
        });
        assertEquals(Usuario.ERROR_USERNAME_DUPLICADO, ex.getMessage(), "Debe lanzar error de userName duplicado");
        verify(usuarioRepo, times(1)).buscarPorUserName("usuario123");
        verify(usuarioRepo, never()).guardar(any(Usuario.class));
    }

    @Test
    @DisplayName("Publicar tweet guarda el usuario actualizado")
    void publicarTweet_guardaUsuarioActualizado() {
        // Setup
        Usuario usuario = new Usuario("usuario123", List.of());
        when(usuarioRepo.buscarPorUserName("usuario123")).thenReturn(usuario);

        // Ejercitación
        twitterService.publicarTweet("usuario123", "Mi primer tweet");

        // Verificación
        verify(usuarioRepo, times(1)).buscarPorUserName("usuario123");
        verify(usuarioRepo, times(1)).guardar(usuario);
        assertEquals(1, usuario.timeline().size(), "El usuario debe tener un tweet");
    }

    @Test
    @DisplayName("Retwittear guarda el usuario actualizado")
    void retwittear_guardaUsuarioActualizado() {
        // Setup
        Usuario autor = new Usuario("autor", List.of());
        Usuario retweeter = new Usuario("retweeter", List.of());
        Tweet tweetOriginal = new Tweet("Tweet original", autor);

        when(usuarioRepo.buscarPorUserName("retweeter")).thenReturn(retweeter);
        when(tweetRepo.buscarPorId(1L)).thenReturn(tweetOriginal);

        // Ejercitación
        twitterService.retwittear("retweeter", 1L);

        // Verificación
        verify(usuarioRepo, times(1)).buscarPorUserName("retweeter");
        verify(tweetRepo, times(1)).buscarPorId(1L);
        verify(usuarioRepo, times(1)).guardar(retweeter);
        assertEquals(1, retweeter.timeline().size(), "El usuario debe tener un retweet");
    }

    @Test
    @DisplayName("Eliminar usuario lo elimina del repositorio")
    void eliminarUsuario_loEliminaDelRepositorio() {
        // Setup
        Usuario usuario = new Usuario("usuario123", List.of());
        when(usuarioRepo.buscarPorUserName("usuario123")).thenReturn(usuario);

        // Ejercitación
        twitterService.eliminarUsuario("usuario123");

        // Verificación
        verify(usuarioRepo, times(1)).buscarPorUserName("usuario123");
        verify(usuarioRepo, times(1)).eliminar(usuario);
    }

    @Test
    @DisplayName("Listar usuarios retorna todos los usuarios del repositorio")
    void listarUsuarios_retornaTodosLosUsuarios() {
        // Setup
        Usuario usuario1 = new Usuario("usuario1", List.of());
        Usuario usuario2 = new Usuario("usuario2", List.of());
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        when(usuarioRepo.listarTodos()).thenReturn(usuarios);

        // Ejercitación
        List<Usuario> resultado = twitterService.listarUsuarios();

        // Verificación
        assertEquals(2, resultado.size(), "Debe retornar 2 usuarios");
        verify(usuarioRepo, times(1)).listarTodos();
    }

    @Test
    @DisplayName("Listar todos los tweets retorna todos los tweets del repositorio")
    void listarTodosTweets_retornaTodosLosTweets() {
        // Setup
        Usuario usuario = new Usuario("usuario1", List.of());
        Tweet tweet1 = new Tweet("Tweet 1", usuario);
        Tweet tweet2 = new Tweet("Tweet 2", usuario);
        List<Tweet> tweets = Arrays.asList(tweet1, tweet2);
        when(tweetRepo.listarTodos()).thenReturn(tweets);

        // Ejercitación
        List<Tweet> resultado = twitterService.listarTodosTweets();

        // Verificación
        assertEquals(2, resultado.size(), "Debe retornar 2 tweets");
        verify(tweetRepo, times(1)).listarTodos();
    }

    @Test
    @DisplayName("Listar tweets de usuario retorna solo los tweets de ese usuario")
    void listarTweetsDeUsuario_retornaSoloSusTweets() {
        // Setup
        Usuario usuario = new Usuario("usuario1", List.of());
        usuario.publicarTweet("Tweet 1");
        usuario.publicarTweet("Tweet 2");
        when(usuarioRepo.buscarPorUserName("usuario1")).thenReturn(usuario);

        // Ejercitación
        List<Tweet> resultado = twitterService.listarTweetsDeUsuario("usuario1");

        // Verificación
        assertEquals(2, resultado.size(), "Debe retornar 2 tweets");
        verify(usuarioRepo, times(1)).buscarPorUserName("usuario1");
    }
}

