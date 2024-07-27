package br.com.fiap.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.core.usecases.cliente.RegistraClienteUseCase;
import br.com.fiap.hackathon.infra.adapter.repository.ClienteRepositoryImpl;
import br.com.fiap.hackathon.spring.repository.ClientesRepositoryJPA;

@SpringBootApplication
public class HackathonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);
	}

	@Bean
	RegistraClienteUseCase registrarClienteUseCase(ClientesRepository service){
		return new RegistraClienteUseCase(service);
	} 

	@Bean
	ClientesRepository clienteRepository(ClientesRepositoryJPA repository){
		return new ClienteRepositoryImpl(repository);
	}

}
