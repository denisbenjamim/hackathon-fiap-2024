package br.com.fiap.hackathon.core.gateway;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.spring.entity.AutenticacaoEntity;

public interface AutenticacaoRepository {

    AutenticacaoEntity buscarPorLogin(String login) throws BusinessException;

    void salvar(AutenticacaoEntity autenticacaoEntity) throws BusinessException;

}
