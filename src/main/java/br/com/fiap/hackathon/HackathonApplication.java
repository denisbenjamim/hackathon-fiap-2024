package br.com.fiap.hackathon;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.fiap.hackathon.core.gateway.AutorizarPagamentosRepository;
import br.com.fiap.hackathon.core.gateway.CartoesRepository;
import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.infra.Generated;
import br.com.fiap.hackathon.infra.adapter.repository.AutorizarPagamentoRepositoryImpl;
import br.com.fiap.hackathon.infra.adapter.repository.CartaoRepositoryImpl;
import br.com.fiap.hackathon.infra.adapter.repository.ClientesRepositoryImpl;
import br.com.fiap.hackathon.spring.repository.AutorizarPagamentoAprovadoRepositoryJPA;
import br.com.fiap.hackathon.spring.repository.AutorizarPagamentoRecusadoRepositoryJPA;
import br.com.fiap.hackathon.spring.repository.CartoesRepositoryJPA;
import br.com.fiap.hackathon.spring.repository.ClientesRepositoryJPA;

@SpringBootApplication
public class HackathonApplication {

	@Generated
	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);
	}

	@Bean
	ClientesRepository clienteRepository(ClientesRepositoryJPA repository){
		return new ClientesRepositoryImpl(repository);
	}

	@Bean
	CartoesRepository cartoesRepository(CartoesRepositoryJPA repository){
		return new CartaoRepositoryImpl(repository);
	}

	@Bean
	AutorizarPagamentosRepository autorizarPagamentosRepository(AutorizarPagamentoRecusadoRepositoryJPA recusadoRepositoryJPA, AutorizarPagamentoAprovadoRepositoryJPA aprovadoRepositoryJPA){
		return new AutorizarPagamentoRepositoryImpl(recusadoRepositoryJPA, aprovadoRepositoryJPA);
	}
}
