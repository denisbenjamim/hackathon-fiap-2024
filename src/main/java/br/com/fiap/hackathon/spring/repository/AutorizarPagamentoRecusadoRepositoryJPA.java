package br.com.fiap.hackathon.spring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.hackathon.spring.entity.AutorizarPagamentoRecusadaEntity;


public interface AutorizarPagamentoRecusadoRepositoryJPA extends JpaRepository<AutorizarPagamentoRecusadaEntity, UUID> {
    List<AutorizarPagamentoRecusadaEntity> findAllByCodigoCliente(String codigoCliente);
}
