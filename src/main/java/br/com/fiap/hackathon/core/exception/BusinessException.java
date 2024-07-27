package br.com.fiap.hackathon.core.exception;

public class BusinessException extends Exception {
    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, Throwable throwable){
        super(message, throwable);
    }
}
