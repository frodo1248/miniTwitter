package unrn.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para PublicarTweetRequest")
class PublicarTweetRequestTest {

    @Test
    @DisplayName("Constructor con parámetros inicializa correctamente")
    void constructorConParametros_inicializaCorrectamente() {
        // Ejercitación
        PublicarTweetRequest request = new PublicarTweetRequest("usuario123", "Mi tweet");

        // Verificación
        assertEquals("usuario123", request.getUserName(), "El userName debe ser el asignado");
        assertEquals("Mi tweet", request.getTexto(), "El texto debe ser el asignado");
    }

    @Test
    @DisplayName("Constructor vacío crea objeto correctamente")
    void constructorVacio_creaObjetoCorrectamente() {
        // Ejercitación
        PublicarTweetRequest request = new PublicarTweetRequest();

        // Verificación
        assertNotNull(request, "El objeto no debe ser nulo");
    }

    @Test
    @DisplayName("Setter de userName funciona correctamente")
    void setterDeUserName_funcionaCorrectamente() {
        // Setup
        PublicarTweetRequest request = new PublicarTweetRequest();

        // Ejercitación
        request.setUserName("nuevoUsuario");

        // Verificación
        assertEquals("nuevoUsuario", request.getUserName(), "El userName debe ser el actualizado");
    }

    @Test
    @DisplayName("Setter de texto funciona correctamente")
    void setterDeTexto_funcionaCorrectamente() {
        // Setup
        PublicarTweetRequest request = new PublicarTweetRequest();

        // Ejercitación
        request.setTexto("Nuevo texto");

        // Verificación
        assertEquals("Nuevo texto", request.getTexto(), "El texto debe ser el actualizado");
    }
}

