package br.com.fiap.hackathon.core.input;

public record ClienteInput (
    String cpf,
    String nome,
    String email,
    String telefone,
    String rua,
    String cidade,
    String estado,
    String cep,
    String pais
) {}
