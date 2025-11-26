package unrn.web;

import jakarta.validation.constraints.NotBlank;

public class RegistrarUsuarioRequest {
    @NotBlank(message = "El userName no puede estar vacío")
    private String userName;

    public RegistrarUsuarioRequest() {
        // Constructor vacío para deserialización
    }

    public RegistrarUsuarioRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
