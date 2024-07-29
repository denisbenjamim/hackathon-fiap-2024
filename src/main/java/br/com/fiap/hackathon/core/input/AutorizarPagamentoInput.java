package br.com.fiap.hackathon.core.input;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.vo.cartao.CartaoVo;
import br.com.fiap.hackathon.core.vo.pagamento.AutorizarPagamentoAprovadoVo;

public record AutorizarPagamentoInput(
    String cpf,
    String numero,
    String data_validade,
    String cvv,
    BigDecimal valor
) {
    public LocalDate getDataValidade() throws BusinessException {
        try {
            if(data_validade == null || data_validade.isBlank() || !data_validade.matches("\\d{2}\\/\\d{2}")){
                throw new ArgumentoObrigatorioException("Data inválida");
            }

            return YearMonth.parse(data_validade, DateTimeFormatter.ofPattern("MM/yy")).atEndOfMonth();
        } catch (java.time.format.DateTimeParseException e) {
            throw new ArgumentoObrigatorioException("Data inválida");
        }
    }

    public AutorizarPagamentoAprovadoVo toVo(CartaoVo cartao){
        return new AutorizarPagamentoAprovadoVo(valor, cartao);
    }
}
