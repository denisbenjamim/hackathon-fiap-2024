package br.com.fiap.hackathon.core.vo.cartao;

import java.math.BigDecimal;
import java.util.Objects;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;
import jakarta.persistence.Transient;

public class CartaoVo {
    final ClienteVo cliente;
    final BigDecimal limite;
    final String numero;
    final String dataValidade;
    final String cvv;

    public CartaoVo(ClienteVo cliente, BigDecimal limite, String numero, String dataValidade, String cvv) throws BusinessException {
        if(Objects.isNull(cliente)){
            throw new ArgumentoObrigatorioException("Cliente do cartão é obrigatório");
        }

        if(limite == null || limite.compareTo(BigDecimal.ZERO) < 1){
            throw new ArgumentoObrigatorioException("Limite do cartão é inválido");
        }

        if(numero == null || numero.isBlank()){
            throw new ArgumentoObrigatorioException("Número do cartão é obrigatório");
        }

        if(!numero.matches("\\d{4} \\d{4} \\d{4} \\d{4}")){
            throw new ArgumentoObrigatorioException("Formato número cartão é inválido");
        }

        if(dataValidade == null || dataValidade.isBlank() || !dataValidade.matches("\\d{2}\\/\\d{2}")){
            throw new ArgumentoObrigatorioException("Data inválida");
        }

        if(cvv == null || cvv.isBlank() || !cvv.matches("\\d{3,4}")){
            throw new ArgumentoObrigatorioException("CVV inválido");
        }

        this.cliente = cliente;
        this.limite = limite;
        this.numero = numero;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
    }

    public ClienteVo getCliente() {
        return cliente;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public String getNumero() {
        return numero;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public String getCvv() {
        return cvv;
    }

    @Transient
    public String getCodigoCliente(){
        return cliente.getCpf();
    }
}
