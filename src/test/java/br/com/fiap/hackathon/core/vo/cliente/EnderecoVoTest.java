package br.com.fiap.hackathon.core.vo.cliente;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;
import br.com.fiap.hackathon.core.exception.ArgumentoObrigatorioException;
import br.com.fiap.hackathon.core.exception.BusinessException;

class EnderecoVoTest {

    @Test
    void validarPropriedadeRuaNullOuVazio() {
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new EnderecoVo(null, null, null, null, null));
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new EnderecoVo("", null, null, null, null));
    }

    @Test
    void validarPropriedadeCidadeNullOuVazio() {
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new EnderecoVo("rua", null, null, null, null));
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new EnderecoVo("rua", "", null, null, null));
    }

    @Test
    void validarPropriedadeEstadoNullOuVazio() {
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new EnderecoVo("rua", "cidade", null, null, null));
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new EnderecoVo("rua", "cidade", "", null, null));
    }

    @Test
    void validarPropriedadeCepNullOuVazio() {
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new EnderecoVo("rua", "cidade", "estado", null, null));
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new EnderecoVo("rua", "cidade", "estado", "", null));
    }

    @Test
    void validarPropriedadePaisNullOuVazio() {
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new EnderecoVo("rua", "cidade", "estado", "cep", null));
       assertThrowsExactly(ArgumentoObrigatorioException.class, () -> new EnderecoVo("rua", "cidade", "estado", "cep", ""));
    }

    @Test
    void deveCriarEnderecoVo() throws BusinessException {
      final EnderecoVo vo =  new EnderecoVo("rua", "cidade", "estado", "cep", "pais");

      assertNotNull(vo);
    }
}
