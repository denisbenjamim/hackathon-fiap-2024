package br.com.fiap.hackathon.infra.adapter.repository;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.gateway.AutenticacaoRepository;
import br.com.fiap.hackathon.spring.entity.AutenticacaoEntity;
import br.com.fiap.hackathon.spring.repository.AutenticacaoRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AutenticacaoRepositoryImpl implements AutenticacaoRepository {

    private final AutenticacaoRepositoryJPA repositoryJPA;

    @Autowired
    public AutenticacaoRepositoryImpl(AutenticacaoRepositoryJPA repositoryJPA) {
        this.repositoryJPA = repositoryJPA;
    }

    @Override
    public AutenticacaoEntity buscarPorLogin(String login) throws BusinessException {
        return repositoryJPA.findByLogin(login)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado com login: " + login));
    }

    @Override
    public void salvar(AutenticacaoEntity autenticacaoEntity) {
        repositoryJPA.save(autenticacaoEntity);
    }
}
