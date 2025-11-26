package unrn.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para UsuarioResponse")
class UsuarioResponseTest {

    @Test
    @DisplayName("Constructor con parámetro inicializa correctamente")
    void constructorConParametro_inicializaCorrectamente() {
        // Ejercitación
        UsuarioResponse response = new UsuarioResponse("usuario123");

        // Verificación
        assertEquals("usuario123", response.getUserName(), "El userName debe ser el asignado");
    }

    @Test
    @DisplayName("Constructor vacío crea objeto correctamente")
    void constructorVacio_creaObjetoCorrectamente() {
        // Ejercitación
        UsuarioResponse response = new UsuarioResponse();

        // Verificación
        assertNotNull(response, "El objeto no debe ser nulo");
    }

    @Test
    @DisplayName("Setter de userName funciona correctamente")
    void setterDeUserName_funcionaCorrectamente() {
        // Setup
        UsuarioResponse response = new UsuarioResponse();

        // Ejercitación
        response.setUserName("nuevoUsuario");

        // Verificación
        assertEquals("nuevoUsuario", response.getUserName(), "El userName debe ser el actualizado");
    }
}

