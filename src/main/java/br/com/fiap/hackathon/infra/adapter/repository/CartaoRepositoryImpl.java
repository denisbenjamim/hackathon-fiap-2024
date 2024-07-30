package br.com.fiap.hackathon.infra.adapter.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.gateway.CartoesRepository;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;
import br.com.fiap.hackathon.spring.entity.CartaoEntity;
import br.com.fiap.hackathon.spring.repository.CartoesRepositoryJPA;

public class CartaoRepositoryImpl implements CartoesRepository {
    final CartoesRepositoryJPA repository;

    public CartaoRepositoryImpl(CartoesRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public void gerar(CartaoVo cartao) throws BusinessException {
        final CartaoEntity entity = new CartaoEntity(cartao);
        repository.save(entity);
    }

    @Override
    public boolean buscarPorNumeroExistente(String numero) {
       return repository.existsByIdNumero(numero);
    }

    @Override
    public List<CartaoVo> buscarTodosCartoesClientePorCPF(String cpf) throws BusinessException {
        final List<CartaoEntity> cartoes = repository.findAllByIdClienteCpf(cpf);
        final List<CartaoVo> cartoesList = new ArrayList<>();
        
        for(CartaoEntity  cartao: cartoes) {
            cartoesList.add(cartao.toVo());
        }

        return cartoesList;
    }

    @Override
    public CartaoVo buscarCartaoClientePor(String numero, String cpf) throws BusinessException {
        final CartaoEntity entity = repository.findByIdNumeroAndIdClienteCpf(numero, cpf);

        if(entity == null){
           return null;
        }

        return entity.toVo();
    }
    
}
