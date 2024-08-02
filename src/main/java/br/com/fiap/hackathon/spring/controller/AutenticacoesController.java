package br.com.fiap.hackathon.spring.controller;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.gateway.AutenticacaoRepository;
import br.com.fiap.hackathon.core.input.AuthenticationInput;
import br.com.fiap.hackathon.core.output.LoginResponseOutput;
import br.com.fiap.hackathon.core.services.TokenService;
import br.com.fiap.hackathon.core.vo.autenticacao.AuthenticationVo;
import br.com.fiap.hackathon.spring.entity.AutenticacaoEntity;
import br.com.fiap.hackathon.spring.swagger.custom.ApiResponse_200_401_403_500;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/autenticacao")
public class AutenticacoesController {

    private final AuthenticationManager authenticationManager;
    private final AutenticacaoRepository autenticacaoRepository;
    private final TokenService tokenService;

    public AutenticacoesController(AutenticacaoRepository autenticacaoRepository, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.autenticacaoRepository = autenticacaoRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    @ApiResponse_200_401_403_500
    public ResponseEntity<LoginResponseOutput> login(@RequestBody @Valid AuthenticationInput data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = this.tokenService.generateToken((AutenticacaoEntity) auth.getPrincipal(), 2); // Token expira em 2 minutos

            return ResponseEntity.ok(new LoginResponseOutput(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/register")
    @ApiResponse_200_401_403_500
    public ResponseEntity<Void> register(@RequestBody @Valid AuthenticationVo data) throws BusinessException {
        if (this.autenticacaoRepository.buscarPorLogin(data.getLogin()) != null) {
            return ResponseEntity.badRequest().build();
        }

        var user = new AutenticacaoEntity(data.getLogin(), data.getPassword());
        this.autenticacaoRepository.salvar(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/token")
    @ApiResponse_200_401_403_500
    public ResponseEntity<LoginResponseOutput> generateLongLivedToken(@RequestBody @Valid AuthenticationInput data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = this.tokenService.generateToken((AutenticacaoEntity) auth.getPrincipal(), 4); // Token expira em 4 minutos

            return ResponseEntity.ok(new LoginResponseOutput(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/currentUser")
    @ApiResponse_200_401_403_500
    public ResponseEntity<?> getUserByToken(@RequestParam("token") String token) throws BusinessException {
        final String loginUsuario = tokenService.validateToken(token);

        if (!StringUtils.hasText(loginUsuario)) {
            return ResponseEntity.status(401).build();
        }

        var user = autenticacaoRepository.buscarPorLogin(loginUsuario);
        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(Map.of("idUsuario", user.getCodigo()));
    }
}
