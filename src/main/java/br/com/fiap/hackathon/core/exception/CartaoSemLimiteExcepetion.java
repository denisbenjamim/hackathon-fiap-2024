package br.com.fiap.hackathon.core.exception;

public class CartaoSemLimiteExcepetion extends BusinessException{

    public CartaoSemLimiteExcepetion() {
        super(402,"Cartão sem limite disponivel");
    }
    
}
