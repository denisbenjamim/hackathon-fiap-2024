package br.com.fiap.hackathon.core.vo.cliente;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;

public class EnderecoVo {
    final String rua;
    final String cidade;
    final String estado;
    final String cep;
    final String pais;
    
    public EnderecoVo(String rua, String cidade, String estado, String cep, String pais) throws BusinessException {
        if(rua == null || rua.isBlank()){
            throw new ArgumentoObrigatorioException("Rua é obrigatório");
        }

        if(cidade == null || cidade.isBlank()){
            throw new ArgumentoObrigatorioException("Cidade é obrigatório");
        }

        if(estado == null || estado.isBlank()){
            throw new ArgumentoObrigatorioException("Estado é obrigatório");
        }

        if(cep == null || cep.isBlank()){
            throw new ArgumentoObrigatorioException("CEP é obrigatório");
        }

        if(pais == null || pais.isBlank()){
            throw new ArgumentoObrigatorioException("Pais é obrigatório");
        }

        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.pais = pais;
    }
    
    public String getRua() {
        return rua;
    }
    public String getCidade() {
        return cidade;
    }
    public String getEstado() {
        return estado;
    }
    public String getCep() {
        return cep;
    }
    public String getPais() {
        return pais;
    }
}