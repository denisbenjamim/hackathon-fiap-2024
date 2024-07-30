package br.com.fiap.hackathon.core.gateway;

import java.util.List;
import java.util.UUID;

import br.com.fiap.hackathon.core.input.AutorizarPagamentoInput;
import br.com.fiap.hackathon.core.output.Output;
import br.com.fiap.hackathon.core.vo.pagamento.AutorizacaoPagamentoVo;

public interface AutorizarPagamentosRepository {
    
    UUID autorizar(AutorizacaoPagamentoVo autorizacao);
    UUID operacaoRecusada(AutorizarPagamentoInput autorizacao);
    List<Output> consultarPagamentosPorClientes(String cpf);
    
}
