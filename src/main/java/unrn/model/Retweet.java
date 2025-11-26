package unrn.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("RETWEET")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Retweet extends Tweet {
    public static final String ERROR_TWEET_NULL = "El tweet original no puede ser nulo";
    public static final String ERROR_RETWEET_PROPIO = "No se puede hacer retweet de un tweet propio";

    @ManyToOne
    @JoinColumn(name = "tweet_original_id", referencedColumnName = "id")
    private Tweet tweetOriginal;

    public Retweet(Tweet tweetOriginal, Usuario autor) {
        super("", autor); // String vacío porque el retweet no tiene texto propio
        assertTweetOriginalNoNull(tweetOriginal);
        assertNoRetweetPropio(tweetOriginal, autor);
        this.tweetOriginal = tweetOriginal;
    }

    private void assertTweetOriginalNoNull(Tweet tweetOriginal) {
        if (tweetOriginal == null) {
            throw new RuntimeException(ERROR_TWEET_NULL);
        }
    }

    private void assertNoRetweetPropio(Tweet tweetOriginal, Usuario autor) {
        if (tweetOriginal.getAutor() == autor) {
            throw new RuntimeException(ERROR_RETWEET_PROPIO);
        }
    }

    public String texto() {
        return tweetOriginal.getTexto();
    }

    public boolean esRetweet() {
        return true;
    }

    @Override
    public String infoExtra() {
        return " (retweet de " + tweetOriginal.autor().userName() + ")";
    }

    public Tweet tweetOriginal() {
        return tweetOriginal;
    }

    public Usuario autor() {
        return super.getAutor();
    }
}
