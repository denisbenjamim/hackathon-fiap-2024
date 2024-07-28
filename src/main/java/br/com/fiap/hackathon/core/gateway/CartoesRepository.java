package br.com.fiap.hackathon.core.gateway;

import java.util.List;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;

public interface CartoesRepository {
    public void gerar(CartaoVo cartao) throws BusinessException;
    public boolean buscarPorNumeroExistente(String numero);
    public List<CartaoVo> buscarTodosCartoesClientePorCPF(String cpf) throws BusinessException;
}
