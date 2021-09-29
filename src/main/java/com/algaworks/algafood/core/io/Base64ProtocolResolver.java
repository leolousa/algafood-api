package com.algaworks.algafood.core.io;

import java.util.Base64;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * Classe para resolver um protocolo base64
 * paara que possamos passar o conteúdo
 * de uma chave privada em uma propriedade
 * 
 * @author Leonardo
 *
 */

public class Base64ProtocolResolver implements ProtocolResolver,
		ApplicationListener<ApplicationContextInitializedEvent> {

	@Override
	public Resource resolve(String location, ResourceLoader resourceLoader) {
		if (location.startsWith("base64:")) {
			byte[] decodedResource = Base64.getDecoder().decode(location.substring(7));
			return new ByteArrayResource(decodedResource);
		}
		
		return null;
	}

	//Método que implementa um listener
	//que será utilizado no início da aplicação
	//para utilizar o protocol revolver de Base64 criado aqui
	@Override
	public void onApplicationEvent(ApplicationContextInitializedEvent event) {
		event.getApplicationContext().addProtocolResolver(this);
	}

}
