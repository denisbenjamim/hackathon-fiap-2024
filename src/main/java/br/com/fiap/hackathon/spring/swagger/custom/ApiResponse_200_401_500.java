package br.com.fiap.hackathon.spring.swagger.custom;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseServerErrorJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseOkJsonToken;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseUnauthorized;

@Retention(RUNTIME)
@Target({ METHOD })
@ApiResponseOkJsonToken
@ApiResponseUnauthorized
@ApiResponseServerErrorJson
public @interface ApiResponse_200_401_500 {}
