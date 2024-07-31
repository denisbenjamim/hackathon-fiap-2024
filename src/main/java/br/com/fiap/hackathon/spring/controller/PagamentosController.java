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
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseOkJsonAutorizacaoPagamento;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseOkJsonConsultaPagamentosCliente;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponsePaymentRequiredJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseServerErrorJson;
import br.com.fiap.hackathon.spring.utils.SpringControllerUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/pagamentos")
public class PagamentosController extends PagamentoController {
    
    public PagamentosController(AutorizarPagamentosRepository repository, CartoesRepository cartoesRepository) {
        super(repository, cartoesRepository);
    }

    @PostMapping
    @ApiResponseOkJsonAutorizacaoPagamento
    @ApiResponsePaymentRequiredJson
    @ApiResponseBadRequestJson
    public ResponseEntity<?> autorizarPagamento(@RequestBody AutorizarPagamentoInput request ){
        return SpringControllerUtils.response(HttpStatus.OK, () -> autorizar(request));
    }

    @GetMapping("/cliente/{Chave}")
    @ApiResponseOkJsonConsultaPagamentosCliente
    @ApiResponseServerErrorJson
    public ResponseEntity<?> consultarPagamentos(@PathVariable("Chave") String cpf) {
        return SpringControllerUtils.response(HttpStatus.OK, () -> consultarPagamentosCliente(cpf));
    }
    
}
