package br.com.fiap.hackathon.core.vo.pagamento;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StatusPagamento {
    @JsonProperty("validando") VALIDANDO, @JsonProperty("aprovado") APROVADO, @JsonProperty("recusado")RECUSADO;

}
