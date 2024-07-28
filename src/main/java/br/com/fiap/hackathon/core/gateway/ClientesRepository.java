package br.com.fiap.hackathon.core.gateway;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;

/**
 * ClientesRepository
 */
public interface ClientesRepository {

    public ClienteVo registrar(ClienteVo cliente) throws BusinessException;
    public ClienteVo buscarPorCPF(String cpf) throws BusinessException;
    public boolean buscarPorCPFExistente(String cpf);


}