package br.com.fiap.hackathon.spring.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.hackathon.spring.entity.AutorizarPagamentoAprovadoEntity;

public interface AutorizarPagamentoAprovadoRepositoryJPA extends JpaRepository<AutorizarPagamentoAprovadoEntity, UUID>{
    
}
