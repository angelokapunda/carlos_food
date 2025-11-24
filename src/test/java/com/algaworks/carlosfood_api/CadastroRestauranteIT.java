package com.algaworks.carlosfood_api;


import com.algaworks.carlosfood_api.domain.model.Cozinha;
import com.algaworks.carlosfood_api.domain.model.Restaurante;
import com.algaworks.carlosfood_api.domain.repository.CozinhaRepository;
import com.algaworks.carlosfood_api.domain.repository.RestauranteRepository;
import com.algaworks.carlosfood_api.util.DatabaseCleaner;
import com.algaworks.carlosfood_api.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroRestauranteIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    private String jsonCorretoRestauranteMatuto;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        jsonCorretoRestauranteMatuto = ResourceUtils.getContentFromResource(
                "/json/correto/restaurante-matuto.json"
        );
        databaseCleaner.clearTables();
        preparaDados();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarRestauranteExistente() {

        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());

    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarRestauranteComSucesso() {
        given()
            .body(jsonCorretoRestauranteMatuto)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    public void preparaDados() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("America");
        cozinhaRepository.save(cozinha);

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Angolana");
        cozinhaRepository.save(cozinha2);
    }

}
