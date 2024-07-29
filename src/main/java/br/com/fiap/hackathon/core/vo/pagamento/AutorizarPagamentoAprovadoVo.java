package br.com.fiap.hackathon.core.vo.pagamento;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;

public class AutorizarPagamentoAprovadoVo {
    final UUID codigo;
    final CartaoVo cartao;
    final BigDecimal valor;
    
    public AutorizarPagamentoAprovadoVo(BigDecimal valor, CartaoVo cartao) {
        this(null, valor, cartao);
    }
    
    public AutorizarPagamentoAprovadoVo(UUID codigo, BigDecimal valor, CartaoVo cartao) {
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

    public UUID getCodigo() {
        return codigo;
    }
    
}
