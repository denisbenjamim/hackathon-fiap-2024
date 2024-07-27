package br.com.fiap.hackathon.spring.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cartao")
public class CartaoEntity {
    
    @OneToOne
    @JoinColumn(name = "cd_cliente")
    ClienteEntity cliente;
    BigDecimal limite = BigDecimal.ZERO;
    @Id
    @Column(length = 16, name = "nr_cartao")
    String numero;
    @Column(length = 5, name = "dt_validade")
    String dataValidade;
    @Column(length = 3, name = "cd_cvv")
    String cvv;

    public ClienteEntity getCliente() {
        return cliente;
    }
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    public BigDecimal getLimite() {
        return limite;
    }
    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getDataValidade() {
        return dataValidade;
    }
    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }
    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    
}
