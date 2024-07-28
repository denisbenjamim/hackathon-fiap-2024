package br.com.fiap.hackathon.spring.entity;

import java.math.BigDecimal;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tb_cartao")
public class CartaoEntity {
   
    @EmbeddedId
    ClienteCartaoEmbeddable id;

    @Column(name = "qt_limite")
    BigDecimal limite = BigDecimal.ZERO;
    
    @Column(length = 5, name = "dt_validade")
    String dataValidade;
    
    @Column(length = 4, name = "cd_cvv")
    String cvv;

    public CartaoEntity() {
    }

    public CartaoEntity(CartaoVo cartao) {
        this.cvv = cartao.getCvv();
        this.dataValidade = cartao.getDataValidade();
        this.limite = cartao.getLimite();
        final ClienteEntity cliente = new ClienteEntity(cartao.getCliente());
        this.id = new ClienteCartaoEmbeddable(cliente, cartao.getNumero());
    }

    public CartaoVo toVo() throws BusinessException{
        return new CartaoVo(id.getCliente().toVo(), limite, id.getNumero(), dataValidade, cvv);
    }

    @Transient
    public ClienteEntity getCliente() {
        return id.getCliente();
    }
   
    public BigDecimal getLimite() {
        return limite;
    }
    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }
    @Transient
    public String getNumero() {
        return id.getNumero();
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

    public ClienteCartaoEmbeddable getId() {
        return id;
    }

    public void setId(ClienteCartaoEmbeddable id) {
        this.id = id;
    }
}
