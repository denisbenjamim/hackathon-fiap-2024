package br.com.fiap.hackathon.core.usecases.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;

public class BuscaClienteUseCaseTest {

    BuscaClienteUseCase useCase;
    @Mock ClientesRepository repository;
    AutoCloseable autoCloseable;
    
    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        useCase = new BuscaClienteUseCase(repository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void naoDeveBuscarClientePorCpfCasoParametroNull() {
       final Throwable throwable = assertThrowsExactly(ArgumentoObrigatorioException.class, ()->  useCase.buscarPor(null));

       assertEquals("CPF do cliente é obrigatório", throwable.getMessage());
    }

    @Test
    void naoDeveBuscarClientePorCpfCasoParametroStringVazia() {
       final Throwable throwable = assertThrowsExactly(ArgumentoObrigatorioException.class, ()->  useCase.buscarPor(""));
       
       assertEquals("CPF do cliente é obrigatório", throwable.getMessage());
    }

    @Test
    void deveBuscarClientePorCpf() throws BusinessException {
        final String cpf = "12345678900";
        final ClienteVo retornoBanco = mock(ClienteVo.class);
        
        when(repository.buscarPorCPF(cpf)).thenReturn(retornoBanco);
        
        final ClienteVo retorno = useCase.buscarPor(cpf);

        assertEquals(retornoBanco, retorno);
        verify(repository).buscarPorCPF(cpf);
       
    }
}
