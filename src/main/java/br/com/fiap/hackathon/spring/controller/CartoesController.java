package br.com.fiap.hackathon.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.hackathon.core.controller.CartaoController;
import br.com.fiap.hackathon.core.gateway.CartoesRepository;
import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.core.input.CartaoInput;
import br.com.fiap.hackathon.spring.swagger.custom.ApiResponse_200_401_403_500;
import br.com.fiap.hackathon.spring.utils.SpringControllerUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/cartao")
@Tag(name = "Cartão endpoint")
public class CartoesController extends CartaoController {
    
    public CartoesController(CartoesRepository repository, ClientesRepository clientesRepository) {
        super(repository, clientesRepository);
    }

    @PostMapping
    @ApiResponse_200_401_403_500
    @Operation(summary = "Registra cartão de um cliente")
    public ResponseEntity<?> gerarCartao(@RequestBody CartaoInput request) {
        return SpringControllerUtils.response(HttpStatus.OK, () -> {
            gerar(request);
            return null;
        });
    }
    
}
