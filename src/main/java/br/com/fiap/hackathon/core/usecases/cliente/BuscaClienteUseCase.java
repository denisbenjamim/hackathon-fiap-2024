package br.com.fiap.hackathon.core.usecases.cliente;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;

public class BuscaClienteUseCase {

    final ClientesRepository repository;
    
    public BuscaClienteUseCase(ClientesRepository repository) {
        this.repository = repository;
    }

    public ClienteVo buscarPor(final String cpf) throws BusinessException {
        if(cpf == null || cpf.isBlank()){
            throw new ArgumentoObrigatorioException("CPF do cliente é obrigatório");
        }

        return repository.buscarPorCPF(cpf);
    }
}
