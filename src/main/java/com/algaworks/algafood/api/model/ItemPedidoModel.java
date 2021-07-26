package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe DTO de representação do recurso para ItemPedido
 * 
 * @author Leonardo
 *
 */
@Getter
@Setter
public class ItemPedidoModel {

    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;            
}
