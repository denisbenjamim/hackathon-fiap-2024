//package br.com.fiap.hackathon.spring.controller;
//
//import br.com.fiap.hackathon.core.controller.AuthenticationController;
//import br.com.fiap.hackathon.core.gateway.AutenticacaoRepository;
//import br.com.fiap.hackathon.core.input.AuthenticationInput;
//import br.com.fiap.hackathon.core.services.TokenService;
//import br.com.fiap.hackathon.core.usecases.autenticacao.AuthenticationUseCase;
//import br.com.fiap.hackathon.core.vo.autenticacao.AuthenticationVo;
//import br.com.fiap.hackathon.spring.swagger.custom.ApiResponseCliente_201_400_500;
//import br.com.fiap.hackathon.spring.utils.SpringControllerUtils;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/autenticacao")
//public class AutenticacoesController extends AuthenticationController {
//
//    public AutenticacoesController(AutenticacaoRepository autenticacaoRepository, TokenService tokenService, AuthenticationManager authenticationManager) {
//        super(new AuthenticationUseCase(autenticacaoRepository, tokenService, authenticationManager));
//    }
//
//    @PostMapping("/login")
//    @ApiResponseCliente_201_400_500
//    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationInput request) {
//        var usernamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
//        var auth = this.authenticationManager.authenticate(usernamePassword);
//        var token = this.tokenService.generateToken((AuthenticationVo) auth.getPrincipal());
//
//        return SpringControllerUtils.response(HttpStatus.OK, () -> login(request));
//    }
//
//    @PostMapping("/register")
//    @ApiResponseCliente_201_400_500
//    public ResponseEntity<?> register(@RequestBody @Valid AuthenticationInput request) {
//        return SpringControllerUtils.response(HttpStatus.CREATED, () -> {
//            register(request);
//            return null;
//        });
//    }
//}
