package br.com.fiap.hackathon.spring.security;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.spring.repository.AutenticacaoRepositoryJPA;
import br.com.fiap.hackathon.spring.services.TokenService;
import jakarta.servlet.http.HttpServletRequest;

public class SecurityFilterTest {
    @Mock
    TokenService tokenService;
    
    @Mock
    AutenticacaoRepositoryJPA userRepository;

    AutoCloseable autoCloseable;
    SecurityFilter componente;

    @BeforeEach
    void setUp() throws BusinessException{
       autoCloseable = MockitoAnnotations.openMocks(this);
       componente = new SecurityFilter(tokenService, userRepository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void naoDeveBuscarLoginCasoNull() {
        componente.autenticarByLogin(null);

        verifyNoInteractions(userRepository);
    }

    @Test
    void naoDeveRetornarAutenticacaoEntityCasoLoginInexistente() {
        when(userRepository.findByLogin("login")).thenReturn(Optional.empty());
        componente.autenticarByLogin("login");

        verify(userRepository).findByLogin("login");

    }

    @Test
    void naoDeveRecuperarTokenCasoHeaderNull() {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final String retorno = componente.recoverToken(request);
        
        verify(request).getHeader("Authorization");
        assertNull(retorno);
    }

    @Test
    void naoDeveRecuperarTokenCasoHeaderNaoComeceComBearer() {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("xYzLmahs");
        
        final String retorno = componente.recoverToken(request);

        verify(request).getHeader("Authorization");
        assertNull(retorno);
    }
}
