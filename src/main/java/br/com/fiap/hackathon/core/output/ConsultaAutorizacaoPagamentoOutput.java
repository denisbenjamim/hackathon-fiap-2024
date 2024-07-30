package br.com.fiap.hackathon.core.output;

import java.math.BigDecimal;

import br.com.fiap.hackathon.core.vo.pagamento.StatusPagamento;

public class ConsultaAutorizacaoPagamentoOutput implements Output {
    
    final String descricao =  "Compra de produto X";
    final String metodo_pagamento = "cartao_credito";
    final StatusPagamento status;
    final BigDecimal valor;
    
    public ConsultaAutorizacaoPagamentoOutput(StatusPagamento status, BigDecimal valor) {
        this.status = status;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMetodo_pagamento() {
        return metodo_pagamento;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
