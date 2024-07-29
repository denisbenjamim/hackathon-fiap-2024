package br.com.fiap.hackathon.core.gateway;

import java.util.UUID;

import br.com.fiap.hackathon.core.input.AutorizarPagamentoInput;
import br.com.fiap.hackathon.core.vo.pagamento.AutorizarPagamentoAprovadoVo;

public interface AutorizarPagamentosRepository {
    
    UUID autorizar(AutorizarPagamentoAprovadoVo autorizacao);
    UUID operacaoRecusada(AutorizarPagamentoInput autorizacao);
}
