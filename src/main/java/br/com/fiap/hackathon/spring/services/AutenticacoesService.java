package br.com.fiap.hackathon.spring.services;

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

    public TokenDTO login(CredencialDTO authInput) throws BusinessException {
        if (authInput == null || authInput.usuario() == null || authInput.usuario().isBlank()
                || authInput.senha() == null || authInput.senha().isBlank()) {
            throw new ArgumentoObrigatorioException("Login e senha são obrigatórios");
        }
        
        var usernamePassword = new UsernamePasswordAuthenticationToken(authInput.usuario(), authInput.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = this.tokenService.generateToken((AutenticacaoEntity) auth.getPrincipal(), 2); // Expira em 2 minutos
        return new TokenDTO(token);
    }
}
