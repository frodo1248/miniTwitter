package unrn.integration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import org.junit.jupiter.api.*;
import unrn.model.Usuario;
import unrn.model.Tweet;
import unrn.repository.TweetRepositoryJpa;
import unrn.repository.UsuarioRepositoryJpa;
import unrn.service.TwitterService;
import unrn.utils.EmfBuilder;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de integración para TwitterService usando H2 en memoria")
class TwitterServiceIntegrationTest {
    private static EntityManagerFactory emf;
    private TwitterService twitterService;
    private UsuarioRepositoryJpa usuarioRepo;
    private TweetRepositoryJpa tweetRepo;

    @BeforeAll
    static void setUpClass() {
        // Configura la base H2 en memoria y crea el schema antes de todos los tests
        emf = new EmfBuilder()
                .memory()
                .addClass(Usuario.class)
                .addClass(Tweet.class)
                .build();
    }

    @AfterAll
    static void tearDownClass() {
        if (emf != null) {
            emf.close();
        }
    }

    @BeforeEach
    void setUp() {
        // Truncar la base antes de cada test para garantizar aislamiento
        emf.getSchemaManager().truncate();
        EntityManager em = emf.createEntityManager();
        usuarioRepo = new UsuarioRepositoryJpa(em);
        tweetRepo = new TweetRepositoryJpa(em);
        twitterService = new TwitterService(usuarioRepo, tweetRepo);
    }

    @Test
    @DisplayName("Registrar usuario y publicar tweet persiste correctamente en la base de datos")
    void registrarUsuarioYPublicarTweet_persisteEnBaseDeDatosCorrectamente() {
        // Setup: Preparar el escenario
        String userName = "usuarioTest";
        String tweetText = "Hola mundo!";

        // Ejercitación: Ejecutar la acción a probar
        twitterService.registrarUsuario(userName);
        twitterService.publicarTweet(userName, tweetText);

        // Verificación: Verificar el resultado esperado
        var usuario = usuarioRepo.buscarPorUserName(userName);
        assertNotNull(usuario, "El usuario debe existir en la base de datos luego de registrarlo");
        assertEquals(userName, usuario.getUserName(), "El userName almacenado debe coincidir con el ingresado");
        assertEquals(1, usuario.timeline().size(), "El usuario debe tener exactamente un tweet publicado");
        assertEquals(tweetText, usuario.timeline().get(0).getTexto(), "El texto del tweet publicado debe coincidir con el ingresado");
    }

    @Test
    @DisplayName("Registrar un usuario con userName duplicado lanza excepción")
    void registrarUsuarioExistente_lanzaExcepcion() {
        // Setup: Registrar un usuario
        String userName = "usuarioDuplicado";
        twitterService.registrarUsuario(userName);
        // Ejercitación y Verificación: Intentar registrar el mismo usuario y esperar excepción
        var ex = assertThrows(RuntimeException.class, () -> {
            twitterService.registrarUsuario(userName);
        });
        assertEquals(Usuario.ERROR_USERNAME_DUPLICADO, ex.getMessage(), "El mensaje de error debe indicar userName duplicado");
    }

    @Test
    @DisplayName("Registrar un usuario con userName nulo lanza excepción")
    void registrarUsuarioConUserNameNulo_lanzaExcepcion() {
        // Ejercitación y Verificación: Intentar registrar usuario con userName nulo y esperar excepción
        var ex = assertThrows(RuntimeException.class, () -> {
            twitterService.registrarUsuario(null);
        });
        assertEquals(Usuario.ERROR_USERNAME_NULL, ex.getMessage(), "El mensaje de error debe indicar userName nulo");
    }
}
