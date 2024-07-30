package br.com.fiap.hackathon.spring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.hackathon.spring.entity.AutorizarPagamentoAprovadoEntity;

public interface AutorizarPagamentoAprovadoRepositoryJPA extends JpaRepository<AutorizarPagamentoAprovadoEntity, UUID>{
    List<AutorizarPagamentoAprovadoEntity> findAllByCartaoIdClienteCpf(String codigoCliente);
}
