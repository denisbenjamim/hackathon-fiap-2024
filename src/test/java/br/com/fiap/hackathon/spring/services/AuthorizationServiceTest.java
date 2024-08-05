package br.com.fiap.hackathon.spring.services;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.spring.repository.AutenticacaoRepositoryJPA;

public class AuthorizationServiceTest {

    @Mock
    AutenticacaoRepositoryJPA autenticacaoRepositoryJPA;

    AutoCloseable autoCloseable;
    AuthorizationService service;
    
    @BeforeEach
    void setUp() throws BusinessException{
       autoCloseable = MockitoAnnotations.openMocks(this);
       service = new AuthorizationService(autenticacaoRepositoryJPA);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void naoDeveRetornarLoginCasoNaoEncontrado() {
        when(autenticacaoRepositoryJPA.findByLogin(anyString())).thenReturn(Optional.empty());

        assertThrowsExactly(UsernameNotFoundException.class, () -> service.loadUserByUsername(null) );
    }
}
