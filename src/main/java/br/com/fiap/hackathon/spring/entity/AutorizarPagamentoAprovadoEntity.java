package br.com.fiap.hackathon.spring.entity;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.fiap.hackathon.core.vo.pagamento.AutorizacaoPagamentoVo;
import br.com.fiap.hackathon.core.vo.pagamento.StatusPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pagamento_aprovado")
public class AutorizarPagamentoAprovadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cd_pagamento", nullable = false)
    UUID chave;
   
    @ManyToOne
    @JoinColumns(foreignKey = @ForeignKey(name = "FK_CARTAO_PAGAMENTO"),value = {
        @JoinColumn(name="nr_cartao", referencedColumnName = "nr_cartao" , nullable = false),
        @JoinColumn(name="cd_cliente", referencedColumnName = "cd_cliente" , nullable = false)
    })
    CartaoEntity cartao;

    @Column(name = "vl_pagamento")
    BigDecimal valor = BigDecimal.ZERO;

    @Column(name = "ds_pagamento")
    String descricao;

    @Column(name = "tp_pagamento")
    String tipoPagamento = "cartao_credito";
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tp_situacao")
    StatusPagamento status = StatusPagamento.APROVADO;

    

    public AutorizarPagamentoAprovadoEntity(AutorizacaoPagamentoVo autorizacao) {
        this.chave = null;
        this.cartao = new CartaoEntity(autorizacao.getCartao());
        this.valor = autorizacao.getValor();
        this.descricao = "";
    }

    public AutorizarPagamentoAprovadoEntity() {
    }
    public UUID getChave() {
        return chave;
    }
    
    public BigDecimal getValor() {
        return valor;
    }
    
    public StatusPagamento getStatus() {
        return status;
    }
    
}
