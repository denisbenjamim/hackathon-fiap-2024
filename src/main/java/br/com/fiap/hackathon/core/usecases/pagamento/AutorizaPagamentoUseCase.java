package br.com.fiap.hackathon.core.usecases.pagamento;

import java.util.UUID;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.gateway.AutorizarPagamentosRepository;
import br.com.fiap.hackathon.core.input.AutorizarPagamentoInput;
import br.com.fiap.hackathon.core.output.AutorizarPagamentoOutput;
import br.com.fiap.hackathon.core.output.Output;
import br.com.fiap.hackathon.core.vo.pagamento.AutorizacaoPagamentoVo;

public class AutorizaPagamentoUseCase {
    
    final AutorizarPagamentosRepository repository;

    public AutorizaPagamentoUseCase(AutorizarPagamentosRepository repository) {
        this.repository = repository;
    }

    public Output aprovar(AutorizacaoPagamentoVo pagamento) throws BusinessException{
        pagamento.mudarStatusPagamentoParaAprovado();
        
        final UUID codigoAutorizacao = repository.autorizar(pagamento);
            
        return new AutorizarPagamentoOutput(codigoAutorizacao.toString());
    }

    public void recusar(AutorizarPagamentoInput pagamento){
        repository.operacaoRecusada(pagamento);
    }
}
