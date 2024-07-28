package br.com.fiap.hackathon.core.vo.cliente;

import java.util.Objects;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;

public class ClienteVo {
    final String cpf;
    final String nome;
    final String email;
    final String telefone;
    final EnderecoVo endereco;
    
    public ClienteVo(String cpf, String nome, String email, String telefone, EnderecoVo endereco) throws BusinessException {
        if(cpf == null || cpf.isBlank()){
            throw new ArgumentoObrigatorioException("CPF é obrigatório");
        }
        if(nome == null || nome.isBlank()){
            throw new ArgumentoObrigatorioException("Nome é obrigatório");
        }
        if(email == null || email.isBlank()){
            throw new ArgumentoObrigatorioException("Email é obrigatório");
        }
        if(telefone == null || telefone.isBlank()){
            throw new ArgumentoObrigatorioException("CPF é obrigatório");
        }

        if(Objects.isNull(endereco)){
            throw new ArgumentoObrigatorioException("Endereço é obrigatório");
        }
        
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public EnderecoVo getEndereco() {
        return endereco;
    }
}
