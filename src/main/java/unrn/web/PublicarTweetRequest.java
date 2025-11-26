package unrn.web;

import jakarta.validation.constraints.NotBlank;

public class PublicarTweetRequest {
    @NotBlank(message = "El userName no puede estar vacío")
    private String userName;

    @NotBlank(message = "El texto del tweet no puede estar vacío")
    private String texto;

    public PublicarTweetRequest() {
        // Constructor vacío para deserialización
    }

    public PublicarTweetRequest(String userName, String texto) {
        this.userName = userName;
        this.texto = texto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}

