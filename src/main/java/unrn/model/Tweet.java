package unrn.model;

public class Tweet {
    static final String ERROR_TEXTO_LONGITUD = "El texto del tweet debe tener entre 1 y 280 caracteres";
    static final String ERROR_TWEET_NULL = "El tweet original no puede ser nulo";
    static final String ERROR_RETWEET_PROPIO = "No se puede hacer retweet de un tweet propio";

    private final String texto;
    private final Usuario autor;
    private final Tweet tweetOriginal;

    // Constructor para tweet normal
    public Tweet(String texto, Usuario autor) {
        assertTextoValido(texto);
        this.texto = texto;
        this.autor = autor;
        this.tweetOriginal = null;
    }

    // Constructor privado para retweet
    private Tweet(Tweet tweetOriginal, Usuario autor) {
        assertTweetOriginalNoNull(tweetOriginal);
        assertNoRetweetPropio(tweetOriginal, autor);
        this.texto = tweetOriginal.texto;
        this.autor = autor;
        this.tweetOriginal = tweetOriginal;
    }

    public static Tweet retweet(Tweet tweetOriginal, Usuario autor) {
        return new Tweet(tweetOriginal, autor);
    }

    private void assertTextoValido(String texto) {
        if (texto == null || texto.length() < 1 || texto.length() > 280) {
            throw new RuntimeException(ERROR_TEXTO_LONGITUD);
        }
    }

    private void assertTweetOriginalNoNull(Tweet tweetOriginal) {
        if (tweetOriginal == null) {
            throw new RuntimeException(ERROR_TWEET_NULL);
        }
    }

    private void assertNoRetweetPropio(Tweet tweetOriginal, Usuario autor) {
        if (tweetOriginal.autor == autor) {
            throw new RuntimeException(ERROR_RETWEET_PROPIO);
        }
    }

    // Solo esta para probar en Main
    public String texto() {
        return texto;
    }

    // Solo esta para probar en Main
    public boolean esRetweet() {
        return tweetOriginal != null;
    }

    // Solo esta para probar en Main
    public Tweet tweetOriginal() {
        return tweetOriginal;
    }

    // Solo esta para probar en Main
    public Usuario autor() {
        return autor;
    }
}
