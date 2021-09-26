package com.algaworks.algafood.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Interface de anotação para definir permissões
 * de acesso aos métodos de todos os controllers da API
 * 
 * @author Leonardo
 *
 */
public @interface CheckSecurity {

	public @interface Cozinhas {
		
		@PreAuthorize("isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
		@PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }
	}
	
	public @interface Restaurantes {
		
		//Executa antes do método anotado
		@PreAuthorize("isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
		//Executa antes do método anotado
		@PreAuthorize("hasAuthority('EDITAR_RESTAURANTES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }
	}
	
	public @interface Pedidos {
		
		//Executa antes do método anotado
		@PreAuthorize("isAuthenticated()")
		//Só executa após a execução do método anotado (cuidado com efeitos colaterais usar em consultas)
		@PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or "
				+ "@appSecurity.getUsuarioId() == returnObject.cliente.id or "
				+ "@appSecurity.gerenciaRestaurante(returnObject.restaurante.id)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeBuscar { }
	}
}
