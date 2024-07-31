package br.com.fiap.hackathon.spring.swagger.custom.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
@ApiResponse(
	responseCode = "200", 
	description = "Operação realizada com sucesso."
   )
public @interface ApiResponseOkJson  {}
