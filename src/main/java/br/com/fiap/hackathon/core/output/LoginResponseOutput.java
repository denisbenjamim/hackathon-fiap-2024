package br.com.fiap.hackathon.core.output;

public class LoginResponseOutput implements Output {
    private String token;

    public LoginResponseOutput(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
