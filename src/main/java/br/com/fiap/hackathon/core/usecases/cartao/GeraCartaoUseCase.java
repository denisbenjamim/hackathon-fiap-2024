package br.com.fiap.hackathon.core.usecases.cartao;

import java.util.Objects;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.exception.CartaoExistenteException;
import br.com.fiap.hackathon.core.exception.QuantidadeMaximaCartoesClienteException;
import br.com.fiap.hackathon.core.gateway.CartoesRepository;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;

public class GeraCartaoUseCase {

    final CartoesRepository repository;
    
    public GeraCartaoUseCase(CartoesRepository repository) {
        this.repository = repository;
    }

    public void gerar(CartaoVo cartao) throws BusinessException {
        if(Objects.isNull(cartao)){
            throw new ArgumentoObrigatorioException("Cartão é obrigatório");
        }

        if(repository.buscarTodosCartoesClientePorCPF(cartao.getCodigoCliente()).size() == 2){
             throw new QuantidadeMaximaCartoesClienteException();
        }
        
        if(repository.buscarPorNumeroExistente(cartao.getNumero())){
            throw new CartaoExistenteException();
        }

        repository.gerar(cartao);
    }
}
