package br.com.fiap.hackathon.core.vo.pagamento;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.exception.PagamentoStatusInvalidoException;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;

public class AutorizacaoPagamentoVo {
    final UUID codigo;
    final CartaoVo cartao;
    final BigDecimal valor;
    final String descricao = "Descrição Autorização";
    final String metodoPagamento = "cartao_credito";
    StatusPagamento statusPagamento =  StatusPagamento.VALIDANDO;
    
    public AutorizacaoPagamentoVo(BigDecimal valor, CartaoVo cartao) throws ArgumentoObrigatorioException {
        this(null, valor, cartao);
    }
    
    public AutorizacaoPagamentoVo(UUID codigo, BigDecimal valor, CartaoVo cartao) throws ArgumentoObrigatorioException {
        if(Objects.isNull(cartao)){
            throw new ArgumentoObrigatorioException("Para autorizar pagamento cartao é obrigatorio");
        }
        
        
        int MENOR_QUE_UM = -1;
        if(valor == null || valor.compareTo(BigDecimal.ONE) == MENOR_QUE_UM){
            throw new ArgumentoObrigatorioException("Valor em Autorizaçao pagamento não pode ser menor que zero");
        }

        this.codigo = codigo;
        this.valor = valor;
        this.cartao = cartao;
    }



    public CartaoVo getCartao() {
        return cartao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public StatusPagamento getStatusPagamento(){
        return this.statusPagamento;
    }

    public void mudarStatusPagamentoParaAprovado() throws BusinessException{
        if(
            StatusPagamento.VALIDANDO.equals(this.statusPagamento) || 
            StatusPagamento.APROVADO.equals(this.statusPagamento)
        ){
            this.statusPagamento = StatusPagamento.APROVADO;
            return;
        }

        throw new PagamentoStatusInvalidoException();
    }

    public void mudarStatusPagamentoParaRecusado() throws BusinessException {
        if(
            StatusPagamento.VALIDANDO.equals(this.statusPagamento) ||
            StatusPagamento.RECUSADO.equals(this.statusPagamento)
        ){
            this.statusPagamento = StatusPagamento.RECUSADO;
            return;
        }

        throw new PagamentoStatusInvalidoException();
    }
    
}
