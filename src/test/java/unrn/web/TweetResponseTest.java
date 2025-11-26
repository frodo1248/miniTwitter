package unrn.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para TweetResponse")
class TweetResponseTest {

    @Test
    @DisplayName("Constructor con todos los parámetros inicializa correctamente un tweet normal")
    void constructorCompleto_inicializaTweetNormalCorrectamente() {
        // Ejercitación
        TweetResponse response = new TweetResponse(
                1L,
                "Mi tweet",
                "usuario123",
                "2025-11-26T15:30:45",
                false,
                null,
                null
        );

        // Verificación
        assertEquals(1L, response.getId(), "El ID debe ser el asignado");
        assertEquals("Mi tweet", response.getTexto(), "El texto debe ser el asignado");
        assertEquals("usuario123", response.getAutor(), "El autor debe ser el asignado");
        assertEquals("2025-11-26T15:30:45", response.getFecha(), "La fecha debe ser la asignada");
        assertFalse(response.isEsRetweet(), "No debe ser un retweet");
        assertNull(response.getTweetOriginalId(), "No debe tener tweetOriginalId");
        assertNull(response.getAutorOriginal(), "No debe tener autorOriginal");
    }

    @Test
    @DisplayName("Constructor con todos los parámetros inicializa correctamente un retweet")
    void constructorCompleto_inicializaRetweetCorrectamente() {
        // Ejercitación
        TweetResponse response = new TweetResponse(
                2L,
                "Tweet original",
                "usuario456",
                "2025-11-26T16:00:00",
                true,
                1L,
                "usuarioOriginal"
        );

        // Verificación
        assertEquals(2L, response.getId(), "El ID debe ser el asignado");
        assertEquals("Tweet original", response.getTexto(), "El texto debe ser el asignado");
        assertEquals("usuario456", response.getAutor(), "El autor del retweet debe ser el asignado");
        assertEquals("2025-11-26T16:00:00", response.getFecha(), "La fecha debe ser la asignada");
        assertTrue(response.isEsRetweet(), "Debe ser un retweet");
        assertEquals(1L, response.getTweetOriginalId(), "Debe tener el ID del tweet original");
        assertEquals("usuarioOriginal", response.getAutorOriginal(), "Debe tener el autor original");
    }

    @Test
    @DisplayName("Constructor vacío crea objeto correctamente")
    void constructorVacio_creaObjetoCorrectamente() {
        // Ejercitación
        TweetResponse response = new TweetResponse();

        // Verificación
        assertNotNull(response, "El objeto no debe ser nulo");
    }

    @Test
    @DisplayName("Setters funcionan correctamente")
    void setters_funcionanCorrectamente() {
        // Setup
        TweetResponse response = new TweetResponse();

        // Ejercitación
        response.setId(10L);
        response.setTexto("Nuevo texto");
        response.setAutor("nuevoAutor");
        response.setFecha("2025-11-26T17:00:00");
        response.setEsRetweet(true);
        response.setTweetOriginalId(5L);
        response.setAutorOriginal("autorOriginal");

        // Verificación
        assertEquals(10L, response.getId(), "El ID debe ser el actualizado");
        assertEquals("Nuevo texto", response.getTexto(), "El texto debe ser el actualizado");
        assertEquals("nuevoAutor", response.getAutor(), "El autor debe ser el actualizado");
        assertEquals("2025-11-26T17:00:00", response.getFecha(), "La fecha debe ser la actualizada");
        assertTrue(response.isEsRetweet(), "Debe ser marcado como retweet");
        assertEquals(5L, response.getTweetOriginalId(), "El tweetOriginalId debe ser el actualizado");
        assertEquals("autorOriginal", response.getAutorOriginal(), "El autorOriginal debe ser el actualizado");
    }

    @Test
    @DisplayName("Getters retornan los valores correctos")
    void getters_retornanValoresCorrectos() {
        // Setup
        TweetResponse response = new TweetResponse(
                3L,
                "Texto de prueba",
                "usuario789",
                "2025-11-26T18:00:00",
                false,
                null,
                null
        );

        // Verificación
        assertEquals(3L, response.getId(), "El getter de ID debe retornar el valor correcto");
        assertEquals("Texto de prueba", response.getTexto(), "El getter de texto debe retornar el valor correcto");
        assertEquals("usuario789", response.getAutor(), "El getter de autor debe retornar el valor correcto");
        assertEquals("2025-11-26T18:00:00", response.getFecha(), "El getter de fecha debe retornar el valor correcto");
        assertFalse(response.isEsRetweet(), "El getter de esRetweet debe retornar false");
        assertNull(response.getTweetOriginalId(), "El getter de tweetOriginalId debe retornar null");
        assertNull(response.getAutorOriginal(), "El getter de autorOriginal debe retornar null");
    }
}

