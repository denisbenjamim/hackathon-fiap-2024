package br.com.fiap.hackathon.spring.services;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.spring.dto.CredencialDTO;

public class AutenticacoesServiceTest {

    @Mock
    TokenService tokenService;
    @Mock
    AuthenticationManager authenticationManager;

    AutoCloseable autoCloseable;
    AutenticacoesService service;
    
    @BeforeEach
    void setUp() throws BusinessException{
       autoCloseable = MockitoAnnotations.openMocks(this);
       service = new AutenticacoesService(tokenService, authenticationManager);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void naoDeveValidarCredencialCasoUsuarioNaoInformado() {
        assertThrowsExactly(ArgumentoObrigatorioException.class, () -> service.login(new CredencialDTO(null, null)));
        assertThrowsExactly(ArgumentoObrigatorioException.class, () -> service.login(new CredencialDTO("", null)));
    }
    @Test
    void naoDeveValidarCredencialCasoSenhaNaoInformada() {
        assertThrowsExactly(ArgumentoObrigatorioException.class, () -> service.login(new CredencialDTO("login", null)));
        assertThrowsExactly(ArgumentoObrigatorioException.class, () -> service.login(new CredencialDTO("login", "")));
    }
}
