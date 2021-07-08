package com.algaworks.algafood;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

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
class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;

	@Test
	public void deveRetornarStatus200_quandoConsultarCozinhas() {
		//Habilita o log da requisição caso o teste falhe
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		//Faz a requisição para testar
		RestAssured.given()
			.basePath("/cozinhas")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
			
	}
	
	@Test
	public void deveConter4Cozinhas_quandoConsultarCozinhas() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		//Faz a requisição para testar
		RestAssured.given()
			.basePath("/cozinhas")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(4))//Testa o retorno de quatro objetos no array JSON
			.body("nome", Matchers.hasItems("Indiana", "Tailandesa"));//Testa o retorno dos nomes nos objetos no array JSON
		
			
	}
}
