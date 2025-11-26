package unrn.repository;

import jakarta.persistence.EntityManager;
import unrn.model.Tweet;

public class TweetRepositoryJpa implements TweetRepository {
    private final EntityManager em;

    public TweetRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public void guardar(Tweet tweet) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.persist(tweet);
        em.getTransaction().commit();
    }

    @Override
    public Tweet buscarPorId(Long id) {
        return em.find(Tweet.class, id);
    }

    @Override
    public java.util.List<Tweet> listarTodos() {
        return em.createQuery("SELECT t FROM Tweet t ORDER BY t.id DESC", Tweet.class).getResultList();
    }
}

