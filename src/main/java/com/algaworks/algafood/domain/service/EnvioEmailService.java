package com.algaworks.algafood.domain.service;

import java.util.Map;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

/**
 * Interface de domínio para envio de e-mail
 * 
 * @author Leonardo
 *
 */
public interface EnvioEmailService {

	void enviar(Mensagem mensagem);
	
	@Getter
	@Builder
	class Mensagem {
		
		//@Singular
		private Set<String> destinatarios;
		
		@NonNull
		private String assunto;
		
		@NonNull
		private String corpo;
		
		@Singular("variavel")
		private Map<String, Object> variaveis;
		
	}
}
