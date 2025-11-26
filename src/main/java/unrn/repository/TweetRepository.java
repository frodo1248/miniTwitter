package unrn.repository;

import unrn.model.Tweet;
import java.util.List;

public interface TweetRepository {
    void guardar(Tweet tweet);
    Tweet buscarPorId(Long id);
    List<Tweet> listarTodos();
}

