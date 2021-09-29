package com.algaworks.algafood;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.algaworks.algafood.core.io.Base64ProtocolResolver;
import com.algaworks.algafood.infraestructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		//Definir o padrão de timezone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		var app = new SpringApplication(AlgafoodApiApplication.class);
		
		//Implementa o protocolo de Base64 criado para utilizar a chave privada fora de arquivo, como uma string
		app.addListeners(new Base64ProtocolResolver());

		app.run(args);
	}

}
