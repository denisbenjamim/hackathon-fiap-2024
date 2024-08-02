package br.com.fiap.hackathon.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.hackathon.core.controller.AutorizacaoPagamentoController;
import br.com.fiap.hackathon.core.gateway.AutorizarPagamentosRepository;
import br.com.fiap.hackathon.core.gateway.CartoesRepository;
import br.com.fiap.hackathon.core.input.AutorizarPagamentoInput;
import br.com.fiap.hackathon.spring.swagger.custom.ApiResponseAutorizaoPagamento_200_401_402_500;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseOkJsonConsultaPagamentosCliente;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseServerErrorJson;
import br.com.fiap.hackathon.spring.swagger.custom.annotation.ApiResponseUnauthorized;
import br.com.fiap.hackathon.spring.utils.SpringControllerUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/pagamentos")
@Tag(name = "Autorização Pagamentos endpoint")
public class AutorizacaoPagamentosController extends AutorizacaoPagamentoController {
    
    public AutorizacaoPagamentosController(AutorizarPagamentosRepository repository, CartoesRepository cartoesRepository) {
        super(repository, cartoesRepository);
    }

    @PostMapping
    @ApiResponseAutorizaoPagamento_200_401_402_500
    @Operation(summary = "Autoriza pagamento realizado por um cliente utilizando um cartão")
    public ResponseEntity<?> autorizarPagamento(@RequestBody AutorizarPagamentoInput request ){
        return SpringControllerUtils.response(HttpStatus.OK, () -> autorizar(request));
    }

    @GetMapping("/cliente/{Chave}")
    @ApiResponseOkJsonConsultaPagamentosCliente
    @ApiResponseUnauthorized
    @ApiResponseServerErrorJson
    @Operation(summary = "Lista com todos pagamentos de um cliente aprovados e recusados")
    public ResponseEntity<?> consultarPagamentos(@PathVariable("Chave") String cpf) {
        return SpringControllerUtils.response(HttpStatus.OK, () -> consultarPagamentosCliente(cpf));
    }
    
}
