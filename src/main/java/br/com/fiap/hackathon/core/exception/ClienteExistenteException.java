package br.com.fiap.hackathon.core.exception;

public class ClienteExistenteException extends BusinessException{

    public ClienteExistenteException() {
        super("Cliente já cadastrado");
    }
    
}
