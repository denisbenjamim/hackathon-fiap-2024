package br.com.fiap.hackathon.core.controller;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.core.input.ClienteInput;
import br.com.fiap.hackathon.core.output.Output;
import br.com.fiap.hackathon.core.usecases.cliente.RegistraClienteUseCase;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;
import br.com.fiap.hackathon.core.vo.cliente.EnderecoVo;

public abstract class ClienteController {
    final ClientesRepository repository;

    public ClienteController(ClientesRepository repository) {
        this.repository = repository;
    }

    protected Output registrar(ClienteInput input) throws BusinessException{
        final EnderecoVo enderecoVo = new EnderecoVo(input.rua(), input.cidade(), input.estado(), input.cep(), input.pais());
        final ClienteVo clienteVo = new ClienteVo(input.cpf(), input.nome(), input.email(), input.telefone(), enderecoVo);
        
        return new RegistraClienteUseCase(repository).registrar(clienteVo);
    }
}
