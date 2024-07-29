package br.com.fiap.hackathon.spring.entity;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.fiap.hackathon.core.input.AutorizarPagamentoInput;
import br.com.fiap.hackathon.core.vo.pagamento.StatusPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pagamento_recusado")
public class AutorizarPagamentoRecusadaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cd_pagamento", nullable = false)
    UUID chave;

    @Column(name="nr_cartao")
    String numero;
    @Column(name="cd_cliente")
    String codigoCliente;

    @Column(name = "vl_pagamento")
    BigDecimal valor = BigDecimal.ZERO;

    @Column(name = "tp_pagamento", nullable = false)
    String tipoPagamento = "cartao_credito";

    @Enumerated(EnumType.STRING)
    @Column(name = "tp_situacao", nullable = false)
    StatusPagamento status = StatusPagamento.RECUSADO;

    public AutorizarPagamentoRecusadaEntity(AutorizarPagamentoInput autorizacao) {
        this.chave = null;
        this.numero = autorizacao.numero();
        this.codigoCliente = autorizacao.cpf();
        this.valor = autorizacao.valor();
    }
    public AutorizarPagamentoRecusadaEntity() {
    }
    public UUID getChave() {
        return chave;
    }
    public void setChave(UUID chave) {
        this.chave = chave;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getCodigoCliente() {
        return codigoCliente;
    }
    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public String getTipoPagamento() {
        return tipoPagamento;
    }
    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    public StatusPagamento getStatus() {
        return status;
    }
    public void setStatus(StatusPagamento status) {
        this.status = status;
    }
        
}
