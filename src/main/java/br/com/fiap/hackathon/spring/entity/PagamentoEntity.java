package br.com.fiap.hackathon.spring.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pagamento")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cd_pagamento", nullable = false)
    UUID chave;
    @ManyToOne
    @JoinColumn(name="cd_cartao", nullable = false)
    CartaoEntity cartao;
    @Column(name = "vl_pagamento", nullable = false)
    BigDecimal valor = BigDecimal.ZERO;

    @Column(name = "ds_pagamento", nullable = false)
    String descricao;

    @Column(name = "tp_pagamento", nullable = false)
    String tipoPagamento;

    @Column(name = "tp_situacao", nullable = false)
    String status;

    public UUID getChave() {
        return chave;
    }
    public void setChave(UUID chave) {
        this.chave = chave;
    }
    public CartaoEntity getCartao() {
        return cartao;
    }
    public void setCartao(CartaoEntity cartao) {
        this.cartao = cartao;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    
}
