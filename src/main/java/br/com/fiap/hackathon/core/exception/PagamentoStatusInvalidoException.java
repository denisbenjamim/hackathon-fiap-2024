package br.com.fiap.hackathon.core.exception;

public class PagamentoStatusInvalidoException extends BusinessException{

    public PagamentoStatusInvalidoException() {
        super("Pagamento não pode ter seu Status alterado após Validado");
    }
    
}
