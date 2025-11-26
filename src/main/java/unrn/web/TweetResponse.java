package unrn.web;

public class TweetResponse {
    private Long id;
    private String texto;
    private String autor;
    private String fecha;
    private boolean esRetweet;
    private Long tweetOriginalId;
    private String autorOriginal;

    public TweetResponse() {
    }

    public TweetResponse(Long id, String texto, String autor, String fecha, boolean esRetweet, Long tweetOriginalId, String autorOriginal) {
        this.id = id;
        this.texto = texto;
        this.autor = autor;
        this.fecha = fecha;
        this.esRetweet = esRetweet;
        this.tweetOriginalId = tweetOriginalId;
        this.autorOriginal = autorOriginal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isEsRetweet() {
        return esRetweet;
    }

    public void setEsRetweet(boolean esRetweet) {
        this.esRetweet = esRetweet;
    }

    public Long getTweetOriginalId() {
        return tweetOriginalId;
    }

    public void setTweetOriginalId(Long tweetOriginalId) {
        this.tweetOriginalId = tweetOriginalId;
    }

    public String getAutorOriginal() {
        return autorOriginal;
    }

    public void setAutorOriginal(String autorOriginal) {
        this.autorOriginal = autorOriginal;
    }
}

