package br.com.fiap.hackathon.core.usecases.cliente;

import java.util.Objects;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.exception.ClienteExistenteException;
import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.core.output.Output;
import br.com.fiap.hackathon.core.output.RegistraClienteOutput;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;

public class RegistraClienteUseCase  {

    final ClientesRepository repository;
   
    
    public RegistraClienteUseCase(ClientesRepository repository) {
        this.repository = repository;
    }

    public Output registrar(final ClienteVo cliente) throws BusinessException {
        if(Objects.isNull(cliente)){
            throw new ArgumentoObrigatorioException("Cliente é obrigatório");
        }

        if(repository.buscarPorCPFExistente(cliente.getCpf())){
            throw new ClienteExistenteException();
        }

        final ClienteVo retorno = repository.registrar(cliente);
        
        return new RegistraClienteOutput(retorno.getCpf());
    }
   
}
