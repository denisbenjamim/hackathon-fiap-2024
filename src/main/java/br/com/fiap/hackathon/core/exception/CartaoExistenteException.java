package br.com.fiap.hackathon.core.exception;

public class CartaoExistenteException extends BusinessException{

    public CartaoExistenteException() {
        super("Cartão já cadastrado");
    }
    
}
