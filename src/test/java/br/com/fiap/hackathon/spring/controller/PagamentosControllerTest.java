package br.com.fiap.hackathon.spring.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import br.com.fiap.hackathon.core.output.ConsultaAutorizacaoPagamentoOutput;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"default","test"})
public class PagamentosControllerTest {

    @LocalServerPort
    int porta;

    @BeforeEach
    void setUp(){
        RestAssured.port = porta;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    
    @Nested
    class ConsultarPagamentosPorCliente{
        @Test
        void deveConsultarAutorizacoesPagamentosCliente() {
            ConsultaAutorizacaoPagamentoOutput[] retorno = given()
                // .contentType(ContentType.JSON)
                .pathParam("Chave","92345678901" )
                .when()
                    .get("/api/pagamentos/cliente/{Chave}")
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(ConsultaAutorizacaoPagamentoOutput[].class);
            ;

            assertEquals(2, retorno.length);
        }

        @Test
        void naoDeveConsultarAutorizacoesPagamentosClienteInexistente() {
            ConsultaAutorizacaoPagamentoOutput[] retorno = given()
                // .contentType(ContentType.JSON)
                .pathParam("Chave","62345678901" )
                .when()
                    .get("/api/pagamentos/cliente/{Chave}")
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(ConsultaAutorizacaoPagamentoOutput[].class);
            ;

            assertEquals(0, retorno.length);
        }
    }

    @Nested
    class AutorizarPagamentos{
        @Test
        void deveAutorizarPagamento() {
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "numero": "1111 2222 3333 4441",
                        "data_validade": "12/24",
                        "cvv": "123",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_OK)
            ;
        }
    
        @Test
        void naoDeveAutorizarPagamentoCasoCpfClienteInvalido() {
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678909",
                        "numero": "1111 2222 3333 4441",
                        "data_validade": "12/24",
                        "cvv": "123",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Não foi encontrado cartão e cliente com os dados informados"))
            ;
        }
    
        @Test
        void naoDeveAutorizarPagamentoCasoNumeroCartaoInvalido() {
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "numero": "1111 2222 3333 4448",
                        "data_validade": "12/24",
                        "cvv": "123",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Não foi encontrado cartão e cliente com os dados informados"))
            ;
        }
    
        @Test
        void naoDeveAutorizarPagamentoCasoDataValidadeInvalida() {
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "numero": "1111 2222 3333 4441",
                        "data_validade": "13/24",
                        "cvv": "123",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Data inválida"))
            ;
        }
    
        @Test
        void naoDeveAutorizarPagamentoCasoDataDiferente() {
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "numero": "1111 2222 3333 4441",
                        "data_validade": "11/24",
                        "cvv": "123",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Data de validade informada é inválida"))
            ;
        }
    
        @Test
        void naoDeveAutorizarPagamentoCasoDataValidadeExpirada() {
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "numero": "1111 2222 3333 4442",
                        "data_validade": "11/23",
                        "cvv": "1234",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Data de validade informada é inválida"))
            ;
        }
    
        @Test
        void naoDeveAutorizarPagamentoCasoCVVInvalido() {
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "numero": "1111 2222 3333 4441",
                        "data_validade": "12/24",
                        "cvv": "12",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Código CVV informado é inválido"))
            ;
        }

        @Test
        void naoDeveAutorizarPagamentoCasoCpfNullOuVazio() {
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "",
                        "numero": "1111 2222 3333 4441",
                        "data_validade": "12/24",
                        "cvv": "123",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Valide os dados enviados para autorização"))
            ;

            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "numero": "1111 2222 3333 4441",
                        "data_validade": "12/24",
                        "cvv": "123",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Valide os dados enviados para autorização"))
            ;
        }

        @Test
        void naoDeveAutorizarPagamentoCasoNumeroCartaoNullOuVazio() {
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "numero": "",
                        "data_validade": "12/24",
                        "cvv": "123",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Valide os dados enviados para autorização"))
            ;

            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "data_validade": "12/24",
                        "cvv": "123",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Valide os dados enviados para autorização"))
            ;
        }

        @Test
        void naoDeveAutorizarPagamentoCasoDataValidadeNullOuVazio() {
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "numero": "1111 2222 3333 4441",
                        "data_validade": "",
                        "cvv": "123",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Valide os dados enviados para autorização"))
            ;

            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "numero": "1111 2222 3333 4441",
                        "cvv": "123",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Valide os dados enviados para autorização"))
            ;
        }

        @Test
        void naoDeveAutorizarPagamentoCasoCVVNullOuVazio() {
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "numero": "1111 2222 3333 4441",
                        "data_validade": "12/24",
                        "cvv": "",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Valide os dados enviados para autorização"))
            ;

            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "numero": "1111 2222 3333 4441",
                        "data_validade": "12/24",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Valide os dados enviados para autorização"))
            ;
        }

        @Test
        void naoDeveAutorizarPagamentoCasoValorNull() {
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "numero": "1111 2222 3333 4441",
                        "data_validade": "12/24",
                        "cvv": "123"
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Valide os dados enviados para autorização"))
            ;
        }

        @Test
        void naoDeveAutorizarPagamentoCasoNaoSejaInformadoNemDado() {
            given()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Valide os dados enviados para autorização"))
            ;
        }

        @Test
        void naoDeveAutorizarPagamentoCasoDataValidadeEstejaFormatoInvalido() {
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "cpf": "12345678901",
                        "numero": "1111 2222 3333 4441",
                        "data_validade": "1223",
                        "cvv": "123",
                        "valor": 500
                    } 
                """)
                .when()
                    .post("/api/pagamentos")
                .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body("message", is("Data inválida"))
            ;
        }
    }
    
}
