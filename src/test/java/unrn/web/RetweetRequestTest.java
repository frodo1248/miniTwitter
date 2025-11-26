package unrn.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para RetweetRequest")
class RetweetRequestTest {

    @Test
    @DisplayName("Constructor con parámetros inicializa correctamente")
    void constructorConParametros_inicializaCorrectamente() {
        // Ejercitación
        RetweetRequest request = new RetweetRequest("usuario123", 5L);

        // Verificación
        assertEquals("usuario123", request.getUserName(), "El userName debe ser el asignado");
        assertEquals(5L, request.getTweetOriginalId(), "El tweetOriginalId debe ser el asignado");
    }

    @Test
    @DisplayName("Constructor vacío crea objeto correctamente")
    void constructorVacio_creaObjetoCorrectamente() {
        // Ejercitación
        RetweetRequest request = new RetweetRequest();

        // Verificación
        assertNotNull(request, "El objeto no debe ser nulo");
    }

    @Test
    @DisplayName("Setter de userName funciona correctamente")
    void setterDeUserName_funcionaCorrectamente() {
        // Setup
        RetweetRequest request = new RetweetRequest();

        // Ejercitación
        request.setUserName("nuevoUsuario");

        // Verificación
        assertEquals("nuevoUsuario", request.getUserName(), "El userName debe ser el actualizado");
    }

    @Test
    @DisplayName("Setter de tweetOriginalId funciona correctamente")
    void setterDeTweetOriginalId_funcionaCorrectamente() {
        // Setup
        RetweetRequest request = new RetweetRequest();

        // Ejercitación
        request.setTweetOriginalId(10L);

        // Verificación
        assertEquals(10L, request.getTweetOriginalId(), "El tweetOriginalId debe ser el actualizado");
    }
}

