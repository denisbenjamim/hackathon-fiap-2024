package br.com.fiap.hackathon.core.usecases.pagamento;

import java.util.List;

import br.com.fiap.hackathon.core.gateway.AutorizarPagamentosRepository;
import br.com.fiap.hackathon.core.output.Output;

public class ConsultaPagamentosUseCase {
    
    final AutorizarPagamentosRepository repository;

    public ConsultaPagamentosUseCase(AutorizarPagamentosRepository repository) {
        this.repository = repository;
    }

    public List<Output> consultarPagamentosPorCliente(String cpf){
        return repository.consultarPagamentosPorClientes(cpf);
    }
}
