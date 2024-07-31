package br.com.fiap.hackathon.spring.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.hackathon.spring.entity.AutorizarPagamentoAprovadoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AutorizarPagamentoAprovadoRepositoryJPA extends JpaRepository<AutorizarPagamentoAprovadoEntity, UUID>{
    List<AutorizarPagamentoAprovadoEntity> findAllByCartaoIdClienteCpf(String codigoCliente);

    @Query("SELECT COALESCE(SUM(p.valor),0) FROM AutorizarPagamentoAprovadoEntity p WHERE p.cartao.id.cliente.cpf = :cd_cliente and p.cartao.id.numero = :nr_cartao ")
    BigDecimal findTotalPagamentosByCpfAAndCartao(@Param("cd_cliente") String codigoCliente, @Param("nr_cartao") String nr_cartao);
}
