package com.algaworks.algafood.domain.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;

/**
 * Classe de serviço responsável por
 * Alterar o status do pedido
 * 
 * @author Leonardo
 *
 */
@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EnvioEmailService envioEmail;
	
	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
		
		pedido.confirmar();
		
		pedidoRepository.save(pedido);
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Perido confirmado!")
				.corpo("pedido-confirmado.html")
				.variavel("pedido", pedido)
				.destinatarios(Set.of(pedido.getCliente().getEmail()))
				.build();
		
		envioEmail.enviar(mensagem);
	}
	
	@Transactional
	public void cancelar(String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
		
		pedido.cancelar();
		
		pedidoRepository.save(pedido);
	}
	
	@Transactional
	public void entregar(String codigoPedido) {
	    Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
	    pedido.entregar();
	}
}
