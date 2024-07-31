package br.com.fiap.hackathon.core.exception;

public class BusinessException extends Exception {
    int statusCode = 500;

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(int statusCode, String message){
        super(message);
        this.statusCode =  statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
