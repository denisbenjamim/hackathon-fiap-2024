package br.com.fiap.hackathon.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.hackathon.core.controller.PagamentoController;
import br.com.fiap.hackathon.core.gateway.AutorizarPagamentosRepository;
import br.com.fiap.hackathon.core.gateway.CartoesRepository;
import br.com.fiap.hackathon.core.input.AutorizarPagamentoInput;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseBadRequestJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseOkJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponsePaymentRequiredJson;
import br.com.fiap.hackathon.spring.utils.SpringControllerUtils;
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/pagamentos")
public class PagamentosController extends PagamentoController {
    
    public PagamentosController(AutorizarPagamentosRepository repository, CartoesRepository cartoesRepository) {
        super(repository, cartoesRepository);
    }

    @PostMapping
    @ApiResponseOkJson
    @ApiResponsePaymentRequiredJson
    @ApiResponseBadRequestJson
    public ResponseEntity<?> autorizarPagamento(@RequestBody AutorizarPagamentoInput request ){
        return SpringControllerUtils.response(HttpStatus.CREATED, () -> autorizar(request));
    }

    @GetMapping("/cliente/{Chave}")
    public ResponseEntity<?> consultarPagamentos(@PathParam("Chave") String cpf) {
        return SpringControllerUtils.response(HttpStatus.CREATED, () -> consultarPagamentosCliente(cpf));
    }
    
}
