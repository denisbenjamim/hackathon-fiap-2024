package br.com.fiap.hackathon.core.exception;

public class CartaoCVVInvalidoExcepetion extends BusinessException{

    public CartaoCVVInvalidoExcepetion() {
        super("Código CVV informado é inválido");
    }
    
}
