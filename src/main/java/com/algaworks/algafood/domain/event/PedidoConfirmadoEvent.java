package com.algaworks.algafood.domain.event;

import com.algaworks.algafood.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * Classe que representa um evento
 * do pedido confirmado
 * 
 * @author Leonardo
 *
 */
@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {

	private Pedido pedido;
}
