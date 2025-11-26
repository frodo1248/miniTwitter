package unrn.web;

public class UsuarioResponse {
    private String userName;

    public UsuarioResponse() {
    }

    public UsuarioResponse(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
