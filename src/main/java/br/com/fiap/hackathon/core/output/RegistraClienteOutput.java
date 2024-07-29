package br.com.fiap.hackathon.core.output;


public class RegistraClienteOutput implements Output {
    final String id_cliente;

    public RegistraClienteOutput(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_cliente() {
        return id_cliente;
    }
    
}
