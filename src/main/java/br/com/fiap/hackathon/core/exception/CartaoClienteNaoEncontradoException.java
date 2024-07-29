package br.com.fiap.hackathon.core.exception;

public class CartaoClienteNaoEncontradoException extends BusinessException{

    public CartaoClienteNaoEncontradoException() {
        super("Não foi encontrado cartão e cliente com os dados informados");
    }
    
}
