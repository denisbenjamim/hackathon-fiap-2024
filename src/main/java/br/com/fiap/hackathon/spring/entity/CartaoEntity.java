package br.com.fiap.hackathon.spring.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cartao")
public class CartaoEntity {
   
    @EmbeddedId
    ClienteCartaoEmbeddable id;

    @Column(name = "qt_limite")
    BigDecimal limite = BigDecimal.ZERO;
    
    @Column(name = "dt_validade")
    LocalDate dataValidade;
    
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
}
