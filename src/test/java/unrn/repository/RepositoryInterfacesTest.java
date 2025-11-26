package unrn.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import unrn.model.Tweet;
import unrn.model.Usuario;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para verificar que las interfaces de Repository están correctamente definidas")
class RepositoryInterfacesTest {

    @Test
    @DisplayName("UsuarioRepository define los métodos necesarios")
    void usuarioRepositoryDefineMetodosNecesarios() {
        // Verificación
        assertDoesNotThrow(() -> {
            UsuarioRepository.class.getMethod("guardar", Usuario.class);
            UsuarioRepository.class.getMethod("buscarPorUserName", String.class);
            UsuarioRepository.class.getMethod("eliminar", Usuario.class);
            UsuarioRepository.class.getMethod("listarTodos");
        }, "UsuarioRepository debe tener todos los métodos necesarios");
    }

    @Test
    @DisplayName("TweetRepository define los métodos necesarios")
    void tweetRepositoryDefineMetodosNecesarios() {
        // Verificación
        assertDoesNotThrow(() -> {
            TweetRepository.class.getMethod("guardar", Tweet.class);
            TweetRepository.class.getMethod("buscarPorId", Long.class);
            TweetRepository.class.getMethod("listarTodos");
        }, "TweetRepository debe tener todos los métodos necesarios");
    }
}

