package com.algaworks.algafood;

import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * Classe de testes de integração
 * Maven: mvnw verify
 * STS: 
 * @author Leonardo
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;

	@BeforeEach //Método de preparação para os testes. Executado antes de cada teste
	public void setUp() {
		//Habilita o log da requisição caso o teste falhe
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		flyway.migrate(); //Roda a migração antes de cada teste garantindo a massa de dados do afterMigrate.sql 
	}

	@Test
	public void deveRetornarStatus200_quandoConsultarCozinhas() {
		
		//Faz a requisição para testar
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
			
	}
	
	@Test
	public void deveConter4Cozinhas_quandoConsultarCozinhas() {
		
		//Faz a requisição para testar
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(4))//Testa o retorno de quatro objetos no array JSON
			.body("nome", Matchers.hasItems("Indiana", "Tailandesa"));//Testa o retorno dos nomes nos objetos no array JSON
		
			
	}
	
	@Test
	public void testRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
			.body("{ \"nome\": \"Chinesa\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
}
