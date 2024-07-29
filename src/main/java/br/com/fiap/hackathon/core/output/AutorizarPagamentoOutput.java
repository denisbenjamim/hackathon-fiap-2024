package br.com.fiap.hackathon.core.output;

public class AutorizarPagamentoOutput implements Output{
    final String chave_pagamento;

    public AutorizarPagamentoOutput(String chave_pagamento) {
        this.chave_pagamento = chave_pagamento;
    }

    public String getChave_pagamento() {
        return chave_pagamento;
    }
}
