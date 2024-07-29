package br.com.fiap.hackathon.core.input;

import java.math.BigDecimal;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;

public record CartaoInput(
    String cpf,
    BigDecimal limite,
    String numero,
    String data_validade,
    String cvv
) {
    public CartaoVo toVo(ClienteVo cliente) throws BusinessException{
        return new CartaoVo(cliente, limite, numero, data_validade, cvv);
    }

}
