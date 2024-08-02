package br.com.fiap.hackathon.spring.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"default","test"})
public class CartoesControllerTest  extends AbstractControllerTest{

    @LocalServerPort
    int porta;

    @BeforeEach
    void setUp(){
        RestAssured.port = porta;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deveGerarUmCartaoParaCliente() {
        given()
            .header(getAuthorization())
            .contentType(ContentType.JSON)
            .body("""
                {
                    "cpf":"12345678900",
                    "limite":1000,
                    "numero":"1234 1234 1234 1234",
                    "data_validade":"12/24",
                    "cvv":"123"
                }  
            """)
            .when()
                .post("/api/cartao")
            .then()
                .statusCode(HttpStatus.SC_OK)
        ;
    }

    @Test
    void naoDeveGerarUmCartaoParaClienteCasoJaTenhaAtingidoLimiteDeCartoes() {
        given()
            .header(getAuthorization())
            .contentType(ContentType.JSON)
            .body("""
                {
                    "cpf":"12345678901",
                    "limite":1000,
                    "numero":"1111 2222 3333 4443",
                    "data_validade":"12/24",
                    "cvv":"123"
                }  
            """)
            .when()
                .post("/api/cartao")
            .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body("message", is("Cliente já possui a quantidade maxima permitida de cartões"))
        ;

    }

    @Test
    void naoDeveGerarUmCartaoParaClienteCasoCartaoRepetido() {
        given()
            .header(getAuthorization())
            .contentType(ContentType.JSON)
            .body("""
                {
                    "cpf":"12345678902",
                    "limite":1000,
                    "numero":"1111 2222 3333 4440",
                    "data_validade":"12/24",
                    "cvv":"123"
                }  
            """)
            .when()
                .post("/api/cartao")
            .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body("message", is("Cartão já cadastrado"))
        ;

    }

    @Test
    void naoDeveGerarUmCartaoCasoClienteNaoRegistrado() {
        given()
            .header(getAuthorization())
            .contentType(ContentType.JSON)
            .body("""
                {
                    "cpf":"99999999999",
                    "limite":1000,
                    "numero":"1111 2222 3333 8888",
                    "data_validade":"12/24",
                    "cvv":"123"
                }  
            """)
            .when()
                .post("/api/cartao")
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("message", is("O CPF '99999999999' não foi encontrado"))
        ;

    }

}
