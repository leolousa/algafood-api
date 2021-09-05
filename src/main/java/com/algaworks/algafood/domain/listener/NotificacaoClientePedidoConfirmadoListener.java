package com.algaworks.algafood.domain.listener;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;

/**
 * Classe do commétodo listener
 * para implementação do evento
 * de envio de e-mail 
 * 
 * @author Leonardo
 *
 */
@Component
public class NotificacaoClientePedidoConfirmadoListener {

	@Autowired
	private EnvioEmailService envioEmail;

	//Método que envia e-mail
	//A anotação executa os eventos após a transação for commitada, se der erro ele faz rollback
	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		Pedido pedido = event.getPedido();
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Perido confirmado!")
				.corpo("pedido-confirmado.html").variavel("pedido", pedido)
				.destinatarios(Set.of(pedido.getCliente().getEmail())).build();

		envioEmail.enviar(mensagem);
	}
}
