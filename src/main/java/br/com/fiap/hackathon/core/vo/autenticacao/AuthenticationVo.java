package br.com.fiap.hackathon.core.vo.autenticacao;

import java.util.Objects;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;

public class AuthenticationVo {
    private final String login;
    private final String password;

    public AuthenticationVo(String login, String password) throws ArgumentoObrigatorioException {
        if (Objects.isNull(login) || login.isBlank()) {
            throw new ArgumentoObrigatorioException("Login é obrigatório");
        }
        if (Objects.isNull(password) || password.isBlank()) {
            throw new ArgumentoObrigatorioException("Senha é obrigatória");
        }
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
