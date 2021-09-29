package com.algaworks.algafood.domain.listener;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.algaworks.algafood.domain.event.PedidoCanceladoEvent;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;

/**
 * Classe do com método listener
 * para implementação do evento
 * de envio de e-mail ao cancelar pedido 
 * 
 * @author Leonardo
 *
 */
@Component
public class NotificacaoClientePedidoCanceladoListener {

	@Autowired
	private EnvioEmailService envioEmail;

	//Método que envia e-mail de cancelamento do pedido
	//A anotação executa os eventos após a transação for commitada
	@TransactionalEventListener
	public void aoCancelarPedido(PedidoCanceladoEvent event) {
		Pedido pedido = event.getPedido();
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Perido cancelado!")
				.corpo("emails/pedido-cancelado.html").variavel("pedido", pedido)
				.destinatarios(Set.of(pedido.getCliente().getEmail())).build();

		envioEmail.enviar(mensagem);
	}
}
