package br.com.fiap.hackathon.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.hackathon.spring.entity.CartaoEntity;
import br.com.fiap.hackathon.spring.entity.ClienteCartaoEmbeddable;

@Repository
public interface CartoesRepositoryJPA extends JpaRepository<CartaoEntity, ClienteCartaoEmbeddable>{
    
    public boolean existsByIdNumero(String numero);
    public List<CartaoEntity> findAllByIdClienteCpf(String cpf);
}
