package br.com.fiap.hackathon.core.exception;

public class QuantidadeMaximaCartoesClienteException extends BusinessException{

    public QuantidadeMaximaCartoesClienteException() {
        super("Cliente já possui a quantidade maxima permitida de cartões");
    }
    
}
