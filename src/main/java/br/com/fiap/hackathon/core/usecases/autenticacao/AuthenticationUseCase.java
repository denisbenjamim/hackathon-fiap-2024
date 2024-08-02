package br.com.fiap.hackathon.core.usecases.autenticacao;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.input.AuthenticationInput;
import br.com.fiap.hackathon.core.output.LoginResponseOutput;
import br.com.fiap.hackathon.core.services.TokenService;
import br.com.fiap.hackathon.spring.entity.AutenticacaoEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthenticationUseCase {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationUseCase(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponseOutput login(AuthenticationInput authInput) throws BusinessException {
        if (authInput == null || authInput.usuario() == null || authInput.usuario().isBlank() || authInput.senha() == null || authInput.senha().isBlank()) {
            throw new ArgumentoObrigatorioException("Login e senha são obrigatórios");
        }

        var usernamePassword = new UsernamePasswordAuthenticationToken(authInput.usuario(), authInput.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = this.tokenService.generateToken((AutenticacaoEntity) auth.getPrincipal(), 2); // Expira em 2 minutos
        return new LoginResponseOutput(token);
    }
}
