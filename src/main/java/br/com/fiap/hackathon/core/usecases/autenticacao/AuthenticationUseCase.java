package br.com.fiap.hackathon.core.usecases.autenticacao;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.gateway.AutenticacaoRepository;
import br.com.fiap.hackathon.core.input.AuthenticationInput;
import br.com.fiap.hackathon.core.output.LoginResponseOutput;
import br.com.fiap.hackathon.core.services.TokenService;
import br.com.fiap.hackathon.core.vo.autenticacao.AuthenticationVo;
import br.com.fiap.hackathon.spring.entity.AutenticacaoEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthenticationUseCase {

    private final AutenticacaoRepository autenticacaoRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationUseCase(AutenticacaoRepository autenticacaoRepository, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.autenticacaoRepository = autenticacaoRepository;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponseOutput login(AuthenticationInput authInput) throws BusinessException {
        if (authInput == null || authInput.login() == null || authInput.login().isBlank() || authInput.password() == null || authInput.password().isBlank()) {
            throw new ArgumentoObrigatorioException("Login e senha são obrigatórios");
        }

        var usernamePassword = new UsernamePasswordAuthenticationToken(authInput.login(), authInput.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = this.tokenService.generateToken((AutenticacaoEntity) auth.getPrincipal());
        return new LoginResponseOutput(token);
    }

    public void register(AuthenticationVo authVo) throws BusinessException {
        if (authVo == null || authVo.getLogin() == null || authVo.getLogin().isBlank() || authVo.getPassword() == null || authVo.getPassword().isBlank()) {
            throw new ArgumentoObrigatorioException("Login e senha são obrigatórios");
        }

        if (this.autenticacaoRepository.buscarPorLogin(authVo.getLogin()) != null) {
            throw new BusinessException("Usuário já existe");
        }

        var user = new AutenticacaoEntity(authVo.getLogin(), new BCryptPasswordEncoder().encode(authVo.getPassword()));
        this.autenticacaoRepository.salvar(user);
    }
}
