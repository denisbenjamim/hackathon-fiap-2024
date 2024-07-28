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
        validarCamposObrigatorios(cpf, nome, email, telefone, endereco);
        
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    private void validarCamposObrigatorios(String cpf, String nome, String email, String telefone, EnderecoVo endereco) throws ArgumentoObrigatorioException {
        if(cpf == null || cpf.isBlank()){
            throw new ArgumentoObrigatorioException("CPF é obrigatório");
        }

        if(!cpf.matches("^\\d{11}$")){
            throw new ArgumentoObrigatorioException("CPF é deve ter exatamente 11 digitos númericos");
        }

        if(nome == null || nome.isBlank()){
            throw new ArgumentoObrigatorioException("Nome é obrigatório");
        }

        if(email == null || email.isBlank()){
            throw new ArgumentoObrigatorioException("Email é obrigatório");
        }

        if(telefone == null || telefone.isBlank()){
            throw new ArgumentoObrigatorioException("Telefone é obrigatório");
        }
        
        if(!telefone.matches("^\\+\\d{2}\\s\\d{2}\\s\\d{4,5}-\\d{4}$")){
            throw new ArgumentoObrigatorioException("Telefone em formato inválido, esperado '+55 13 12345-1234'");
        }

        if(Objects.isNull(endereco)){
            throw new ArgumentoObrigatorioException("Endereço é obrigatório");
        }
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
