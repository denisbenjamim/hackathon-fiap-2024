package br.com.fiap.hackathon.spring.utils;

import br.com.fiap.hackathon.core.exception.BusinessException;

@FunctionalInterface
public interface GerarResponse<T> {

	T get() throws BusinessException;
}
