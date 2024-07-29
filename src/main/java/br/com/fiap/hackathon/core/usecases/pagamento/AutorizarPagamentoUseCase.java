package br.com.fiap.hackathon.core.usecases.pagamento;

import java.util.UUID;

import br.com.fiap.hackathon.core.gateway.AutorizarPagamentosRepository;
import br.com.fiap.hackathon.core.input.AutorizarPagamentoInput;
import br.com.fiap.hackathon.core.output.AutorizarPagamentoOutput;
import br.com.fiap.hackathon.core.output.Output;
import br.com.fiap.hackathon.core.vo.pagamento.AutorizarPagamentoAprovadoVo;

public class AutorizarPagamentoUseCase {
    
    final AutorizarPagamentosRepository repository;

    public AutorizarPagamentoUseCase(AutorizarPagamentosRepository repository) {
        this.repository = repository;
    }

    public Output aprovar(AutorizarPagamentoAprovadoVo pagamento){
        final UUID codigoAutorizacao = repository.autorizar(pagamento);
            
        return new AutorizarPagamentoOutput(codigoAutorizacao.toString());
    }

    public void recusar(AutorizarPagamentoInput pagamento){
        repository.operacaoRecusada(pagamento);
    }
}
