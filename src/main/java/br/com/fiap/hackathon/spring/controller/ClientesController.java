package br.com.fiap.hackathon.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.hackathon.core.controller.ClienteController;
import br.com.fiap.hackathon.core.gateway.ClientesRepository;
import br.com.fiap.hackathon.core.input.ClienteInput;
import br.com.fiap.hackathon.spring.swagger.custom.ApiResponseCliente_200_400_500;
import br.com.fiap.hackathon.spring.utils.SpringControllerUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cliente")
public class ClientesController extends ClienteController {

    public ClientesController(ClientesRepository repository) {
        super(repository);
    }

    @PostMapping
    @ApiResponseCliente_200_400_500
    public ResponseEntity<?> registrarCliente(@RequestBody ClienteInput request) {
        return SpringControllerUtils.response(HttpStatus.OK, () -> registrar(request));
    }
}
