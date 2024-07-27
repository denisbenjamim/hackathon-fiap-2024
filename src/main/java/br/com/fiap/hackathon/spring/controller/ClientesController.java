package br.com.fiap.hackathon.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.hackathon.spring.dto.cliente.RequestClienteDTO;
import br.com.fiap.hackathon.spring.service.ClienteService;
import br.com.fiap.hackathon.spring.swagger.custom.ApiResponseCliente_201_400_500;
import br.com.fiap.hackathon.spring.utils.SpringControllerUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/cliente")
public class ClientesController {

    @Autowired
    ClienteService service;

    @PostMapping
    @ApiResponseCliente_201_400_500
    public ResponseEntity<?> registrarCliente(@RequestBody RequestClienteDTO request) {
        return SpringControllerUtils.response(HttpStatus.CREATED, () -> service.registrar(request));
    }
    
}
