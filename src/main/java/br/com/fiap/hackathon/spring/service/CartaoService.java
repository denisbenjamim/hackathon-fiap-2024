package br.com.fiap.hackathon.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.usecases.cartao.GeraCartaoUseCase;
import br.com.fiap.hackathon.core.usecases.cliente.BuscaClienteUseCase;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;
import br.com.fiap.hackathon.spring.dto.cartao.RequestCartaoDTO;

@Service
public class CartaoService {
    @Autowired
    BuscaClienteUseCase buscaClienteUseCase;

    @Autowired
    GeraCartaoUseCase geraCartaoUseCase;

    public void gerar(RequestCartaoDTO request) throws BusinessException{
        final ClienteVo cliente = buscaClienteUseCase.buscarPor(request.cpf());
        final CartaoVo vo = request.toVo(cliente);

        geraCartaoUseCase.gerar(vo);
    }
}
