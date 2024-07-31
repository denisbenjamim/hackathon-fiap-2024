package br.com.fiap.hackathon.core.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.exception.CartaoCVVInvalidoExcepetion;
import br.com.fiap.hackathon.core.exception.CartaoDataValidaInvalidaExcepetion;
import br.com.fiap.hackathon.core.exception.CartaoSemLimiteExcepetion;
import br.com.fiap.hackathon.core.gateway.AutorizarPagamentosRepository;
import br.com.fiap.hackathon.core.gateway.CartoesRepository;
import br.com.fiap.hackathon.core.input.AutorizarPagamentoInput;
import br.com.fiap.hackathon.core.output.Output;
import br.com.fiap.hackathon.core.usecases.cartao.BuscaCartaoUseCase;
import br.com.fiap.hackathon.core.usecases.pagamento.AutorizaPagamentoUseCase;
import br.com.fiap.hackathon.core.usecases.pagamento.ConsultaPagamentosUseCase;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;
import br.com.fiap.hackathon.core.vo.pagamento.AutorizacaoPagamentoVo;
import io.micrometer.common.util.StringUtils;

public abstract class PagamentoController {
    final BuscaCartaoUseCase buscaCartaoUseCase;
    final AutorizaPagamentoUseCase autorizarPagamentoUseCase;
    final ConsultaPagamentosUseCase consultaPagamentosUseCase;

    public PagamentoController(AutorizarPagamentosRepository repository, CartoesRepository cartoesRepository) {
        this.autorizarPagamentoUseCase = new AutorizaPagamentoUseCase(repository);
        this.buscaCartaoUseCase = new BuscaCartaoUseCase(cartoesRepository);
        this.consultaPagamentosUseCase = new ConsultaPagamentosUseCase(repository);
    }

    protected Output autorizar(AutorizarPagamentoInput input) throws BusinessException{
        if(validarInput(input)){
            throw new ArgumentoObrigatorioException("Valide os dados enviados para autorização");
        }

        try{
            final CartaoVo cartao = buscaCartaoUseCase.buscarCartaoClientePor(input.numero(), input.cpf());

            if(!cartao.getDataValidade().isEqual(input.getDataValidade()) || LocalDate.now().isAfter(cartao.getDataValidade())){
                throw new CartaoDataValidaInvalidaExcepetion();
            }
            
            if(!input.cvv().equals(cartao.getCvv())){
                throw new CartaoCVVInvalidoExcepetion();
            }

            final int VALOR_MAIOR_LIMITE = -1;

            final BigDecimal valorTotalComprasAprovadas = consultaPagamentosUseCase.consultarValorTotalDePagamentoPorClienteECartao(input.cpf(),input.numero());
            final BigDecimal valorTotalComprasAprovadasComCompraAtual = valorTotalComprasAprovadas.add(input.valor());

            if(cartao.getLimite().compareTo(valorTotalComprasAprovadasComCompraAtual) == VALOR_MAIOR_LIMITE){
                throw new CartaoSemLimiteExcepetion();
            }
            
            final AutorizacaoPagamentoVo vo = input.toVo(cartao);
            
            return autorizarPagamentoUseCase.aprovar(vo);
        } catch(BusinessException exception){
            autorizarPagamentoUseCase.recusar(input);
            
            throw exception;
        }
    }

    private boolean validarInput(AutorizarPagamentoInput input) {
        return 
        StringUtils.isBlank(input.cpf()) || 
        StringUtils.isBlank(input.cvv()) || 
        StringUtils.isBlank(input.data_validade()) || 
        StringUtils.isBlank(input.numero()) || 
        input.valor() == null;
    }

    protected List<Output> consultarPagamentosCliente(String cpf) throws BusinessException {
        return consultaPagamentosUseCase.consultarPagamentosPorCliente(cpf) ;
    }

}
