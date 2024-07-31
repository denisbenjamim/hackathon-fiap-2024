package br.com.fiap.hackathon.core.exception;

public class CartaoDataValidaInvalidaExcepetion extends BusinessException{

    public CartaoDataValidaInvalidaExcepetion() {
        super("Data de validade informada é inválida");
    }
    
}
