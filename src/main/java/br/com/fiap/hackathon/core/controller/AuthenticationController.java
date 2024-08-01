package br.com.fiap.hackathon.core.controller;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.input.AuthenticationInput;
import br.com.fiap.hackathon.core.output.LoginResponseOutput;
import br.com.fiap.hackathon.core.usecases.autenticacao.AuthenticationUseCase;
import br.com.fiap.hackathon.core.vo.autenticacao.AuthenticationVo;

public abstract class AuthenticationController {

    final AuthenticationUseCase authenticationUseCase;

    protected AuthenticationController(AuthenticationUseCase authenticationUseCase) {
        this.authenticationUseCase = authenticationUseCase;
    }

    protected LoginResponseOutput login(AuthenticationInput input) throws BusinessException {
        return authenticationUseCase.login(input);
    }

    protected void register(AuthenticationVo input) throws BusinessException {
        authenticationUseCase.register(input);
    }
}
