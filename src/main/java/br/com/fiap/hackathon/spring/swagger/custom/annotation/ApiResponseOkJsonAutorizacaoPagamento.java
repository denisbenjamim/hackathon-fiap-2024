package br.com.fiap.hackathon.spring.swagger.custom.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.http.MediaType;

import br.com.fiap.hackathon.core.output.AutorizarPagamentoOutput;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })

@ApiResponse(
	responseCode = "200", 
	description = "Operação realizada com sucesso.",
    content = { 
		@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = AutorizarPagamentoOutput.class)
		)
})
public @interface ApiResponseOkJsonAutorizacaoPagamento  {}
