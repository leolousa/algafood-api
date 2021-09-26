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
		
		@PreAuthorize("(hasAuthority('CONSULTAR_PEDIDOS') or " 
				+ "@appSecurity.getUsuarioId() == #filtro.clienteId or"
				+ "@appSecurity.gerenciaRestaurante(#filtro.restauranteId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodePesquisar { }
		
		@PreAuthorize("isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCriar { }

		@PreAuthorize("hasAuthority('GERENCIAR_PEDIDOS') or "
				+ "@appSecurity.gerenciaRestauranteDoPedido(#codigoPedido)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarPedidos { }
	}
	
	public @interface FormasPagamento {

	    @PreAuthorize("hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeEditar { }

	    @PreAuthorize("isAuthenticated()")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeConsultar { }
	    
	}
	
	public @interface Cidades {

	    @PreAuthorize("hasAuthority('EDITAR_CIDADES')")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeEditar { }

	    @PreAuthorize("isAuthenticated()")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeConsultar { }
	    
	}

	public @interface Estados {
	    
	    @PreAuthorize("hasAuthority('EDITAR_ESTADOS')")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeEditar { }

	    @PreAuthorize("isAuthenticated()")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeConsultar { }
	    
	}
	
	public @interface UsuariosGruposPermissoes {

	    @PreAuthorize("@appSecurity.getUsuarioId() == #usuarioId")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeAlterarPropriaSenha { }
	    
	    @PreAuthorize("hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
	            + "@appSecurity.getUsuarioId() == #usuarioId")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeAlterarUsuario { }

	    @PreAuthorize("hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES')")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeEditar { }
	    

	    @PreAuthorize("hasAuthority('CONSULTAR_USUARIOS_GRUPOS_PERMISSOES')")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeConsultar { }
	    
	}
}
