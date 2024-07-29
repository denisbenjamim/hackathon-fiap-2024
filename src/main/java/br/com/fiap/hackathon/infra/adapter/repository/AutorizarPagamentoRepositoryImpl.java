package br.com.fiap.hackathon.infra.adapter.repository;

import java.util.UUID;

import br.com.fiap.hackathon.core.gateway.AutorizarPagamentosRepository;
import br.com.fiap.hackathon.core.input.AutorizarPagamentoInput;
import br.com.fiap.hackathon.core.vo.pagamento.AutorizarPagamentoAprovadoVo;
import br.com.fiap.hackathon.spring.entity.AutorizarPagamentoAprovadoEntity;
import br.com.fiap.hackathon.spring.entity.AutorizarPagamentoRecusadaEntity;
import br.com.fiap.hackathon.spring.repository.AutorizarPagamentoAprovadoRepositoryJPA;
import br.com.fiap.hackathon.spring.repository.AutorizarPagamentoRecusadoRepositoryJPA;

public class AutorizarPagamentoRepositoryImpl implements AutorizarPagamentosRepository {

    final AutorizarPagamentoRecusadoRepositoryJPA recusadoRepositoryJPA;
    final AutorizarPagamentoAprovadoRepositoryJPA aprovadoRepositoryJPA;

    public AutorizarPagamentoRepositoryImpl(AutorizarPagamentoRecusadoRepositoryJPA recusadoRepositoryJPA, AutorizarPagamentoAprovadoRepositoryJPA aprovadoRepositoryJPA) {
        this.recusadoRepositoryJPA = recusadoRepositoryJPA;
        this.aprovadoRepositoryJPA = aprovadoRepositoryJPA;
    }

    @Override
    public UUID autorizar(AutorizarPagamentoAprovadoVo pagamento) {
       final AutorizarPagamentoAprovadoEntity entity = new AutorizarPagamentoAprovadoEntity(pagamento);

       return aprovadoRepositoryJPA.save(entity).getChave();
    }

    @Override
    public UUID operacaoRecusada(AutorizarPagamentoInput autorizacao) {
        final AutorizarPagamentoRecusadaEntity entity = new AutorizarPagamentoRecusadaEntity(autorizacao);

        return recusadoRepositoryJPA.save(entity).getChave();
    }

}
