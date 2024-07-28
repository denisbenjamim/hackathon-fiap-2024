package br.com.fiap.hackathon.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.usecases.cliente.RegistraClienteUseCase;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;
import br.com.fiap.hackathon.spring.dto.cliente.RequestClienteDTO;
import br.com.fiap.hackathon.spring.dto.cliente.ResponseClienteDTO;
import br.com.fiap.hackathon.spring.repository.ClientesRepositoryJPA;

@Service
public class ClienteService {
    @Autowired
    RegistraClienteUseCase registrarCliente;

    @Autowired
    ClientesRepositoryJPA repositoryJPA;

    public ResponseClienteDTO registrar(RequestClienteDTO cliente) throws BusinessException{
        final ClienteVo clienteRegistrado = registrarCliente.registrar(cliente.toVo());
            
        return  new ResponseClienteDTO(clienteRegistrado.getCpf());
    }
}
