package unrn.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    static final String ERROR_USERNAME_DUPLICADO = "Ya existe un usuario con ese userName";
    static final String ERROR_USERNAME_LONGITUD = "El userName debe tener entre 5 y 25 caracteres";
    static final String ERROR_USERNAME_NULL = "El userName no puede ser nulo";

    private final String userName;
    private final List<Tweet> tweets;

    public Usuario(String userName, List<Usuario> usuariosExistentes) {
        assertUserNameNoNull(userName);
        assertUserNameLongitud(userName);
        assertUserNameUnico(userName, usuariosExistentes);
        this.userName = userName;
        this.tweets = new ArrayList<>();
    }

    public void publicarTweet(String texto) {
        Tweet tweet = new Tweet(texto, this);
        tweets.add(tweet);
    }

    public void retwittear(Tweet tweetOriginal) {
        Tweet retweet = Tweet.retweet(tweetOriginal, this);
        tweets.add(retweet);
    }

    public List<Tweet> timeline() {
        return Collections.unmodifiableList(tweets);
    }

    public void eliminar() {
        tweets.clear();
    }

    // Solo esta para probar en Main
    public String userName() {
        return userName;
    }

    private void assertUserNameNoNull(String userName) {
        if (userName == null) {
            throw new RuntimeException(ERROR_USERNAME_NULL);
        }
    }

    private void assertUserNameLongitud(String userName) {
        if (userName.length() < 5 || userName.length() > 25) {
            throw new RuntimeException(ERROR_USERNAME_LONGITUD);
        }
    }

    private void assertUserNameUnico(String userName, List<Usuario> usuariosExistentes) {
        for (Usuario u : usuariosExistentes) {
            if (u.userName.equals(userName)) {
                throw new RuntimeException(ERROR_USERNAME_DUPLICADO);
            }
        }
    }
}
