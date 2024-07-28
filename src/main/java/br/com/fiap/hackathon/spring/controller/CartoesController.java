package br.com.fiap.hackathon.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.hackathon.spring.dto.cartao.RequestCartaoDTO;
import br.com.fiap.hackathon.spring.service.CartaoService;
import br.com.fiap.hackathon.spring.swagger.custom.ApiResponse_204_400_500;
import br.com.fiap.hackathon.spring.utils.SpringControllerUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/cartao")
public class CartoesController {

    @Autowired
    CartaoService service;
    
    @PostMapping
    @ApiResponse_204_400_500
    public ResponseEntity<?> gerar(@RequestBody RequestCartaoDTO request) {
        return SpringControllerUtils.response(HttpStatus.NO_CONTENT, () -> {
            service.gerar(request);
            return null;
        });
    }
    
}
