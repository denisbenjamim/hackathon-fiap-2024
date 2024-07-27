package br.com.fiap.hackathon.infra.adapter.repository;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;
import br.com.fiap.hackathon.spring.entity.ClienteEntity;
import br.com.fiap.hackathon.spring.repository.ClientesRepositoryJPA;


public class ClienteRepositoryImpl implements ClientesRepository {
    final ClientesRepositoryJPA repository;

    public ClienteRepositoryImpl(ClientesRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public ClienteVo registrar(ClienteVo cliente) throws BusinessException {     
        ClienteEntity entity = new ClienteEntity(cliente);
        entity = repository.save(entity);
        
        return entity.toVo();
    }
}
