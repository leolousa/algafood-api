package com.algaworks.algafood.domain.exception;

/**
 * Classe de Exception customizada para a entidade Pedido
 * @author Leonardo
 *
 */
public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public PedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    
    public PedidoNaoEncontradoException(Long pedidoId) {
        this(String.format("Não existe um pedido com código %d", pedidoId));
    }   
} 
