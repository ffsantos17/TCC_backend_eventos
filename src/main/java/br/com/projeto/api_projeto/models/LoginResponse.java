package br.com.projeto.api_projeto.models;

public class LoginResponse {

    Usuario usuario;
    String token;
    String responseStatus;

    public LoginResponse(Usuario usuario, String token, String responseStatus) {
        this.usuario = usuario;
        this.token = token;
        this.responseStatus = responseStatus;
    }
}
