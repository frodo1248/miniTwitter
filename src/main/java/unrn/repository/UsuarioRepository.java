package unrn.repository;

import unrn.model.Usuario;
import java.util.List;

public interface UsuarioRepository {
    void guardar(Usuario usuario);
    Usuario buscarPorUserName(String userName);
    void eliminar(Usuario usuario);
    List<Usuario> listarTodos();
}

