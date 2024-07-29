package br.com.fiap.hackathon.core.usecases.cartao;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.exception.CartaoClienteNaoEncontradoException;
import br.com.fiap.hackathon.core.gateway.CartoesRepository;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;

public class BuscaCartaoUseCase {
   final CartoesRepository repository;

    public BuscaCartaoUseCase(CartoesRepository repository) {
        this.repository = repository;
    }

    public boolean buscarPorNumeroExistente(String numero){
        return repository.buscarPorNumeroExistente(numero);
    }

    public CartaoVo  buscarCartaoClientePor(String numero, String cpf) throws BusinessException {
        final CartaoVo vo = repository.buscarCartaoClientePor(numero, cpf);
        
        if(vo == null){
            throw new CartaoClienteNaoEncontradoException();
        }
        
        return vo;
    }
   
}
