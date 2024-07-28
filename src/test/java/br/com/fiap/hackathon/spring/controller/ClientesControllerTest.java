package br.com.fiap.hackathon.spring.controller;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ClientesControllerTest {

    @LocalServerPort
    int porta;

    @BeforeEach
    void setUp(){
        RestAssured.port = porta;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deveRegistrarCliente() {
        given()
            .contentType(ContentType.JSON)
            .body("""
                {
                    "cpf":"1111111111",
                    "nome":"João da Silva",
                    "email":"joao@example.com",
                    "telefone":"+55 11 91234-5678",
                    "rua":"Rua A",
                    "cidade":"Cidade",
                    "estado":"Estado",
                    "cep":"12345-678",
                    "pais":"Brasil"
                }  
            """)
            .when()
                .post("/api/cliente")
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id_cliente",is("1111111111"))
        ;
    }

    @Test
    void naoDeveRegistrarClienteRepetido() {
        given()
            .contentType(ContentType.JSON)
            .body("""
                {
                    "cpf":"1111111112",
                    "nome":"João da Silva",
                    "email":"joao@example.com",
                    "telefone":"+55 11 91234-5678",
                    "rua":"Rua A",
                    "cidade":"Cidade",
                    "estado":"Estado",
                    "cep":"12345-678",
                    "pais":"Brasil"
                }  
            """)
            .when()
                .post("/api/cliente")
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("id_cliente",is("1111111112"))
        ;

        given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "cpf":"1111111112",
                "nome":"João da Silva",
                "email":"joao@example.com",
                "telefone":"+55 11 91234-5678",
                "rua":"Rua A",
                "cidade":"Cidade",
                "estado":"Estado",
                "cep":"12345-678",
                "pais":"Brasil"
            }  
        """)
        .when()
            .post("/api/cliente")
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .body("message",is("Cliente já cadastrado"))
    ;
    }
}
