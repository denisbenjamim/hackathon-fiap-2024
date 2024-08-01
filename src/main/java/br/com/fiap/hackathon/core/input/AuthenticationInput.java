package br.com.fiap.hackathon.core.input;

public record AuthenticationInput(
        String login,
        String password
) {
}
