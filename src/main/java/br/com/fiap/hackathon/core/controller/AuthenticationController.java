package br.com.fiap.hackathon.core.controller;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.input.AuthenticationInput;
import br.com.fiap.hackathon.core.output.Output;
import br.com.fiap.hackathon.core.usecases.autenticacao.AuthenticationUseCase;

public abstract class AuthenticationController {

    final AuthenticationUseCase authenticationUseCase;

    protected AuthenticationController(AuthenticationUseCase authenticationUseCase) {
        this.authenticationUseCase = authenticationUseCase;
    }

    protected Output login(AuthenticationInput input) throws BusinessException {
        return authenticationUseCase.login(input);
    }
}
