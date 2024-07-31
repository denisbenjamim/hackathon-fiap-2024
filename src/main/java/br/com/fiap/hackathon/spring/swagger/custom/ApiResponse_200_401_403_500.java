package br.com.fiap.hackathon.spring.swagger.custom;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseServerErrorJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseBadRequestJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseForbiddemJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseOkJson;


@Retention(RUNTIME)
@Target({ METHOD })
@ApiResponseOkJson
@ApiResponseForbiddemJson
@ApiResponseBadRequestJson
@ApiResponseServerErrorJson
public @interface ApiResponse_200_401_403_500 {}
