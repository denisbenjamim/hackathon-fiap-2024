package br.com.fiap.hackathon.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.hackathon.core.controller.CartaoController;
import br.com.fiap.hackathon.core.gateway.CartoesRepository;
import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.core.input.CartaoInput;
import br.com.fiap.hackathon.spring.swagger.custom.ApiResponse_204_400_500;
import br.com.fiap.hackathon.spring.utils.SpringControllerUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/cartao")
public class CartoesController extends CartaoController {
    
    public CartoesController(CartoesRepository repository, ClientesRepository clientesRepository) {
        super(repository, clientesRepository);
    }

    @PostMapping
    @ApiResponse_204_400_500
    public ResponseEntity<?> gerarCartao(@RequestBody CartaoInput request) {
        return SpringControllerUtils.response(HttpStatus.NO_CONTENT, () -> {
            gerar(request);
            return null;
        });
    }
    
}
