package br.com.fiap.hackathon.spring.swagger.custom.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.http.MediaType;

import br.com.fiap.hackathon.spring.utils.MessageErrorHandler;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })

@ApiResponse(
	responseCode = "403", 
	description = "Número máximo de cartões atingido",
    content = { 
		@Content(
			mediaType = MediaType.APPLICATION_JSON_VALUE,
			schema = @Schema(implementation = MessageErrorHandler.class)
		)
})
public @interface ApiResponseForbiddemJson {}
