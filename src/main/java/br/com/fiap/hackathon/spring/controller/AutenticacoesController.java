package br.com.fiap.hackathon.spring.controller;

import br.com.fiap.hackathon.spring.dto.CredencialDTO;
import br.com.fiap.hackathon.spring.services.AutenticacoesService;
import br.com.fiap.hackathon.spring.swagger.custom.ApiResponse_200_401_500;
import br.com.fiap.hackathon.spring.utils.SpringControllerUtils;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autenticacao")
public class AutenticacoesController {

    @Autowired
    AutenticacoesService service;

    @PostMapping
    @ApiResponse_200_401_500
    public ResponseEntity<?> login(@RequestBody @Valid CredencialDTO data) {
       return SpringControllerUtils.response(HttpStatus.OK, ()-> service.login(data));
    }

}
