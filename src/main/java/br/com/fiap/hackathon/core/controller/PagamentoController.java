package br.com.fiap.hackathon.core.controller;

import java.time.LocalDate;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.exception.CartaoCVVInvalidoExcepetion;
import br.com.fiap.hackathon.core.exception.CartaoDataValidaInvalidaExcepetion;
import br.com.fiap.hackathon.core.exception.CartaoSemLimiteExcepetion;
import br.com.fiap.hackathon.core.gateway.AutorizarPagamentosRepository;
import br.com.fiap.hackathon.core.gateway.CartoesRepository;
import br.com.fiap.hackathon.core.input.AutorizarPagamentoInput;
import br.com.fiap.hackathon.core.output.Output;
import br.com.fiap.hackathon.core.usecases.cartao.BuscaCartaoUseCase;
import br.com.fiap.hackathon.core.usecases.pagamento.AutorizarPagamentoUseCase;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;
import br.com.fiap.hackathon.core.vo.pagamento.AutorizarPagamentoAprovadoVo;

public abstract class PagamentoController {
    final BuscaCartaoUseCase buscaCartaoUseCase;
    final AutorizarPagamentoUseCase autorizarPagamentoUseCase;

    public PagamentoController(AutorizarPagamentosRepository repository, CartoesRepository cartoesRepository) {
        this.autorizarPagamentoUseCase = new AutorizarPagamentoUseCase(repository);
        this.buscaCartaoUseCase = new BuscaCartaoUseCase(cartoesRepository);
    }

    protected Output autorizar(AutorizarPagamentoInput input) throws BusinessException{
        try{
            final CartaoVo cartao = buscaCartaoUseCase.buscarCartaoClientePor(input.numero(), input.cpf());
            
            final int VALOR_MAIOR_LIMITE = -1;
            
            if(cartao.getLimite().compareTo(input.valor()) == VALOR_MAIOR_LIMITE){
                throw new CartaoSemLimiteExcepetion();
            }
            
            if(!cartao.getDataValidade().isEqual(input.getDataValidade()) && cartao.getDataValidade().isAfter(LocalDate.now())){
                throw new CartaoDataValidaInvalidaExcepetion();
            }
            
            if(!input.cvv().equals(cartao.getCvv())){
                throw new CartaoCVVInvalidoExcepetion();
            } 
            
            final AutorizarPagamentoAprovadoVo vo = input.toVo(cartao);
            
            return autorizarPagamentoUseCase.aprovar(vo);
        } catch(BusinessException exception){
            autorizarPagamentoUseCase.recusar(input);
            
            throw exception;
        }
    }
}
