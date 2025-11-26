package unrn.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para RegistrarUsuarioRequest")
class RegistrarUsuarioRequestTest {

    @Test
    @DisplayName("Constructor con parámetro inicializa correctamente")
    void constructorConParametro_inicializaCorrectamente() {
        // Ejercitación
        RegistrarUsuarioRequest request = new RegistrarUsuarioRequest("usuario123");

        // Verificación
        assertEquals("usuario123", request.getUserName(), "El userName debe ser el asignado");
    }

    @Test
    @DisplayName("Constructor vacío crea objeto correctamente")
    void constructorVacio_creaObjetoCorrectamente() {
        // Ejercitación
        RegistrarUsuarioRequest request = new RegistrarUsuarioRequest();

        // Verificación
        assertNotNull(request, "El objeto no debe ser nulo");
    }

    @Test
    @DisplayName("Setter de userName funciona correctamente")
    void setterDeUserName_funcionaCorrectamente() {
        // Setup
        RegistrarUsuarioRequest request = new RegistrarUsuarioRequest();

        // Ejercitación
        request.setUserName("nuevoUsuario");

        // Verificación
        assertEquals("nuevoUsuario", request.getUserName(), "El userName debe ser el actualizado");
    }
}

