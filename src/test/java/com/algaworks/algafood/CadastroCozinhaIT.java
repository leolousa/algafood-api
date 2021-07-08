package com.algaworks.algafood;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DataBaseCleaner;

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
	private DataBaseCleaner dataBaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@BeforeEach //Método de preparação para os testes. Executado antes de cada teste
	public void setUp() {
		//Habilita o log da requisição caso o teste falhe
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		dataBaseCleaner.clearTables();
		preparaDados();
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
	
	private void preparaDados() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Americana");
		cozinhaRepository.save(cozinha2);
		
		Cozinha cozinha3 = new Cozinha();
		cozinha3.setNome("Indiana");
		cozinhaRepository.save(cozinha3);
		
		Cozinha cozinha4 = new Cozinha();
		cozinha4.setNome("Brasileira");
		cozinhaRepository.save(cozinha4);
	}
}
