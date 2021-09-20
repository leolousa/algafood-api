package com.algaworks.algafood.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

/**
 * Classe utilitáris para retornar
 * dados do usuário logado e o contexto da autenticação
 *  
 * @author Leonardo
 *
 */
@Component
public class AppSecurity {

	//Método que retorna o objeto Token que está autenticando a requisição atual
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public Long getUsuarioId() { // Pega o id do usuário de dentro do Token
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		
		return jwt.getClaim("usuario_id");
	}
}
