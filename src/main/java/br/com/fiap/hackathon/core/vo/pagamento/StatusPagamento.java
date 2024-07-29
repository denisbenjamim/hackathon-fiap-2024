package br.com.fiap.hackathon.core.vo.pagamento;

public enum StatusPagamento {
    APROVADO, RECUSADO;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
