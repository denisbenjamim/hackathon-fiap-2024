package br.com.fiap.hackathon.spring.controller;

import br.com.fiap.hackathon.spring.dto.CredencialDTO;
import br.com.fiap.hackathon.spring.services.AutenticacoesService;
import br.com.fiap.hackathon.spring.swagger.custom.ApiResponse_200_401_500;
import br.com.fiap.hackathon.spring.utils.SpringControllerUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autenticacao")
@Tag(name = "Autenticação endpoint")
public class AutenticacoesController {

    @Autowired
    AutenticacoesService service;

    @PostMapping
    @ApiResponse_200_401_500
    @Operation(summary = "Autentica credencial e retorno token")
    public ResponseEntity<?> login(@RequestBody @Valid CredencialDTO data) {
       return SpringControllerUtils.response(HttpStatus.OK, ()-> service.login(data));
    }

}
