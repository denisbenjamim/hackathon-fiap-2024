package br.com.fiap.hackathon.core.usecases.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
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
import br.com.fiap.hackathon.core.exception.ClienteExistenteException;
import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.core.output.RegistraClienteOutput;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;

class RegistraClienteUseCaseTest {

    RegistraClienteUseCase useCase;
    @Mock ClientesRepository repository;
    AutoCloseable autoCloseable;
    
    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        useCase = new RegistraClienteUseCase(repository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void naoDeveRegistrarClienteCasoClienteNull() {
        final Throwable throwable = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> useCase.registrar(null));
        assertEquals("Cliente é obrigatório", throwable.getMessage());
    }

    @Test
    void naoDeveRegistrarClienteCasoClienteNaoRegistrado() {
        final ClienteVo clienteVo = mock(ClienteVo.class);
        
        when(repository.buscarPorCPFExistente(any())).thenReturn(true);
        
        final Throwable throwable = assertThrowsExactly(ClienteExistenteException.class, () -> useCase.registrar(clienteVo));
        assertEquals("Cliente já cadastrado", throwable.getMessage());

        verify(repository).buscarPorCPFExistente(any());
    }

    @Test
    void deveRegistrarCliente() throws BusinessException {
        final ClienteVo clienteVo = mock(ClienteVo.class);
        final ClienteVo clienteSalvoVo = mock(ClienteVo.class);
        when(clienteSalvoVo.getCpf()).thenReturn("11111111119");
        when(repository.registrar(clienteVo)).thenReturn(clienteSalvoVo);
        
        final RegistraClienteOutput retorno = (RegistraClienteOutput) useCase.registrar(clienteVo);

        assertEquals(clienteSalvoVo.getCpf(), retorno.getId_cliente());

        verify(repository).registrar(clienteVo);
    }
}
