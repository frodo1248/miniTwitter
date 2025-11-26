package unrn.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import unrn.model.Retweet;
import unrn.model.Tweet;
import unrn.model.Usuario;
import unrn.service.TwitterService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests unitarios para TweetController")
class TweetControllerTest {

    private TweetController controller;
    private TwitterService twitterService;

    @BeforeEach
    void setUp() {
        twitterService = mock(TwitterService.class);
        controller = new TweetController(twitterService);
    }

    @Test
    @DisplayName("Publicar tweet válido retorna 201 CREATED")
    void publicarTweetValido_retorna201Created() {
        // Setup
        PublicarTweetRequest request = new PublicarTweetRequest("usuario123", "Mi primer tweet");
        doNothing().when(twitterService).publicarTweet("usuario123", "Mi primer tweet");

        // Ejercitación
        ResponseEntity<?> response = controller.publicarTweet(request);

        // Verificación
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Debe retornar 201 CREATED");
        verify(twitterService, times(1)).publicarTweet("usuario123", "Mi primer tweet");
    }

    @Test
    @DisplayName("Publicar tweet con texto inválido retorna 400 BAD REQUEST")
    void publicarTweetConTextoInvalido_retorna400BadRequest() {
        // Setup
        String textoLargo = "a".repeat(281);
        PublicarTweetRequest request = new PublicarTweetRequest("usuario123", textoLargo);
        doThrow(new RuntimeException(Tweet.ERROR_TEXTO_LONGITUD))
                .when(twitterService).publicarTweet("usuario123", textoLargo);

        // Ejercitación
        ResponseEntity<?> response = controller.publicarTweet(request);

        // Verificación
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Debe retornar 400 BAD REQUEST");
        assertEquals(Tweet.ERROR_TEXTO_LONGITUD, response.getBody(), "Debe retornar mensaje de error de longitud");
    }

    @Test
    @DisplayName("Publicar tweet con error inesperado retorna 500 INTERNAL SERVER ERROR")
    void publicarTweetConErrorInesperado_retorna500InternalServerError() {
        // Setup
        PublicarTweetRequest request = new PublicarTweetRequest("usuario123", "Tweet");
        doThrow(new RuntimeException("Error de base de datos"))
                .when(twitterService).publicarTweet("usuario123", "Tweet");

        // Ejercitación
        ResponseEntity<?> response = controller.publicarTweet(request);

        // Verificación
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "Debe retornar 500 INTERNAL SERVER ERROR");
        assertEquals("Error inesperado", response.getBody(), "Debe retornar mensaje de error genérico");
    }

    @Test
    @DisplayName("Retwittear un tweet válido retorna 201 CREATED")
    void retweetearTweetValido_retorna201Created() {
        // Setup
        RetweetRequest request = new RetweetRequest("usuario123", 1L);
        doNothing().when(twitterService).retwittear("usuario123", 1L);

        // Ejercitación
        ResponseEntity<?> response = controller.retwittear(request);

        // Verificación
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Debe retornar 201 CREATED");
        verify(twitterService, times(1)).retwittear("usuario123", 1L);
    }

    @Test
    @DisplayName("Retwittear con tweet nulo retorna 400 BAD REQUEST")
    void retweetearConTweetNulo_retorna400BadRequest() {
        // Setup
        RetweetRequest request = new RetweetRequest("usuario123", 1L);
        doThrow(new RuntimeException(Retweet.ERROR_TWEET_NULL))
                .when(twitterService).retwittear("usuario123", 1L);

        // Ejercitación
        ResponseEntity<?> response = controller.retwittear(request);

        // Verificación
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Debe retornar 400 BAD REQUEST");
        assertEquals(Retweet.ERROR_TWEET_NULL, response.getBody(), "Debe retornar mensaje de tweet nulo");
    }

    @Test
    @DisplayName("Retwittear tweet propio retorna 400 BAD REQUEST")
    void retweetearTweetPropio_retorna400BadRequest() {
        // Setup
        RetweetRequest request = new RetweetRequest("usuario123", 1L);
        doThrow(new RuntimeException(Retweet.ERROR_RETWEET_PROPIO))
                .when(twitterService).retwittear("usuario123", 1L);

        // Ejercitación
        ResponseEntity<?> response = controller.retwittear(request);

        // Verificación
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Debe retornar 400 BAD REQUEST");
        assertEquals(Retweet.ERROR_RETWEET_PROPIO, response.getBody(), "Debe retornar mensaje de retweet propio");
    }

    @Test
    @DisplayName("Retwittear con error inesperado retorna 500 INTERNAL SERVER ERROR")
    void retweetearConErrorInesperado_retorna500InternalServerError() {
        // Setup
        RetweetRequest request = new RetweetRequest("usuario123", 1L);
        doThrow(new RuntimeException("Error de base de datos"))
                .when(twitterService).retwittear("usuario123", 1L);

        // Ejercitación
        ResponseEntity<?> response = controller.retwittear(request);

        // Verificación
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "Debe retornar 500 INTERNAL SERVER ERROR");
        assertEquals("Error inesperado", response.getBody(), "Debe retornar mensaje de error genérico");
    }

    @Test
    @DisplayName("Listar todos los tweets retorna 200 OK con lista de tweets")
    void listarTodosTweets_retorna200OkConListaDeTweets() {
        // Setup
        Usuario usuario = new Usuario("usuario1", List.of());
        Tweet tweet1 = new Tweet("Primer tweet", usuario);
        Tweet tweet2 = new Tweet("Segundo tweet", usuario);
        List<Tweet> tweets = Arrays.asList(tweet1, tweet2);
        when(twitterService.listarTodosTweets()).thenReturn(tweets);

        // Ejercitación
        ResponseEntity<List<TweetResponse>> response = controller.listarTodosTweets();

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Debe retornar 200 OK");
        assertNotNull(response.getBody(), "El body no debe ser nulo");
        assertEquals(2, response.getBody().size(), "Debe retornar 2 tweets");
        assertEquals("Primer tweet", response.getBody().get(0).getTexto(), "Primer tweet debe ser correcto");
        assertEquals("Segundo tweet", response.getBody().get(1).getTexto(), "Segundo tweet debe ser correcto");
        verify(twitterService, times(1)).listarTodosTweets();
    }

    @Test
    @DisplayName("Listar tweets cuando no hay tweets retorna lista vacía")
    void listarTweetsSinTweets_retornaListaVacia() {
        // Setup
        when(twitterService.listarTodosTweets()).thenReturn(List.of());

        // Ejercitación
        ResponseEntity<List<TweetResponse>> response = controller.listarTodosTweets();

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Debe retornar 200 OK");
        assertNotNull(response.getBody(), "El body no debe ser nulo");
        assertTrue(response.getBody().isEmpty(), "La lista debe estar vacía");
    }

    @Test
    @DisplayName("Listar tweets de usuario específico retorna 200 OK con sus tweets")
    void listarTweetsDeUsuarioEspecifico_retorna200OkConSusTweets() {
        // Setup
        Usuario usuario = new Usuario("usuario1", List.of());
        Tweet tweet1 = new Tweet("Tweet de usuario1", usuario);
        List<Tweet> tweets = List.of(tweet1);
        when(twitterService.listarTweetsDeUsuario("usuario1")).thenReturn(tweets);

        // Ejercitación
        ResponseEntity<List<TweetResponse>> response = controller.listarTweetsDeUsuario("usuario1");

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Debe retornar 200 OK");
        assertNotNull(response.getBody(), "El body no debe ser nulo");
        assertEquals(1, response.getBody().size(), "Debe retornar 1 tweet");
        assertEquals("Tweet de usuario1", response.getBody().get(0).getTexto(), "El tweet debe ser correcto");
        verify(twitterService, times(1)).listarTweetsDeUsuario("usuario1");
    }

    @Test
    @DisplayName("Listar tweets de usuario inexistente retorna 404 NOT FOUND")
    void listarTweetsDeUsuarioInexistente_retorna404NotFound() {
        // Setup
        when(twitterService.listarTweetsDeUsuario("usuarioInexistente"))
                .thenThrow(new RuntimeException("Usuario no encontrado"));

        // Ejercitación
        ResponseEntity<List<TweetResponse>> response = controller.listarTweetsDeUsuario("usuarioInexistente");

        // Verificación
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Debe retornar 404 NOT FOUND");
        assertNull(response.getBody(), "El body debe ser nulo");
    }

    @Test
    @DisplayName("Convertir tweet a response incluye fecha correctamente")
    void convertirTweetAResponse_incluyeFechaCorrectamente() {
        // Setup
        Usuario usuario = new Usuario("usuario1", List.of());
        Tweet tweet = new Tweet("Tweet con fecha", usuario);
        List<Tweet> tweets = List.of(tweet);
        when(twitterService.listarTodosTweets()).thenReturn(tweets);

        // Ejercitación
        ResponseEntity<List<TweetResponse>> response = controller.listarTodosTweets();

        // Verificación
        assertNotNull(response.getBody(), "El body no debe ser nulo");
        assertNotNull(response.getBody().get(0).getFecha(), "La fecha no debe ser nula");
        assertFalse(response.getBody().get(0).isEsRetweet(), "No debe ser un retweet");
    }

    @Test
    @DisplayName("Convertir retweet a response incluye información del tweet original")
    void convertirRetweetAResponse_incluyeInformacionDelTweetOriginal() {
        // Setup
        Usuario usuario1 = new Usuario("usuario1", List.of());
        Usuario usuario2 = new Usuario("usuario2", List.of());
        Tweet tweetOriginal = new Tweet("Tweet original", usuario1);
        Retweet retweet = new Retweet(tweetOriginal, usuario2);
        List<Tweet> tweets = List.of(retweet);
        when(twitterService.listarTodosTweets()).thenReturn(tweets);

        // Ejercitación
        ResponseEntity<List<TweetResponse>> response = controller.listarTodosTweets();

        // Verificación
        assertNotNull(response.getBody(), "El body no debe ser nulo");
        TweetResponse tweetResponse = response.getBody().get(0);
        assertTrue(tweetResponse.isEsRetweet(), "Debe ser un retweet");
        assertNotNull(tweetResponse.getAutorOriginal(), "Debe tener el autor del tweet original");
        assertEquals("usuario1", tweetResponse.getAutorOriginal(), "El autor original debe ser usuario1");
        assertEquals("Tweet original", tweetResponse.getTexto(), "El texto debe ser el del tweet original");
    }
}

