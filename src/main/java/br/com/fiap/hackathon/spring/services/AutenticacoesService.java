package br.com.fiap.hackathon.spring.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.spring.dto.CredencialDTO;
import br.com.fiap.hackathon.spring.dto.TokenDTO;
import br.com.fiap.hackathon.spring.entity.AutenticacaoEntity;

@Service
public class AutenticacoesService {

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;
    
    public AutenticacoesService() {}

    protected AutenticacoesService(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    public TokenDTO login(CredencialDTO authInput) throws BusinessException {
        if (validarDadosCredencial(authInput)) {
            throw new ArgumentoObrigatorioException("Login e senha são obrigatórios");
        }
        
        var usernamePassword = new UsernamePasswordAuthenticationToken(authInput.usuario(), authInput.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = this.tokenService.generateToken((AutenticacaoEntity) auth.getPrincipal(), 2); // Expira em 2 minutos
        return new TokenDTO(token);
    }

    private boolean validarDadosCredencial(CredencialDTO authInput) {
        return StringUtils.isBlank(authInput.usuario()) || StringUtils.isBlank(authInput.senha());
    }
}
