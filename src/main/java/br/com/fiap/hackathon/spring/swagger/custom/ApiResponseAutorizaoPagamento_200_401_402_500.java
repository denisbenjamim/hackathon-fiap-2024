package br.com.fiap.hackathon.spring.swagger.custom;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseServerErrorJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseOkJsonAutorizacaoPagamento;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponsePaymentRequiredJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseUnauthorized;

@Retention(RUNTIME)
@Target({ METHOD })
@ApiResponseOkJsonAutorizacaoPagamento
@ApiResponseUnauthorized
@ApiResponsePaymentRequiredJson
@ApiResponseServerErrorJson
public @interface ApiResponseAutorizaoPagamento_200_401_402_500 {}
