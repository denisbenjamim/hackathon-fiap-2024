package br.com.fiap.hackathon.spring.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * ClienteCartaoEmbeddable
 */
@Embeddable
public class ClienteCartaoEmbeddable implements Serializable{

    @ManyToOne
    @JoinColumn(name = "cd_cliente",foreignKey = @ForeignKey(name = "FK_CLIENTE_CARTAO"))
    ClienteEntity cliente;
    
    @Column(length = 19, name = "nr_cartao", unique = true)
    String numero;

    public ClienteCartaoEmbeddable() {
    }

    public ClienteCartaoEmbeddable(ClienteEntity cliente, String numero) {
        this.cliente = cliente;
        this.numero = numero;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public String getNumero() {
        return numero;
    }
}