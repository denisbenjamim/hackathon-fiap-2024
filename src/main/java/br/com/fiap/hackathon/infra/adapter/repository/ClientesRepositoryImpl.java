package br.com.fiap.hackathon.infra.adapter.repository;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.exception.EntidadeNaoEncontradaException;
import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;
import br.com.fiap.hackathon.spring.entity.ClienteEntity;
import br.com.fiap.hackathon.spring.repository.ClientesRepositoryJPA;


public class ClientesRepositoryImpl implements ClientesRepository {
    final ClientesRepositoryJPA repository;

    public ClientesRepositoryImpl(ClientesRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public ClienteVo registrar(ClienteVo cliente) throws BusinessException {     
        ClienteEntity entity = new ClienteEntity(cliente);
        entity = repository.save(entity);
        
        return entity.toVo();
    }

    @Override
    public ClienteVo buscarPorCPF(String cpf) throws BusinessException {
        final ClienteEntity entity = repository.findByCpf(cpf);

        if(entity == null){
            throw new EntidadeNaoEncontradaException("O CPF '"+cpf+"' não foi encontrado");
        }

        return entity.toVo();
    }

    @Override
    public boolean buscarPorCPFExistente(String cpf){
        return repository.existsByCpf(cpf);
    }
}
