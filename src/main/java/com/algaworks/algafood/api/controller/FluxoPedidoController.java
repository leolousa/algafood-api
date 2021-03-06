package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.openapi.controller.FluxoPedidoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.service.FluxoPedidoService;

import io.swagger.annotations.ApiOperation;
/**
 * Classe controller que é responsável
 * pela alteração do fluxo do pedido
 * 
 * @author Leonardo
 *
 */
@RestController
@RequestMapping(path = "/pedidos/{codigoPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
public class FluxoPedidoController implements FluxoPedidoControllerOpenApi {

	@Autowired
	private FluxoPedidoService fluxoPedido;
	
	@CheckSecurity.Pedidos.PodeGerenciarPedidos
	@ApiOperation("Confirma um pedido")
	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable String codigoPedido) {
    	fluxoPedido.confirmar(codigoPedido);
    }
	
	@CheckSecurity.Pedidos.PodeGerenciarPedidos
	@ApiOperation("Cancela um pedido")
	@PutMapping("/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable String codigoPedido) {
	    fluxoPedido.cancelar(codigoPedido);
	}
	
	@CheckSecurity.Pedidos.PodeGerenciarPedidos
	@ApiOperation("Registra a entrega de um pedido")
	@PutMapping("/entrega")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregar(@PathVariable String codigoPedido) {
	    fluxoPedido.entregar(codigoPedido);
	}
    
}
