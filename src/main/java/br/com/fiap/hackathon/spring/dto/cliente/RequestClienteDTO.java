package br.com.fiap.hackathon.spring.dto.cliente;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;
import br.com.fiap.hackathon.core.vo.cliente.EnderecoVo;

public record RequestClienteDTO(
    String cpf,
    String nome,
    String email,
    String telefone,
    String rua,
    String cidade,
    String estado,
    String cep,
    String pais
) {
    public ClienteVo toVo() throws BusinessException{
        final EnderecoVo endereco = new EnderecoVo(rua, cidade, estado, cep, pais);
        return new ClienteVo(cpf, nome, email, telefone, endereco);
    }
}
