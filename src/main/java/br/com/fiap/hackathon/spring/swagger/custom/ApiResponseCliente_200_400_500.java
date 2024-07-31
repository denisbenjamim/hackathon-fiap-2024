package br.com.fiap.hackathon.spring.swagger.custom;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseServerErrorJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseBadRequestJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseOkJsonCliente;


@Retention(RUNTIME)
@Target({ METHOD })
@ApiResponseOkJsonCliente
@ApiResponseBadRequestJson
@ApiResponseServerErrorJson
public @interface ApiResponseCliente_200_400_500 {}
