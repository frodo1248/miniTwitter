package unrn.repository;

import jakarta.persistence.EntityManager;
import unrn.model.Usuario;

public class UsuarioRepositoryJpa implements UsuarioRepository {
    private final EntityManager em;

    public UsuarioRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public void guardar(Usuario usuario) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.persist(usuario);
        em.getTransaction().commit();
    }

    @Override
    public Usuario buscarPorUserName(String userName) {
        return em.find(Usuario.class, userName);
    }

    @Override
    public void eliminar(Usuario usuario) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
        em.getTransaction().commit();
    }

    @Override
    public java.util.List<Usuario> listarTodos() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }
}

