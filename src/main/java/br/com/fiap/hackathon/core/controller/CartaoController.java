package br.com.fiap.hackathon.core.controller;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.gateway.CartoesRepository;
import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.core.input.CartaoInput;
import br.com.fiap.hackathon.core.usecases.cartao.GeraCartaoUseCase;
import br.com.fiap.hackathon.core.usecases.cliente.BuscaClienteUseCase;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;

public abstract class CartaoController {
    final CartoesRepository repository;
    final ClientesRepository clientesRepository;
    
    public CartaoController(CartoesRepository repository, ClientesRepository clientesRepository) {
        this.repository = repository;
        this.clientesRepository = clientesRepository;
    }

    protected void gerar(CartaoInput input) throws BusinessException{
        final ClienteVo cliente = new BuscaClienteUseCase(clientesRepository).buscarPor(input.cpf());
        final CartaoVo cartao = input.toVo(cliente);

        new GeraCartaoUseCase(repository).gerar(cartao);
    }
}
