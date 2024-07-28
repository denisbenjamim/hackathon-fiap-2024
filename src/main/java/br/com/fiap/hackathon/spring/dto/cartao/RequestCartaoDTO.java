package br.com.fiap.hackathon.spring.dto.cartao;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;

public record RequestCartaoDTO(
    String cpf,
    BigDecimal limite,
    String numero,
   @JsonProperty("data_validade") String dataValidade,
    String cvv
) {
    public CartaoVo toVo(ClienteVo cliente) throws BusinessException{
        return new CartaoVo(cliente, limite, numero, dataValidade, cvv);
    }

}
