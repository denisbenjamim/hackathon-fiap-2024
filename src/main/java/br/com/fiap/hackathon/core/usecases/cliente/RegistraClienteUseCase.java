package br.com.fiap.hackathon.core.usecases.cliente;

import java.util.Objects;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;

public class RegistraClienteUseCase {

    final ClientesRepository repository;
    
    public RegistraClienteUseCase(ClientesRepository repository) {
        this.repository = repository;
    }

    public ClienteVo registrar(final ClienteVo cliente) throws BusinessException {
        if(Objects.isNull(cliente)){
            throw new ArgumentoObrigatorioException("Cliente é obrigatório");
        }

        return repository.registrar(cliente);
    }
}
