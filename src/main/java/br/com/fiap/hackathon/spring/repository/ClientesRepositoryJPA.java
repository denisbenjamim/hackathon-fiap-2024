package br.com.fiap.hackathon.spring.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.hackathon.spring.entity.ClienteEntity;


@Repository
public interface ClientesRepositoryJPA extends JpaRepository<ClienteEntity, UUID> {
    
    public ClienteEntity findByCpf(String cpf);
    public boolean existsByCpf(String cpf);
}
