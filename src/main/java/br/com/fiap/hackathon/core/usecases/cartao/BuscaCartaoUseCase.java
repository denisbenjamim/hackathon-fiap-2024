package br.com.fiap.hackathon.core.usecases.cartao;

import br.com.fiap.hackathon.core.gateway.CartoesRepository;

public class BuscaCartaoUseCase {
   final CartoesRepository repository;

    public BuscaCartaoUseCase(CartoesRepository repository) {
        this.repository = repository;
    }

    public boolean buscarPorNumeroExistente(String numero){
        return repository.buscarPorNumeroExistente(numero);
    }
   
}
