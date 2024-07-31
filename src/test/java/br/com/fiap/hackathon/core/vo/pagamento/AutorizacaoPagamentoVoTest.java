package br.com.fiap.hackathon.core.vo.pagamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.exception.PagamentoStatusInvalidoException;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;

public class AutorizacaoPagamentoVoTest {
    @Test
    void naoDeveCriarAutorizacaoPagamentoCasoParametroCartaoNull() {
        final Throwable retorno = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new AutorizacaoPagamentoVo(BigDecimal.ONE, null));
        assertEquals("Para autorizar pagamento cartao é obrigatorio", retorno.getMessage());
    }

    @Test
    void naoDeveCriarAutorizacaoPagamentoCasoParametroValorNull() {
        final CartaoVo cartao = mock(CartaoVo.class);
        final Throwable retorno = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new AutorizacaoPagamentoVo(null, cartao));
        
        assertEquals("Valor em Autorizaçao pagamento não pode ser menor que zero", retorno.getMessage());
    }

    @Test
    void naoDeveCriarAutorizacaoPagamentoCasoParametroValorMenorQueUm() {
        final CartaoVo cartao = mock(CartaoVo.class);
        final Throwable retorno1 = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new AutorizacaoPagamentoVo(BigDecimal.ZERO, cartao));
        final Throwable retorno2 = assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new AutorizacaoPagamentoVo(BigDecimal.valueOf(-1), cartao));
        
        assertEquals("Valor em Autorizaçao pagamento não pode ser menor que zero", retorno1.getMessage());
        assertEquals("Valor em Autorizaçao pagamento não pode ser menor que zero", retorno2.getMessage());
    }

    @Test
    void deveCriarAutorizacaoPagamento() throws ArgumentoObrigatorioException{
        final CartaoVo cartao = mock(CartaoVo.class);
        final AutorizacaoPagamentoVo retorno1 = new AutorizacaoPagamentoVo(UUID.randomUUID(), BigDecimal.valueOf(100), cartao);
        final AutorizacaoPagamentoVo retorno2 = new AutorizacaoPagamentoVo(BigDecimal.valueOf(100), cartao);

        assertNotNull(retorno1);
        assertNotNull(retorno2);
    }

    @Test
    void deveAlterarStatusAutorizacaoPagamentoParaAprovado() throws BusinessException{
        final CartaoVo cartao = mock(CartaoVo.class);
        final AutorizacaoPagamentoVo retorno = new AutorizacaoPagamentoVo(UUID.randomUUID(), BigDecimal.valueOf(100), cartao);

        retorno.mudarStatusPagamentoParaAprovado();
        assertEquals(StatusPagamento.APROVADO, retorno.getStatusPagamento());
    }

    @Test
    void naoDeveAlterarStatusAutorizacaoPagamentoCasoJaAprovado() throws BusinessException{
        final CartaoVo cartao = mock(CartaoVo.class);
        final AutorizacaoPagamentoVo retorno1 = new AutorizacaoPagamentoVo(UUID.randomUUID(), BigDecimal.valueOf(100), cartao);

        //Mockando Valor inicial do Status
        retorno1.mudarStatusPagamentoParaAprovado();

        //Tentando Alterar status pelo mesmo valor
        retorno1.mudarStatusPagamentoParaAprovado();

        assertEquals(StatusPagamento.APROVADO, retorno1.getStatusPagamento());
    }

    @Test
    void naoDeveAlterarStatusAutorizacaoPagamentoCasoJaAprovadoParaRecusado() throws BusinessException{
        final CartaoVo cartao = mock(CartaoVo.class);
        final Throwable retorno = assertThrowsExactly(PagamentoStatusInvalidoException.class, () ->{
            final AutorizacaoPagamentoVo vo = new AutorizacaoPagamentoVo(UUID.randomUUID(), BigDecimal.valueOf(100), cartao);
            vo.mudarStatusPagamentoParaAprovado();
            vo.mudarStatusPagamentoParaRecusado();
        });

        assertEquals("Pagamento não pode ter seu Status alterado após Validado", retorno.getMessage());
    }

    @Test
    void naoDeveAlterarStatusAutorizacaoPagamentoCasoJaRecusado() throws BusinessException{
        final CartaoVo cartao = mock(CartaoVo.class);
        final AutorizacaoPagamentoVo retorno1 = new AutorizacaoPagamentoVo(UUID.randomUUID(), BigDecimal.valueOf(100), cartao);

        //Mockando Valor inicial do Status
        retorno1.mudarStatusPagamentoParaRecusado();

        //Tentando Alterar status pelo mesmo valor
        retorno1.mudarStatusPagamentoParaRecusado();

        assertEquals(StatusPagamento.RECUSADO, retorno1.getStatusPagamento());
    }

    @Test
    void naoDeveAlterarStatusAutorizacaoPagamentoCasoJaRecusadoParaAprovado() throws BusinessException{
        final CartaoVo cartao = mock(CartaoVo.class);
        final Throwable retorno = assertThrowsExactly(PagamentoStatusInvalidoException.class, () ->{
            final AutorizacaoPagamentoVo vo = new AutorizacaoPagamentoVo(UUID.randomUUID(), BigDecimal.valueOf(100), cartao);
            vo.mudarStatusPagamentoParaRecusado();
            vo.mudarStatusPagamentoParaAprovado();
        });

        assertEquals("Pagamento não pode ter seu Status alterado após Validado", retorno.getMessage());
    }
}
