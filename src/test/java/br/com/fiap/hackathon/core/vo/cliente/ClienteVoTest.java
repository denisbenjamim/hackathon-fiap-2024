package br.com.fiap.hackathon.core.vo.cliente;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;

class ClienteVoTest {
    
    @Test
    void validarPropriedadeCpfNullOuVazio() {
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo(null, null, null, null, null));
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("", null, null, null, null));
    }

    @Test
    void validarPropriedadeCpfPossui11DigitosNumericos() {
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("1111111111", null, null, null, null));
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("11111111111", null, null, null, null));
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("AAAAAAAAAAA", null, null, null, null));
    }

    @Test
    void validarPropriedadeNomeNullOuVazio() {
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("11111111111", null, null, null, null));
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("11111111111", "", null, null, null));
    }

    @Test
    void validarPropriedadeEmailNullOuVazio() {
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("11111111111", "nome", null, null, null));
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("11111111111", "nome", "", null, null));
    }

    @Test
    void validarPropriedadeTelefoneNullOuVazio() {
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("11111111111", "nome", "email@email.com", null, null));
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("11111111111", "nome", "email@email.com", "", null));
    }

    @Test
    void validarPropriedadeTelefoneFormatoEsperado() {
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("11111111111", "nome", "email@email.com", "+551112341234", null));
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("11111111111", "nome", "email@email.com", "+55 11 1234 1234", null));
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("11111111111", "nome", "email@email.com", "55 11 1234-1234", null));
    }

    @Test
    void validarPropriedadeEnderecoNull() {
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new ClienteVo("11111111111", "nome", "email@email.com", "+55 11 1234-1234", null));
    }

    @Test
    void deveCriarClienteVo() throws BusinessException {
        final EnderecoVo enderecoVo =  new EnderecoVo("rua", "cidade", "estado", "cep", "pais");
        final ClienteVo clienteVo = new ClienteVo("11111111111", "nome", "email@email.com", "+55 11 1234-1234", enderecoVo);

        assertNotNull(clienteVo);
    }
}
