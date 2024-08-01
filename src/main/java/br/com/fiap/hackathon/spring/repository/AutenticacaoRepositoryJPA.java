package br.com.fiap.hackathon.spring.repository;

import br.com.fiap.hackathon.spring.entity.AutenticacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutenticacaoRepositoryJPA extends JpaRepository<AutenticacaoEntity, Integer> {

    Optional<AutenticacaoEntity> findByLogin(String login);
}
