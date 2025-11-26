package unrn.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Usuario {
    public static final String ERROR_USERNAME_DUPLICADO = "Ya existe un usuario con ese userName";
    public static final String ERROR_USERNAME_LONGITUD = "El userName debe tener entre 5 y 25 caracteres";
    public static final String ERROR_USERNAME_NULL = "El userName no puede ser nulo";

    @Id
    @Column(length = 25, nullable = false, unique = true)
    private String userName;


    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Tweet> tweets = new ArrayList<>();

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
        Retweet retweet = new Retweet(tweetOriginal, this);
        tweets.add(retweet);
    }

    public List<Tweet> timeline() {
        return Collections.unmodifiableList(tweets);
    }

    public String userName() {
        return this.userName;
    }

    public void eliminar() {
        tweets.clear();
    }

    private void assertUserNameNoNull(String userName) {
        if (userName == null) {
            throw new RuntimeException(ERROR_USERNAME_NULL);
        }
    }

    private void assertUserNameLongitud(String userName) {
        if (userName == null) return; // Dejar que la validación de nulo lo maneje
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
