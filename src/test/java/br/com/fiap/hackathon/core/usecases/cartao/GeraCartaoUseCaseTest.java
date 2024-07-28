package br.com.fiap.hackathon.core.usecases.cartao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.exception.CartaoExistenteException;
import br.com.fiap.hackathon.core.exception.QuantidadeMaximaCartoesClienteException;
import br.com.fiap.hackathon.core.gateway.CartoesRepository;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;

class GeraCartaoUseCaseTest {

    GeraCartaoUseCase useCase;
    @Mock CartoesRepository repository;
    AutoCloseable autoCloseable;
    
    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        useCase = new GeraCartaoUseCase(repository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void naoDeveGerarSeCartaoNull() {
        final Throwable throwable = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> useCase.gerar(null));

        assertEquals("Cartão é obrigatório", throwable.getMessage());
    }

    @Test
    void naoDeveGerarSeClienteJaPossuirQuantidadeMaximaCartoes() throws BusinessException {
        final CartaoVo  cartaoVo = mock(CartaoVo.class);

        when(repository.buscarTodosCartoesClientePorCPF(any())).thenReturn(Arrays.asList(cartaoVo, cartaoVo));
        
        final Throwable throwable = assertThrowsExactly(QuantidadeMaximaCartoesClienteException.class, () -> useCase.gerar(cartaoVo));

        assertEquals("Cliente já possui a quantidade maxima permitida de cartões", throwable.getMessage());
    }

    @Test
    void naoDeveGerarSeNumeroCartaoJaRegistrado() throws BusinessException {
        final CartaoVo  cartaoVo = mock(CartaoVo.class);

        when(repository.buscarPorNumeroExistente(any())).thenReturn(true);
        
        final Throwable throwable = assertThrowsExactly(CartaoExistenteException.class, () -> useCase.gerar(cartaoVo));

        assertEquals("Cartão já cadastrado", throwable.getMessage());
    }

    @Test
    void deveGeraCartaoParaCliente() throws BusinessException {
        final CartaoVo  cartaoVo = mock(CartaoVo.class);
        doNothing().when(repository).gerar(cartaoVo);
        useCase.gerar(cartaoVo);

        verify(repository).gerar(cartaoVo);
    }
}
