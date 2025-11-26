package unrn.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import unrn.model.Usuario;
import unrn.service.TwitterService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests unitarios para UsuarioController")
class UsuarioControllerTest {

    private UsuarioController controller;
    private TwitterService twitterService;

    @BeforeEach
    void setUp() {
        twitterService = mock(TwitterService.class);
        controller = new UsuarioController(twitterService);
    }

    @Test
    @DisplayName("Registrar usuario válido retorna 201 CREATED")
    void registrarUsuarioValido_retorna201Created() {
        // Setup
        RegistrarUsuarioRequest request = new RegistrarUsuarioRequest("usuario123");
        doNothing().when(twitterService).registrarUsuario("usuario123");

        // Ejercitación
        ResponseEntity<?> response = controller.registrarUsuario(request);

        // Verificación
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Debe retornar 201 CREATED");
        verify(twitterService, times(1)).registrarUsuario("usuario123");
    }

    @Test
    @DisplayName("Registrar usuario duplicado retorna 400 BAD REQUEST con mensaje de error")
    void registrarUsuarioDuplicado_retorna400BadRequest() {
        // Setup
        RegistrarUsuarioRequest request = new RegistrarUsuarioRequest("usuario123");
        doThrow(new RuntimeException(Usuario.ERROR_USERNAME_DUPLICADO))
                .when(twitterService).registrarUsuario("usuario123");

        // Ejercitación
        ResponseEntity<?> response = controller.registrarUsuario(request);

        // Verificación
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Debe retornar 400 BAD REQUEST");
        assertEquals(Usuario.ERROR_USERNAME_DUPLICADO, response.getBody(), "Debe retornar el mensaje de error correcto");
    }

    @Test
    @DisplayName("Registrar usuario con userName nulo retorna 400 BAD REQUEST")
    void registrarUsuarioConUserNameNulo_retorna400BadRequest() {
        // Setup
        RegistrarUsuarioRequest request = new RegistrarUsuarioRequest("usuario123");
        doThrow(new RuntimeException(Usuario.ERROR_USERNAME_NULL))
                .when(twitterService).registrarUsuario("usuario123");

        // Ejercitación
        ResponseEntity<?> response = controller.registrarUsuario(request);

        // Verificación
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Debe retornar 400 BAD REQUEST");
        assertEquals(Usuario.ERROR_USERNAME_NULL, response.getBody(), "Debe retornar mensaje de error de userName nulo");
    }

    @Test
    @DisplayName("Registrar usuario con userName de longitud inválida retorna 400 BAD REQUEST")
    void registrarUsuarioConUserNameInvalido_retorna400BadRequest() {
        // Setup
        RegistrarUsuarioRequest request = new RegistrarUsuarioRequest("abc");
        doThrow(new RuntimeException(Usuario.ERROR_USERNAME_LONGITUD))
                .when(twitterService).registrarUsuario("abc");

        // Ejercitación
        ResponseEntity<?> response = controller.registrarUsuario(request);

        // Verificación
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Debe retornar 400 BAD REQUEST");
        assertEquals(Usuario.ERROR_USERNAME_LONGITUD, response.getBody(), "Debe retornar mensaje de error de longitud");
    }

    @Test
    @DisplayName("Registrar usuario con error inesperado retorna 500 INTERNAL SERVER ERROR")
    void registrarUsuarioConErrorInesperado_retorna500InternalServerError() {
        // Setup
        RegistrarUsuarioRequest request = new RegistrarUsuarioRequest("usuario123");
        doThrow(new RuntimeException("Error de base de datos"))
                .when(twitterService).registrarUsuario("usuario123");

        // Ejercitación
        ResponseEntity<?> response = controller.registrarUsuario(request);

        // Verificación
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "Debe retornar 500 INTERNAL SERVER ERROR");
        assertEquals("Error inesperado", response.getBody(), "Debe retornar mensaje de error genérico");
    }

    @Test
    @DisplayName("Listar usuarios retorna 200 OK con lista de usuarios")
    void listarUsuarios_retorna200OkConListaDeUsuarios() {
        // Setup
        Usuario usuario1 = new Usuario("usuario1", List.of());
        Usuario usuario2 = new Usuario("usuario2", List.of());
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        when(twitterService.listarUsuarios()).thenReturn(usuarios);

        // Ejercitación
        ResponseEntity<List<UsuarioResponse>> response = controller.listarUsuarios();

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Debe retornar 200 OK");
        assertNotNull(response.getBody(), "El body no debe ser nulo");
        assertEquals(2, response.getBody().size(), "Debe retornar 2 usuarios");
        assertEquals("usuario1", response.getBody().get(0).getUserName(), "Primer usuario debe ser usuario1");
        assertEquals("usuario2", response.getBody().get(1).getUserName(), "Segundo usuario debe ser usuario2");
        verify(twitterService, times(1)).listarUsuarios();
    }

    @Test
    @DisplayName("Listar usuarios cuando no hay usuarios retorna lista vacía")
    void listarUsuariosSinUsuarios_retornaListaVacia() {
        // Setup
        when(twitterService.listarUsuarios()).thenReturn(List.of());

        // Ejercitación
        ResponseEntity<List<UsuarioResponse>> response = controller.listarUsuarios();

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Debe retornar 200 OK");
        assertNotNull(response.getBody(), "El body no debe ser nulo");
        assertTrue(response.getBody().isEmpty(), "La lista debe estar vacía");
    }
}

