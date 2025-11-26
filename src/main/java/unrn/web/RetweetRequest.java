package unrn.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RetweetRequest {
    @NotBlank(message = "El userName no puede estar vacío")
    private String userName;

    @NotNull(message = "El ID del tweet original no puede ser nulo")
    private Long tweetOriginalId;

    public RetweetRequest() {
        // Constructor vacío para deserialización
    }

    public RetweetRequest(String userName, Long tweetOriginalId) {
        this.userName = userName;
        this.tweetOriginalId = tweetOriginalId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getTweetOriginalId() {
        return tweetOriginalId;
    }

    public void setTweetOriginalId(Long tweetOriginalId) {
        this.tweetOriginalId = tweetOriginalId;
    }
}

