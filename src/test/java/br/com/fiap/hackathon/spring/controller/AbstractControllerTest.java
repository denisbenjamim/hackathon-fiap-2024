package br.com.fiap.hackathon.spring.controller;

import static io.restassured.RestAssured.given;

import br.com.fiap.hackathon.spring.dto.TokenDTO;
import io.restassured.http.ContentType;
import io.restassured.http.Header;

public abstract class AbstractControllerTest {
    private String token;

    private String gerarToken() {
        final TokenDTO as = given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "usuario": "adj2",
                        "senha": "adj@1234"
                    }
                """)
                .when()
                .post("/api/autenticacao")
                .then()
                .extract().as(TokenDTO.class);
                
        return "Bearer " + as.token();

    }

    public Header getAuthorization(){
        if(token == null){
            token = gerarToken();
        }

        return new Header("Authorization", token);
    }
}
