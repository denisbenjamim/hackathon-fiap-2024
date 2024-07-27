package br.com.fiap.hackathon.spring.swagger.custom;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseServerErrorJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseBadRequestJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseCreateJsonCliente;


@Retention(RUNTIME)
@Target({ METHOD })
@ApiResponseCreateJsonCliente
@ApiResponseBadRequestJson
@ApiResponseServerErrorJson
public @interface ApiResponseCliente_201_400_500 {}
