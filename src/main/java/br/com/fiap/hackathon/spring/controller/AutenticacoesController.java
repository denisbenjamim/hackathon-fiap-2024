package br.com.fiap.hackathon.spring.controller;

import br.com.fiap.hackathon.core.gateway.AutenticacaoRepository;
import br.com.fiap.hackathon.core.input.AuthenticationInput;
import br.com.fiap.hackathon.core.output.LoginResponseOutput;
import br.com.fiap.hackathon.core.services.TokenService;
import br.com.fiap.hackathon.spring.entity.AutenticacaoEntity;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseBadRequestJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseOkJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseServerErrorJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseUnauthorizedJson;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autenticacao")
public class AutenticacoesController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacoesController(AutenticacaoRepository autenticacaoRepository, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    @ApiResponseOkJson
    @ApiResponseUnauthorizedJson
    @ApiResponseBadRequestJson
    @ApiResponseServerErrorJson
    public ResponseEntity<LoginResponseOutput> login(@RequestBody @Valid AuthenticationInput data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.usuario(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = this.tokenService.generateToken((AutenticacaoEntity) auth.getPrincipal(), 2); // Token expira em 2 minutos

            return ResponseEntity.ok(new LoginResponseOutput(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

}
