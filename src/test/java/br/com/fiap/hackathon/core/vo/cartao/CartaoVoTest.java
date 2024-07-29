package br.com.fiap.hackathon.core.vo.cartao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;

class CartaoVoTest {
    @Mock
    ClienteVo clienteVo;
    AutoCloseable autoCloseable;
    
    @BeforeEach
    void setUp() throws BusinessException{
       autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void validarSePropriedadeClienteNull() {
       final Throwable throwable = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(null, null, null, null, null));
       assertEquals("Cliente do cartão é obrigatório", throwable.getMessage());
    }

    @Test
    void validarSePropriedadeLimiteNullOuMenorQue1() {
       final Throwable retornoNull = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, null, null, null, null));
       final Throwable retornoMenor = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, BigDecimal.ZERO, null, null, null));
      
       assertEquals("Limite do cartão é inválido", retornoNull.getMessage());
       assertEquals("Limite do cartão é inválido", retornoMenor.getMessage());
    }

    @Test
    void validarSePropriedadeNumeroNullOuVazio() {
       final Throwable retornoNull = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, BigDecimal.ONE, null, null, null));
       final Throwable retornoMenor = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, BigDecimal.ONE, "", null, null));
      
       assertEquals("Número do cartão é obrigatório", retornoNull.getMessage());
       assertEquals("Número do cartão é obrigatório", retornoMenor.getMessage());
    }

    @Test
    void validarSePropriedadeNumeroEstaNoFormatoEsperado() {
       final Throwable retorno1 = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, BigDecimal.ONE, "1234 1234 1234 12345", null, null));
       final Throwable retorno2 = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, BigDecimal.ONE, "1234123412341234", null, null));
       final Throwable retorno3 = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, BigDecimal.ONE, "1234-1234-1234-1234", null, null));
      
       assertEquals("Formato número cartão é inválido", retorno1.getMessage());
       assertEquals("Formato número cartão é inválido", retorno2.getMessage());
       assertEquals("Formato número cartão é inválido", retorno3.getMessage());
    }

    @Test
    void validarSePropriedadeDataValidadeEstaNoFormatoEsperado() {
       final Throwable retorno1 = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, BigDecimal.ONE, "1234 1234 1234 1234", null, null));
       final Throwable retorno2 = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, BigDecimal.ONE, "1234 1234 1234 1234", "", null));
       final Throwable retorno3 = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, BigDecimal.ONE, "1234 1234 1234 1234", "1/24", null));
      
       assertEquals("Data inválida", retorno1.getMessage());
       assertEquals("Data inválida", retorno2.getMessage());
       assertEquals("Data inválida", retorno3.getMessage());
    }

    @Test
    void validarSePropriedadeCVVEstaNoFormatoEsperado() {
       final Throwable retorno1 = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, BigDecimal.ONE, "1234 1234 1234 1234", "01/12", null));
       final Throwable retorno2 = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, BigDecimal.ONE, "1234 1234 1234 1234", "01/12", ""));
       final Throwable retorno3 = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, BigDecimal.ONE, "1234 1234 1234 1234", "01/12", "12"));
       final Throwable retorno4 = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new CartaoVo(clienteVo, BigDecimal.ONE, "1234 1234 1234 1234", "01/12", "12345"));
      
       assertEquals("CVV inválido", retorno1.getMessage());
       assertEquals("CVV inválido", retorno2.getMessage());
       assertEquals("CVV inválido", retorno3.getMessage());
       assertEquals("CVV inválido", retorno4.getMessage());
    }

    @Test
    void deveCriarCartao() throws BusinessException{
        final CartaoVo cartao1 = new CartaoVo(clienteVo, BigDecimal.ONE, "1234 1234 1234 1234", "01/12", "1234");
        final CartaoVo cartao2 = new CartaoVo(clienteVo, BigDecimal.ONE, "1234 1234 1234 1234", "01/12", "123");

        assertNotNull(cartao1);
        assertNotNull(cartao2);
    }
}
